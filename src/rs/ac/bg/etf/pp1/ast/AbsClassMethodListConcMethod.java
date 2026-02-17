// generated with ast extension for cup
// version 0.8
// 16/1/2026 23:34:31


package rs.ac.bg.etf.pp1.ast;

public class AbsClassMethodListConcMethod extends AbsClassMethodList {

    private AbsClassMethodList AbsClassMethodList;
    private ClassMethodDecl ClassMethodDecl;

    public AbsClassMethodListConcMethod (AbsClassMethodList AbsClassMethodList, ClassMethodDecl ClassMethodDecl) {
        this.AbsClassMethodList=AbsClassMethodList;
        if(AbsClassMethodList!=null) AbsClassMethodList.setParent(this);
        this.ClassMethodDecl=ClassMethodDecl;
        if(ClassMethodDecl!=null) ClassMethodDecl.setParent(this);
    }

    public AbsClassMethodList getAbsClassMethodList() {
        return AbsClassMethodList;
    }

    public void setAbsClassMethodList(AbsClassMethodList AbsClassMethodList) {
        this.AbsClassMethodList=AbsClassMethodList;
    }

    public ClassMethodDecl getClassMethodDecl() {
        return ClassMethodDecl;
    }

    public void setClassMethodDecl(ClassMethodDecl ClassMethodDecl) {
        this.ClassMethodDecl=ClassMethodDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(AbsClassMethodList!=null) AbsClassMethodList.accept(visitor);
        if(ClassMethodDecl!=null) ClassMethodDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AbsClassMethodList!=null) AbsClassMethodList.traverseTopDown(visitor);
        if(ClassMethodDecl!=null) ClassMethodDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AbsClassMethodList!=null) AbsClassMethodList.traverseBottomUp(visitor);
        if(ClassMethodDecl!=null) ClassMethodDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AbsClassMethodListConcMethod(\n");

        if(AbsClassMethodList!=null)
            buffer.append(AbsClassMethodList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassMethodDecl!=null)
            buffer.append(ClassMethodDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AbsClassMethodListConcMethod]");
        return buffer.toString();
    }
}
