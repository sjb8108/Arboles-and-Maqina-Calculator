package machine.instructions;
import machine.Maquina;
import machine.InstructionStack;

public class Print implements Instruction{
    private final InstructionStack stack;
    public Print(Maquina machine){
        this.stack = machine.getInstructionStack();
    }
    @Override
    public void execute(){
        System.out.println(this.stack.pop());
    }
    @Override
    public String toString() {
        return Maquina.PRINT;
    }
}
