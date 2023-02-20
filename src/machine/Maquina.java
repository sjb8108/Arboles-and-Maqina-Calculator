package machine;

import common.Errors;
import common.SymbolTable;
import machine.instructions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * The machine can process/execute a series of low level MAQ instructions using
 * an instruction stack and symbol table.
 *
 * @author RIT CS
 * @author Scott Bullock
 */
public class Maquina {
    /** the push instruction */
    public final static String PUSH = "PUSH";
    /** the print instruction */
    public final static String PRINT = "PRINT";
    /** the store instruction */
    public final static String STORE = "STORE";
    /** the load instruction */
    public final static String LOAD = "LOAD";
    /** the negate instruction */
    public final static String NEGATE = "NEG";
    /** the square root instruction */
    public final static String SQUARE_ROOT = "SQRT";
    /** the add instruction */
    public final static String ADD = "ADD";
    /** the subtract instruction */
    public final static String SUBTRACT = "SUB";
    /** the multiply instruction */
    public final static String MULTIPLY = "MUL";
    /** the divide instruction */
    public final static String DIVIDE = "DIV";
    /** the modulus instruction */
    public final static String MODULUS = "MOD";

    /** the list of valid machine instructions */
    public static final List< String > OPERATIONS =
            List.of(
                    ADD,
                    DIVIDE,
                    LOAD,
                    MODULUS,
                    MULTIPLY,
                    NEGATE,
                    PUSH,
                    PRINT,
                    SQUARE_ROOT,
                    STORE,
                    SUBTRACT
            );

    /** the terminating character when reading machine instructions from user (not file) */
    private final static String EOF = ".";

    private SymbolTable symbolTable;
    private InstructionStack instructionStack;

    private List instructionList;

    /**
     * Create a new machine, with an empty symbol table, instruction stack, and
     * list of instructions.
     */
    public Maquina() {
        this.symbolTable = new  SymbolTable();
        this.instructionStack = new  InstructionStack();
        this.instructionList = new  ArrayList<>();
    }

    /**
     * Return the instruction stack.
     *
     * @return the stack
     */
    public InstructionStack getInstructionStack() {
        return this.instructionStack;
    }

    /**
     * Return the symbol table.
     *
     * @return the symbol table
     */
    public SymbolTable getSymbolTable() {
        return this.symbolTable;
    }


    /**
     * Assemble the machine instructions.
     *
     * @param maqIn the input source
     * @param stdin true if input is coming from standard input (for prompting)
     */
    public void assemble(Scanner maqIn, boolean stdin) {
        if (stdin) {
            System.out.println("🤖 ");
            String instruction = "Placeholder";
            Scanner sc= new Scanner(System.in);
            while (!(instruction.equals(EOF))){
                System.out.println("Enter the Instruction");
                instruction = sc.nextLine();
                this.instructionList.add(instruction);
            }
        }
        else{
            while (maqIn.hasNextLine()){
                String[] fields = maqIn.nextLine().strip().split("\\s+");
                String instruction;
                if (fields.length == 2){
                    instruction = fields[0] + " " + fields[1];
                    this.instructionList.add(instruction);

                }
                else{
                    instruction = fields[0];
                    this.instructionList.add(instruction);
                }
            }
            for (int i = 0; i < instructionList.size(); i++){
                String instruction = this.instructionList.get(i).toString().split(" ")[0];
                if ((instruction.equals(ADD)) || instruction.equals(DIVIDE) ||
                        instruction.equals(LOAD) || instruction.equals(MODULUS) ||
                        instruction.equals(MULTIPLY) || instruction.equals(NEGATE)
                        || instruction.equals(PRINT) || instruction.equals(PRINT)
                        || instruction.equals(PUSH) || instruction.equals(SQUARE_ROOT)
                        || instruction.equals(STORE) || instruction.equals(SUBTRACT)){
                }
                else{
                    Errors.report(Errors.Type.ILLEGAL_INSTRUCTION, instruction);
                }
            }
            System.out.println("(MAQ) Machine instructions:");
            for (int i = 0; i < this.instructionList.size(); i++){
                System.out.println(this.instructionList.get(i));
            }
        }
    }

    /**
     * Executes each assembled machine instruction in order.  When completed it
     * displays the symbol table and the instruction stack.
     */
    public void execute() {
        System.out.println("(MAQ) Executing...");
        for (int j = 0; j < this.instructionList.size(); j++){
            String[] instruction = this.instructionList.get(j).toString().split(" ");
            if (instruction.length == 2){
                String command = instruction[0];
                if (command.equals(PUSH)){
                    int value = Integer.valueOf(instruction[1]);
                    new Push(value, this).execute();
                }
                else if (command.equals(STORE)) {
                    String variable = instruction[1];
                    new Store(variable, this).execute();
                }
                else{
                    String variable = instruction[1];
                    if (this.symbolTable.has(variable) == false){
                        Errors.report(Errors.Type.UNINITIALIZED, variable);
                    }
                    new Load(variable, this).execute();
                }
            }
            else{
                String command = instruction[0];
                if (command.equals(ADD)){
                    new Add(this).execute();
                }
                else if (command.equals(DIVIDE)) {
                    if ((int) this.instructionStack.top() == 0){
                        Errors.report(Errors.Type.DIVIDE_BY_ZERO);
                    }
                    new Divide(this).execute();
                }
                else if (command.equals(MODULUS)) {
                    new Modulus(this).execute();
                }
                else if (command.equals(MULTIPLY)) {
                    new Multiply(this).execute();
                }
                else if (command.equals(NEGATE)) {
                    new Negate(this).execute();
                }
                else if (command.equals(SQUARE_ROOT)) {
                    if ((int) this.instructionStack.top() < 0){
                        Errors.report(Errors.Type.NEGATIVE_SQUARE_ROOT);
                    }
                    new SquareRoot(this).execute();
                }
                else if (command.equals(SUBTRACT)) {
                    new Subtract(this).execute();
                }
                else if (command.equals(PRINT)){
                    new Print(this).execute();
                }
            }
        }

        System.out.println("(MAQ) Completed execution!");
        System.out.println("(MAQ) Symbol table:");
        System.out.println(this.getSymbolTable());
        System.out.println(this.getInstructionStack());
    }

    /**
     * The main method.  Machine instructions can either be specified from standard input
     * (no command line), or from a file (only argument on command line).  From
     * here the machine assembles the instructions and then executes them.
     *
     * @param args command line argument (optional)
     * @throws FileNotFoundException if the machine file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        // determine input source
        Scanner maqIn = null;
        boolean stdin = false;
        if (args.length == 0) {
            maqIn = new Scanner(System.in);
            stdin = true;
        } else if (args.length == 1){
            maqIn = new Scanner(new File(args[0]));
        } else {
            System.out.println("Usage: java Maquina [filename.maq]");
            System.exit(1);
        }

        Maquina machine = new Maquina();
        machine.assemble(maqIn, stdin);     // assemble the machine instructions
        machine.execute();                  // execute the program
        maqIn.close();
    }
}
