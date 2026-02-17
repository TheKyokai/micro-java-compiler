// generated with ast extension for cup
// version 0.8
// 17/1/2026 12:7:1


package rs.ac.bg.etf.pp1.ast;

public class FirstIdentEnumDeclList extends EnumDeclList {

    private SingleIdentEnumDecl SingleIdentEnumDecl;

    public FirstIdentEnumDeclList (SingleIdentEnumDecl SingleIdentEnumDecl) {
        this.SingleIdentEnumDecl=SingleIdentEnumDecl;
        if(SingleIdentEnumDecl!=null) SingleIdentEnumDecl.setParent(this);
    }

    public SingleIdentEnumDecl getSingleIdentEnumDecl() {
        return SingleIdentEnumDecl;
    }

    public void setSingleIdentEnumDecl(SingleIdentEnumDecl SingleIdentEnumDecl) {
        this.SingleIdentEnumDecl=SingleIdentEnumDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SingleIdentEnumDecl!=null) SingleIdentEnumDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SingleIdentEnumDecl!=null) SingleIdentEnumDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SingleIdentEnumDecl!=null) SingleIdentEnumDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FirstIdentEnumDeclList(\n");

        if(SingleIdentEnumDecl!=null)
            buffer.append(SingleIdentEnumDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FirstIdentEnumDeclList]");
        return buffer.toString();
    }
}
