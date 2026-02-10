// generated with ast extension for cup
// version 0.8
// 9/1/2026 12:14:35


package rs.ac.bg.etf.pp1.ast;

public class AbsClassDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private String className;
    private ClassExtension ClassExtension;
    private FieldDeclList FieldDeclList;
    private AbsClassMethods AbsClassMethods;

    public AbsClassDecl (String className, ClassExtension ClassExtension, FieldDeclList FieldDeclList, AbsClassMethods AbsClassMethods) {
        this.className=className;
        this.ClassExtension=ClassExtension;
        if(ClassExtension!=null) ClassExtension.setParent(this);
        this.FieldDeclList=FieldDeclList;
        if(FieldDeclList!=null) FieldDeclList.setParent(this);
        this.AbsClassMethods=AbsClassMethods;
        if(AbsClassMethods!=null) AbsClassMethods.setParent(this);
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className=className;
    }

    public ClassExtension getClassExtension() {
        return ClassExtension;
    }

    public void setClassExtension(ClassExtension ClassExtension) {
        this.ClassExtension=ClassExtension;
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
        if(ClassExtension!=null) ClassExtension.accept(visitor);
        if(FieldDeclList!=null) FieldDeclList.accept(visitor);
        if(AbsClassMethods!=null) AbsClassMethods.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassExtension!=null) ClassExtension.traverseTopDown(visitor);
        if(FieldDeclList!=null) FieldDeclList.traverseTopDown(visitor);
        if(AbsClassMethods!=null) AbsClassMethods.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassExtension!=null) ClassExtension.traverseBottomUp(visitor);
        if(FieldDeclList!=null) FieldDeclList.traverseBottomUp(visitor);
        if(AbsClassMethods!=null) AbsClassMethods.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AbsClassDecl(\n");

        buffer.append(" "+tab+className);
        buffer.append("\n");

        if(ClassExtension!=null)
            buffer.append(ClassExtension.toString("  "+tab));
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
