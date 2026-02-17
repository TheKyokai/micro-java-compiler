// generated with ast extension for cup
// version 0.8
// 16/1/2026 23:34:31


package rs.ac.bg.etf.pp1.ast;

public class DesignatorChainFieldAccess extends DesignatorChain {

    private DesignatorChain DesignatorChain;
    private String fieldName;

    public DesignatorChainFieldAccess (DesignatorChain DesignatorChain, String fieldName) {
        this.DesignatorChain=DesignatorChain;
        if(DesignatorChain!=null) DesignatorChain.setParent(this);
        this.fieldName=fieldName;
    }

    public DesignatorChain getDesignatorChain() {
        return DesignatorChain;
    }

    public void setDesignatorChain(DesignatorChain DesignatorChain) {
        this.DesignatorChain=DesignatorChain;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName=fieldName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorChain!=null) DesignatorChain.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorChain!=null) DesignatorChain.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorChain!=null) DesignatorChain.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorChainFieldAccess(\n");

        if(DesignatorChain!=null)
            buffer.append(DesignatorChain.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+fieldName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorChainFieldAccess]");
        return buffer.toString();
    }
}
