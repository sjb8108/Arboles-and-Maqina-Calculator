package machine.instructions;

import machine.InstructionStack;
import machine.Maquina;
/**
 * The multiply class for the Maquina clas that also implements Instruction
 */
public class Multiply implements Instruction{
    private final InstructionStack stack;
    /**
     * Gets the machine stacks so the multiplication can occur
     * @param machine the main object that does the backend application
     */
    public Multiply(Maquina machine){
        this.stack = machine.getInstructionStack();
    }
    /**
     * Pops off the top two elements in the stacks and
     * multiplies them together and pushes the total back
     */
    @Override
    public void execute(){
        int firstpop = this.stack.pop();
        int secondpop = this.stack.pop();
        int mutli = firstpop*secondpop;
        this.stack.push(mutli);
    }
    /**
     * Shows of what the instruction did
     * @return a string of what the instruction did
     */
    @Override
    public String toString() {
        return Maquina.MULTIPLY;
    }
}
