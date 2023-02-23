package interpreter.nodes.expression;

import common.SymbolTable;
import machine.Maquina;
import machine.instructions.Push;

import java.io.PrintWriter;

/**
 * The Constant class for the arboles class that also
 * implements the expression node interface
 */
public class Constant implements ExpressionNode{
    private int constant;

    /**
     * Gets the integer value to it can be expressed as a constant
     * @param value the int being a constant
     */
    public Constant(int value){
        this.constant = value;
    }

    /**
     * Prints out the constants
     */
    public void emit(){
        System.out.print(this.constant);
    }

    /**
     * Gets the constant and returns it
     * @param symTable the symbol table, if needed, to fetch the variable values.
     * @return the constant
     */
    public int evaluate(SymbolTable symTable){
        return this.constant;
    }

    /**
     * Pushes the constant with a new instance of Maquina machine and prints it
     * @param out the stream to write output to using out.println()
     */
    public void compile(PrintWriter out){
        out.println(new Push(this.constant, new Maquina()));
    }
}
