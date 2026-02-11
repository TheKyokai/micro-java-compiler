// generated with ast extension for cup
// version 0.8
// 11/1/2026 18:37:44


package rs.ac.bg.etf.pp1.ast;

public class AbsMethodDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ReturnType ReturnType;
    private AbsMethodName AbsMethodName;
    private FormalParams FormalParams;

    public AbsMethodDecl (ReturnType ReturnType, AbsMethodName AbsMethodName, FormalParams FormalParams) {
        this.ReturnType=ReturnType;
        if(ReturnType!=null) ReturnType.setParent(this);
        this.AbsMethodName=AbsMethodName;
        if(AbsMethodName!=null) AbsMethodName.setParent(this);
        this.FormalParams=FormalParams;
        if(FormalParams!=null) FormalParams.setParent(this);
    }

    public ReturnType getReturnType() {
        return ReturnType;
    }

    public void setReturnType(ReturnType ReturnType) {
        this.ReturnType=ReturnType;
    }

    public AbsMethodName getAbsMethodName() {
        return AbsMethodName;
    }

    public void setAbsMethodName(AbsMethodName AbsMethodName) {
        this.AbsMethodName=AbsMethodName;
    }

    public FormalParams getFormalParams() {
        return FormalParams;
    }

    public void setFormalParams(FormalParams FormalParams) {
        this.FormalParams=FormalParams;
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
        if(ReturnType!=null) ReturnType.accept(visitor);
        if(AbsMethodName!=null) AbsMethodName.accept(visitor);
        if(FormalParams!=null) FormalParams.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ReturnType!=null) ReturnType.traverseTopDown(visitor);
        if(AbsMethodName!=null) AbsMethodName.traverseTopDown(visitor);
        if(FormalParams!=null) FormalParams.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ReturnType!=null) ReturnType.traverseBottomUp(visitor);
        if(AbsMethodName!=null) AbsMethodName.traverseBottomUp(visitor);
        if(FormalParams!=null) FormalParams.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("AbsMethodDecl(\n");

        if(ReturnType!=null)
            buffer.append(ReturnType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AbsMethodName!=null)
            buffer.append(AbsMethodName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormalParams!=null)
            buffer.append(FormalParams.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [AbsMethodDecl]");
        return buffer.toString();
    }
}
