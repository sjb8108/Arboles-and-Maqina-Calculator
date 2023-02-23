package interpreter.nodes.expression;

import common.Errors;
import common.SymbolTable;
import machine.Maquina;
import machine.instructions.*;

import java.io.PrintWriter;
/**
 * The BinaryOperation class that is used in arboles class and
 * implements the expression node interface
 */
public class BinaryOperation implements ExpressionNode{
    private String operation;
    private ExpressionNode theLeftChild;
    private ExpressionNode theRightChild;

    /**
     * Creates the binary operations with the operator given and expression nodes
     * @param operator the sign/operator used to do an calcuation
     * @param leftChild One of two expression nodes
     * @param rightChild One of two expression nodes
     */
    public BinaryOperation(String operator, ExpressionNode leftChild,
                           ExpressionNode rightChild){
        this.operation = operator;
        this.theLeftChild = leftChild;
        this.theRightChild = rightChild;
    }
    /**
     * Prints and the operations and emits the expression nodes
     */
    public void emit(){
        System.out.print("( ");
        this.theLeftChild.emit();
        System.out.print(" "+this.operation+" ");
        this.theRightChild.emit();
        System.out.print(" )");
    }
    /**
     * Depending on what the operations is, a calcuation will be done
     * @param symTable the symbol table, if needed, to fetch the variable values.
     * @return an int value of the result of the calcuation
     */
    public int evaluate(SymbolTable symTable){
        if (this.operation.equals("+")){
            return this.theLeftChild.evaluate(symTable) +
                    this.theRightChild.evaluate(symTable);
        } else if (this.operation.equals("-")) {
            return this.theLeftChild.evaluate(symTable) -
                    this.theRightChild.evaluate(symTable);
        } else if (this.operation.equals("*")) {
            return this.theLeftChild.evaluate(symTable) *
                    this.theRightChild.evaluate(symTable);
        } else if (this.operation.equals("/")) {
            if (this.theRightChild.evaluate(symTable) == 0){
                Errors.report(Errors.Type.DIVIDE_BY_ZERO);
            }
            return this.theLeftChild.evaluate(symTable) /
                    this.theRightChild.evaluate(symTable);
        }else{
            return this.theLeftChild.evaluate(symTable) %
                    this.theRightChild.evaluate(symTable);
        }
    }
    /**
     * Depending on the operation it will compile the expression nodes
     * and Add/Subtract/Multiply/Divide/Modulus with a new Maquina machine
     * @param out the stream to write output to using out.println()
     */
    public void compile(PrintWriter out){
        if (this.operation.equals("+")){
            this.theLeftChild.compile(out);
            this.theRightChild.compile(out);
            out.println(new Add(new Maquina()));
        } else if (this.operation.equals("-")) {
            this.theLeftChild.compile(out);
            this.theRightChild.compile(out);
            out.println(new Subtract(new Maquina()));
        } else if (this.operation.equals("*")) {
            this.theLeftChild.compile(out);
            this.theRightChild.compile(out);
            out.println(new Multiply(new Maquina()));
        } else if (this.operation.equals("/")) {
            this.theLeftChild.compile(out);
            this.theRightChild.compile(out);
            out.println(new Divide(new Maquina()));
        }else{
            this.theLeftChild.compile(out);
            this.theRightChild.compile(out);
            out.println(new Modulus(new Maquina()));
        }
    }
}
