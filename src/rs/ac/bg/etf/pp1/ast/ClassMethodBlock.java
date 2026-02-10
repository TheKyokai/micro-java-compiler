// generated with ast extension for cup
// version 0.8
// 9/1/2026 12:14:35


package rs.ac.bg.etf.pp1.ast;

public class ClassMethodBlock extends ClassMethods {

    private ExtMethodIntercept ExtMethodIntercept;
    private ClassMethodDeclList ClassMethodDeclList;
    private ExtMethodEIntercept ExtMethodEIntercept;

    public ClassMethodBlock (ExtMethodIntercept ExtMethodIntercept, ClassMethodDeclList ClassMethodDeclList, ExtMethodEIntercept ExtMethodEIntercept) {
        this.ExtMethodIntercept=ExtMethodIntercept;
        if(ExtMethodIntercept!=null) ExtMethodIntercept.setParent(this);
        this.ClassMethodDeclList=ClassMethodDeclList;
        if(ClassMethodDeclList!=null) ClassMethodDeclList.setParent(this);
        this.ExtMethodEIntercept=ExtMethodEIntercept;
        if(ExtMethodEIntercept!=null) ExtMethodEIntercept.setParent(this);
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

    public ExtMethodEIntercept getExtMethodEIntercept() {
        return ExtMethodEIntercept;
    }

    public void setExtMethodEIntercept(ExtMethodEIntercept ExtMethodEIntercept) {
        this.ExtMethodEIntercept=ExtMethodEIntercept;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExtMethodIntercept!=null) ExtMethodIntercept.accept(visitor);
        if(ClassMethodDeclList!=null) ClassMethodDeclList.accept(visitor);
        if(ExtMethodEIntercept!=null) ExtMethodEIntercept.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExtMethodIntercept!=null) ExtMethodIntercept.traverseTopDown(visitor);
        if(ClassMethodDeclList!=null) ClassMethodDeclList.traverseTopDown(visitor);
        if(ExtMethodEIntercept!=null) ExtMethodEIntercept.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExtMethodIntercept!=null) ExtMethodIntercept.traverseBottomUp(visitor);
        if(ClassMethodDeclList!=null) ClassMethodDeclList.traverseBottomUp(visitor);
        if(ExtMethodEIntercept!=null) ExtMethodEIntercept.traverseBottomUp(visitor);
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

        if(ExtMethodEIntercept!=null)
            buffer.append(ExtMethodEIntercept.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassMethodBlock]");
        return buffer.toString();
    }
}
