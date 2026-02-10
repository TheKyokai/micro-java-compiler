// generated with ast extension for cup
// version 0.8
// 9/1/2026 12:14:35


package rs.ac.bg.etf.pp1.ast;

public class ExtMethodEndIntercept extends ExtMethodEIntercept {

    public ExtMethodEndIntercept () {
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
        buffer.append("ExtMethodEndIntercept(\n");

        buffer.append(tab);
        buffer.append(") [ExtMethodEndIntercept]");
        return buffer.toString();
    }
}
