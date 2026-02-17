// generated with ast extension for cup
// version 0.8
// 17/1/2026 12:7:1


package rs.ac.bg.etf.pp1.ast;

public class FirstGlVarDecl extends MultGlVarDecl {

    private SingleGlVarDecl SingleGlVarDecl;

    public FirstGlVarDecl (SingleGlVarDecl SingleGlVarDecl) {
        this.SingleGlVarDecl=SingleGlVarDecl;
        if(SingleGlVarDecl!=null) SingleGlVarDecl.setParent(this);
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
        if(SingleGlVarDecl!=null) SingleGlVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SingleGlVarDecl!=null) SingleGlVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SingleGlVarDecl!=null) SingleGlVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FirstGlVarDecl(\n");

        if(SingleGlVarDecl!=null)
            buffer.append(SingleGlVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FirstGlVarDecl]");
        return buffer.toString();
    }
}
