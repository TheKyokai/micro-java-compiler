// generated with ast extension for cup
// version 0.8
// 13/1/2026 20:3:49


package rs.ac.bg.etf.pp1.ast;

public class Designator implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private DesignatorChain DesignatorChain;

    public Designator (DesignatorChain DesignatorChain) {
        this.DesignatorChain=DesignatorChain;
        if(DesignatorChain!=null) DesignatorChain.setParent(this);
    }

    public DesignatorChain getDesignatorChain() {
        return DesignatorChain;
    }

    public void setDesignatorChain(DesignatorChain DesignatorChain) {
        this.DesignatorChain=DesignatorChain;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
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
        buffer.append("Designator(\n");

        if(DesignatorChain!=null)
            buffer.append(DesignatorChain.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Designator]");
        return buffer.toString();
    }
}
