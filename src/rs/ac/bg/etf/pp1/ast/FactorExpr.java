// generated with ast extension for cup
// version 0.8
// 17/1/2026 16:31:33


package rs.ac.bg.etf.pp1.ast;

public class FactorExpr extends Factor {

    private FactorExprStartIntr FactorExprStartIntr;
    private Expr Expr;
    private FactorExprEndIntr FactorExprEndIntr;

    public FactorExpr (FactorExprStartIntr FactorExprStartIntr, Expr Expr, FactorExprEndIntr FactorExprEndIntr) {
        this.FactorExprStartIntr=FactorExprStartIntr;
        if(FactorExprStartIntr!=null) FactorExprStartIntr.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.FactorExprEndIntr=FactorExprEndIntr;
        if(FactorExprEndIntr!=null) FactorExprEndIntr.setParent(this);
    }

    public FactorExprStartIntr getFactorExprStartIntr() {
        return FactorExprStartIntr;
    }

    public void setFactorExprStartIntr(FactorExprStartIntr FactorExprStartIntr) {
        this.FactorExprStartIntr=FactorExprStartIntr;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public FactorExprEndIntr getFactorExprEndIntr() {
        return FactorExprEndIntr;
    }

    public void setFactorExprEndIntr(FactorExprEndIntr FactorExprEndIntr) {
        this.FactorExprEndIntr=FactorExprEndIntr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FactorExprStartIntr!=null) FactorExprStartIntr.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(FactorExprEndIntr!=null) FactorExprEndIntr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FactorExprStartIntr!=null) FactorExprStartIntr.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(FactorExprEndIntr!=null) FactorExprEndIntr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FactorExprStartIntr!=null) FactorExprStartIntr.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(FactorExprEndIntr!=null) FactorExprEndIntr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorExpr(\n");

        if(FactorExprStartIntr!=null)
            buffer.append(FactorExprStartIntr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FactorExprEndIntr!=null)
            buffer.append(FactorExprEndIntr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorExpr]");
        return buffer.toString();
    }
}
