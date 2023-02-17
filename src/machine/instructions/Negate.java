package machine.instructions;

import machine.InstructionStack;
import machine.Maquina;
/**
 * The negate class for the Maquina clas that also implements Instruction
 */
public class Negate implements Instruction{
    private final InstructionStack stack;
    /**
     * Gets the machine stacks so the negation can occur
     * @param machine the main object that does the backend application
     */
    public Negate(Maquina machine){
        this.stack = machine.getInstructionStack();
    }
    /**
     * Pops off the top element in the stacks and
     * negates it and pushes the total back
     */
    @Override
    public void execute(){
        int popped = this.stack.pop();
        popped = popped - (2*popped);
        this.stack.push(popped);
    }
    /**
     * Shows of what the instruction did
     * @return a string of what the instruction did
     */
    @Override
    public String toString() {
        return Maquina.NEGATE;
    }
}
