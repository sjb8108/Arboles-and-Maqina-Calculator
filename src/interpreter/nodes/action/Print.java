package interpreter.nodes.action;
import common.SymbolTable;
import interpreter.nodes.expression.ExpressionNode;
import machine.Maquina;

import java.io.PrintWriter;

public class Print implements ActionNode{
    private ExpressionNode theChild;
    public Print(ExpressionNode child){
        this.theChild = child;
    }
    public void execute(SymbolTable symTable){
        System.out.println(this.theChild.evaluate(symTable));
    }
    public void emit(){
        System.out.print("Print ");
        this.theChild.emit();
    }
    public void compile(PrintWriter out){
        this.theChild.compile(out);
        out.println(new machine.instructions.Print(new Maquina()));
    }
}
