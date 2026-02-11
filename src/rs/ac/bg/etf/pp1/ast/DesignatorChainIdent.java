// generated with ast extension for cup
// version 0.8
// 11/1/2026 18:37:44


package rs.ac.bg.etf.pp1.ast;

public class DesignatorChainIdent extends DesignatorChain {

    private String objName;

    public DesignatorChainIdent (String objName) {
        this.objName=objName;
    }

    public String getObjName() {
        return objName;
    }

    public void setObjName(String objName) {
        this.objName=objName;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorChainIdent(\n");

        buffer.append(" "+tab+objName);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorChainIdent]");
        return buffer.toString();
    }
}
