package interpreter.nodes.expression;

public class Variable implements ExpressionNode{
    private String variableName;
    public Variable(String name){
        this.variableName = name;
    }

}
