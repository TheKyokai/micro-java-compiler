// generated with ast extension for cup
// version 0.8
// 11/1/2026 18:37:44


package rs.ac.bg.etf.pp1.ast;

public class ForStatement extends Statement {

    private ForDesignatorStmnt ForDesignatorStmnt;
    private ForCondition ForCondition;
    private ForDesignatorStmnt ForDesignatorStmnt1;
    private Statement Statement;

    public ForStatement (ForDesignatorStmnt ForDesignatorStmnt, ForCondition ForCondition, ForDesignatorStmnt ForDesignatorStmnt1, Statement Statement) {
        this.ForDesignatorStmnt=ForDesignatorStmnt;
        if(ForDesignatorStmnt!=null) ForDesignatorStmnt.setParent(this);
        this.ForCondition=ForCondition;
        if(ForCondition!=null) ForCondition.setParent(this);
        this.ForDesignatorStmnt1=ForDesignatorStmnt1;
        if(ForDesignatorStmnt1!=null) ForDesignatorStmnt1.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public ForDesignatorStmnt getForDesignatorStmnt() {
        return ForDesignatorStmnt;
    }

    public void setForDesignatorStmnt(ForDesignatorStmnt ForDesignatorStmnt) {
        this.ForDesignatorStmnt=ForDesignatorStmnt;
    }

    public ForCondition getForCondition() {
        return ForCondition;
    }

    public void setForCondition(ForCondition ForCondition) {
        this.ForCondition=ForCondition;
    }

    public ForDesignatorStmnt getForDesignatorStmnt1() {
        return ForDesignatorStmnt1;
    }

    public void setForDesignatorStmnt1(ForDesignatorStmnt ForDesignatorStmnt1) {
        this.ForDesignatorStmnt1=ForDesignatorStmnt1;
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
        if(ForCondition!=null) ForCondition.accept(visitor);
        if(ForDesignatorStmnt1!=null) ForDesignatorStmnt1.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForDesignatorStmnt!=null) ForDesignatorStmnt.traverseTopDown(visitor);
        if(ForCondition!=null) ForCondition.traverseTopDown(visitor);
        if(ForDesignatorStmnt1!=null) ForDesignatorStmnt1.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForDesignatorStmnt!=null) ForDesignatorStmnt.traverseBottomUp(visitor);
        if(ForCondition!=null) ForCondition.traverseBottomUp(visitor);
        if(ForDesignatorStmnt1!=null) ForDesignatorStmnt1.traverseBottomUp(visitor);
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

        if(ForCondition!=null)
            buffer.append(ForCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForDesignatorStmnt1!=null)
            buffer.append(ForDesignatorStmnt1.toString("  "+tab));
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
