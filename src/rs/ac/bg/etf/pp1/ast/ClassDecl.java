// generated with ast extension for cup
// version 0.8
// 9/1/2026 12:14:35


package rs.ac.bg.etf.pp1.ast;

public class ClassDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ClassName ClassName;
    private ClassExtension ClassExtension;
    private ExtFieldIntercept ExtFieldIntercept;
    private FieldDeclList FieldDeclList;
    private ClassMethods ClassMethods;

    public ClassDecl (ClassName ClassName, ClassExtension ClassExtension, ExtFieldIntercept ExtFieldIntercept, FieldDeclList FieldDeclList, ClassMethods ClassMethods) {
        this.ClassName=ClassName;
        if(ClassName!=null) ClassName.setParent(this);
        this.ClassExtension=ClassExtension;
        if(ClassExtension!=null) ClassExtension.setParent(this);
        this.ExtFieldIntercept=ExtFieldIntercept;
        if(ExtFieldIntercept!=null) ExtFieldIntercept.setParent(this);
        this.FieldDeclList=FieldDeclList;
        if(FieldDeclList!=null) FieldDeclList.setParent(this);
        this.ClassMethods=ClassMethods;
        if(ClassMethods!=null) ClassMethods.setParent(this);
    }

    public ClassName getClassName() {
        return ClassName;
    }

    public void setClassName(ClassName ClassName) {
        this.ClassName=ClassName;
    }

    public ClassExtension getClassExtension() {
        return ClassExtension;
    }

    public void setClassExtension(ClassExtension ClassExtension) {
        this.ClassExtension=ClassExtension;
    }

    public ExtFieldIntercept getExtFieldIntercept() {
        return ExtFieldIntercept;
    }

    public void setExtFieldIntercept(ExtFieldIntercept ExtFieldIntercept) {
        this.ExtFieldIntercept=ExtFieldIntercept;
    }

    public FieldDeclList getFieldDeclList() {
        return FieldDeclList;
    }

    public void setFieldDeclList(FieldDeclList FieldDeclList) {
        this.FieldDeclList=FieldDeclList;
    }

    public ClassMethods getClassMethods() {
        return ClassMethods;
    }

    public void setClassMethods(ClassMethods ClassMethods) {
        this.ClassMethods=ClassMethods;
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
        if(ClassName!=null) ClassName.accept(visitor);
        if(ClassExtension!=null) ClassExtension.accept(visitor);
        if(ExtFieldIntercept!=null) ExtFieldIntercept.accept(visitor);
        if(FieldDeclList!=null) FieldDeclList.accept(visitor);
        if(ClassMethods!=null) ClassMethods.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassName!=null) ClassName.traverseTopDown(visitor);
        if(ClassExtension!=null) ClassExtension.traverseTopDown(visitor);
        if(ExtFieldIntercept!=null) ExtFieldIntercept.traverseTopDown(visitor);
        if(FieldDeclList!=null) FieldDeclList.traverseTopDown(visitor);
        if(ClassMethods!=null) ClassMethods.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassName!=null) ClassName.traverseBottomUp(visitor);
        if(ClassExtension!=null) ClassExtension.traverseBottomUp(visitor);
        if(ExtFieldIntercept!=null) ExtFieldIntercept.traverseBottomUp(visitor);
        if(FieldDeclList!=null) FieldDeclList.traverseBottomUp(visitor);
        if(ClassMethods!=null) ClassMethods.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDecl(\n");

        if(ClassName!=null)
            buffer.append(ClassName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassExtension!=null)
            buffer.append(ClassExtension.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExtFieldIntercept!=null)
            buffer.append(ExtFieldIntercept.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FieldDeclList!=null)
            buffer.append(FieldDeclList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassMethods!=null)
            buffer.append(ClassMethods.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDecl]");
        return buffer.toString();
    }
}
