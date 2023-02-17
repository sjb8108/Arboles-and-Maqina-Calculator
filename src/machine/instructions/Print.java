package machine.instructions;
import machine.Maquina;
import machine.InstructionStack;
/**
 * The print class for the Maquina clas that also implements Instruction
 */
public class Print implements Instruction{
    private final InstructionStack stack;
    /**
     * Gets the machine stacks so the printint can occur
     * @param machine the main object that does the backend application
     */
    public Print(Maquina machine){
        this.stack = machine.getInstructionStack();
    }
    /**
     * Pops off the top element in the stacks and
     * prints it out
     */
    @Override
    public void execute(){
        System.out.println(this.stack.pop());
    }
    /**
     * Shows of what the instruction did
     * @return a string of what the instruction did
     */
    @Override
    public String toString() {
        return Maquina.PRINT;
    }
}
