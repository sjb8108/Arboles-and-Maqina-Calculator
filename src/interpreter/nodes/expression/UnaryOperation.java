package interpreter.nodes.expression;

import common.Errors;
import common.SymbolTable;
import machine.Maquina;
import machine.instructions.Negate;
import machine.instructions.SquareRoot;

import java.io.PrintWriter;

/**
 * The UnaryOperation class that is used in arboles class and
 * implements the expression node interface
 */
public class UnaryOperation implements ExpressionNode{
    private String operation;
    private ExpressionNode theChild;

    /**
     * Creates the unary operations with the operator given and expression node
     * @param operator the sign/operator used to do an calcuation
     * @param child the expression node
     */
    public UnaryOperation(String operator, ExpressionNode child){
        this.operation = operator;
        this.theChild = child;
    }

    /**
     * Prints and the operations and emits the expression node
     */
    public void emit(){
        System.out.print(this.operation);
        this.theChild.emit();
    }

    /**
     * Depending on what the operations is, a calcuation will be done
     * @param symTable the symbol table, if needed, to fetch the variable values.
     * @return an int value of the result of the calcuation
     */
    public int evaluate(SymbolTable symTable){
        if (this.operation.equals("!")){
            return this.theChild.evaluate(symTable)-(2*this.theChild.evaluate(symTable));
        }
        else{
            if (this.theChild.evaluate(symTable) < 0){
                Errors.report(Errors.Type.NEGATIVE_SQUARE_ROOT);
            }
            return (int) Math.sqrt(this.theChild.evaluate(symTable));
        }
    }

    /**
     * Depending on the operation it will compile the expression node
     * and negate/SquareRoot with a new Maquina machine
     * @param out the stream to write output to using out.println()
     */
    public void compile(PrintWriter out){
        if (this.operation.equals("!")){
            this.theChild.compile(out);
            out.println(new Negate(new Maquina()));
        }
        else{
            this.theChild.compile(out);
            out.println(new SquareRoot(new Maquina()));
        }
    }
}
