package interpreter.nodes.action;

import common.SymbolTable;
import interpreter.nodes.expression.ExpressionNode;

import java.io.PrintWriter;

public class Assignment implements ActionNode{
    private String variableName;
    private ExpressionNode theChild;
    public Assignment(String name, ExpressionNode child){
        this.variableName = name;
        this.theChild = child;
    }
    public void execute(SymbolTable symTable){

    }
    public void emit(){

    }
    public void compile(PrintWriter out){

    }

}
