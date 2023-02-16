package machine.instructions;

import machine.InstructionStack;
import machine.Maquina;

public class Multiply implements Instruction{
    private final InstructionStack stack;
    public Multiply(Maquina machine){
        this.stack = machine.getInstructionStack();
    }
    @Override
    public void execute(){
        int firstpop = this.stack.pop();
        int secondpop = this.stack.pop();
        int mutli = firstpop*secondpop;
        this.stack.push(mutli);
    }
    @Override
    public String toString() {
        return Maquina.MULTIPLY;
    }
}
