package interpreter.nodes.action;

import common.Errors;
import common.SymbolTable;
import interpreter.nodes.expression.ExpressionNode;
import machine.Maquina;

import java.io.PrintWriter;
/**
 * The Assignment Class used by the arboles class and
 * implements the action node interface
 */
public class Assignment implements ActionNode{
    private String variableName;
    private ExpressionNode theChild;
    /**
     * Creates a Assignment object with the given expression node and variable name
     * @param child the expression node
     */
    public Assignment(String name, ExpressionNode child){
        this.variableName = name;
        this.theChild = child;
    }

    /**
     * Puts in the variable name and evaluated expression node into the symbol table
     * @param symTable the table where variable values are stored
     */
    public void execute(SymbolTable symTable){
        symTable.set(this.variableName, this.theChild.evaluate(symTable));
    }
    /**
     * Prints out the variable and the expression
     * node using the expressions node emit method
     */
    public void emit(){
        System.out.print(this.variableName + " = ");
        this.theChild.emit();

    }
    /**
     * compliles the expression node using the compile class and creates a Store object
     * from the Maquina class with a new Maquina machine
     * @param out the stream to write output to using out.println()
     */
    public void compile(PrintWriter out){
        this.theChild.compile(out);
        out.println(new machine.instructions.Store(this.variableName, new Maquina()));
    }

}
