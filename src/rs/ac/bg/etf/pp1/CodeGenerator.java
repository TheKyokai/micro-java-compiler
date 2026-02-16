package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

import rs.ac.bg.etf.pp1.ast.AbsClassName;
import rs.ac.bg.etf.pp1.ast.ActualParam;
import rs.ac.bg.etf.pp1.ast.ClassMethodDecl;
import rs.ac.bg.etf.pp1.ast.ClassMethodName;
import rs.ac.bg.etf.pp1.ast.ClassName;
import rs.ac.bg.etf.pp1.ast.Designator;
import rs.ac.bg.etf.pp1.ast.DesignatorAssignment;
import rs.ac.bg.etf.pp1.ast.DesignatorChain;
import rs.ac.bg.etf.pp1.ast.DesignatorChainFieldAccess;
import rs.ac.bg.etf.pp1.ast.DesignatorChainIdent;
import rs.ac.bg.etf.pp1.ast.DesignatorChainIndexAccess;
import rs.ac.bg.etf.pp1.ast.DesignatorDec;
import rs.ac.bg.etf.pp1.ast.DesignatorDst;
import rs.ac.bg.etf.pp1.ast.DesignatorFCall;
import rs.ac.bg.etf.pp1.ast.DesignatorInc;
import rs.ac.bg.etf.pp1.ast.Div;
import rs.ac.bg.etf.pp1.ast.Equal;
import rs.ac.bg.etf.pp1.ast.FCallInterrupt;
import rs.ac.bg.etf.pp1.ast.FactorArrayInst;
import rs.ac.bg.etf.pp1.ast.FactorBoolConst;
import rs.ac.bg.etf.pp1.ast.FactorCharConst;
import rs.ac.bg.etf.pp1.ast.FactorFCall;
import rs.ac.bg.etf.pp1.ast.FactorNumConst;
import rs.ac.bg.etf.pp1.ast.FactorTypeInst;
import rs.ac.bg.etf.pp1.ast.Factors;
import rs.ac.bg.etf.pp1.ast.GreaterEqual;
import rs.ac.bg.etf.pp1.ast.GreaterThan;
import rs.ac.bg.etf.pp1.ast.LesserThan;
import rs.ac.bg.etf.pp1.ast.LesserThanEqual;
import rs.ac.bg.etf.pp1.ast.MethodDecl;
import rs.ac.bg.etf.pp1.ast.MethodName;
import rs.ac.bg.etf.pp1.ast.Minus;
import rs.ac.bg.etf.pp1.ast.Mod;
import rs.ac.bg.etf.pp1.ast.Mul;
import rs.ac.bg.etf.pp1.ast.NotEqual;
import rs.ac.bg.etf.pp1.ast.NumConstPrintStatement;
import rs.ac.bg.etf.pp1.ast.Plus;
import rs.ac.bg.etf.pp1.ast.PrintStatement;
import rs.ac.bg.etf.pp1.ast.ReadStatement;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.Terms;
import rs.ac.bg.etf.pp1.ast.TypedReturnStatement;
import rs.ac.bg.etf.pp1.ast.UMinusFirstTermInList;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.ac.bg.etf.pp1.ast.VoidReturnStatement;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {

	Obj mainObj;
	private Obj program;
	private int nGlobalVars;
	int currentStaticAddr = -1;
	private Map<Obj, List<Integer>> vftPointerFixupMap = new HashMap<Obj, List<Integer>>();
	private Stack<Obj> invokedMethStack = new Stack<Obj>();
	private Obj currentInvokedMeth = Tab.noObj;

	public CodeGenerator(Obj mainMethObj, int nGlobalVars) {
		this.mainObj = mainMethObj;
		this.nGlobalVars = nGlobalVars;

		program = Tab.currentScope.getLocals().symbols().stream().filter(sym -> sym.getKind() == Obj.Prog).findFirst()
				.get();

		// Inserting built in methods

		Tab.chrObj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(Tab.chrObj.getLevel());
		Code.put(Tab.chrObj.getLocalSymbols().size());
		Code.put(Code.load_n);
		Code.put(Code.exit);
		Code.put(Code.return_);

		Tab.ordObj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(Tab.ordObj.getLevel());
		Code.put(Tab.ordObj.getLocalSymbols().size());
		Code.put(Code.load_n);
		Code.put(Code.exit);
		Code.put(Code.return_);

		Tab.lenObj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(Tab.lenObj.getLevel());
		Code.put(Tab.lenObj.getLocalSymbols().size());
		Code.put(Code.load_n);
		Code.put(Code.arraylength);
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	private void initVFTables() {
		currentStaticAddr = nGlobalVars;
		// Getting all classes
		for (Obj classObj : program.getLocalSymbols().stream()
				.filter(sym -> sym.getKind() == Obj.Type && sym.getType().getKind() == Struct.Class)
				.collect(Collectors.toList())) {
			System.out.println("Checking class " + classObj.getName());
			classObj.setAdr(currentStaticAddr);
			// Getting all class methods
			for (Obj meth : classObj.getType().getMembers().stream().filter(sym -> sym.getKind() == Obj.Meth)
					.collect(Collectors.toList())) {
				for (char c : meth.getName().toCharArray()) {
					Code.loadConst(c);
					Code.put(Code.putstatic);
					Code.put2(currentStaticAddr++);
				}
				// MethodName terminator
				Code.loadConst(-1);
				Code.put(Code.putstatic);
				Code.put2(currentStaticAddr++);
				// MethodAddress
				Code.loadConst(meth.getAdr());
				Code.put(Code.putstatic);
				Code.put2(currentStaticAddr++);
			}
			Code.loadConst(-2);
			Code.put(Code.putstatic);
			Code.put2(currentStaticAddr++);
			int oldPc = Code.pc;
			for (int fixupPc : vftPointerFixupMap.get(classObj)) {
				Code.pc = fixupPc;
				Code.put4(classObj.getAdr()); // Always using 4 bytes for const load
			}
			Code.pc = oldPc;
		}
	}

	@Override
	public void visit(MethodName methodName) {
		methodName.obj.setAdr(Code.pc);
		if (methodName.obj == mainObj)
			initVFTables();
		// ENTER
		Code.put(Code.enter);
		Code.put(methodName.obj.getLevel()); // Formal parameter count
		Code.put(methodName.obj.getLocalSymbols().size()); // Formal parameter count + Local variable count
	}

	@Override
	public void visit(MethodDecl methodDecl) {
		if (methodDecl.obj.getType() == Tab.noType) {
			// Void Method
			Code.put(Code.exit);
			Code.put(Code.return_);
		} else {
			// Trap for non-void methods
			Code.put(Code.trap);
			Code.put(1);
		}
	}
	
	
	@Override
	public void visit(ClassMethodName classMethodName) {
		System.out.println("Class meth " + classMethodName.obj.getName());
		classMethodName.obj.setAdr(Code.pc);
		// ENTER
		Code.put(Code.enter);
		Code.put(classMethodName.obj.getLevel()); // Formal parameter count
		Code.put(classMethodName.obj.getLocalSymbols().size()); // Formal parameter count + Local variable count
	}
	
	@Override
	public void visit(ClassMethodDecl classMethodDecl) {
		if (classMethodDecl.obj.getType() == Tab.noType) {
			// Void Method
			Code.put(Code.exit);
			Code.put(Code.return_);
		} else {
			// Trap for non-void methods
			Code.put(Code.trap);
			Code.put(1);
		}
	}

	@Override
	public void visit(TypedReturnStatement typedReturnStatement) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	@Override
	public void visit(VoidReturnStatement voidReturnStatement) {
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	
	@Override
	public void visit(FCallInterrupt fCallInterrupt) {
		invokedMethStack.push(currentInvokedMeth);
		SyntaxNode parent = fCallInterrupt.getParent();
		if (parent instanceof DesignatorFCall) 
			currentInvokedMeth = ((DesignatorFCall)parent).getDesignator().obj;
		else if (parent instanceof FactorFCall) 
			currentInvokedMeth = ((FactorFCall)parent).getDesignator().obj;
		if (currentInvokedMeth.getFpPos() != 0)
			// Creating a duplicate for VFTP retrieval
			Code.put(Code.dup);
	}
	
	@Override
	public void visit(ActualParam actualParam) {
		// Pushing the duplicate to the top
		if (currentInvokedMeth.getFpPos() != 0) {
			Code.put(Code.dup_x1);
			Code.put(Code.pop);
		}
	}
	
	
	@Override
	public void visit(DesignatorFCall designatorFCall) {
		currentInvokedMeth = invokedMethStack.pop();
		Obj methObj = designatorFCall.getDesignator().obj; 
		if (methObj.getFpPos() == 0) {
			// Global function
			Code.put(Code.call);
			Code.put2(designatorFCall.getDesignator().obj.getAdr() - Code.pc + 1); // Accounting for call byte
			return;
		}
		// Class method
		Code.put(Code.getfield);
		Code.put2(0);
		Code.put(Code.invokevirtual);
		for (char c:methObj.getName().toCharArray())
			Code.put4(c);
		Code.put4(-1);
	}

	@Override
	public void visit(FactorFCall factorFCall) {
		currentInvokedMeth = invokedMethStack.pop();
		Code.put(Code.call);
		Code.put2(factorFCall.getDesignator().obj.getAdr() - Code.pc + 1); // Accounting for call byte
	}

	@Override
	public void visit(FactorNumConst factorNumConst) {
		Code.loadConst(factorNumConst.obj.getAdr());
	}

	@Override
	public void visit(FactorCharConst factorCharConst) {
		Code.loadConst(factorCharConst.obj.getAdr());
	}

	@Override
	public void visit(FactorBoolConst factorBoolConst) {
		Code.loadConst(factorBoolConst.obj.getAdr());
	}

	@Override
	public void visit(PrintStatement printStatement) {
		Code.loadConst(0);
		if (printStatement.getExpr().obj.getType() == Tab.charType)
			Code.put(Code.bprint);
		else
			Code.put(Code.print);
	}

	@Override
	public void visit(NumConstPrintStatement numConstPrintStatement) {
		Code.loadConst(numConstPrintStatement.getVal());
		if (numConstPrintStatement.getExpr().obj.getType() == Tab.charType)
			Code.put(Code.bprint);
		else
			Code.put(Code.print);
	}

	// Prerano ucitava element sa adrese za nizove
	@Override
	public void visit(DesignatorChainIdent designatorChainIdent) {
		if (designatorChainIdent.obj.getKind() == Obj.Fld)
			Code.put(Code.load_n);
	}

	@Override
	public void visit(DesignatorChainFieldAccess designatorChainFieldAccess) {
		Obj prevObj = designatorChainFieldAccess.getDesignatorChain().obj;
		if (prevObj.getKind() == Obj.Type && prevObj.getType().getKind() == Struct.Enum)
			// Will load const later
			return;

		if (prevObj.getType().getKind() == Struct.Array) {
			Code.load(prevObj); // Loading array on stack for length later
			return;
		}
		
		if (prevObj.getType().getKind() == Struct.Class) {
			// In case of chaining class fields load now
			Code.load(prevObj);
			
		}

	}

	@Override
	public void visit(DesignatorChainIndexAccess designatorChainIndexAccess) {
		// Must be and array var or fld
		Obj prevObj = designatorChainIndexAccess.getDesignatorChain().obj; 
		if (prevObj.getKind() == Obj.Fld) {
			Code.put(Code.dup_x1); // adr expr -> expr adr expr
			Code.put(Code.pop);  // expr adr expr -> expr adr
		}
		Code.load(designatorChainIndexAccess.getDesignatorChain().obj);
		Code.put(Code.dup_x1); // Expression already on exprstack -> array addr must be below
		Code.put(Code.pop); // Removing the extra array addr
	}

	@Override
	public void visit(Designator designator) {
		// Delayed load by one step for assignment
		Obj finalObj = designator.obj;

		if (designator.getDesignatorChain() instanceof DesignatorChainIdent) {
			if (finalObj.getKind() != Obj.Meth)
				Code.load(finalObj);
			return;
		}

		if (designator.getDesignatorChain() instanceof DesignatorChainFieldAccess) {
			if (finalObj.getKind() == Obj.Type) { // .length flag
				Code.put(Code.arraylength);
				return;
			}

			if (finalObj.getKind() != Obj.Meth)
				Code.load(finalObj);

			return;
		}
		// Must be Obj.Elem
		if (finalObj.getType().getKind() == Struct.Char)
			// Char array
			Code.put(Code.baload);
		else
			Code.put(Code.aload);
	}
	
	@Override
	public void visit(DesignatorDst designatorDst) {
		
	}

	@Override
	public void visit(FactorArrayInst factorArrayInst) {
		Code.put(Code.newarray);
		if (factorArrayInst.getType().obj.getType().getKind() == Struct.Char)
			// Char array
			Code.put(0);
		else
			Code.put(1);
	}

	@Override
	public void visit(DesignatorAssignment designatorAssignment) {
		Code.store(designatorAssignment.getDesignatorDst().obj);
	}

	// TODO Check for array elem loading
	@Override
	public void visit(DesignatorInc designatorInc) {
		Obj prevObj = designatorInc.getDesignatorDst().obj;

		// Loading
		DesignatorChain chain = designatorInc.getDesignatorDst().getDesignatorChain();
		if (chain instanceof DesignatorChainIdent) {
			// Must be int variable
			Code.load(prevObj);
		} else if (chain instanceof DesignatorChainFieldAccess) {
			// Must be int field -> class var already on exprstack
			Code.put(Code.dup);
			Code.load(prevObj);
		} else {
			// Must be int elem -> array addr and index already on exprstack
			Code.put(Code.dup2);
			Code.put(Code.aload);
		}
		// Addition
		Code.loadConst(1);
		Code.put(Code.add);

		// Storing
		if (chain instanceof DesignatorChainIdent)
			// Must be int variable
			Code.store(prevObj);
		else if (chain instanceof DesignatorChainFieldAccess)
			// Must be int field -> class var already on exprstack
			Code.store(prevObj);
		else
			// Must be int elem -> array addr and index already on exprstack
			Code.put(Code.astore);
	}

	@Override
	public void visit(DesignatorDec designatorDec) {
		Obj prevObj = designatorDec.getDesignatorDst().obj;

		// Loading
		DesignatorChain chain = designatorDec.getDesignatorDst().getDesignatorChain();
		if (chain instanceof DesignatorChainIdent) {
			// Must be int variable
			Code.load(prevObj);
		} else if (chain instanceof DesignatorChainFieldAccess) {
			// Must be int field -> class var already on exprstack
			Code.put(Code.dup);
			Code.load(prevObj);
		} else {
			// Must be int elem -> array addr and index already on exprstack
			Code.put(Code.dup2);
			Code.put(Code.aload);
		}
		// Subtraction
		Code.loadConst(1);
		Code.put(Code.sub);

		// Storing
		if (chain instanceof DesignatorChainIdent)
			// Must be int variable
			Code.store(prevObj);
		else if (chain instanceof DesignatorChainFieldAccess)
			// Must be int field -> class var already on exprstack
			Code.store(prevObj);
		else
			// Must be int elem -> array addr and index already on exprstack
			Code.put(Code.astore);
	}

	private enum ADDOP {
		NONE(""), ADD("addition"), SUB("subtraction");

		private final String ident;

		ADDOP(String id) {
			ident = id;
		}

		String getIdent() {
			return ident;
		}
	}

	private enum MULOP {
		NONE(""), MUL("multiplication"), DIV("division"), MOD("mod");

		private final String ident;

		MULOP(String id) {
			ident = id;
		}

		String getIdent() {
			return ident;
		}
	}

	private enum RELOP {
		NONE, EQUAL, NOTEQUAL, GREATER, GREATEREQ, LESSER, LESSEREQ
	}

	private ADDOP currentAddOp = ADDOP.NONE;
	private MULOP currentMulOp = MULOP.NONE;
	private RELOP currentRelOp = RELOP.NONE;

	@Override
	public void visit(Plus Plus) {
		currentAddOp = ADDOP.ADD;
	}

	@Override
	public void visit(Minus Minus) {
		currentAddOp = ADDOP.SUB;
	}

	@Override
	public void visit(Div Div) {
		currentMulOp = MULOP.DIV;
	}

	@Override
	public void visit(Mul Mul) {
		currentMulOp = MULOP.MUL;
	}

	@Override
	public void visit(Mod Mod) {
		currentMulOp = MULOP.MOD;
	}

	@Override
	public void visit(Equal equal) {
		currentRelOp = RELOP.EQUAL;
	}

	@Override
	public void visit(NotEqual notEqual) {
		currentRelOp = RELOP.NOTEQUAL;
	}

	@Override
	public void visit(GreaterThan greaterThan) {
		currentRelOp = RELOP.GREATER;
	}

	@Override
	public void visit(GreaterEqual greaterEqual) {
		currentRelOp = RELOP.GREATEREQ;
	}

	@Override
	public void visit(LesserThan lesserThan) {
		currentRelOp = RELOP.LESSER;
	}

	@Override
	public void visit(LesserThanEqual lesserThanEqual) {
		currentRelOp = RELOP.LESSEREQ;
	}

	@Override
	public void visit(Factors factors) {
		switch (currentMulOp) {
		case MUL:
			Code.put(Code.mul);
			break;
		case MOD:
			Code.put(Code.rem);
			break;
		case DIV:
			Code.put(Code.div);
			break;
		default:
			// Never happening
		}
	}

	@Override
	public void visit(Terms terms) {
		switch (currentAddOp) {
		case ADD:
			Code.put(Code.add);
			break;
		case SUB:
			Code.put(Code.sub);
			break;
		default:
			// Never happening
		}
	}

	@Override
	public void visit(UMinusFirstTermInList uMinusFirstTermInList) {
		Code.put(Code.neg);
	}

	@Override
	public void visit(ReadStatement readStatement) {
		if (readStatement.getDesignatorDst().obj.getType().getKind() == Struct.Char)
			Code.put(Code.bread);
		else
			Code.put(Code.read);
		Code.store(readStatement.getDesignatorDst().obj);
	}

	// Classes

	@Override
	public void visit(ClassName className) {
		vftPointerFixupMap.put(className.obj, new ArrayList<Integer>());
	}

	// Probably useless since abs class cannot be instantiated
	@Override
	public void visit(AbsClassName absClassName) {
		vftPointerFixupMap.put(absClassName.obj, new ArrayList<Integer>());
	}
	
	@Override
	public void visit(FactorTypeInst factorTypeInst) {
		Code.put(Code.new_);
		Code.put2((factorTypeInst.obj.getType().getNumberOfFields() + 1) * 4); // If s represents words, x4 for bytes -> Will test
		// Will exist so no check needed
		Obj classObj = program.getLocalSymbols().stream().filter(sym -> sym.getKind() == Obj.Type && sym.getType() == factorTypeInst.obj.getType()).findFirst().get();
		Code.put(Code.dup);
		Code.put(Code.const_);
		if (currentStaticAddr == -1) 
			vftPointerFixupMap.get(classObj).add(Code.pc);
		Code.put4(classObj.getAdr());
		Code.put(Code.putfield);
		Code.put2(0);
	}
	
	
}
