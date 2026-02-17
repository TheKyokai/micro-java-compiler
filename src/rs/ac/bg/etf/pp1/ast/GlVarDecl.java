// generated with ast extension for cup
// version 0.8
// 17/1/2026 16:31:33


package rs.ac.bg.etf.pp1.ast;

public class GlVarDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private MultGlVarDecl MultGlVarDecl;

    public GlVarDecl (Type Type, MultGlVarDecl MultGlVarDecl) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.MultGlVarDecl=MultGlVarDecl;
        if(MultGlVarDecl!=null) MultGlVarDecl.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public MultGlVarDecl getMultGlVarDecl() {
        return MultGlVarDecl;
    }

    public void setMultGlVarDecl(MultGlVarDecl MultGlVarDecl) {
        this.MultGlVarDecl=MultGlVarDecl;
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
        if(Type!=null) Type.accept(visitor);
        if(MultGlVarDecl!=null) MultGlVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(MultGlVarDecl!=null) MultGlVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(MultGlVarDecl!=null) MultGlVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlVarDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MultGlVarDecl!=null)
            buffer.append(MultGlVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlVarDecl]");
        return buffer.toString();
    }
}
