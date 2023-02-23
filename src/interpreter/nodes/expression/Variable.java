package interpreter.nodes.expression;

import common.Errors;
import common.SymbolTable;
import machine.Maquina;
import machine.instructions.Load;

import java.io.PrintWriter;

public class Variable implements ExpressionNode{
    private String variableName;
    public Variable(String name){
        this.variableName = name;
    }
    public void emit(){
        System.out.print(this.variableName);
    }
    public int evaluate(SymbolTable symTable){
        if (symTable.has(this.variableName) == false){
            Errors.report(Errors.Type.UNINITIALIZED, this.variableName);
        }
        return symTable.get(variableName);
    }
    public void compile(PrintWriter out){
        out.println(new Load(this.variableName, new Maquina()));
    }
}
