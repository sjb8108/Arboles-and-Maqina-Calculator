package interpreter.nodes.expression;

import common.SymbolTable;
import machine.Maquina;
import machine.instructions.Push;

import java.io.PrintWriter;

public class Constant implements ExpressionNode{
    private int constant;
    public Constant(int value){
        this.constant = value;
    }
    public void emit(){
        System.out.print(this.constant);
    }
    public int evaluate(SymbolTable symTable){
        return this.constant;
    }
    public void compile(PrintWriter out){
        out.println(new Push(this.constant, new Maquina()));
    }
}
