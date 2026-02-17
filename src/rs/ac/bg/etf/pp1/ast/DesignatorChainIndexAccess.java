// generated with ast extension for cup
// version 0.8
// 17/1/2026 12:7:1


package rs.ac.bg.etf.pp1.ast;

public class DesignatorChainIndexAccess extends DesignatorChain {

    private DesignatorChain DesignatorChain;
    private IndexInterrupt IndexInterrupt;
    private Expr Expr;

    public DesignatorChainIndexAccess (DesignatorChain DesignatorChain, IndexInterrupt IndexInterrupt, Expr Expr) {
        this.DesignatorChain=DesignatorChain;
        if(DesignatorChain!=null) DesignatorChain.setParent(this);
        this.IndexInterrupt=IndexInterrupt;
        if(IndexInterrupt!=null) IndexInterrupt.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public DesignatorChain getDesignatorChain() {
        return DesignatorChain;
    }

    public void setDesignatorChain(DesignatorChain DesignatorChain) {
        this.DesignatorChain=DesignatorChain;
    }

    public IndexInterrupt getIndexInterrupt() {
        return IndexInterrupt;
    }

    public void setIndexInterrupt(IndexInterrupt IndexInterrupt) {
        this.IndexInterrupt=IndexInterrupt;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorChain!=null) DesignatorChain.accept(visitor);
        if(IndexInterrupt!=null) IndexInterrupt.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorChain!=null) DesignatorChain.traverseTopDown(visitor);
        if(IndexInterrupt!=null) IndexInterrupt.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorChain!=null) DesignatorChain.traverseBottomUp(visitor);
        if(IndexInterrupt!=null) IndexInterrupt.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorChainIndexAccess(\n");

        if(DesignatorChain!=null)
            buffer.append(DesignatorChain.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IndexInterrupt!=null)
            buffer.append(IndexInterrupt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorChainIndexAccess]");
        return buffer.toString();
    }
}
