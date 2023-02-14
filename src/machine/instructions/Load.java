package machine.instructions;

import common.SymbolTable;
import machine.InstructionStack;
import machine.Maquina;

public class Load implements Instruction{
    private final InstructionStack stack;
    private String name;
    private SymbolTable symbolTable;
    public Load(String name, Maquina machine){
        this.stack = machine.getInstructionStack();
        this.name = name;
        this.symbolTable = machine.getSymbolTable();
    }
    @Override
    public void execute() {
        this.stack.push(this.symbolTable.get(this.name));
    }
    @Override
    public String toString(){
        return Maquina.LOAD + " " + this.name;
    }
}
