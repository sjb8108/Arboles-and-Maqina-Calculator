package interpreter.nodes.expression;

import common.SymbolTable;
import machine.Maquina;
import machine.instructions.*;

import java.io.PrintWriter;

public class BinaryOperation implements ExpressionNode{
    private String operation;
    private ExpressionNode theLeftChild;
    private ExpressionNode theRightChild;
    public BinaryOperation(String operator, ExpressionNode leftChild, ExpressionNode rightChild){
        this.operation = operator;
        this.theLeftChild = leftChild;
        this.theRightChild = rightChild;
    }
    public void emit(){
        System.out.print("( ");
        this.theLeftChild.emit();
        System.out.print(" "+this.operation+" ");
        this.theRightChild.emit();
        System.out.print(" )");
    }
    public int evaluate(SymbolTable symTable){
        if (this.operation.equals("+")){
            return this.theLeftChild.evaluate(symTable) + this.theRightChild.evaluate(symTable);
        } else if (this.operation.equals("-")) {
            return this.theLeftChild.evaluate(symTable) - this.theRightChild.evaluate(symTable);
        } else if (this.operation.equals("*")) {
            return this.theLeftChild.evaluate(symTable) * this.theRightChild.evaluate(symTable);
        } else if (this.operation.equals("/")) {
            return this.theLeftChild.evaluate(symTable) / this.theRightChild.evaluate(symTable);
        }else{
            return this.theLeftChild.evaluate(symTable) % this.theRightChild.evaluate(symTable);
        }
    }
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
