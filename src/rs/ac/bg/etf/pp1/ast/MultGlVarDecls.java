// generated with ast extension for cup
// version 0.8
// 17/1/2026 16:31:33


package rs.ac.bg.etf.pp1.ast;

public class MultGlVarDecls extends MultGlVarDecl {

    private MultGlVarDecl MultGlVarDecl;
    private SingleGlVarDecl SingleGlVarDecl;

    public MultGlVarDecls (MultGlVarDecl MultGlVarDecl, SingleGlVarDecl SingleGlVarDecl) {
        this.MultGlVarDecl=MultGlVarDecl;
        if(MultGlVarDecl!=null) MultGlVarDecl.setParent(this);
        this.SingleGlVarDecl=SingleGlVarDecl;
        if(SingleGlVarDecl!=null) SingleGlVarDecl.setParent(this);
    }

    public MultGlVarDecl getMultGlVarDecl() {
        return MultGlVarDecl;
    }

    public void setMultGlVarDecl(MultGlVarDecl MultGlVarDecl) {
        this.MultGlVarDecl=MultGlVarDecl;
    }

    public SingleGlVarDecl getSingleGlVarDecl() {
        return SingleGlVarDecl;
    }

    public void setSingleGlVarDecl(SingleGlVarDecl SingleGlVarDecl) {
        this.SingleGlVarDecl=SingleGlVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MultGlVarDecl!=null) MultGlVarDecl.accept(visitor);
        if(SingleGlVarDecl!=null) SingleGlVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MultGlVarDecl!=null) MultGlVarDecl.traverseTopDown(visitor);
        if(SingleGlVarDecl!=null) SingleGlVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MultGlVarDecl!=null) MultGlVarDecl.traverseBottomUp(visitor);
        if(SingleGlVarDecl!=null) SingleGlVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultGlVarDecls(\n");

        if(MultGlVarDecl!=null)
            buffer.append(MultGlVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SingleGlVarDecl!=null)
            buffer.append(SingleGlVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultGlVarDecls]");
        return buffer.toString();
    }
}
