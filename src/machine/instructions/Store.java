package machine.instructions;

import common.SymbolTable;
import machine.InstructionStack;
import machine.Maquina;

public class Store implements Instruction{
    private final InstructionStack stack;
    private String name;
    private SymbolTable symbolTable;
    public Store(String name, Maquina machine){
        this.stack = machine.getInstructionStack();
        this.name = name;
        this.symbolTable = machine.getSymbolTable();
    }
    @Override
    public void execute(){
        this.symbolTable.set(this.name, this.stack.pop());
    }
    @Override
    public String toString(){
        return Maquina.STORE + " " + this.name;
    }
}
