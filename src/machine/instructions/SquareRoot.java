package machine.instructions;

import machine.InstructionStack;
import machine.Maquina;
/**
 * The square root class for the Maquina clas that also implements Instruction
 */
public class SquareRoot implements Instruction{
    private final InstructionStack stack;
    /**
     * Gets the machine stacks so the square rooting can occur
     * @param machine the main object that does the backend application
     */
    public SquareRoot(Maquina machine){
        this.stack = machine.getInstructionStack();
    }
    /**
     * Pops off the top element in the stacks and
     * square roots it and pushes the total back
     */
    @Override
    public void execute(){
        double popped = this.stack.pop();
        int push = (int) Math.sqrt(popped);
        this.stack.push(push);
    }
    /**
     * Shows of what the instruction did
     * @return a string of what the instruction did
     */
    @Override
    public String toString() {
        return Maquina.SQUARE_ROOT;
    }
}
