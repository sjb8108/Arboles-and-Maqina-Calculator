package machine.instructions;

import machine.InstructionStack;
import machine.Maquina;
/**
 * The divide class for the Maquina clas that also implements Instruction
 */
public class Divide implements Instruction{
    private final InstructionStack stack;
    /**
     * Gets the machine stacks so the division can occur
     * @param machine the main object that does the backend application
     */
    public Divide(Maquina machine){
        this.stack = machine.getInstructionStack();
    }
    /**
     * Pops off the top two elements in the stacks and
     * divides them together and pushes the total back
     */
    @Override
    public void execute(){
        int firstpop = this.stack.pop();
        int secondpop = this.stack.pop();
        int divided = secondpop/firstpop;
        this.stack.push(divided);
    }
    /**
     * Shows of what the instruction did
     * @return a string of what the instruction did
     */
    @Override
    public String toString() {
        return Maquina.DIVIDE;
    }
}
