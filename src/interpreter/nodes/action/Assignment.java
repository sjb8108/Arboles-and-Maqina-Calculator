package interpreter.nodes.action;

import common.SymbolTable;
import interpreter.nodes.expression.ExpressionNode;
import machine.Maquina;

import java.io.PrintWriter;

public class Assignment implements ActionNode{
    private String variableName;
    private ExpressionNode theChild;
    public Assignment(String name, ExpressionNode child){
        this.variableName = name;
        this.theChild = child;
    }
    public void execute(SymbolTable symTable){
        symTable.set(this.variableName, this.theChild.evaluate(symTable));
    }
    public void emit(){
        System.out.print(this.variableName + " = ");
        theChild.emit();

    }
    public void compile(PrintWriter out){
        this.theChild.compile(out);
        out.println(new machine.instructions.Store(this.variableName, new Maquina()));
    }

}
