// generated with ast extension for cup
// version 0.8
// 17/1/2026 16:31:33


package rs.ac.bg.etf.pp1.ast;

public class ForStatement extends Statement {

    private ForDesignatorStmnt ForDesignatorStmnt;
    private ForBeforeCond ForBeforeCond;
    private ForCondition ForCondition;
    private ForAfterCond ForAfterCond;
    private ForDesignatorStmnt ForDesignatorStmnt1;
    private ForInterrupt ForInterrupt;
    private Statement Statement;

    public ForStatement (ForDesignatorStmnt ForDesignatorStmnt, ForBeforeCond ForBeforeCond, ForCondition ForCondition, ForAfterCond ForAfterCond, ForDesignatorStmnt ForDesignatorStmnt1, ForInterrupt ForInterrupt, Statement Statement) {
        this.ForDesignatorStmnt=ForDesignatorStmnt;
        if(ForDesignatorStmnt!=null) ForDesignatorStmnt.setParent(this);
        this.ForBeforeCond=ForBeforeCond;
        if(ForBeforeCond!=null) ForBeforeCond.setParent(this);
        this.ForCondition=ForCondition;
        if(ForCondition!=null) ForCondition.setParent(this);
        this.ForAfterCond=ForAfterCond;
        if(ForAfterCond!=null) ForAfterCond.setParent(this);
        this.ForDesignatorStmnt1=ForDesignatorStmnt1;
        if(ForDesignatorStmnt1!=null) ForDesignatorStmnt1.setParent(this);
        this.ForInterrupt=ForInterrupt;
        if(ForInterrupt!=null) ForInterrupt.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public ForDesignatorStmnt getForDesignatorStmnt() {
        return ForDesignatorStmnt;
    }

    public void setForDesignatorStmnt(ForDesignatorStmnt ForDesignatorStmnt) {
        this.ForDesignatorStmnt=ForDesignatorStmnt;
    }

    public ForBeforeCond getForBeforeCond() {
        return ForBeforeCond;
    }

    public void setForBeforeCond(ForBeforeCond ForBeforeCond) {
        this.ForBeforeCond=ForBeforeCond;
    }

    public ForCondition getForCondition() {
        return ForCondition;
    }

    public void setForCondition(ForCondition ForCondition) {
        this.ForCondition=ForCondition;
    }

    public ForAfterCond getForAfterCond() {
        return ForAfterCond;
    }

    public void setForAfterCond(ForAfterCond ForAfterCond) {
        this.ForAfterCond=ForAfterCond;
    }

    public ForDesignatorStmnt getForDesignatorStmnt1() {
        return ForDesignatorStmnt1;
    }

    public void setForDesignatorStmnt1(ForDesignatorStmnt ForDesignatorStmnt1) {
        this.ForDesignatorStmnt1=ForDesignatorStmnt1;
    }

    public ForInterrupt getForInterrupt() {
        return ForInterrupt;
    }

    public void setForInterrupt(ForInterrupt ForInterrupt) {
        this.ForInterrupt=ForInterrupt;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ForDesignatorStmnt!=null) ForDesignatorStmnt.accept(visitor);
        if(ForBeforeCond!=null) ForBeforeCond.accept(visitor);
        if(ForCondition!=null) ForCondition.accept(visitor);
        if(ForAfterCond!=null) ForAfterCond.accept(visitor);
        if(ForDesignatorStmnt1!=null) ForDesignatorStmnt1.accept(visitor);
        if(ForInterrupt!=null) ForInterrupt.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForDesignatorStmnt!=null) ForDesignatorStmnt.traverseTopDown(visitor);
        if(ForBeforeCond!=null) ForBeforeCond.traverseTopDown(visitor);
        if(ForCondition!=null) ForCondition.traverseTopDown(visitor);
        if(ForAfterCond!=null) ForAfterCond.traverseTopDown(visitor);
        if(ForDesignatorStmnt1!=null) ForDesignatorStmnt1.traverseTopDown(visitor);
        if(ForInterrupt!=null) ForInterrupt.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForDesignatorStmnt!=null) ForDesignatorStmnt.traverseBottomUp(visitor);
        if(ForBeforeCond!=null) ForBeforeCond.traverseBottomUp(visitor);
        if(ForCondition!=null) ForCondition.traverseBottomUp(visitor);
        if(ForAfterCond!=null) ForAfterCond.traverseBottomUp(visitor);
        if(ForDesignatorStmnt1!=null) ForDesignatorStmnt1.traverseBottomUp(visitor);
        if(ForInterrupt!=null) ForInterrupt.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForStatement(\n");

        if(ForDesignatorStmnt!=null)
            buffer.append(ForDesignatorStmnt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForBeforeCond!=null)
            buffer.append(ForBeforeCond.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForCondition!=null)
            buffer.append(ForCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForAfterCond!=null)
            buffer.append(ForAfterCond.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForDesignatorStmnt1!=null)
            buffer.append(ForDesignatorStmnt1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForInterrupt!=null)
            buffer.append(ForInterrupt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForStatement]");
        return buffer.toString();
    }
}
