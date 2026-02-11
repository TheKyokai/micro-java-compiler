// generated with ast extension for cup
// version 0.8
// 11/1/2026 18:37:44


package rs.ac.bg.etf.pp1.ast;

public class ClassMethodBlock extends ClassMethods {

    private ExtMethodIntercept ExtMethodIntercept;
    private ClassMethodDeclList ClassMethodDeclList;

    public ClassMethodBlock (ExtMethodIntercept ExtMethodIntercept, ClassMethodDeclList ClassMethodDeclList) {
        this.ExtMethodIntercept=ExtMethodIntercept;
        if(ExtMethodIntercept!=null) ExtMethodIntercept.setParent(this);
        this.ClassMethodDeclList=ClassMethodDeclList;
        if(ClassMethodDeclList!=null) ClassMethodDeclList.setParent(this);
    }

    public ExtMethodIntercept getExtMethodIntercept() {
        return ExtMethodIntercept;
    }

    public void setExtMethodIntercept(ExtMethodIntercept ExtMethodIntercept) {
        this.ExtMethodIntercept=ExtMethodIntercept;
    }

    public ClassMethodDeclList getClassMethodDeclList() {
        return ClassMethodDeclList;
    }

    public void setClassMethodDeclList(ClassMethodDeclList ClassMethodDeclList) {
        this.ClassMethodDeclList=ClassMethodDeclList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExtMethodIntercept!=null) ExtMethodIntercept.accept(visitor);
        if(ClassMethodDeclList!=null) ClassMethodDeclList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExtMethodIntercept!=null) ExtMethodIntercept.traverseTopDown(visitor);
        if(ClassMethodDeclList!=null) ClassMethodDeclList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExtMethodIntercept!=null) ExtMethodIntercept.traverseBottomUp(visitor);
        if(ClassMethodDeclList!=null) ClassMethodDeclList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassMethodBlock(\n");

        if(ExtMethodIntercept!=null)
            buffer.append(ExtMethodIntercept.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassMethodDeclList!=null)
            buffer.append(ClassMethodDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassMethodBlock]");
        return buffer.toString();
    }
}
