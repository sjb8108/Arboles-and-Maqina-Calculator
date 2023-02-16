package machine.instructions;

import machine.InstructionStack;
import machine.Maquina;

public class Subtract implements Instruction{
    private final InstructionStack stack;
    public Subtract(Maquina machine){
        this.stack = machine.getInstructionStack();
    }
    @Override
    public void execute(){
        int firstpop = this.stack.pop();
        int secondpop = this.stack.pop();
        int sub = secondpop-firstpop;
        this.stack.push(sub);
    }
    @Override
    public String toString() {
        return Maquina.SUBTRACT;
    }
}
