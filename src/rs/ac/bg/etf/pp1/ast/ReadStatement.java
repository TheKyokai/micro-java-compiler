// generated with ast extension for cup
// version 0.8
// 17/1/2026 16:31:33


package rs.ac.bg.etf.pp1.ast;

public class ReadStatement extends Statement {

    private DesignatorDst DesignatorDst;

    public ReadStatement (DesignatorDst DesignatorDst) {
        this.DesignatorDst=DesignatorDst;
        if(DesignatorDst!=null) DesignatorDst.setParent(this);
    }

    public DesignatorDst getDesignatorDst() {
        return DesignatorDst;
    }

    public void setDesignatorDst(DesignatorDst DesignatorDst) {
        this.DesignatorDst=DesignatorDst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorDst!=null) DesignatorDst.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorDst!=null) DesignatorDst.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorDst!=null) DesignatorDst.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ReadStatement(\n");

        if(DesignatorDst!=null)
            buffer.append(DesignatorDst.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ReadStatement]");
        return buffer.toString();
    }
}
