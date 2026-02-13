// generated with ast extension for cup
// version 0.8
// 13/1/2026 0:38:39


package rs.ac.bg.etf.pp1.ast;

public class EnumDecls extends EnumDeclList {

    private EnumDeclList EnumDeclList;
    private SingleIdentEnumDecl SingleIdentEnumDecl;

    public EnumDecls (EnumDeclList EnumDeclList, SingleIdentEnumDecl SingleIdentEnumDecl) {
        this.EnumDeclList=EnumDeclList;
        if(EnumDeclList!=null) EnumDeclList.setParent(this);
        this.SingleIdentEnumDecl=SingleIdentEnumDecl;
        if(SingleIdentEnumDecl!=null) SingleIdentEnumDecl.setParent(this);
    }

    public EnumDeclList getEnumDeclList() {
        return EnumDeclList;
    }

    public void setEnumDeclList(EnumDeclList EnumDeclList) {
        this.EnumDeclList=EnumDeclList;
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
        if(EnumDeclList!=null) EnumDeclList.accept(visitor);
        if(SingleIdentEnumDecl!=null) SingleIdentEnumDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(EnumDeclList!=null) EnumDeclList.traverseTopDown(visitor);
        if(SingleIdentEnumDecl!=null) SingleIdentEnumDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(EnumDeclList!=null) EnumDeclList.traverseBottomUp(visitor);
        if(SingleIdentEnumDecl!=null) SingleIdentEnumDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("EnumDecls(\n");

        if(EnumDeclList!=null)
            buffer.append(EnumDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SingleIdentEnumDecl!=null)
            buffer.append(SingleIdentEnumDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [EnumDecls]");
        return buffer.toString();
    }
}
