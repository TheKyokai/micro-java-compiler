// generated with ast extension for cup
// version 0.8
// 13/1/2026 0:38:39


package rs.ac.bg.etf.pp1.ast;

public class MultVarDecls extends MultVarDecl {

    private MultVarDecl MultVarDecl;
    private SingleVarDecl SingleVarDecl;

    public MultVarDecls (MultVarDecl MultVarDecl, SingleVarDecl SingleVarDecl) {
        this.MultVarDecl=MultVarDecl;
        if(MultVarDecl!=null) MultVarDecl.setParent(this);
        this.SingleVarDecl=SingleVarDecl;
        if(SingleVarDecl!=null) SingleVarDecl.setParent(this);
    }

    public MultVarDecl getMultVarDecl() {
        return MultVarDecl;
    }

    public void setMultVarDecl(MultVarDecl MultVarDecl) {
        this.MultVarDecl=MultVarDecl;
    }

    public SingleVarDecl getSingleVarDecl() {
        return SingleVarDecl;
    }

    public void setSingleVarDecl(SingleVarDecl SingleVarDecl) {
        this.SingleVarDecl=SingleVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MultVarDecl!=null) MultVarDecl.accept(visitor);
        if(SingleVarDecl!=null) SingleVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MultVarDecl!=null) MultVarDecl.traverseTopDown(visitor);
        if(SingleVarDecl!=null) SingleVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MultVarDecl!=null) MultVarDecl.traverseBottomUp(visitor);
        if(SingleVarDecl!=null) SingleVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultVarDecls(\n");

        if(MultVarDecl!=null)
            buffer.append(MultVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SingleVarDecl!=null)
            buffer.append(SingleVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultVarDecls]");
        return buffer.toString();
    }
}
