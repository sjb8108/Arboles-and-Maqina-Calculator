package machine.instructions;

import machine.InstructionStack;
import machine.Maquina;

public class Add implements Instruction{
    private final InstructionStack stack;
    public Add(Maquina machine){

        this.stack = machine.getInstructionStack();
    }
    @Override
    public void execute(){
        int firstpop = this.stack.pop();
        int secondpop = this.stack.pop();
        int added = firstpop+secondpop;
        this.stack.push(added);
    }
    @Override
    public String toString() {
        return Maquina.ADD;
    }
}
