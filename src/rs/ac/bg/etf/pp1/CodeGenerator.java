package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Collectors;

import rs.ac.bg.etf.pp1.ast.*;
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

		// Inserting built-in methods

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
			classObj.setAdr(currentStaticAddr);
			// Getting all class methods
			for (Obj meth : classObj.getType().getMembers().stream().filter(sym -> sym.getKind() == Obj.Meth)
					.collect(Collectors.toList())) {

				// Address can never be zero for non-abstract method -> method not overridden ->
				// bind to superclass method
				if (meth.getAdr() == 0 && meth.getFpPos() == -1) {
					Struct superClass = classObj.getType().getElemType();
					if (superClass == Tab.noType || superClass == null) {
						System.out.println("Unexpected inconsistant state");
						break;
					}
					Obj superMeth = superClass.getMembersTable().searchKey(meth.getName());
					if (superMeth.getKind() != Obj.Meth)
						// Unexpected
						System.out.println("Unexpected inconsistant state");
					while (superMeth.getAdr() == 0) {
						superClass = superClass.getElemType();
						superMeth = superClass.getMembersTable().searchKey(meth.getName());
						if (superMeth.getKind() != Obj.Meth)
							// Unexpected
							System.out.println("Unexpected inconsistant state");
					}
					meth.setAdr(superMeth.getAdr());

				}

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
			currentInvokedMeth = ((DesignatorFCall) parent).getDesignator().obj;
		else if (parent instanceof FactorFCall)
			currentInvokedMeth = ((FactorFCall) parent).getDesignator().obj;
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
		for (char c : methObj.getName().toCharArray())
			Code.put4(c);
		Code.put4(-1);
	}

	@Override
	public void visit(FactorFCall factorFCall) {
		currentInvokedMeth = invokedMethStack.pop();
		Obj methObj = factorFCall.getDesignator().obj;
		if (methObj.getFpPos() == 0) {
			// Global function
			Code.put(Code.call);
			Code.put2(factorFCall.getDesignator().obj.getAdr() - Code.pc + 1); // Accounting for call byte
			return;
		}
		// Class method
		Code.put(Code.getfield);
		Code.put2(0);
		Code.put(Code.invokevirtual);
		for (char c : methObj.getName().toCharArray())
			Code.put4(c);
		Code.put4(-1);

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

	@Override
	public void visit(DesignatorChainIdent designatorChainIdent) {
		if (designatorChainIdent.obj.getKind() == Obj.Fld
				|| (designatorChainIdent.obj.getKind() == Obj.Meth && designatorChainIdent.obj.getFpPos() != 0))
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
			Code.put(Code.pop); // expr adr expr -> expr adr
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

	@Override
	public void visit(DesignatorInc designatorInc) {
		Obj prevObj = designatorInc.getDesignatorDst().obj;

		// Loading
		DesignatorChain chain = designatorInc.getDesignatorDst().getDesignatorChain();
		if (chain instanceof DesignatorChainIdent) {
			// Must be int variable or int field in the method of that class
			if (prevObj.getKind() == Obj.Fld)
				Code.put(Code.dup);
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
			// Must be int variable or int field in the method of that class
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
			if (prevObj.getKind() == Obj.Fld)
				Code.put(Code.dup);
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
		NONE, ADD, SUB;
	}

	private enum MULOP {
		NONE, MUL, DIV, MOD
	}

	private enum RELOP {
		NONE, EQUAL, NOTEQUAL, GREATER, GREATEREQ, LESSER, LESSEREQ
	}

	private Stack<ADDOP> addopStack = new Stack<CodeGenerator.ADDOP>();
	private Stack<MULOP> mulopStack = new Stack<CodeGenerator.MULOP>();

	private ADDOP currentAddOp = ADDOP.NONE;
	private MULOP currentMulOp = MULOP.NONE;
	private RELOP currentRelOp = RELOP.NONE; // No need for stack -> will remain last visited

	@Override
	public void visit(Plus Plus) {
		addopStack.push(currentAddOp);
		currentAddOp = ADDOP.ADD;
	}

	@Override
	public void visit(Minus Minus) {
		addopStack.push(currentAddOp);
		currentAddOp = ADDOP.SUB;
	}

	@Override
	public void visit(Div Div) {
		mulopStack.push(currentMulOp);
		currentMulOp = MULOP.DIV;
	}

	@Override
	public void visit(Mul Mul) {
		mulopStack.push(currentMulOp);
		currentMulOp = MULOP.MUL;
	}

	@Override
	public void visit(Mod Mod) {
		mulopStack.push(currentMulOp);
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
		currentMulOp = mulopStack.pop();
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
		currentAddOp = addopStack.pop();
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
		Code.put2((factorTypeInst.obj.getType().getNumberOfFields() + 1) * 4); // If s represents words, x4 for bytes ->
																				// Will test
		// Will exist so no check needed
		Obj classObj = program.getLocalSymbols().stream()
				.filter(sym -> sym.getKind() == Obj.Type && sym.getType() == factorTypeInst.obj.getType()).findFirst()
				.get();
		Code.put(Code.dup);
		Code.put(Code.const_);
		if (currentStaticAddr == -1)
			vftPointerFixupMap.get(classObj).add(Code.pc);
		Code.put4(classObj.getAdr());
		Code.put(Code.putfield);
		Code.put2(0);
	}

	// Control structure nesting enum

	enum CSType {
		NONE, SWITCH, FOR
	}

	private Stack<CSType> csNestingStack = new Stack<CodeGenerator.CSType>();
	private CSType currentCS = CSType.NONE;

	// Switch

	private Stack<List<Integer>> breakNestingStack = new Stack<List<Integer>>();
	private List<Integer> breakFixupAddresses = null;

	private Stack<Integer> caseCheckJumpAddrStack = new Stack<Integer>();
	private int prevCaseCheckJumpAddr = -1;

	@Override
	public void visit(SwitchInterrupt switchInterrupt) {
		// Set current structure
		csNestingStack.push(currentCS);
		currentCS = CSType.SWITCH;

		breakNestingStack.push(breakFixupAddresses);
		breakFixupAddresses = new ArrayList<Integer>();
		caseCheckJumpAddrStack.push(prevCaseCheckJumpAddr);
		prevCaseCheckJumpAddr = -1;
	}

	@Override
	public void visit(SwitchStatement switchStatement) {
		// Fixup last case condition jump
		Code.fixup(prevCaseCheckJumpAddr);
		prevCaseCheckJumpAddr = caseCheckJumpAddrStack.pop();
		// Fixup all break statements
		for (int addr : breakFixupAddresses)
			Code.fixup(addr);
		breakFixupAddresses = breakNestingStack.pop();
		currentCS = csNestingStack.pop();
		Code.put(Code.pop); // Removing extra expression
	}

	@Override
	public void visit(CaseNum caseNum) {
		if (prevCaseCheckJumpAddr != -1)
			// Fixup last case mismatch jump
			Code.fixup(prevCaseCheckJumpAddr);
		Code.put(Code.dup); // 1 byte
		Code.put(Code.const_); // 1 byte
		Code.put4(caseNum.getVal()); // 4 bytes
		Code.putFalseJump(Code.eq, 0); // 3 bytes
		prevCaseCheckJumpAddr = Code.pc - 2; // Jmp address
	}

	@Override
	public void visit(SingleCase singleCase) {
		SyntaxNode grandParent = singleCase.getParent().getParent();
		if (grandParent instanceof SwitchStatement)
			return;
		// Adding a case check skip for fallthrough
		Code.putJump(Code.pc + 12); // 9 bytes for case check and 3 bytes for the jump itself
	}

	@Override
	public void visit(BreakStatement breakStatement) {
		Code.putJump(0);
		breakFixupAddresses.add(Code.pc - 2);
	}

	// Condition
	private Stack<List<Integer>> falseConditionAddrStack = new Stack<List<Integer>>();
	private List<Integer> falseConditionAddrs = null;

	private Stack<List<Integer>> trueConditionAddrStack = new Stack<List<Integer>>();
	private List<Integer> trueConditionAddrs = null;

	private Stack<Boolean> condStartStack = new Stack<Boolean>(); 
	private boolean conditionStart = true;
	// Conditions are responsible for keeping the previous jump fixup addresses on
	// stacks
	// if, for and ternary restore the state in case of nesting

	@Override
	public void visit(SimpleCondFact simpleCondFact) {
		if (conditionStart) {
			conditionStart = false;
			falseConditionAddrStack.push(falseConditionAddrs);
			trueConditionAddrStack.push(trueConditionAddrs);

			falseConditionAddrs = new ArrayList<Integer>();
			trueConditionAddrs = new ArrayList<Integer>();
		}
		Code.put(Code.const_1); // Check if true/1
		Code.putFalseJump(Code.eq, 0); // Jump on neq
		falseConditionAddrs.add(Code.pc - 2);
	}

	@Override
	public void visit(RelopCondFact relopCondFact) {
		if (conditionStart) {
			conditionStart = false;
			falseConditionAddrStack.push(falseConditionAddrs);
			trueConditionAddrStack.push(trueConditionAddrs);

			falseConditionAddrs = new ArrayList<Integer>();
			trueConditionAddrs = new ArrayList<Integer>();
		}
		switch (currentRelOp) {
		case EQUAL:
			Code.putFalseJump(Code.eq, 0);
			break;
		case NOTEQUAL:
			Code.putFalseJump(Code.ne, 0);
			break;
		case GREATER:
			Code.putFalseJump(Code.gt, 0);
			break;
		case GREATEREQ:
			Code.putFalseJump(Code.ge, 0);
			break;
		case LESSER:
			Code.putFalseJump(Code.lt, 0);
			break;
		case LESSEREQ:
			Code.putFalseJump(Code.le, 0);
			break;
		default:
			// Never happening
		}
		falseConditionAddrs.add(Code.pc - 2);
	}

	@Override
	public void visit(OrInterrupt orInterrupt) {
		// Unconditional jump for control that made it to ||
		Code.putJump(0);
		trueConditionAddrs.add(Code.pc - 2);
		// Fixup to redirect unsuccessful control to other CondTerm
		for (int fca : falseConditionAddrs)
			Code.fixup(fca);
		falseConditionAddrs.clear();
	}

	// For loop

	private Stack<Integer> forBodyJumpStack = new Stack<Integer>();
	private int forBodyJumpAddress = -1; // Addr where the body start will be written

	private Stack<Integer> afterBodyStack = new Stack<Integer>();
	private int afterBodyAddress = -1;

	private Stack<List<Integer>> continueAddrStack = new Stack<List<Integer>>();
	private List<Integer> continueAddresses = null;

	private int forCondCheckAddr = -1;

	@Override
	public void visit(ForBeforeCond forBeforeCond) {
		// Preparing for new loop
		forBodyJumpStack.push(forBodyJumpAddress);
		afterBodyStack.push(afterBodyAddress);
		continueAddrStack.push(continueAddresses);
		breakNestingStack.push(breakFixupAddresses);

		forCondCheckAddr = Code.pc;
	}

	@Override
	public void visit(ForAfterCond forAfterCond) {
		conditionStart = true;
		// New loop initialization
		Code.putJump(0);
		forBodyJumpAddress = Code.pc - 2;
		afterBodyAddress = Code.pc;
		continueAddresses = new ArrayList<Integer>();
		breakFixupAddresses = new ArrayList<Integer>();
	}

	@Override
	public void visit(ForStatement forStatement) {
		// Continue fixup -> end up at the post designatorStmt block
		for (int ca : continueAddresses)
			Code.fixup(ca);

		Code.putJump(afterBodyAddress);

		// Break fixup
		for (int ba : breakFixupAddresses)
			Code.fixup(ba);

		for (int fca : falseConditionAddrs)
			Code.fixup(fca);

		// Restoring previous state
		forBodyJumpAddress = forBodyJumpStack.pop();
		afterBodyAddress = afterBodyStack.pop();
		continueAddresses = continueAddrStack.pop();
		breakFixupAddresses = breakNestingStack.pop();
		currentCS = csNestingStack.pop();

		falseConditionAddrs = falseConditionAddrStack.pop();
		trueConditionAddrs = trueConditionAddrStack.pop();
	}

	@Override
	public void visit(ForInterrupt forInterrupt) {
		// Set current structure
		csNestingStack.push(currentCS);
		currentCS = CSType.FOR;

		Code.putJump(forCondCheckAddr); // Jump to check
		Code.fixup(forBodyJumpAddress);	
		for (int tca : trueConditionAddrs)
			Code.fixup(tca);
	}
	
	@Override
	public void visit(NoForCond noForCond) {
		falseConditionAddrStack.push(falseConditionAddrs);
		trueConditionAddrStack.push(trueConditionAddrs);

		falseConditionAddrs = new ArrayList<Integer>();
		trueConditionAddrs = new ArrayList<Integer>();
	}

	@Override
	public void visit(ContinueStatement continueStatement) {
		// Adjust for switch -> pop needed
		if (currentCS == CSType.SWITCH)
			Code.put(Code.pop);
		Code.putJump(0);
		continueAddresses.add(Code.pc - 2);
	}

	// If branching

	private Stack<Integer> elseSkipAddrStack = new Stack<Integer>();
	private int elseSkipAddr = -1;

	@Override
	public void visit(IfBeforeCond ifBeforeCond) {
		// For if-else purpose
		elseSkipAddrStack.push(elseSkipAddr);
	}

	@Override
	public void visit(IfAfterCond ifAfterCond) {
		conditionStart = true;
		// Successful controls to end up at the body
		for (int tca : trueConditionAddrs)
			Code.fixup(tca);
	}

	@Override
	public void visit(IfStatement ifStatement) {
		// Unsuccessful controls to end up at the end
		for (int fca : falseConditionAddrs)
			Code.fixup(fca);
		falseConditionAddrs = falseConditionAddrStack.pop();
		trueConditionAddrs = trueConditionAddrStack.pop();

		// For if-else purpose
		elseSkipAddr = elseSkipAddrStack.pop();
	}

	@Override
	public void visit(ElseInterrupt elseInterrupt) {
		// Else skip for successful controls
		Code.putJump(0);
		elseSkipAddr = Code.pc - 2;
		// Unsuccessful controls to end up at else
		for (int fca : falseConditionAddrs)
			Code.fixup(fca);
	}

	@Override
	public void visit(IfElseStatement ifElseStatement) {
		Code.fixup(elseSkipAddr);

		falseConditionAddrs = falseConditionAddrStack.pop();
		trueConditionAddrs = trueConditionAddrStack.pop();

		// For if-else purpose
		elseSkipAddr = elseSkipAddrStack.pop();
	}

	// Ternary expression

	@Override
	public void visit(TernaryInterrupt ternaryInterrupt) {
		elseSkipAddrStack.push(elseSkipAddr);
		conditionStart = true;
		// Successful controls to end up at the body
		for (int tca : trueConditionAddrs)
			Code.fixup(tca);
	}

	@Override
	public void visit(TernaryElse ternaryElse) {
		// Else skip for successful controls
		Code.putJump(0);
		elseSkipAddr = Code.pc - 2;
		// Unsuccessful controls to end up at else
		for (int fca : falseConditionAddrs)
			Code.fixup(fca);
	}

	@Override
	public void visit(TernaryExpr ternaryExpr) {
		Code.fixup(elseSkipAddr);

		falseConditionAddrs = falseConditionAddrStack.pop();
		trueConditionAddrs = trueConditionAddrStack.pop();

		// For if-else purpose
		elseSkipAddr = elseSkipAddrStack.pop();
	}

	@Override
	public void visit(FactorExprStartIntr factorExprStartIntr) {
		// In case of ternary operators in conditions
		condStartStack.push(conditionStart);
		conditionStart = true;
	}

	@Override
	public void visit(FactorExprEndIntr factorExprEndIntr) {
		conditionStart = condStartStack.pop();
	}

}
