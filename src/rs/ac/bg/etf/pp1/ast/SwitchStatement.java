// generated with ast extension for cup
// version 0.8
// 16/1/2026 2:18:22


package rs.ac.bg.etf.pp1.ast;

public class SwitchStatement extends Statement {

    private Expr Expr;
    private SwitchInterrupt SwitchInterrupt;
    private CaseList CaseList;

    public SwitchStatement (Expr Expr, SwitchInterrupt SwitchInterrupt, CaseList CaseList) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.SwitchInterrupt=SwitchInterrupt;
        if(SwitchInterrupt!=null) SwitchInterrupt.setParent(this);
        this.CaseList=CaseList;
        if(CaseList!=null) CaseList.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public SwitchInterrupt getSwitchInterrupt() {
        return SwitchInterrupt;
    }

    public void setSwitchInterrupt(SwitchInterrupt SwitchInterrupt) {
        this.SwitchInterrupt=SwitchInterrupt;
    }

    public CaseList getCaseList() {
        return CaseList;
    }

    public void setCaseList(CaseList CaseList) {
        this.CaseList=CaseList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(SwitchInterrupt!=null) SwitchInterrupt.accept(visitor);
        if(CaseList!=null) CaseList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(SwitchInterrupt!=null) SwitchInterrupt.traverseTopDown(visitor);
        if(CaseList!=null) CaseList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(SwitchInterrupt!=null) SwitchInterrupt.traverseBottomUp(visitor);
        if(CaseList!=null) CaseList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SwitchStatement(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SwitchInterrupt!=null)
            buffer.append(SwitchInterrupt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CaseList!=null)
            buffer.append(CaseList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SwitchStatement]");
        return buffer.toString();
    }
}
