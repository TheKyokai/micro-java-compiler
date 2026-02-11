// generated with ast extension for cup
// version 0.8
// 11/1/2026 18:37:44


package rs.ac.bg.etf.pp1.ast;

public class ActualParams extends ActualParamList {

    private ActualParam ActualParam;
    private MultActualParam MultActualParam;

    public ActualParams (ActualParam ActualParam, MultActualParam MultActualParam) {
        this.ActualParam=ActualParam;
        if(ActualParam!=null) ActualParam.setParent(this);
        this.MultActualParam=MultActualParam;
        if(MultActualParam!=null) MultActualParam.setParent(this);
    }

    public ActualParam getActualParam() {
        return ActualParam;
    }

    public void setActualParam(ActualParam ActualParam) {
        this.ActualParam=ActualParam;
    }

    public MultActualParam getMultActualParam() {
        return MultActualParam;
    }

    public void setMultActualParam(MultActualParam MultActualParam) {
        this.MultActualParam=MultActualParam;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ActualParam!=null) ActualParam.accept(visitor);
        if(MultActualParam!=null) MultActualParam.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ActualParam!=null) ActualParam.traverseTopDown(visitor);
        if(MultActualParam!=null) MultActualParam.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ActualParam!=null) ActualParam.traverseBottomUp(visitor);
        if(MultActualParam!=null) MultActualParam.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActualParams(\n");

        if(ActualParam!=null)
            buffer.append(ActualParam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MultActualParam!=null)
            buffer.append(MultActualParam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ActualParams]");
        return buffer.toString();
    }
}
