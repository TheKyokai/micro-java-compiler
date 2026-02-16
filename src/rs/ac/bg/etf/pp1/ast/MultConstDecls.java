// generated with ast extension for cup
// version 0.8
// 16/1/2026 17:31:48


package rs.ac.bg.etf.pp1.ast;

public class MultConstDecls extends MultConstDecl {

    private MultConstDecl MultConstDecl;
    private SingleConstDecl SingleConstDecl;

    public MultConstDecls (MultConstDecl MultConstDecl, SingleConstDecl SingleConstDecl) {
        this.MultConstDecl=MultConstDecl;
        if(MultConstDecl!=null) MultConstDecl.setParent(this);
        this.SingleConstDecl=SingleConstDecl;
        if(SingleConstDecl!=null) SingleConstDecl.setParent(this);
    }

    public MultConstDecl getMultConstDecl() {
        return MultConstDecl;
    }

    public void setMultConstDecl(MultConstDecl MultConstDecl) {
        this.MultConstDecl=MultConstDecl;
    }

    public SingleConstDecl getSingleConstDecl() {
        return SingleConstDecl;
    }

    public void setSingleConstDecl(SingleConstDecl SingleConstDecl) {
        this.SingleConstDecl=SingleConstDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MultConstDecl!=null) MultConstDecl.accept(visitor);
        if(SingleConstDecl!=null) SingleConstDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MultConstDecl!=null) MultConstDecl.traverseTopDown(visitor);
        if(SingleConstDecl!=null) SingleConstDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MultConstDecl!=null) MultConstDecl.traverseBottomUp(visitor);
        if(SingleConstDecl!=null) SingleConstDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultConstDecls(\n");

        if(MultConstDecl!=null)
            buffer.append(MultConstDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SingleConstDecl!=null)
            buffer.append(SingleConstDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultConstDecls]");
        return buffer.toString();
    }
}
