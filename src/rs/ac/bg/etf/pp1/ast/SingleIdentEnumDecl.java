// generated with ast extension for cup
// version 0.8
// 13/1/2026 20:3:49


package rs.ac.bg.etf.pp1.ast;

public class SingleIdentEnumDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String innerEnumName;
    private NumConstAssign NumConstAssign;

    public SingleIdentEnumDecl (String innerEnumName, NumConstAssign NumConstAssign) {
        this.innerEnumName=innerEnumName;
        this.NumConstAssign=NumConstAssign;
        if(NumConstAssign!=null) NumConstAssign.setParent(this);
    }

    public String getInnerEnumName() {
        return innerEnumName;
    }

    public void setInnerEnumName(String innerEnumName) {
        this.innerEnumName=innerEnumName;
    }

    public NumConstAssign getNumConstAssign() {
        return NumConstAssign;
    }

    public void setNumConstAssign(NumConstAssign NumConstAssign) {
        this.NumConstAssign=NumConstAssign;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(NumConstAssign!=null) NumConstAssign.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NumConstAssign!=null) NumConstAssign.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NumConstAssign!=null) NumConstAssign.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleIdentEnumDecl(\n");

        buffer.append(" "+tab+innerEnumName);
        buffer.append("\n");

        if(NumConstAssign!=null)
            buffer.append(NumConstAssign.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleIdentEnumDecl]");
        return buffer.toString();
    }
}
