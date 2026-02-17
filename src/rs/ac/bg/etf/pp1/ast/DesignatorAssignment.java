// generated with ast extension for cup
// version 0.8
// 16/1/2026 23:34:31


package rs.ac.bg.etf.pp1.ast;

public class DesignatorAssignment extends DesignatorStatement {

    private DesignatorDst DesignatorDst;
    private Assignop Assignop;
    private Expr Expr;

    public DesignatorAssignment (DesignatorDst DesignatorDst, Assignop Assignop, Expr Expr) {
        this.DesignatorDst=DesignatorDst;
        if(DesignatorDst!=null) DesignatorDst.setParent(this);
        this.Assignop=Assignop;
        if(Assignop!=null) Assignop.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public DesignatorDst getDesignatorDst() {
        return DesignatorDst;
    }

    public void setDesignatorDst(DesignatorDst DesignatorDst) {
        this.DesignatorDst=DesignatorDst;
    }

    public Assignop getAssignop() {
        return Assignop;
    }

    public void setAssignop(Assignop Assignop) {
        this.Assignop=Assignop;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorDst!=null) DesignatorDst.accept(visitor);
        if(Assignop!=null) Assignop.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorDst!=null) DesignatorDst.traverseTopDown(visitor);
        if(Assignop!=null) Assignop.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorDst!=null) DesignatorDst.traverseBottomUp(visitor);
        if(Assignop!=null) Assignop.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorAssignment(\n");

        if(DesignatorDst!=null)
            buffer.append(DesignatorDst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Assignop!=null)
            buffer.append(Assignop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorAssignment]");
        return buffer.toString();
    }
}
