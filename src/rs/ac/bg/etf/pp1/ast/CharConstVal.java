// generated with ast extension for cup
// version 0.8
// 13/1/2026 20:3:49


package rs.ac.bg.etf.pp1.ast;

public class CharConstVal extends ConstVal {

    private Character val;

    public CharConstVal (Character val) {
        this.val=val;
    }

    public Character getVal() {
        return val;
    }

    public void setVal(Character val) {
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
        buffer.append("CharConstVal(\n");

        buffer.append(" "+tab+val);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CharConstVal]");
        return buffer.toString();
    }
}
