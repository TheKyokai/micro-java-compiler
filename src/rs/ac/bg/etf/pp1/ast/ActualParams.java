// generated with ast extension for cup
// version 0.8
// 16/1/2026 2:18:22


package rs.ac.bg.etf.pp1.ast;

public class ActualParams extends ActualParamList {

    private MultActualParam MultActualParam;

    public ActualParams (MultActualParam MultActualParam) {
        this.MultActualParam=MultActualParam;
        if(MultActualParam!=null) MultActualParam.setParent(this);
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
        if(MultActualParam!=null) MultActualParam.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MultActualParam!=null) MultActualParam.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MultActualParam!=null) MultActualParam.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ActualParams(\n");

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
