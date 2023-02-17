package machine.instructions;

import common.SymbolTable;
import machine.InstructionStack;
import machine.Maquina;
/**
 * The store class for the Maquina clas that also implements Instruction
 */
public class Store implements Instruction{
    private final InstructionStack stack;
    private String name;
    private SymbolTable symbolTable;
    /**
     * Gets the machine stacks and the string variable so they can store the
     * varabible in the symbol table
     * @param machine the main object that does the backend application
     */
    public Store(String name, Maquina machine){
        this.stack = machine.getInstructionStack();
        this.name = name;
        this.symbolTable = machine.getSymbolTable();
    }
    /**
     * Sets the variable given in the Store constructor to the top
     * element in the stack
     */
    @Override
    public void execute(){
        this.symbolTable.set(this.name, this.stack.pop());
    }
    /**
     * Shows of what the instruction did
     * @return a string of what the instruction did
     */
    @Override
    public String toString(){
        return Maquina.STORE + " " + this.name;
    }
}
