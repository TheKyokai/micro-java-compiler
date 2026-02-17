// generated with ast extension for cup
// version 0.8
// 16/1/2026 23:34:31


package rs.ac.bg.etf.pp1.ast;

public class SingleCase implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private CaseNum CaseNum;
    private StatementList StatementList;

    public SingleCase (CaseNum CaseNum, StatementList StatementList) {
        this.CaseNum=CaseNum;
        if(CaseNum!=null) CaseNum.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public CaseNum getCaseNum() {
        return CaseNum;
    }

    public void setCaseNum(CaseNum CaseNum) {
        this.CaseNum=CaseNum;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
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
        if(CaseNum!=null) CaseNum.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CaseNum!=null) CaseNum.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CaseNum!=null) CaseNum.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleCase(\n");

        if(CaseNum!=null)
            buffer.append(CaseNum.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleCase]");
        return buffer.toString();
    }
}
