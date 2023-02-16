package machine.instructions;

import machine.InstructionStack;
import machine.Maquina;

public class Divide implements Instruction{
    private final InstructionStack stack;
    public Divide(Maquina machine){
        this.stack = machine.getInstructionStack();
    }
    @Override
    public void execute(){
        int firstpop = this.stack.pop();
        int secondpop = this.stack.pop();
        int divided = secondpop/firstpop;
        this.stack.push(divided);
    }
    @Override
    public String toString() {
        return Maquina.DIVIDE;
    }
}
