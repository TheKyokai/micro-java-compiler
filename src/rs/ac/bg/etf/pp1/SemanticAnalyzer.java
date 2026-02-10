package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.BoolConstVal;
import rs.ac.bg.etf.pp1.ast.CExtension;
import rs.ac.bg.etf.pp1.ast.CharConstVal;
import rs.ac.bg.etf.pp1.ast.ClassDecl;
import rs.ac.bg.etf.pp1.ast.ClassMethodDecl;
import rs.ac.bg.etf.pp1.ast.ClassMethodName;
import rs.ac.bg.etf.pp1.ast.ClassName;
import rs.ac.bg.etf.pp1.ast.ConstDecl;
import rs.ac.bg.etf.pp1.ast.EnumDecl;
import rs.ac.bg.etf.pp1.ast.EnumName;
import rs.ac.bg.etf.pp1.ast.EnumNumConstAssign;
import rs.ac.bg.etf.pp1.ast.ExtFieldIntercept;
import rs.ac.bg.etf.pp1.ast.ExtMethodIntercept;
import rs.ac.bg.etf.pp1.ast.IsArray;
import rs.ac.bg.etf.pp1.ast.NoCExtension;
import rs.ac.bg.etf.pp1.ast.NoClassMethods;
import rs.ac.bg.etf.pp1.ast.NumConstVal;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.ast.ProgramName;
import rs.ac.bg.etf.pp1.ast.SingleConstDecl;
import rs.ac.bg.etf.pp1.ast.SingleFormalParam;
import rs.ac.bg.etf.pp1.ast.SingleIdentEnumDecl;
import rs.ac.bg.etf.pp1.ast.SingleVarDecl;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.Type;
import rs.ac.bg.etf.pp1.ast.TypeReturn;
import rs.ac.bg.etf.pp1.ast.VarDecl;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.ac.bg.etf.pp1.ast.VoidReturn;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {

	private final Struct boolType;

	// Logging and error handling
	Logger log = Logger.getLogger(getClass());
	boolean errorDetected = false;

	public void report_error(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" on line ").append(line);
		log.error(msg.toString());
		errorDetected = true;
	}

	public void report_info(String message, SyntaxNode info) {
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0 : info.getLine();
		if (line != 0)
			msg.append(" on line ").append(line);
		log.info(msg.toString());
	}

	// Util

	private boolean isSuperClass(Struct superClass, Struct type) {
		while (type != Tab.noType && type != superClass)
			type = type.getElemType();
		return type == superClass;
	}

	private Struct currentType; // Type currently used for declaration of variables or method returns
	private int nextConstVal; // Next value to be assigned to a constant

	private Struct currentClassType; // Class currently being declared
	private boolean returnTyped; // Return type flag
	private boolean extendsClass; // Class extension flag
	private Obj overridingMeth; // Method currently being overridden
	private Obj currentMeth; // Method currently being declared
	private List<Obj> inheritedMethods = new ArrayList<Obj>(); // Not implemented/overriden methods inherited from the
																// superclass
	private int currentFpPos; // Current formal parameter position

	public SemanticAnalyzer(Struct boolType) {
		this.boolType = boolType;
	}

	@Override
	public void visit(Program program) {
		Tab.chainLocalSymbols(program.getProgramName().obj);
		Tab.closeScope();
	}

	@Override
	public void visit(ProgramName programName) {
		programName.obj = Tab.insert(Obj.Prog, programName.getProgramName(), Tab.noType);
		Tab.openScope();
	}

	@Override
	public void visit(Type type) {
		Obj typeNode = Tab.find(type.getTypeName());
		if (typeNode == Tab.noObj) {
			report_error("Type '" + type.getTypeName() + "' used before declaration", type);
			currentType = Tab.noType;
			return;
		}
		if (typeNode.getKind() != Obj.Type) {
			report_error("Name '" + type.getTypeName() + "' does not represent a type", type);
			currentType = Tab.noType;
			return;
		}
		currentType = typeNode.getType();
		type.obj = typeNode;
	}

	@Override
	public void visit(VoidReturn voidReturn) {
		currentType = Tab.noType;
		returnTyped = false;
	}

	@Override
	public void visit(TypeReturn typeReturn) {
		returnTyped = true;
	}

	// VarDecl
	@Override
	public void visit(VarDecl varDecl) {
		currentType = Tab.noType;
	}

	@Override
	public void visit(SingleVarDecl singleVarDecl) {
		String name = singleVarDecl.getVarName();
		if (currentType == Tab.noType) {
			return;
		}
		if (Tab.currentScope.findSymbol(name) != null) {
			report_error("Redeclaration of already used name '" + name + "'", singleVarDecl);
			return;
		}

		if (extendsClass && currentClassType.getElemType() == Tab.noType)
			return;
		Struct varType = (singleVarDecl.getArrayDecl() instanceof IsArray) ? new Struct(Struct.Array, currentType)
				: currentType;
		Tab.insert(Obj.Var, name, varType);
	}

	// ConstDecl
	@Override
	public void visit(ConstDecl ConstDecl) {
		currentType = Tab.noType;
	}

	@Override
	public void visit(SingleConstDecl singleConstDecl) {
		String name = singleConstDecl.getConstName();
		if (currentType == Tab.noType) {
			return;
		}
		if (Tab.currentScope.findSymbol(name) != null) {
			report_error("Redeclaration of already used name '" + name + "'", singleConstDecl);
			return;
		}
		if (singleConstDecl.getConstVal().struct != currentType) {
			report_error("Types do not match for the definition of constant '" + name + "'", singleConstDecl);
			return;
		}

		Tab.insert(Obj.Con, name, currentType);
		Tab.find(name).setAdr(nextConstVal);
	}

	@Override
	public void visit(BoolConstVal boolConstVal) {
		boolConstVal.struct = boolType;
		nextConstVal = boolConstVal.getVal() ? 1 : 0;
	}

	@Override
	public void visit(CharConstVal charConstVal) {
		charConstVal.struct = Tab.charType;
		nextConstVal = charConstVal.getVal();
	}

	@Override
	public void visit(NumConstVal numConstVal) {
		numConstVal.struct = Tab.intType;
		nextConstVal = numConstVal.getVal();
	}

	// EnumDecl
	@Override
	public void visit(EnumName enumName) {
		String name = enumName.getName();
		if (Tab.currentScope.findSymbol(name) != null) {
			report_error("Redeclaration of already used name '" + name + "'", enumName);
			currentClassType = Tab.noType;
			return;
		}
		currentClassType = new Struct(Struct.Enum, Tab.intType);
		Tab.insert(Obj.Type, name, currentClassType);
		Tab.openScope();
		nextConstVal = 0;
	}

	@Override
	public void visit(EnumDecl enumDecl) {
		Tab.chainLocalSymbols(currentClassType);
		Tab.closeScope();
		currentClassType = Tab.noType;
	}

	@Override
	public void visit(EnumNumConstAssign enumNumConstAssign) {
		nextConstVal = enumNumConstAssign.getVal();
	}

	@Override
	public void visit(SingleIdentEnumDecl singleIdentEnumDecl) {
		String name = singleIdentEnumDecl.getInnerEnumName();
		if (currentClassType == Tab.noType) {
			return;
		}
		if (Tab.currentScope.findSymbol(name) != null) {
			report_error("Redeclaration of already used name '" + name + "'", singleIdentEnumDecl);
			return;
		}
		if (Tab.currentScope.getLocals() != null
				&& Tab.currentScope.getLocals().symbols().stream().anyMatch(sym -> sym.getAdr() == nextConstVal)) {
			report_error("Reuse of the same value for enum constant '" + name + "'", singleIdentEnumDecl);
			return;
		}

		Obj enumConst = Tab.insert(Obj.Con, name, Tab.intType);
		enumConst.setAdr(nextConstVal++);
	}

	Obj currentClassObj; // Obj of the class currently being declared -> used for report_error 
	// ClassDecl

	@Override
	public void visit(ClassName className) {
		String name = className.getClassName();
		if (Tab.currentScope.findSymbol(name) != null) {
			report_error("Redeclaration of already used name '" + name + "'", className);
			currentClassType = Tab.noType;
			return;
		}
		currentClassType = new Struct(Struct.Class);
		currentClassObj = Tab.insert(Obj.Type, name, currentClassType);
		currentClassObj.setFpPos(0);
		Tab.openScope();
	}

	@Override
	public void visit(CExtension cExtension) {
		if (currentClassType == currentType)
			currentType = Tab.noType;
		if (currentType.getKind() != Struct.Class) {
			String specification = cExtension.getType().obj != null
					? " of type '" + cExtension.getType().obj.getName() + "'"
					: "";
			report_error("Illegal class extension" + specification, cExtension);
			currentType = Tab.noType;
		}
		currentClassType.setElementType(currentType);
		extendsClass = true;
	}

	@Override
	public void visit(NoCExtension noCExtension) {
		currentType = Tab.noType;
		currentClassType.setElementType(currentType);
		extendsClass = false;
	}

	@Override
	public void visit(ClassDecl classDecl) {
		Tab.chainLocalSymbols(currentClassType);
		Tab.closeScope();
		currentClassType = Tab.noType;
		extendsClass = false;
		if (inheritedMethods.stream().filter(meth -> meth.getFpPos() == 1).findAny().isPresent()) {
			report_error("Not all abstract methods from the superclass were defined for the class '" + currentClassObj.getName() + "'", classDecl);
			inheritedMethods.forEach(meth -> report_error("    '" + meth.getName() + "' not defined", null));
		}
		inheritedMethods.clear();
	}

	@Override
	public void visit(ExtFieldIntercept extFieldIntercept) {
		if (currentClassType.getElemType() == Tab.noType)
			return;
		currentType.getMembers().stream().filter(sym -> sym.getKind() == Obj.Var)
				.forEach(field -> Tab.insert(Obj.Var, field.getName(), field.getType()));
	}

	@Override
	public void visit(ExtMethodIntercept extMethodIntercept) {
		if (currentClassType.getElemType() == Tab.noType)
			return;
		inheritedMethods.clear();
		currentClassType.getElemType().getMembers().stream().filter(sym -> sym.getKind() == Obj.Meth).forEach(meth -> {
			Obj overMeth = Tab.insert(Obj.Meth, meth.getName(), meth.getType());
			overMeth.setLevel(meth.getLevel());
			Tab.openScope();
			meth.getLocalSymbols().stream().forEach(param -> {
				Obj newParam;
				if (param.getName().equals("this"))
					newParam = Tab.insert(Obj.Var, param.getName(), currentClassType);
				else
					newParam = Tab.insert(Obj.Var, param.getName(), param.getType());
				newParam.setFpPos(param.getFpPos());
				newParam.setLevel(param.getLevel());
			});
			inheritedMethods.add(overMeth);
			Tab.chainLocalSymbols(overMeth);
			Tab.closeScope();
		});
	}

	@Override
	public void visit(NoClassMethods noClassMethods) {
		if (currentClassType.getElemType() == Tab.noType)
			return;
		inheritedMethods.clear();
		currentClassType.getElemType().getMembers().stream().filter(sym -> sym.getKind() == Obj.Meth).forEach(meth -> {
			Obj overMeth = Tab.insert(Obj.Meth, meth.getName(), meth.getType());
			overMeth.setLevel(meth.getLevel());
			Tab.openScope();
			meth.getLocalSymbols().stream().forEach(param -> {
				Obj newParam;
				if (param.getName().equals("this"))
					newParam = Tab.insert(Obj.Var, param.getName(), currentClassType);
				else
					newParam = Tab.insert(Obj.Var, param.getName(), param.getType());
				newParam.setFpPos(param.getFpPos());
				newParam.setLevel(param.getLevel());
			});
			inheritedMethods.add(overMeth);
			Tab.chainLocalSymbols(overMeth);
			Tab.closeScope();
		});
	}

	@Override
	public void visit(ClassMethodName classMethodName) {
		if (returnTyped && currentType == Tab.noType)
			return;
		currentFpPos = 0;
		Obj overMeth = Tab.currentScope.findSymbol(classMethodName.getName());
		if (overMeth == null) {
			overridingMeth = Tab.noObj;
			currentMeth = Tab.insert(Obj.Meth, classMethodName.getName(), currentType);
			Tab.openScope();
			Obj thisObj = Tab.insert(Obj.Var, "this", currentClassType);
			thisObj.setLevel(2);
			thisObj.setFpPos(currentFpPos++);
			return;
		}

		// Redeclaration inside of the same class
		if (!inheritedMethods.contains(overMeth)) {
			report_error("Redeclaration of method '" + classMethodName.getName() + "'", classMethodName);
			currentMeth = Tab.noObj;
			return;
		}

		// TODO Test Enum return
		// Return type check
		if (!isSuperClass(overMeth.getType(), currentType)) {
			report_error("Illegal redefinition of return type for method '" + classMethodName.getName() + "'",
					classMethodName);
			currentMeth = Tab.noObj;
			return;
		}

		// Method to be overridden -> removing from the inherited not overridden methods
		inheritedMethods.remove(overMeth);


		if (overMeth.getType() != currentType) {
			// Adjusting the Obj of the method
			overridingMeth = overMeth;
			Scope oldScope = Tab.currentScope;
			Tab.closeScope();
			Tab.openScope();
			
			List<Obj> leftoverMethods = new ArrayList<>();
			oldScope.getLocals().symbols().forEach(sym -> {
				if (sym.getKind() == Obj.Var) {
					Tab.insert(sym.getKind(), sym.getName(), sym.getType()).setLevel(sym.getLevel());
					return;
				}
				Obj meth; // New method Obj created for the copy
				if (sym != overMeth) {
					meth = Tab.insert(sym.getKind(), sym.getName(), sym.getType());
					meth.setLevel(sym.getLevel());
					Tab.openScope();
					sym.getLocalSymbols().stream().forEach(param -> {
						Obj newParam = Tab.insert(Obj.Var, param.getName(), param.getType());
						newParam.setFpPos(param.getFpPos());
						newParam.setLevel(param.getLevel());
					});
					Tab.chainLocalSymbols(meth);
					Tab.closeScope();
				}
				else {
					meth = Tab.insert(sym.getKind(), sym.getName(), currentType);
					currentMeth = meth;
				}
				
				if (inheritedMethods.contains(sym))
					leftoverMethods.add(sym);
			});
			inheritedMethods = leftoverMethods;
		} else {
			overridingMeth = new Obj(Obj.Meth, overMeth.getName(), overMeth.getType(), overMeth.getAdr(), overMeth.getLevel());
			Tab.openScope();
			overMeth.getLocalSymbols().stream().forEach(param -> {
				Obj newParam = Tab.insert(Obj.Var, param.getName(), param.getType());
				newParam.setFpPos(param.getFpPos());
				newParam.setLevel(param.getLevel());
			});
			Tab.chainLocalSymbols(overridingMeth);
			overridingMeth.setFpPos(overMeth.getFpPos());
			Tab.closeScope();
			currentMeth = overMeth;
		}
		Tab.openScope();
		Obj thisObj = Tab.insert(Obj.Var, "this", currentClassType);
		thisObj.setLevel(2);
		thisObj.setFpPos(currentFpPos++);
	}

	@Override
	public void visit(ClassMethodDecl classMethodDecl) {
		if (currentMeth == Tab.noObj)
			return;
		if (overridingMeth != Tab.noObj && currentFpPos < overridingMeth.getLevel()) {
			report_error("Not all parameters were provided for overriding of method '" + currentMeth.getName() + "'", classMethodDecl);
		}
		currentMeth.setLevel(currentFpPos);
		Tab.chainLocalSymbols(currentMeth);
		Tab.closeScope();
		currentMeth = Tab.noObj;
		overridingMeth = Tab.noObj;
	}

	@Override
	public void visit(SingleFormalParam singleFormalParam) {
		if (currentMeth == Tab.noObj || currentType == Tab.noType)
			return;
		if (overridingMeth != Tab.noObj) {
			if (overridingMeth.getLevel() <= currentFpPos){
				report_error("Too many parameters provided for method '" + currentMeth.getName() + "'", singleFormalParam);
				return;
			}
			Struct superClassParamType = overridingMeth.getLocalSymbols().stream().filter(param -> param.getFpPos() == currentFpPos).findFirst().get().getType();
			if (!isSuperClass(currentType, superClassParamType))
				report_error("Illegal change of formal parameter type for parameter '" + singleFormalParam.getParamName() + "'", singleFormalParam);
		}
		Obj formalParamObj = Tab.insert(Obj.Var, singleFormalParam.getParamName(), currentType);
		formalParamObj.setFpPos(currentFpPos++);
		formalParamObj.setLevel(2);
	}


}
