// generated with ast extension for cup
// version 0.8
// 11/1/2026 18:37:44


package rs.ac.bg.etf.pp1.ast;

public class CExtensionError extends ClassExtension {

    public CExtensionError () {
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
        buffer.append("CExtensionError(\n");

        buffer.append(tab);
        buffer.append(") [CExtensionError]");
        return buffer.toString();
    }
}
