package machine.instructions;

import common.SymbolTable;
import machine.InstructionStack;
import machine.Maquina;
/**
 * The load class for the Maquina clas that also implements Instruction
 */
public class Load implements Instruction{
    private final InstructionStack stack;
    private String name;
    private SymbolTable symbolTable;
    /**
     * Gets the machine stacks and name of the varaible
     * put in the symbol table for the loading can occur
     * @param machine the main object that does the backend application
     */
    public Load(String name, Maquina machine){
        this.stack = machine.getInstructionStack();
        this.name = name;
        this.symbolTable = machine.getSymbolTable();
    }
    /**
     * Pushes the number that representes the name variable
     * in the symbol table
     */
    @Override
    public void execute() {
        this.stack.push(this.symbolTable.get(this.name));
    }
    /**
     * Shows of what the instruction did
     * @return a string of what the instruction did
     */
    @Override
    public String toString(){
        return Maquina.LOAD + " " + this.name;
    }
}
