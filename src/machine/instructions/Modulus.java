package machine.instructions;

import machine.InstructionStack;
import machine.Maquina;

public class Modulus implements Instruction{
    private final InstructionStack stack;
    public Modulus(Maquina machine){
        this.stack = machine.getInstructionStack();
    }
    @Override
    public void execute(){
        int firstpop = this.stack.pop();
        int secondpop = this.stack.pop();
        int mod = secondpop%firstpop;
        this.stack.push(mod);
    }
    @Override
    public String toString() {
        return Maquina.MODULUS;
    }
}
