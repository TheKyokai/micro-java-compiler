// generated with ast extension for cup
// version 0.8
// 17/1/2026 12:7:1


package rs.ac.bg.etf.pp1.ast;

public class AbsClassMethodBlock extends AbsClassMethods {

    private ExtMethodIntercept ExtMethodIntercept;
    private AbsClassMethodList AbsClassMethodList;

    public AbsClassMethodBlock (ExtMethodIntercept ExtMethodIntercept, AbsClassMethodList AbsClassMethodList) {
        this.ExtMethodIntercept=ExtMethodIntercept;
        if(ExtMethodIntercept!=null) ExtMethodIntercept.setParent(this);
        this.AbsClassMethodList=AbsClassMethodList;
        if(AbsClassMethodList!=null) AbsClassMethodList.setParent(this);
    }

    public ExtMethodIntercept getExtMethodIntercept() {
        return ExtMethodIntercept;
    }

    public void setExtMethodIntercept(ExtMethodIntercept ExtMethodIntercept) {
        this.ExtMethodIntercept=ExtMethodIntercept;
    }

    public AbsClassMethodList getAbsClassMethodList() {
        return AbsClassMethodList;
    }

    public void setAbsClassMethodList(AbsClassMethodList AbsClassMethodList) {
        this.AbsClassMethodList=AbsClassMethodList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExtMethodIntercept!=null) ExtMethodIntercept.accept(visitor);
        if(AbsClassMethodList!=null) AbsClassMethodList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExtMethodIntercept!=null) ExtMethodIntercept.traverseTopDown(visitor);
        if(AbsClassMethodList!=null) AbsClassMethodList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExtMethodIntercept!=null) ExtMethodIntercept.traverseBottomUp(visitor);
        if(AbsClassMethodList!=null) AbsClassMethodList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AbsClassMethodBlock(\n");

        if(ExtMethodIntercept!=null)
            buffer.append(ExtMethodIntercept.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AbsClassMethodList!=null)
            buffer.append(AbsClassMethodList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AbsClassMethodBlock]");
        return buffer.toString();
    }
}
