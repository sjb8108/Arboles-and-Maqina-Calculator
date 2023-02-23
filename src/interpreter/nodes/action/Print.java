package interpreter.nodes.action;
import common.Errors;
import common.SymbolTable;
import interpreter.nodes.expression.ExpressionNode;
import machine.Maquina;

import java.io.PrintWriter;

/**
 * The Print Class used by the arboles class and
 * implements the action node interface
 */
public class Print implements ActionNode{
    private ExpressionNode theChild;

    /**
     * Creates a Print object with the given expression node
     * @param child the expression node
     */
    public Print(ExpressionNode child){
        this.theChild = child;
    }

    /**
     * Prints out the expression node evaluation with the symbol table
     * @param symTable the table where variable values are stored
     */
    public void execute(SymbolTable symTable){
        System.out.println(this.theChild.evaluate(symTable));
    }

    /**
     * Prints out the expression nodes using the expressions node emit method
     */
    public void emit(){
        System.out.print("Print ");
        this.theChild.emit();
    }

    /**
     * compliles the expression node using the compile class and creates a Print object
     * from the Maquina class with a new Maquina machine
     * @param out the stream to write output to using out.println()
     */
    public void compile(PrintWriter out){
        this.theChild.compile(out);
        out.println(new machine.instructions.Print(new Maquina()));
    }
}
