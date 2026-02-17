// generated with ast extension for cup
// version 0.8
// 17/1/2026 12:7:1


package rs.ac.bg.etf.pp1.ast;

public class TernaryExpr extends Expr {

    private Condition Condition;
    private TernaryInterrupt TernaryInterrupt;
    private Expr Expr;
    private TernaryElse TernaryElse;
    private Expr Expr1;

    public TernaryExpr (Condition Condition, TernaryInterrupt TernaryInterrupt, Expr Expr, TernaryElse TernaryElse, Expr Expr1) {
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.TernaryInterrupt=TernaryInterrupt;
        if(TernaryInterrupt!=null) TernaryInterrupt.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.TernaryElse=TernaryElse;
        if(TernaryElse!=null) TernaryElse.setParent(this);
        this.Expr1=Expr1;
        if(Expr1!=null) Expr1.setParent(this);
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public TernaryInterrupt getTernaryInterrupt() {
        return TernaryInterrupt;
    }

    public void setTernaryInterrupt(TernaryInterrupt TernaryInterrupt) {
        this.TernaryInterrupt=TernaryInterrupt;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public TernaryElse getTernaryElse() {
        return TernaryElse;
    }

    public void setTernaryElse(TernaryElse TernaryElse) {
        this.TernaryElse=TernaryElse;
    }

    public Expr getExpr1() {
        return Expr1;
    }

    public void setExpr1(Expr Expr1) {
        this.Expr1=Expr1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Condition!=null) Condition.accept(visitor);
        if(TernaryInterrupt!=null) TernaryInterrupt.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(TernaryElse!=null) TernaryElse.accept(visitor);
        if(Expr1!=null) Expr1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(TernaryInterrupt!=null) TernaryInterrupt.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(TernaryElse!=null) TernaryElse.traverseTopDown(visitor);
        if(Expr1!=null) Expr1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(TernaryInterrupt!=null) TernaryInterrupt.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(TernaryElse!=null) TernaryElse.traverseBottomUp(visitor);
        if(Expr1!=null) Expr1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TernaryExpr(\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TernaryInterrupt!=null)
            buffer.append(TernaryInterrupt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TernaryElse!=null)
            buffer.append(TernaryElse.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr1!=null)
            buffer.append(Expr1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TernaryExpr]");
        return buffer.toString();
    }
}
