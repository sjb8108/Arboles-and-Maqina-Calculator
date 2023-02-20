package interpreter.nodes.expression;

import common.SymbolTable;
import machine.Maquina;
import machine.instructions.Negate;
import machine.instructions.SquareRoot;

import java.io.PrintWriter;

public class UnaryOperation implements ExpressionNode{
    private String operation;
    private ExpressionNode theChild;
    public UnaryOperation(String operator, ExpressionNode child){
        this.operation = operator;
        this.theChild = child;
    }
    public void emit(){
        System.out.print(this.operation);
        this.theChild.emit();
    }
    public int evaluate(SymbolTable symTable){
        if (this.operation.equals("!")){
            return this.theChild.evaluate(symTable) - (2 * this.theChild.evaluate(symTable));
        }
        else{
            return (int) Math.sqrt(this.theChild.evaluate(symTable));
        }
    }
    public void compile(PrintWriter out){
        if (this.operation.equals("!")){
            theChild.compile(out);
            out.println(new Negate(new Maquina()));
        }
        else{
            theChild.compile(out);
            out.println(new SquareRoot(new Maquina()));
        }
    }
}
