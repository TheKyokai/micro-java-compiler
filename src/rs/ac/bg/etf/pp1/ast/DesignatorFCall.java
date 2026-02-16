// generated with ast extension for cup
// version 0.8
// 16/1/2026 17:31:48


package rs.ac.bg.etf.pp1.ast;

public class DesignatorFCall extends DesignatorStatement {

    private Designator Designator;
    private FCallInterrupt FCallInterrupt;
    private ActualParamList ActualParamList;

    public DesignatorFCall (Designator Designator, FCallInterrupt FCallInterrupt, ActualParamList ActualParamList) {
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.FCallInterrupt=FCallInterrupt;
        if(FCallInterrupt!=null) FCallInterrupt.setParent(this);
        this.ActualParamList=ActualParamList;
        if(ActualParamList!=null) ActualParamList.setParent(this);
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public FCallInterrupt getFCallInterrupt() {
        return FCallInterrupt;
    }

    public void setFCallInterrupt(FCallInterrupt FCallInterrupt) {
        this.FCallInterrupt=FCallInterrupt;
    }

    public ActualParamList getActualParamList() {
        return ActualParamList;
    }

    public void setActualParamList(ActualParamList ActualParamList) {
        this.ActualParamList=ActualParamList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Designator!=null) Designator.accept(visitor);
        if(FCallInterrupt!=null) FCallInterrupt.accept(visitor);
        if(ActualParamList!=null) ActualParamList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(FCallInterrupt!=null) FCallInterrupt.traverseTopDown(visitor);
        if(ActualParamList!=null) ActualParamList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(FCallInterrupt!=null) FCallInterrupt.traverseBottomUp(visitor);
        if(ActualParamList!=null) ActualParamList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorFCall(\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FCallInterrupt!=null)
            buffer.append(FCallInterrupt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActualParamList!=null)
            buffer.append(ActualParamList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorFCall]");
        return buffer.toString();
    }
}
