package rs.ac.bg.etf.pp1.util;

import java.util.stream.Collectors;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.DumpSymbolTableVisitor;

public class ExpandedDumpSymbolTableVisitor extends DumpSymbolTableVisitor {
	

	@Override
	public void visitObjNode(Obj objToVisit) {
		switch (objToVisit.getKind()) {
		case Obj.Con:
			output.append("Con ");
			break;
		case Obj.Var:
			output.append("Var ");
			break;
		case Obj.Type:
			output.append("Type ");
			break;
		case Obj.Meth:
			output.append("Meth ");
			break;
		case Obj.Fld:
			output.append("Fld ");
			break;
		case Obj.Prog:
			output.append("Prog ");
			break;
		}

		output.append(objToVisit.getName());
		output.append(": ");

		
		Obj program = Tab.currentScope.getLocals().symbols().stream().filter(sym -> sym.getKind() == Obj.Prog).collect(Collectors.toList()).get(0);
		if ((Obj.Var == objToVisit.getKind()) && "this".equalsIgnoreCase(objToVisit.getName())) {
			Obj classType = program.getLocalSymbols().stream().filter(sym -> sym.getKind() == Obj.Type && sym.getType() == objToVisit.getType()).collect(Collectors.toList()).get(0);
			output.append(classType.getName());
		}
		else 
			if ((objToVisit.getKind() == Obj.Meth || objToVisit.getKind() == Obj.Var || objToVisit.getKind() == Obj.Fld) && (objToVisit.getType().getKind() == Struct.Class) || objToVisit.getType().getKind() == Struct.Enum) {
				Obj classType = program.getLocalSymbols().stream().filter(sym -> sym.getKind() == Obj.Type && sym.getType() == objToVisit.getType()).collect(Collectors.toList()).get(0);
				output.append(classType.getName());
			} else
				objToVisit.getType().accept(this);

		output.append(", ");
		output.append(objToVisit.getAdr());
		output.append(", ");
		output.append(objToVisit.getLevel());
		output.append(", ");
		output.append(objToVisit.getFpPos() + " ");

		if (objToVisit.getKind() == Obj.Prog || objToVisit.getKind() == Obj.Meth) {
			output.append("\n");
			nextIndentationLevel();
		}

		for (Obj o : objToVisit.getLocalSymbols()) {
			output.append(currentIndent.toString());
			o.accept(this);
			output.append("\n");
		}

		if (objToVisit.getKind() == Obj.Prog || objToVisit.getKind() == Obj.Meth)
			previousIndentationLevel();

	}
	
	@Override
	public void visitStructNode(Struct structToVisit) {
		switch (structToVisit.getKind()) {
		case Struct.None:
			output.append("notype");
			break;
		case Struct.Int:
			output.append("int");
			break;
		case Struct.Char:
			output.append("char");
			break;
		case Struct.Bool:
			output.append("bool");
			break;
		case Struct.Array:
			output.append("Arr of ");
			
			switch (structToVisit.getElemType().getKind()) {
			case Struct.None:
				output.append("notype");
				break;
			case Struct.Int:
				output.append("int");
				break;
			case Struct.Char:
				output.append("char");
				break;
			case Struct.Bool:
				output.append("bool");
				break;
			case Struct.Class:
				Obj program = Tab.currentScope.getLocals().symbols().stream().filter(sym -> sym.getKind() == Obj.Prog).collect(Collectors.toList()).get(0);
				Obj classType = program.getLocalSymbols().stream().filter(sym -> sym.getKind() == Obj.Type && sym.getType() == structToVisit.getElemType()).collect(Collectors.toList()).get(0);
				output.append(classType.getName());
				break;
			}
			break;
		case Struct.Class:
			output.append("Class [");
			for (Obj obj : structToVisit.getMembers()) {
				obj.accept(this);
			}
			output.append("]");
			break;
		case Struct.Enum:
			output.append("Enum [");
			for (Obj obj : structToVisit.getMembers()) {
				obj.accept(this);
			}
			output.append("]");
			break;
		}
	}
	
}
