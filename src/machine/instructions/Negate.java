package machine.instructions;

import machine.InstructionStack;
import machine.Maquina;

public class Negate implements Instruction{
    private final InstructionStack stack;
    public Negate(Maquina machine){
        this.stack = machine.getInstructionStack();
    }
    @Override
    public void execute(){
        int popped = this.stack.pop();
        popped = popped - (2*popped);
        this.stack.push(popped);
    }
    @Override
    public String toString() {
        return Maquina.NEGATE;
    }
}
