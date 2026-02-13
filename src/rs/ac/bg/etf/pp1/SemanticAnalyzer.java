package rs.ac.bg.etf.pp1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.AbsClassDecl;
import rs.ac.bg.etf.pp1.ast.AbsClassName;
import rs.ac.bg.etf.pp1.ast.AbsMethodDecl;
import rs.ac.bg.etf.pp1.ast.AbsMethodName;
import rs.ac.bg.etf.pp1.ast.Addop;
import rs.ac.bg.etf.pp1.ast.AndCondTerm;
import rs.ac.bg.etf.pp1.ast.BoolConstVal;
import rs.ac.bg.etf.pp1.ast.BreakStatement;
import rs.ac.bg.etf.pp1.ast.CExtension;
import rs.ac.bg.etf.pp1.ast.CharConstVal;
import rs.ac.bg.etf.pp1.ast.ClassDecl;
import rs.ac.bg.etf.pp1.ast.ClassMethodDecl;
import rs.ac.bg.etf.pp1.ast.ClassMethodName;
import rs.ac.bg.etf.pp1.ast.ClassName;
import rs.ac.bg.etf.pp1.ast.ConstDecl;
import rs.ac.bg.etf.pp1.ast.ContinueStatement;
import rs.ac.bg.etf.pp1.ast.Designator;
import rs.ac.bg.etf.pp1.ast.DesignatorChainFieldAccess;
import rs.ac.bg.etf.pp1.ast.DesignatorChainIdent;
import rs.ac.bg.etf.pp1.ast.DesignatorChainIndexAccess;
import rs.ac.bg.etf.pp1.ast.DesignatorDec;
import rs.ac.bg.etf.pp1.ast.DesignatorInc;
import rs.ac.bg.etf.pp1.ast.Div;
import rs.ac.bg.etf.pp1.ast.EnumDecl;
import rs.ac.bg.etf.pp1.ast.EnumName;
import rs.ac.bg.etf.pp1.ast.EnumNumConstAssign;
import rs.ac.bg.etf.pp1.ast.Equal;
import rs.ac.bg.etf.pp1.ast.ExtFieldIntercept;
import rs.ac.bg.etf.pp1.ast.ExtMethodIntercept;
import rs.ac.bg.etf.pp1.ast.FactorArrayInst;
import rs.ac.bg.etf.pp1.ast.FactorBoolConst;
import rs.ac.bg.etf.pp1.ast.FactorCharConst;
import rs.ac.bg.etf.pp1.ast.FactorDesignator;
import rs.ac.bg.etf.pp1.ast.FactorExpr;
import rs.ac.bg.etf.pp1.ast.FactorFCall;
import rs.ac.bg.etf.pp1.ast.FactorNumConst;
import rs.ac.bg.etf.pp1.ast.FactorTypeInst;
import rs.ac.bg.etf.pp1.ast.Factors;
import rs.ac.bg.etf.pp1.ast.FirstFactorInList;
import rs.ac.bg.etf.pp1.ast.FirstTermInList;
import rs.ac.bg.etf.pp1.ast.ForCond;
import rs.ac.bg.etf.pp1.ast.ForInterrupt;
import rs.ac.bg.etf.pp1.ast.ForStatement;
import rs.ac.bg.etf.pp1.ast.GreaterEqual;
import rs.ac.bg.etf.pp1.ast.GreaterThan;
import rs.ac.bg.etf.pp1.ast.IfElseStatement;
import rs.ac.bg.etf.pp1.ast.IfStatement;
import rs.ac.bg.etf.pp1.ast.IsArray;
import rs.ac.bg.etf.pp1.ast.LesserThan;
import rs.ac.bg.etf.pp1.ast.LesserThanEqual;
import rs.ac.bg.etf.pp1.ast.MethodDecl;
import rs.ac.bg.etf.pp1.ast.MethodName;
import rs.ac.bg.etf.pp1.ast.Minus;
import rs.ac.bg.etf.pp1.ast.Mod;
import rs.ac.bg.etf.pp1.ast.Mul;
import rs.ac.bg.etf.pp1.ast.NoAbsClassMethods;
import rs.ac.bg.etf.pp1.ast.NoCExtension;
import rs.ac.bg.etf.pp1.ast.NoClassMethods;
import rs.ac.bg.etf.pp1.ast.NotEqual;
import rs.ac.bg.etf.pp1.ast.NumConstPrintStatement;
import rs.ac.bg.etf.pp1.ast.NumConstVal;
import rs.ac.bg.etf.pp1.ast.OrCondition;
import rs.ac.bg.etf.pp1.ast.Plus;
import rs.ac.bg.etf.pp1.ast.PrintStatement;
import rs.ac.bg.etf.pp1.ast.Program;
import rs.ac.bg.etf.pp1.ast.ProgramName;
import rs.ac.bg.etf.pp1.ast.ReadStatement;
import rs.ac.bg.etf.pp1.ast.RelopCondFact;
import rs.ac.bg.etf.pp1.ast.SimpleCondFact;
import rs.ac.bg.etf.pp1.ast.SimpleCondTerm;
import rs.ac.bg.etf.pp1.ast.SimpleCondition;
import rs.ac.bg.etf.pp1.ast.SimpleExpr;
import rs.ac.bg.etf.pp1.ast.SingleConstDecl;
import rs.ac.bg.etf.pp1.ast.SingleFormalParam;
import rs.ac.bg.etf.pp1.ast.SingleIdentEnumDecl;
import rs.ac.bg.etf.pp1.ast.SingleVarDecl;
import rs.ac.bg.etf.pp1.ast.SwitchStatement;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.Term;
import rs.ac.bg.etf.pp1.ast.Terms;
import rs.ac.bg.etf.pp1.ast.TernaryExpr;
import rs.ac.bg.etf.pp1.ast.Type;
import rs.ac.bg.etf.pp1.ast.TypeReturn;
import rs.ac.bg.etf.pp1.ast.TypedReturnStatement;
import rs.ac.bg.etf.pp1.ast.UMinusFirstTermInList;
import rs.ac.bg.etf.pp1.ast.VarDecl;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.ac.bg.etf.pp1.ast.VoidReturn;
import rs.ac.bg.etf.pp1.ast.VoidReturnStatement;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.*;

public class SemanticAnalyzer extends VisitorAdaptor {

	private final Struct boolType;

	private final Obj intObj;
	private final Obj chrObj;
	private final Obj boolObj;

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
		if (superClass.getKind() != Struct.Class || type.getKind() != Struct.Class)
			return false;
		while (type != Tab.noType && type != superClass)
			type = type.getElemType();
		return type == superClass;
	}

	private Obj findArrayElemTypeObj(Struct arrayStruct) {
		if (arrayStruct.getKind() != Struct.Array)
			return Tab.noObj;

		return findTypeObj(arrayStruct.getElemType());
	}

	private Obj findTypeObj(Struct type) {
		if (type.equals(intObj.getType()))
			return intObj;

		if (type.equals(chrObj.getType()))
			return chrObj;

		if (type.equals(boolObj.getType()))
			return boolObj;

		Scope currentSearchScope = Tab.currentScope;
		while (currentSearchScope != null && currentSearchScope.getOuter() != null
				&& currentSearchScope.getOuter().getOuter() != null) {
			currentSearchScope = currentSearchScope.getOuter(); // Looking for program scope
		}
		Optional<Obj> typeObjOpt = currentSearchScope.getLocals().symbols().stream()
				.filter(sym -> sym.getKind() == Obj.Type && sym.getType().equals(type)).findFirst();
		if (!typeObjOpt.isPresent()) {
			return Tab.noObj;
		}
		return typeObjOpt.get();
	}

	private Struct currentType; // Type currently used for declaration of variables or method returns
	private int nextConstVal; // Next value to be assigned to a constant

	private Struct currentClassType = Tab.noType; // Class currently being declared
	private boolean classDeclInitialized = false;
	private boolean returnTyped; // Return type flag
	private Obj overridingMeth = Tab.noObj; // Method currently being overridden
	private Obj currentMeth = Tab.noObj; // Method currently being declared
	private List<Obj> inheritedMethods = new ArrayList<Obj>(); // Not implemented/overriden methods inherited from the
																// superclass
	private int currentFpPos = 0; // Current formal parameter position

	public SemanticAnalyzer(Struct boolType) {
		this.boolType = boolType;
		this.intObj = Tab.find("int");
		this.chrObj = Tab.find("char");
		this.boolObj = Tab.find("bool");

		if (chrObj == Tab.noObj)
			System.out.println("Nema char");
		if (intObj == Tab.noObj)
			System.out.println("Nema int");
		if (boolObj == Tab.noObj)
			System.out.println("Nema bool");
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
		if (currentType == Tab.noType) { // Type does not exist
			return;
		}
		if (Tab.currentScope.findSymbol(name) != null) {
			report_error("Redeclaration of already used name '" + name + "'", singleVarDecl);
			return;
		}

		if (classDeclInitialized && currentClassType == Tab.noType) // Class not initialized -> cannot declare field
			return;
		Struct varType = (singleVarDecl.getArrayDecl() instanceof IsArray) ? new Struct(Struct.Array, currentType)
				: currentType;
		Obj cVar = null;
		if (classDeclInitialized && currentMeth == Tab.noObj)
			cVar = Tab.insert(Obj.Fld, name, varType);
		else
			cVar = Tab.insert(Obj.Var, name, varType);
		if (classDeclInitialized && currentMeth != Tab.noObj)
			cVar.setLevel(2);
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
		classDeclInitialized = true;
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
	}

	@Override
	public void visit(NoCExtension noCExtension) {
		currentType = Tab.noType;
		currentClassType.setElementType(currentType);
	}

	@Override
	public void visit(ClassDecl classDecl) {
		if (currentClassType == Tab.noType)
			return;
		Tab.chainLocalSymbols(currentClassType);
		Tab.closeScope();
		currentClassType = Tab.noType;
		currentClassObj = Tab.noObj;
		classDeclInitialized = false;
		if (inheritedMethods.stream().filter(meth -> meth.getFpPos() == 1).findAny().isPresent()) {
			report_error("Not all abstract methods from the superclass were defined for the class '"
					+ currentClassObj.getName() + "'", classDecl);
			inheritedMethods.forEach(meth -> report_error("    '" + meth.getName() + "' not defined", null));
		}
		inheritedMethods.clear();
	}

	@Override
	public void visit(ExtFieldIntercept extFieldIntercept) {
		if (currentClassType.getElemType() == Tab.noType)
			return;
		currentType.getMembers().stream().filter(sym -> sym.getKind() == Obj.Fld)
				.forEach(field -> Tab.insert(Obj.Fld, field.getName(), field.getType()));
	}

	@Override
	public void visit(ExtMethodIntercept extMethodIntercept) {
		// Copying all superclass methods
		if (currentClassType.getElemType() == Tab.noType)
			return;
		inheritedMethods.clear();
		currentClassType.getElemType().getMembers().stream().filter(sym -> sym.getKind() == Obj.Meth).forEach(meth -> {
			Obj overMeth = Tab.insert(Obj.Meth, meth.getName(), meth.getType());
			overMeth.setLevel(meth.getLevel());
			overMeth.setFpPos(meth.getFpPos());
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
		// Copying all superclass methods
		if (currentClassType.getElemType() == Tab.noType)
			return;
		inheritedMethods.clear();
		currentClassType.getElemType().getMembers().stream().filter(sym -> sym.getKind() == Obj.Meth).forEach(meth -> {
			Obj overMeth = Tab.insert(Obj.Meth, meth.getName(), meth.getType());
			overMeth.setLevel(meth.getLevel());
			overMeth.setFpPos(meth.getFpPos());
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
		if ((returnTyped && currentType == Tab.noType) || currentClassType == Tab.noType) {
			currentMeth = Tab.noObj;
			return;
		}
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

		// Return type check
		if ((overMeth.getType().getKind() == Struct.Class && !isSuperClass(overMeth.getType(), currentType))
				|| (overMeth.getType().getKind() != Struct.Class && !overMeth.getType().equals(currentType))) {
			report_error("Illegal redefinition of return type for method '" + classMethodName.getName() + "'",
					classMethodName);
			currentMeth = Tab.noObj;
			return;
		}

		// Method to be overridden -> removing from the inherited not overridden methods
		inheritedMethods.remove(overMeth);

		if (!overMeth.getType().equals(currentType)) {
			// New return type -> Adjusting the Obj of the method
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
				} else {
					meth = Tab.insert(sym.getKind(), sym.getName(), currentType);
					currentMeth = meth;
				}

				if (inheritedMethods.contains(sym))
					leftoverMethods.add(sym);
			});
			inheritedMethods = leftoverMethods;
		} else {
			// Same return type
			overridingMeth = new Obj(Obj.Meth, overMeth.getName(), overMeth.getType(), overMeth.getAdr(),
					overMeth.getLevel());
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
			currentMeth.setFpPos(0);
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
			report_error("Not all parameters were provided for overriding of method '" + currentMeth.getName() + "'",
					classMethodDecl);
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
		if (singleFormalParam.getArrayDecl() instanceof IsArray)
			currentType = new Struct(Struct.Array, currentType);

		if (overridingMeth != Tab.noObj) {
			if (overridingMeth.getLevel() <= currentFpPos) {
				report_error("Too many parameters provided for method '" + currentMeth.getName() + "'",
						singleFormalParam);
				return;
			}
			Struct superClassParamType = overridingMeth.getLocalSymbols().stream()
					.filter(param -> param.getFpPos() == currentFpPos).findFirst().get().getType();

			if (!currentType.equals(superClassParamType))
				report_error("Illegal change of formal parameter type for parameter '"
						+ singleFormalParam.getParamName() + "'", singleFormalParam);

			// Allowing superclasses as formal parameter type redefinitions
			/*
			 * if (!isSuperClass(currentType, superClassParamType))
			 * report_error("Illegal change of formal parameter type for parameter '" +
			 * singleFormalParam.getParamName() + "'", singleFormalParam);
			 */
		}
		Obj formalParamObj = Tab.insert(Obj.Var, singleFormalParam.getParamName(), currentType);
		formalParamObj.setFpPos(currentFpPos++);
		formalParamObj.setLevel(currentClassType == Tab.noType ? 1 : 2);
	}

	// AbstractClassDecl

	@Override
	public void visit(AbsClassName absClassName) {
		String name = absClassName.getName();
		classDeclInitialized = true;
		if (Tab.currentScope.findSymbol(name) != null) {
			report_error("Redeclaration of already used name '" + name + "'", absClassName);
			currentClassType = Tab.noType;
			return;
		}
		currentClassType = new Struct(Struct.Class);
		currentClassObj = Tab.insert(Obj.Type, name, currentClassType);
		currentClassObj.setFpPos(1);
		Tab.openScope();
	}

	@Override
	public void visit(AbsClassDecl absClassDecl) {
		if (currentClassType == Tab.noType)
			return;
		Tab.chainLocalSymbols(currentClassType);
		Tab.closeScope();
		currentClassType = Tab.noType;
		currentClassObj = Tab.noObj;
		classDeclInitialized = false;
		inheritedMethods.clear();
	}

	@Override
	public void visit(NoAbsClassMethods noAbsClassMethods) {
		// Copying all superclass methods
		if (currentClassType.getElemType() == Tab.noType)
			return;
		inheritedMethods.clear();
		currentClassType.getElemType().getMembers().stream().filter(sym -> sym.getKind() == Obj.Meth).forEach(meth -> {
			Obj overMeth = Tab.insert(Obj.Meth, meth.getName(), meth.getType());
			overMeth.setLevel(meth.getLevel());
			overMeth.setFpPos(meth.getFpPos());
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

	// TODO TEST FOR BUGS
	@Override
	public void visit(AbsMethodName absMethodName) {
		if ((returnTyped && currentType == Tab.noType) || currentClassType == Tab.noType) {
			currentMeth = Tab.noObj;
			return;
		}
		currentFpPos = 0;
		Obj overMeth = Tab.currentScope.findSymbol(absMethodName.getName());
		if (overMeth == null) {
			overridingMeth = Tab.noObj;
			currentMeth = Tab.insert(Obj.Meth, absMethodName.getName(), currentType);
			currentMeth.setFpPos(1);
			Tab.openScope();
			Obj thisObj = Tab.insert(Obj.Var, "this", currentClassType);
			thisObj.setLevel(2);
			thisObj.setFpPos(currentFpPos++);
			return;
		}

		// Redeclaration inside of the same class
		if (!inheritedMethods.contains(overMeth)) {
			report_error("Redeclaration of method '" + absMethodName.getName() + "'", absMethodName);
			currentMeth = Tab.noObj;
			return;
		}

		// Abstract method cannot override a concrete one
		if (overMeth.getFpPos() == 0) {
			report_error("Concrete method '" + absMethodName.getName() + "' cannot be redeclared abstract",
					absMethodName);
			currentMeth = Tab.noObj;
			return;
		}

		// Return type check
		if ((overMeth.getType().getKind() == Struct.Class && !isSuperClass(overMeth.getType(), currentType))
				|| (overMeth.getType().getKind() != Struct.Class && !overMeth.getType().equals(currentType))) {
			report_error("Illegal redefinition of return type for method '" + absMethodName.getName() + "'",
					absMethodName);
			currentMeth = Tab.noObj;
			return;
		}

		// Method to be overridden -> removing from the inherited not overridden methods
		inheritedMethods.remove(overMeth);

		if (overMeth.getType().equals(currentType)) {
			// New return type -> Adjusting the Obj of the method
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
				} else {
					meth = Tab.insert(sym.getKind(), sym.getName(), currentType);
					currentMeth = meth;
				}

				if (inheritedMethods.contains(sym))
					leftoverMethods.add(sym);
			});
			inheritedMethods = leftoverMethods;
		} else {
			// Same return type
			overridingMeth = new Obj(Obj.Meth, overMeth.getName(), overMeth.getType(), overMeth.getAdr(),
					overMeth.getLevel());
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
			currentMeth.setFpPos(0);
		}
		currentMeth.setFpPos(1);
		Tab.openScope();
		Obj thisObj = Tab.insert(Obj.Var, "this", currentClassType);
		thisObj.setLevel(2);
		thisObj.setFpPos(currentFpPos++);
	}

	@Override
	public void visit(AbsMethodDecl absMethodDecl) {
		if (currentMeth == Tab.noObj)
			return;
		if (overridingMeth != Tab.noObj && currentFpPos < overridingMeth.getLevel()) {
			report_error("Not all parameters were provided for overriding of method '" + currentMeth.getName() + "'",
					absMethodDecl);
		}
		currentMeth.setLevel(currentFpPos);
		Tab.chainLocalSymbols(currentMeth);
		Tab.closeScope();
		currentMeth = Tab.noObj;
		overridingMeth = Tab.noObj;
	}

	// GlobalMethodDecl
	@Override
	public void visit(MethodName methodName) {
		if (returnTyped && currentType == Tab.noType)
			return;
		currentFpPos = 0;
		Obj foundSymbol = Tab.currentScope.findSymbol(methodName.getName());
		if (foundSymbol != null) {
			report_error("Name '" + methodName.getName() + "' already defined within scope", methodName);
			currentMeth = Tab.noObj;
			return;
		}
		currentMeth = Tab.insert(Obj.Meth, methodName.getName(), currentType);
		Tab.openScope();
	}

	@Override
	public void visit(MethodDecl methodDecl) {
		if (currentMeth == Tab.noObj)
			return;
		currentMeth.setLevel(currentFpPos);
		Tab.chainLocalSymbols(currentMeth);
		Tab.closeScope();
		currentMeth = Tab.noObj;
		overridingMeth = Tab.noObj;
	}

	// Expressions and conditions

	// USELESS -> Probably needed for code generation
	private enum ADDOP {
		NONE(""), PLUS("addition"), MINUS("subtraction");

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
		currentAddOp = ADDOP.PLUS;
	}

	@Override
	public void visit(Minus Minus) {
		currentAddOp = ADDOP.MINUS;
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
	

	private Struct assignOpEnumType = Tab.noType; // Enum assignment
	private Obj lastVisitedDesignator = Tab.noObj; // Actual parameter checking

	@Override
	public void visit(Designator designator) {
		lastVisitedDesignator = designator.obj = designator.getDesignatorChain().obj;
	}

	@Override
	public void visit(DesignatorChainIdent designatorChainIdent) {
		assignOpEnumType = Tab.noType;
		Obj foundSymbol = Tab.find(designatorChainIdent.getName());
		if (foundSymbol == Tab.noObj) {
			report_error("Unknown name '" + designatorChainIdent.getName() + "'", designatorChainIdent);
			designatorChainIdent.obj = Tab.noObj;
			return;
		}
		// TODO REMOVE THE MEGA OVERKILL
		if (foundSymbol.getKind() != Obj.Con && foundSymbol.getKind() != Obj.Var
				&& !(foundSymbol.getKind() == Obj.Type && foundSymbol.getType().getKind() == Struct.Enum)
				&& foundSymbol.getKind() != Obj.Meth && foundSymbol.getKind() != Obj.Fld) {
			report_error("Name '" + designatorChainIdent.getName()
					+ "' does not represent an enum, a constant, a method nor a variable reachable from the current scope",
					designatorChainIdent);
			designatorChainIdent.obj = Tab.noObj;
			return;
		}
		designatorChainIdent.obj = foundSymbol;
		assignOpEnumType = Tab.noType;
	}

	@Override
	public void visit(DesignatorChainFieldAccess designatorChainFieldAccess) {
		if (designatorChainFieldAccess.getDesignatorChain().obj == Tab.noObj)
			return;
		if (designatorChainFieldAccess.getDesignatorChain().obj.getKind() == Obj.Meth) {
			report_error("Invalid acccess for method '" + designatorChainFieldAccess.getDesignatorChain().obj.getName()
					+ "'", designatorChainFieldAccess);
			return;
		}
		if (designatorChainFieldAccess.getDesignatorChain().obj.getKind() == Obj.Con) {
			report_error("Invalid acccess for constant '"
					+ designatorChainFieldAccess.getDesignatorChain().obj.getName() + "'", designatorChainFieldAccess);
			return;
		}
		String accessName = designatorChainFieldAccess.getFieldName();
		switch (designatorChainFieldAccess.getDesignatorChain().obj.getType().getKind()) {
		case Struct.Array:
			if (!accessName.equals("length")) {
				report_error("Invalid field/method access of an array", designatorChainFieldAccess);
				designatorChainFieldAccess.obj = Tab.noObj;
				return;
			}
			designatorChainFieldAccess.obj = intObj;
			break;
		case Struct.Enum:
			Optional<Obj> enumConstOpt = designatorChainFieldAccess.getDesignatorChain().obj.getType().getMembers()
					.stream().filter(con -> con.getName().equals(accessName)).findFirst();
			if (!enumConstOpt.isPresent()) {
				report_error("Invalid enum constant access. Constant with name '" + accessName + "' does not exist",
						designatorChainFieldAccess);
				designatorChainFieldAccess.obj = Tab.noObj;
				return;
			}
			assignOpEnumType = designatorChainFieldAccess.getDesignatorChain().obj.getType();
			designatorChainFieldAccess.obj = intObj;
			break;
		case Struct.Class:
			Optional<Obj> classFieldOpt;
			// Current class struct has not yet been chained
			if (designatorChainFieldAccess.getDesignatorChain().obj.getName().equals("this")
					|| designatorChainFieldAccess.getDesignatorChain().obj.getType().equals(currentClassType))
				// TODO REMOVE TEMP PRINT
				classFieldOpt = Tab.currentScope.getOuter().getLocals().symbols().stream()
						.filter(sym -> sym.getName().equals(accessName)).findFirst();
			else
				// TODO REMOVE TEMP PRINT
				classFieldOpt = designatorChainFieldAccess.getDesignatorChain().obj.getType().getMembers().stream()
						.filter(sym -> sym.getName().equals(accessName)).findFirst();
			if (!classFieldOpt.isPresent()) {
				report_error("Invalid class field/method access. Field/method with name '" + accessName + "'",
						designatorChainFieldAccess);
				designatorChainFieldAccess.obj = Tab.noObj;
				return;
			}
			designatorChainFieldAccess.obj = classFieldOpt.get();

		}

	}

	@Override
	public void visit(DesignatorChainIndexAccess designatorChainIndexAccess) {
		if (designatorChainIndexAccess.getDesignatorChain().obj == Tab.noObj)
			return;
		if (designatorChainIndexAccess.getDesignatorChain().obj.getKind() != Obj.Var) {
			report_error("Indexed access of non-variable", designatorChainIndexAccess);
			designatorChainIndexAccess.obj = Tab.noObj;
			return;
		}
		if (designatorChainIndexAccess.getDesignatorChain().obj.getType().getKind() != Struct.Array) {
			report_error("Indexed access of non-array variable", designatorChainIndexAccess);
			designatorChainIndexAccess.obj = Tab.noObj;
			return;
		}
		// TODO TEST
		Obj indexExpr = designatorChainIndexAccess.getExpr().obj;
		if (indexExpr == null || indexExpr == Tab.noObj || !indexExpr.getType().equals(Tab.intType)) {
			report_error("Array cannot be indexed using expressions not resulting in int type",
					designatorChainIndexAccess);
			designatorChainIndexAccess.obj = Tab.noObj;
			return;
		}
		Obj arrayElemObj = findArrayElemTypeObj(designatorChainIndexAccess.getDesignatorChain().obj.getType());
		designatorChainIndexAccess.obj = arrayElemObj;
	}

	@Override
	public void visit(SimpleExpr simpleExpr) {
		simpleExpr.obj = simpleExpr.getTermList().obj;
	}

	@Override
	public void visit(FactorDesignator factorDesignator) {
		factorDesignator.obj = factorDesignator.getDesignator().obj;
	}

	// TODO Add act param checking
	@Override
	public void visit(FactorFCall factorFCall) {
		Obj methObj = factorFCall.getDesignator().obj;
		if (methObj == Tab.noObj || methObj.getKind() != Obj.Meth) {
			report_error("Method call of non-method designator", factorFCall);
			factorFCall.obj = Tab.noObj;
			return;
		}
		Obj methRetObj = findTypeObj(methObj.getType());
		//TODO REMOVE LATER
		//System.out.println("Found type '" + methRetObj.getName() + "' for method '" + methObj.getName() + "'");
		factorFCall.obj = methRetObj;
	}

	@Override
	public void visit(FactorNumConst factorNumConst) {
		factorNumConst.obj = intObj;
	}

	@Override
	public void visit(FactorCharConst factorCharConst) {
		factorCharConst.obj = chrObj;
	}

	@Override
	public void visit(FactorBoolConst factorBoolConst) {
		factorBoolConst.obj = boolObj;
	}

	@Override
	public void visit(FactorTypeInst factorTypeInst) {
		if (currentType == Tab.noType)
			return;
		if (currentType.getKind() != Struct.Class) {
			report_error("Instantiating a non-class type ", factorTypeInst);
			factorTypeInst.obj = Tab.noObj;
			return;
		}
		Obj classTypeObj = findTypeObj(currentType);
		//TODO REMOVE LATER
		//System.out.println("Found type '" + classTypeObj.getName() + "'");
		if (classTypeObj.getFpPos() == 1) {
			report_error("Instantiating an abstract class '" + classTypeObj.getName() + "'", factorTypeInst);
			factorTypeInst.obj = Tab.noObj;
			return;
		}
		factorTypeInst.obj = classTypeObj;
	}

	@Override
	public void visit(FactorArrayInst factorArrayInst) {
		if (!factorArrayInst.getExpr().obj.getType().equals(Tab.intType)) {
			report_error("Invalid non-int expression during array instantiating", factorArrayInst);
			factorArrayInst.obj = Tab.noObj;
			return;
		}
		if (currentType == Tab.noType)
			return;
		factorArrayInst.obj = new Obj(Obj.Var, "array_wrapper_obj", new Struct(Struct.Array, currentType));
	}

	@Override
	public void visit(FactorExpr factorExpr) {
		factorExpr.obj = factorExpr.getExpr().obj;
	}

	@Override
	public void visit(FirstFactorInList firstFactorInList) {
		firstFactorInList.obj = firstFactorInList.getFactor().obj;
	}

	@Override
	public void visit(Factors factors) {
		if (factors.getFactorList().obj.getType() != Tab.intType || factors.getFactor().obj.getType() != Tab.intType) {
			report_error("Invalid " + currentMulOp.getIdent() + " of non-compatible types", factors);
			factors.obj = Tab.noObj;
			currentMulOp = MULOP.NONE;
			return;
		}
		factors.obj = intObj;
		currentMulOp = MULOP.NONE;
	}

	@Override
	public void visit(Term term) {
		term.obj = term.getFactorList().obj;
	}

	@Override
	public void visit(FirstTermInList firstTermInList) {
		firstTermInList.obj = firstTermInList.getTerm().obj;
	}

	@Override
	public void visit(UMinusFirstTermInList uMinusFirstTermInList) {
		Obj termObj = uMinusFirstTermInList.getTerm().obj;
		if (!termObj.getType().equals(Tab.intType)) {
			report_error("Unary minus used on non-int type term", uMinusFirstTermInList);
			uMinusFirstTermInList.obj = Tab.noObj;
			return;
		}
		uMinusFirstTermInList.obj = termObj;

	}

	@Override
	public void visit(Terms terms) {
		if (terms.getTermList().obj.getType() != Tab.intType || terms.getTerm().obj.getType() != Tab.intType) {
			report_error("Invalid " + currentAddOp.getIdent() + " of non-compatible types", terms);
			terms.obj = Tab.noObj;
			currentAddOp = ADDOP.NONE;
			return;
		}
		terms.obj = intObj;
		currentAddOp = ADDOP.NONE;
	}
	
	
	@Override
	public void visit(TernaryExpr ternaryExpr) {
		//TODO TEST MORE
		if (!ternaryExpr.getExpr().obj.getType().equals(ternaryExpr.getExpr1().obj.getType())) {
			report_error("Ternary expression sub-expression types do not match", ternaryExpr);
			ternaryExpr.obj = Tab.noObj;
			return;
		}
		ternaryExpr.obj = ternaryExpr.getExpr().obj;
	}
	
	
	@Override
	public void visit(SimpleCondFact simpleCondFact) {
		simpleCondFact.obj = simpleCondFact.getTermList().obj;
	}
	
	@Override
	public void visit(RelopCondFact relopCondFact) {
		Obj leftExpr = relopCondFact.getTermList().obj, rightExpr = relopCondFact.getTermList1().obj; 
		if (!leftExpr.getType().compatibleWith(rightExpr.getType())) {
			report_error("Incompatible type comparison", relopCondFact);
			relopCondFact.obj = Tab.noObj;
			currentRelOp = RELOP.NONE;
			return;
		}
		if (leftExpr.getType().isRefType() && rightExpr.getType().isRefType()
				&& currentRelOp != RELOP.EQUAL && currentRelOp != RELOP.NOTEQUAL) {
			report_error("Invalid comparison of references (only == and != relational operators are allowed)", relopCondFact);
			relopCondFact.obj = Tab.noObj;
			currentRelOp = RELOP.NONE;
			return;
		}
		currentRelOp = RELOP.NONE;
		relopCondFact.obj = boolObj;
	}
	
	
	@Override
	public void visit(SimpleCondTerm simpleCondTerm) {
		simpleCondTerm.obj = simpleCondTerm.getCondFact().obj;
	}
	
	@Override
	public void visit(AndCondTerm andCondTerm) {
		if (andCondTerm.getCondFact().obj.getType() != boolType
				|| andCondTerm.getCondTerm().obj.getType() != boolType)
			andCondTerm.obj = Tab.noObj;
		else
			andCondTerm.obj = boolObj;
	}
	
	@Override
	public void visit(SimpleCondition simpleCondition) {
		simpleCondition.obj = simpleCondition.getCondTerm().obj;
	}
	
	@Override
	public void visit(OrCondition orCondition) {
		if (orCondition.getCondition().obj.getType() != boolType
				|| orCondition.getCondTerm().obj.getType() != boolType)
			orCondition.obj = Tab.noObj;
		else
			orCondition.obj = boolObj;
	}
	
	
	private int forLoopDepth = 0;
	private int switchDepth = 0;
	
	@Override
	public void visit(ForCond forCond) {
		if (forCond.getCondition().obj.getType() != boolType) 
			report_error("Invalid condition type in for loop", forCond);
	}
	
	@Override
	public void visit(ForInterrupt forInterrupt) {
		forLoopDepth++;
	}
	
	@Override
	public void visit(ForStatement forStatement) {
		forLoopDepth--;
	}
	
	@Override
	public void visit(IfElseStatement ifElseStatement) {
		if (ifElseStatement.getCondition().obj.getType() != boolType)
			report_error("Invalid if condition type", ifElseStatement);
	}
	
	@Override
	public void visit(IfStatement ifStatement) {
		if (ifStatement.getCondition().obj.getType() != boolType)
			report_error("Invalid if condition type", ifStatement);
	}
	
	@Override
	public void visit(BreakStatement breakStatement) {
		if (forLoopDepth == 0 && switchDepth == 0)
			report_error("Break outside of a for loop or a switch", breakStatement);
	}
	
	@Override
	public void visit(ContinueStatement continueStatement) {
		if (forLoopDepth == 0)
			report_error("Continue outside of a for loop", continueStatement);
	}
	
	@Override
	public void visit(SwitchStatement switchStatement) {
		if (!switchStatement.getExpr().obj.getType().equals(Tab.intType))
			report_error("Invalid switch expression type", switchStatement);
	}
	
	//TODO TEST EVERYTHING BELOW
	@Override
	public void visit(PrintStatement printStatement) {
		Struct exprType = printStatement.getExpr().obj.getType();
		if (!exprType.equals(Tab.intType) 
				&& !exprType.equals(boolType)
				&& !exprType.equals(Tab.charType))
			report_error("Invalid print expression type", printStatement);
	}
	
	
	@Override
	public void visit(NumConstPrintStatement numConstPrintStatement) {
		Struct exprType = numConstPrintStatement.getExpr().obj.getType();
		if (!exprType.equals(Tab.intType) 
				&& !exprType.equals(boolType)
				&& !exprType.equals(Tab.charType))
			report_error("Invalid print expression type", numConstPrintStatement);
	}
	
	@Override
	public void visit(ReadStatement readStatement) {
		Obj designatorObj = readStatement.getDesignator().obj; 
		if (designatorObj.getKind() != Obj.Var 
				&& designatorObj.getKind() != Obj.Fld)
			report_error("Read designator is not an array element, variable or a field", readStatement);
		if (!designatorObj.getType().equals(Tab.intType) 
				&& !designatorObj.getType().equals(boolType)
				&& !designatorObj.getType().equals(Tab.charType))
			report_error("Invalid read designator type", readStatement);
	}
	
	@Override
	public void visit(VoidReturnStatement voidReturnStatement) {
		if (currentMeth == Tab.noObj)
			return;
		if (returnTyped)
			report_error("Return statement of a non-void method does not return anything", voidReturnStatement);
	}
	
	
	@Override
	public void visit(TypedReturnStatement typedReturnStatement) {
		if (currentMeth == Tab.noObj)
			return;
		if (!returnTyped) {
			report_error("Return statement of a void method returns something", typedReturnStatement);
			return;
		}
		if (!currentMeth.getType().equals(typedReturnStatement.getExpr().obj.getType()))
			report_error("Return statement expression type does not match the return type of the method", typedReturnStatement);
	}
	
	
	@Override
	public void visit(DesignatorDec designatorDec) {
		Obj designatorObj = designatorDec.getDesignator().obj;
		if (designatorObj.getKind() != Obj.Var 
				&& designatorObj.getKind() != Obj.Fld)
			report_error("Decrement designator is not an array element, variable or a field", designatorDec);
		if (!designatorObj.getType().equals(Tab.intType))
			report_error("Invalid decrement designator type", designatorDec);
	}
	
	@Override
	public void visit(DesignatorInc designatorInc) {
		Obj designatorObj = designatorInc.getDesignator().obj;
		if (designatorObj.getKind() != Obj.Var 
				&& designatorObj.getKind() != Obj.Fld)
			report_error("Increment designator is not an array element, variable or a field", designatorInc);
		if (!designatorObj.getType().equals(Tab.intType))
			report_error("Invalid increment designator type", designatorInc);
	}
	
	
	
}
