// generated with ast extension for cup
// version 0.8
// 17/1/2026 12:7:1


package rs.ac.bg.etf.pp1.ast;

public class IfElseStatement extends Statement {

    private IfBeforeCond IfBeforeCond;
    private Condition Condition;
    private IfAfterCond IfAfterCond;
    private Statement Statement;
    private ElseInterrupt ElseInterrupt;
    private Statement Statement1;

    public IfElseStatement (IfBeforeCond IfBeforeCond, Condition Condition, IfAfterCond IfAfterCond, Statement Statement, ElseInterrupt ElseInterrupt, Statement Statement1) {
        this.IfBeforeCond=IfBeforeCond;
        if(IfBeforeCond!=null) IfBeforeCond.setParent(this);
        this.Condition=Condition;
        if(Condition!=null) Condition.setParent(this);
        this.IfAfterCond=IfAfterCond;
        if(IfAfterCond!=null) IfAfterCond.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.ElseInterrupt=ElseInterrupt;
        if(ElseInterrupt!=null) ElseInterrupt.setParent(this);
        this.Statement1=Statement1;
        if(Statement1!=null) Statement1.setParent(this);
    }

    public IfBeforeCond getIfBeforeCond() {
        return IfBeforeCond;
    }

    public void setIfBeforeCond(IfBeforeCond IfBeforeCond) {
        this.IfBeforeCond=IfBeforeCond;
    }

    public Condition getCondition() {
        return Condition;
    }

    public void setCondition(Condition Condition) {
        this.Condition=Condition;
    }

    public IfAfterCond getIfAfterCond() {
        return IfAfterCond;
    }

    public void setIfAfterCond(IfAfterCond IfAfterCond) {
        this.IfAfterCond=IfAfterCond;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public ElseInterrupt getElseInterrupt() {
        return ElseInterrupt;
    }

    public void setElseInterrupt(ElseInterrupt ElseInterrupt) {
        this.ElseInterrupt=ElseInterrupt;
    }

    public Statement getStatement1() {
        return Statement1;
    }

    public void setStatement1(Statement Statement1) {
        this.Statement1=Statement1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(IfBeforeCond!=null) IfBeforeCond.accept(visitor);
        if(Condition!=null) Condition.accept(visitor);
        if(IfAfterCond!=null) IfAfterCond.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(ElseInterrupt!=null) ElseInterrupt.accept(visitor);
        if(Statement1!=null) Statement1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(IfBeforeCond!=null) IfBeforeCond.traverseTopDown(visitor);
        if(Condition!=null) Condition.traverseTopDown(visitor);
        if(IfAfterCond!=null) IfAfterCond.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(ElseInterrupt!=null) ElseInterrupt.traverseTopDown(visitor);
        if(Statement1!=null) Statement1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(IfBeforeCond!=null) IfBeforeCond.traverseBottomUp(visitor);
        if(Condition!=null) Condition.traverseBottomUp(visitor);
        if(IfAfterCond!=null) IfAfterCond.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(ElseInterrupt!=null) ElseInterrupt.traverseBottomUp(visitor);
        if(Statement1!=null) Statement1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("IfElseStatement(\n");

        if(IfBeforeCond!=null)
            buffer.append(IfBeforeCond.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Condition!=null)
            buffer.append(Condition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(IfAfterCond!=null)
            buffer.append(IfAfterCond.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ElseInterrupt!=null)
            buffer.append(ElseInterrupt.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement1!=null)
            buffer.append(Statement1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [IfElseStatement]");
        return buffer.toString();
    }
}
