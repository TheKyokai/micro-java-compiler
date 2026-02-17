package rs.ac.bg.etf.pp1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java_cup.runtime.Symbol;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.util.ExpandedDumpSymbolTableVisitor;
import rs.ac.bg.etf.pp1.util.Log4JUtils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.SymbolTableVisitor;

public class Compiler {

	static {
		DOMConfigurator.configure(Log4JUtils.instance().findLoggerConfigFile());
		Log4JUtils.instance().prepareLogFile(Logger.getRootLogger());
	}
	
	//TODO REFACTOR DUMP
	public static void tsdump() {
		SymbolTableVisitor stv = new ExpandedDumpSymbolTableVisitor();
		log.info("==================== Symbol Table Dump ====================");
		for (Scope s = Tab.currentScope; s != null; s = s.getOuter()) {
			s.accept(stv);
		}
		log.info("\n" + stv.getOutput());

	}
	
	private static Program programAst = null;
	private static Logger log = Logger.getLogger(Compiler.class);

	public static void main(String[] args) throws Exception {

		

		if (args.length < 1) {
			log.error("Source file not specified.");
			return;
		}
		
		
		if (args.length < 2) {
			log.error("Obj file not specified.");
			return;
		}

		Reader br = null;
		try {

			File sourceCode = new File(args[0]);
			if (!sourceCode.exists()) {
				log.error("Source file ( " + args[0] + " ) not found.");
				return;
			}

			log.info("Compiling source file: " + sourceCode.getAbsolutePath() + "\n");

			log.info("===================== Syntax Analysis ====================");

			br = new BufferedReader(new FileReader(sourceCode));
			Yylex lexer = new Yylex(br);
			MJParser p = new MJParser(lexer);

			Symbol s = p.parse();

			if (p.error_detected) {
				log.error("Syntax errors detected. Could not continue further compiling...");
				return;
			}
			log.info("Syntax Analysis Successful\n");
			programAst = (Program) (s.value);
			log.info("\n\n" + programAst.toString("") + "\n");
			
			
			log.info("==================== Semantic Analysis ====================");

			Tab.init();
			Struct boolType = new Struct(Struct.Bool);
			Tab.insert(Obj.Type, "bool", boolType);

			SemanticAnalyzer semAnalyzer = new SemanticAnalyzer(boolType);

			programAst.traverseBottomUp(semAnalyzer);

			if (semAnalyzer.errorDetected) {
				log.error("Semantic errors detected. Could not continue further compiling...");
				return;
			}

			log.info("Semantic Analysis Successful\n");
			tsdump();
			
			CodeGenerator codeGenerator = new CodeGenerator(semAnalyzer.mainMeth, semAnalyzer.nGlobalVars);
			programAst.traverseBottomUp(codeGenerator);
			
			
			Code.mainPc = codeGenerator.mainObj.getAdr();	
			Code.dataSize = codeGenerator.currentStaticAddr;
			
			File objFile = new File(args[1]);
        	log.info("Generating bytecode file: " + objFile.getAbsolutePath());
        	if (objFile.exists())
        		objFile.delete();
			
			Code.write(new FileOutputStream(objFile));
			log.info("Compiling successful. Bytecode generated.");
			

		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e1) {
					log.error(e1.getMessage(), e1);
				}
 		}

	}

}


