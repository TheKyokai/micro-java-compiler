// generated with ast extension for cup
// version 0.8
// 16/1/2026 23:34:31


package rs.ac.bg.etf.pp1.ast;

public class AbsClassMethodListAbsMethod extends AbsClassMethodList {

    private AbsClassMethodList AbsClassMethodList;
    private AbsMethodDecl AbsMethodDecl;

    public AbsClassMethodListAbsMethod (AbsClassMethodList AbsClassMethodList, AbsMethodDecl AbsMethodDecl) {
        this.AbsClassMethodList=AbsClassMethodList;
        if(AbsClassMethodList!=null) AbsClassMethodList.setParent(this);
        this.AbsMethodDecl=AbsMethodDecl;
        if(AbsMethodDecl!=null) AbsMethodDecl.setParent(this);
    }

    public AbsClassMethodList getAbsClassMethodList() {
        return AbsClassMethodList;
    }

    public void setAbsClassMethodList(AbsClassMethodList AbsClassMethodList) {
        this.AbsClassMethodList=AbsClassMethodList;
    }

    public AbsMethodDecl getAbsMethodDecl() {
        return AbsMethodDecl;
    }

    public void setAbsMethodDecl(AbsMethodDecl AbsMethodDecl) {
        this.AbsMethodDecl=AbsMethodDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AbsClassMethodList!=null) AbsClassMethodList.accept(visitor);
        if(AbsMethodDecl!=null) AbsMethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AbsClassMethodList!=null) AbsClassMethodList.traverseTopDown(visitor);
        if(AbsMethodDecl!=null) AbsMethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AbsClassMethodList!=null) AbsClassMethodList.traverseBottomUp(visitor);
        if(AbsMethodDecl!=null) AbsMethodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AbsClassMethodListAbsMethod(\n");

        if(AbsClassMethodList!=null)
            buffer.append(AbsClassMethodList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AbsMethodDecl!=null)
            buffer.append(AbsMethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AbsClassMethodListAbsMethod]");
        return buffer.toString();
    }
}
