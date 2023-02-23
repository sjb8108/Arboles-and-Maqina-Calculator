package interpreter;

import common.Errors;
import common.SymbolTable;
import interpreter.nodes.action.ActionNode;
import interpreter.nodes.action.Assignment;
import interpreter.nodes.action.Print;
import interpreter.nodes.expression.*;
import machine.Maquina;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The main program for the high level ARB language.  It takes a program in ARB,
 * converts it into a token list, builds the parse trees for each statement,
 * displays the program in infix, interprets/evaluates the program, then
 * compiles into MAQ instructions so that the machine, Maquina, can execute it.
 *
 * @author RIT CS
 */
public class Arboles {
    /** the terminating character when reading machine instructions from user (not file) */
    private final static String EOF = ".";

    /** the ARB print token */
    private final static String PRINT = "@";
    /** the ARB assignment token */
    private final static String ASSIGN = "=";

    /** the location to generate the compiled ARB program of MAQ instructions */
    private final static String TMP_MAQ_FILE = "tmp/TEMP.maq";

    private ArrayList<String> tokenList;
    private ArrayList<ActionNode> actionNodesList;

    /**
     * Create a new Arbelos instance.  The result of this method is the tokenization
     * of the entire ARB input into a list of strings.
     *
     * @param in where to read the ARB input from
     * @param stdin if true, the user should be prompted to enter ARB statements until
     *              a terminating ".".
     */
    public Arboles(Scanner in, boolean stdin) {
        this.tokenList = new ArrayList<String>();
        this.actionNodesList = new ArrayList<ActionNode>();
        if (stdin) {
            System.out.print("🌳 ");
            String[] instruction = {"Placeholder"};
            Scanner sc= new Scanner(System.in);
            while (!(instruction[0].equals(EOF))){
                System.out.println("Enter the Instruction");
                instruction = sc.nextLine().strip().split(" ");
                for (String s : instruction){
                    this.tokenList.add(s);
                }
            }
        }
        else{
            System.out.println("(ARB) prefix...");
            while (in.hasNextLine()){
                String line = in.nextLine();
                System.out.println(line);
                String[] lineSplit = line.strip().split(" ");
                for (String s : lineSplit){
                    this.tokenList.add(s);
                }
            }
        }

    }

    /**
     * Build the parse trees into the program which is a list of ActioNode's -
     * one per line of ARB input.
     */
    public void buildProgram() {
        while (this.tokenList.size() > 0){
            if (this.tokenList.get(0).equals(ASSIGN)){
                this.tokenList.remove(0);
                this.actionNodesList.add(new Assignment(this.tokenList.remove(0),
                        getExpressionNode(this.tokenList.get(1))));
            }
            else if (this.tokenList.get(0).equals(PRINT)) {
                this.tokenList.remove(0);
                this.actionNodesList.add(new Print(getExpressionNode(this.tokenList.get(0))));
            }
            else{
                Errors.report(Errors.Type.ILLEGAL_ACTION, this.tokenList.get(0));
            }
        }
    }

    public ExpressionNode getExpressionNode(String token){
        token = this.tokenList.remove(0);
        if (token.equals(ASSIGN) || token.equals(PRINT)){
            ExpressionNode ExpressionNode = null;
            return ExpressionNode;
        }
        else if (token.equals("+") || token.equals("-") || token.equals("*")
                || token.equals("/") || token.equals("%")) {
            try{
                return new BinaryOperation(token, getExpressionNode(this.tokenList.get(0)),
                        getExpressionNode(this.tokenList.get(0)));
            }
            catch (IndexOutOfBoundsException out){
                Errors.report(Errors.Type.PREMATURE_END);
            }
            return new BinaryOperation(token, getExpressionNode(this.tokenList.get(0)),
                    getExpressionNode(this.tokenList.get(0)));
        }
        else if (token.equals("!") || token.equals("$")) {
            return new UnaryOperation(token, getExpressionNode(this.tokenList.get(0)));
        }
        else if (token.matches("^[a-zA-Z].*") == true) {
            return new Variable(token);
        }
        else{
            try {
                return new Constant(Integer.parseInt(token));
            }
            catch (NumberFormatException caught){
                Errors.report(Errors.Type.ILLEGAL_OPERATOR);
            }
            return new Constant(Integer.parseInt(token));
        }
    }

    /**
     * Displays the entire ARB program of ActionNode's to standard
     * output using emit().
     */
    public void displayProgram() {
        System.out.println("(ARB) infix...");
        for (int i = 0; i < this.actionNodesList.size(); i++){
            this.actionNodesList.get(i).emit();
            System.out.print("\n");
        }
    }

    /**
     * Execute the ARB program of ActionNode's to standard output using execute().
     * In order to execute the ActionNodes, a local SymbolTable must be created here
     * for use.
     */
    public void interpretProgram() {
        SymbolTable symTable = new SymbolTable();
        System.out.println("(ARB) interpreting program...");
        for (int i = 0; i < this.actionNodesList.size(); i++){
            this.actionNodesList.get(i).execute(symTable);
        }
        System.out.println("(ARB) Symbol table:");
        System.out.print(symTable);
    }

    /**
     * Compile the ARB program using ActionNode's compile() into the
     * temporary MAQ instruction file.
     *
     * @throws IOException if there are issues working with the temp file
     */
    public void compileProgram() throws IOException {
        System.out.println("(ARB) compiling program to " + TMP_MAQ_FILE + "...");
        PrintWriter out = new PrintWriter(TMP_MAQ_FILE);
        for (int i = 0; i < this.actionNodesList.size(); i++){
            this.actionNodesList.get(i).compile(out);
        }
        out.close();
    }

    /**
     * Takes the generated MAQ instruction file and assembles/executes
     * it using the Maquina machine.
     *
     * @throws FileNotFoundException if the MAQ file cannot be found.
     */
    public void executeProgram() throws FileNotFoundException {
        Maquina machine = new Maquina();
        Scanner maqIn;
        maqIn = new Scanner(new File(TMP_MAQ_FILE));
        machine.assemble(maqIn, false);
        machine.execute();
    }

    /**
     * The main program runs either with no input (ARB program entered through standard
     * input), or with a file name that represents the ARB program.
     *
     * @param args command line arguments
     * @throws IOException if there are issues working with the ARB/MAQ files.
     */
    public static void main(String[] args) throws IOException {
        // determine ARB input source
        Scanner arbIn = null;
        boolean stdin = false;
        if (args.length == 0) {
            arbIn = new Scanner(System.in);
            stdin = true;
        } else if (args.length == 1) {
            arbIn = new Scanner(new File(args[0]));
        } else {
            System.out.println("Usage: java Arbelos filename.arb");
            System.exit(1);
        }

        // step 1: read ARB program into token list
        Arboles interpreter = new Arboles(arbIn, stdin);

        // step 2: parse and build the program from the token list
        interpreter.buildProgram();

        // step 3: display the program in infix
        interpreter.displayProgram();

        // step 4: interpret program
        interpreter.interpretProgram();

        // step 5: compile the program
        interpreter.compileProgram();

        // step 6: have machine execute compiled program
        interpreter.executeProgram();
    }
}
