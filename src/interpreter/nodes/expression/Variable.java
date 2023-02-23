package interpreter.nodes.expression;

import common.Errors;
import common.SymbolTable;
import machine.Maquina;
import machine.instructions.Load;

import java.io.PrintWriter;

/**
 * The Varaible that is used in the Arboles Class and
 * implement the expression node interface
 */
public class Variable implements ExpressionNode{
    private String variableName;

    /**
     * Creates a new variable with the string given
     * @param name the name of the variable
     */
    public Variable(String name){
        this.variableName = name;
    }

    /**
     * Prints out the varaible name
     */
    public void emit(){
        System.out.print(this.variableName);
    }

    /**
     * Gets the corresponding number to the varaible name
     * @param symTable the symbol table, if needed, to fetch the variable values.
     * @return the value related to the variable in the symbol table
     */
    public int evaluate(SymbolTable symTable){
        if (symTable.has(this.variableName) == false){
            Errors.report(Errors.Type.UNINITIALIZED, this.variableName);
        }
        return symTable.get(variableName);
    }

    /**
     * Loads the variable with a new instance of Maquina machine and prints it
     * @param out the stream to write output to using out.println()
     */
    public void compile(PrintWriter out){
        out.println(new Load(this.variableName, new Maquina()));
    }
}
