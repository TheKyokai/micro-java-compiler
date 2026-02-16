// generated with ast extension for cup
// version 0.8
// 16/1/2026 17:31:48


package rs.ac.bg.etf.pp1.ast;

public class Cases extends CaseList {

    private CaseList CaseList;
    private SingleCase SingleCase;

    public Cases (CaseList CaseList, SingleCase SingleCase) {
        this.CaseList=CaseList;
        if(CaseList!=null) CaseList.setParent(this);
        this.SingleCase=SingleCase;
        if(SingleCase!=null) SingleCase.setParent(this);
    }

    public CaseList getCaseList() {
        return CaseList;
    }

    public void setCaseList(CaseList CaseList) {
        this.CaseList=CaseList;
    }

    public SingleCase getSingleCase() {
        return SingleCase;
    }

    public void setSingleCase(SingleCase SingleCase) {
        this.SingleCase=SingleCase;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CaseList!=null) CaseList.accept(visitor);
        if(SingleCase!=null) SingleCase.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CaseList!=null) CaseList.traverseTopDown(visitor);
        if(SingleCase!=null) SingleCase.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CaseList!=null) CaseList.traverseBottomUp(visitor);
        if(SingleCase!=null) SingleCase.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Cases(\n");

        if(CaseList!=null)
            buffer.append(CaseList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SingleCase!=null)
            buffer.append(SingleCase.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Cases]");
        return buffer.toString();
    }
}
