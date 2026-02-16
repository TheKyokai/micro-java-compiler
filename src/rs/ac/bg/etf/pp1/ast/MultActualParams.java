// generated with ast extension for cup
// version 0.8
// 16/1/2026 17:31:48


package rs.ac.bg.etf.pp1.ast;

public class MultActualParams extends MultActualParam {

    private MultActualParam MultActualParam;
    private ActualParam ActualParam;

    public MultActualParams (MultActualParam MultActualParam, ActualParam ActualParam) {
        this.MultActualParam=MultActualParam;
        if(MultActualParam!=null) MultActualParam.setParent(this);
        this.ActualParam=ActualParam;
        if(ActualParam!=null) ActualParam.setParent(this);
    }

    public MultActualParam getMultActualParam() {
        return MultActualParam;
    }

    public void setMultActualParam(MultActualParam MultActualParam) {
        this.MultActualParam=MultActualParam;
    }

    public ActualParam getActualParam() {
        return ActualParam;
    }

    public void setActualParam(ActualParam ActualParam) {
        this.ActualParam=ActualParam;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MultActualParam!=null) MultActualParam.accept(visitor);
        if(ActualParam!=null) ActualParam.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MultActualParam!=null) MultActualParam.traverseTopDown(visitor);
        if(ActualParam!=null) ActualParam.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MultActualParam!=null) MultActualParam.traverseBottomUp(visitor);
        if(ActualParam!=null) ActualParam.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultActualParams(\n");

        if(MultActualParam!=null)
            buffer.append(MultActualParam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActualParam!=null)
            buffer.append(ActualParam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultActualParams]");
        return buffer.toString();
    }
}
