package machine.instructions;

import machine.InstructionStack;
import machine.Maquina;

public class SquareRoot implements Instruction{
    private final InstructionStack stack;
    public SquareRoot(Maquina machine){
        this.stack = machine.getInstructionStack();
    }
    @Override
    public void execute(){
        double popped = this.stack.pop();
        int push = (int) Math.sqrt(popped);
        this.stack.push(push);
    }
    @Override
    public String toString() {
        return Maquina.SQUARE_ROOT;
    }
}
