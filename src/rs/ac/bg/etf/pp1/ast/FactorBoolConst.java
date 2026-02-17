// generated with ast extension for cup
// version 0.8
// 17/1/2026 16:31:33


package rs.ac.bg.etf.pp1.ast;

public class FactorBoolConst extends Factor {

    private Boolean val;

    public FactorBoolConst (Boolean val) {
        this.val=val;
    }

    public Boolean getVal() {
        return val;
    }

    public void setVal(Boolean val) {
        this.val=val;
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
        buffer.append("FactorBoolConst(\n");

        buffer.append(" "+tab+val);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorBoolConst]");
        return buffer.toString();
    }
}
