// generated with ast extension for cup
// version 0.8
// 17/1/2026 12:7:1


package rs.ac.bg.etf.pp1.ast;

public class GlobalVarDecl extends GlobalDecl {

    private GlVarDecl GlVarDecl;

    public GlobalVarDecl (GlVarDecl GlVarDecl) {
        this.GlVarDecl=GlVarDecl;
        if(GlVarDecl!=null) GlVarDecl.setParent(this);
    }

    public GlVarDecl getGlVarDecl() {
        return GlVarDecl;
    }

    public void setGlVarDecl(GlVarDecl GlVarDecl) {
        this.GlVarDecl=GlVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(GlVarDecl!=null) GlVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(GlVarDecl!=null) GlVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(GlVarDecl!=null) GlVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalVarDecl(\n");

        if(GlVarDecl!=null)
            buffer.append(GlVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalVarDecl]");
        return buffer.toString();
    }
}
