// generated with ast extension for cup
// version 0.8
// 17/1/2026 12:7:1


package rs.ac.bg.etf.pp1.ast;

public class AbsClassDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private AbsClassName AbsClassName;
    private ClassExtension ClassExtension;
    private ExtFieldIntercept ExtFieldIntercept;
    private FieldDeclList FieldDeclList;
    private AbsClassMethods AbsClassMethods;

    public AbsClassDecl (AbsClassName AbsClassName, ClassExtension ClassExtension, ExtFieldIntercept ExtFieldIntercept, FieldDeclList FieldDeclList, AbsClassMethods AbsClassMethods) {
        this.AbsClassName=AbsClassName;
        if(AbsClassName!=null) AbsClassName.setParent(this);
        this.ClassExtension=ClassExtension;
        if(ClassExtension!=null) ClassExtension.setParent(this);
        this.ExtFieldIntercept=ExtFieldIntercept;
        if(ExtFieldIntercept!=null) ExtFieldIntercept.setParent(this);
        this.FieldDeclList=FieldDeclList;
        if(FieldDeclList!=null) FieldDeclList.setParent(this);
        this.AbsClassMethods=AbsClassMethods;
        if(AbsClassMethods!=null) AbsClassMethods.setParent(this);
    }

    public AbsClassName getAbsClassName() {
        return AbsClassName;
    }

    public void setAbsClassName(AbsClassName AbsClassName) {
        this.AbsClassName=AbsClassName;
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

    public AbsClassMethods getAbsClassMethods() {
        return AbsClassMethods;
    }

    public void setAbsClassMethods(AbsClassMethods AbsClassMethods) {
        this.AbsClassMethods=AbsClassMethods;
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
        if(AbsClassName!=null) AbsClassName.accept(visitor);
        if(ClassExtension!=null) ClassExtension.accept(visitor);
        if(ExtFieldIntercept!=null) ExtFieldIntercept.accept(visitor);
        if(FieldDeclList!=null) FieldDeclList.accept(visitor);
        if(AbsClassMethods!=null) AbsClassMethods.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(AbsClassName!=null) AbsClassName.traverseTopDown(visitor);
        if(ClassExtension!=null) ClassExtension.traverseTopDown(visitor);
        if(ExtFieldIntercept!=null) ExtFieldIntercept.traverseTopDown(visitor);
        if(FieldDeclList!=null) FieldDeclList.traverseTopDown(visitor);
        if(AbsClassMethods!=null) AbsClassMethods.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(AbsClassName!=null) AbsClassName.traverseBottomUp(visitor);
        if(ClassExtension!=null) ClassExtension.traverseBottomUp(visitor);
        if(ExtFieldIntercept!=null) ExtFieldIntercept.traverseBottomUp(visitor);
        if(FieldDeclList!=null) FieldDeclList.traverseBottomUp(visitor);
        if(AbsClassMethods!=null) AbsClassMethods.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AbsClassDecl(\n");

        if(AbsClassName!=null)
            buffer.append(AbsClassName.toString("  "+tab));
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

        if(AbsClassMethods!=null)
            buffer.append(AbsClassMethods.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AbsClassDecl]");
        return buffer.toString();
    }
}
