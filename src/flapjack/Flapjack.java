package flapjack; /**
 * Created with IntelliJ IDEA.
 * User: Hennie de Villiers
 * Date: 6/17/13
 * Time: 5:18 PM
 */

import flapjack.exceptions.*;
import flapjack.machine.FJMachine;
import flapjack.reader.FJReader;

import java.io.*;

public class Flapjack {
    static private FJMachine machine = new FJMachine();
    static private String prompt = "> ";
    static private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    
    static private final String 	RUN_COMMAND = "run",
                           			LOAD_COMMAND = "load",
                           			INSTRUCT_COMMAND = "instruct",
                           			HELP_COMMAND = "help",
                                	STATE_COMMAND = "state",
                                	VERBOSE_COMMAND = "verbose",
                                	RESET_COMMAND = "reset",
                                	CLEAR_COMMAND = "clear",
                           			STEP_COMMAND = "step",
                           			QUIT_COMMAND = "quit";

    static private int verbosity_level = 1;  
    static private boolean step = false;
    
    static String readCommand()
    {
    	String command = null;
    	
    	try {
    		command = bufferedReader.readLine();
    	} catch (Throwable e) {
    		System.out.println("Oops, someone just threw something at me:" + e);
    		System.exit(1);
    	}
    	return command;
    }

    static String readAllStdin()
    {
    	StringBuilder program = new StringBuilder();
    	
		try {
			int current = bufferedReader.read();
			while (current != -1)
			{
				program.append((char)current);
				current = bufferedReader.read();
			}
    	} catch (Throwable e) {
			System.out.println("Exception while reading all of stdin: " + e);
		}
		return program.toString();
    }


    static void loadString(String input) {
        try {
            machine.loadInstructions(FJReader.readAllFormsFromString(machine.getGlobalPackage(), input));
        } catch (Throwable e) {
            System.out.println(e);
        }
    }    


    static void printState()
    {
    	switch (verbosity_level) {
    	case 1:
    	case 2:
    		System.out.println(machine.dataStacksAsString());
    		break;
    	case 3:
    		try {
    			System.out.println(machine.allStacksAsString());
    		} catch (InstructionAccessViolation e) {
    			System.out.println("InstructionAccessViolation while printing all stacks in the REPL. This shouldn't be a problem :/");
    			System.exit(1);
    		}
    	}
    }

    static boolean executeStep() throws FJException {
    	boolean result = machine.executeStep();
    	System.out.println("123");
    	printState();
    	return result;
    }

    static void executeAll() throws FJException {
    	boolean ran = false;
    	while (machine.executeStep()) {
    		ran = true;
    		if (verbosity_level > 1) {
    			printState();
    		}
    	}
    	if (ran) 
    		System.out.println("Final state: " + machine.dataStacksAsString());
    	else
    		System.out.println("No more instructions.");
    }
    
    static void printUsage() {
    	System.out.println("Usage:");
    	System.out.println("No parameters: Start the REPL. Inside the REPL, type help for REPL commands.");
    	System.out.println("-f file1 file2 file3 : Load and execute the files in succession on the same virtual machine.");
    	System.out.println("-p : Run program input from stdin.");
   	}
    static void printHelp() {
    	System.out.println("Flapjack REPL Help");
    	System.out.println("==================");
    	System.out.println("Commands:");
    	System.out.println(RUN_COMMAND + ": Causes the virtual machine to execute instructions until the instruction stacks are empty.");
    	System.out.println(LOAD_COMMAND + " filename: Read a file called filename, and load the results as instructions.");
    	System.out.println(INSTRUCT_COMMAND + ": The next line is inputted into the console is executed without processing for commands (handy if the first form has the same name as a command).");
    	System.out.println(HELP_COMMAND + ": Prints this friendly message. :)");
    	System.out.println(STATE_COMMAND + ": Prints the current state of the virtual machine, based on the current level of verbosity.");
    	System.out.println(VERBOSE_COMMAND + ": Toggles the verbosity level: At level 1, only the data stacks are printed at the end of a step or run. At level 2, data stacks are printed at every step, even during a run. At level 3, all stacks are printed.");
    	System.out.println(STEP_COMMAND + ": Toggles stepping behaviour. If on, instructions are executed one by one. Note that, when stepping is on, giving an empty command by pressing enter will cause the VM to execute a step.");
    	System.out.println(RESET_COMMAND + ": Creates a new virtual machine, replacing the current one.");
    	System.out.println(CLEAR_COMMAND + ": Erases instruction stacks. This means data stacks and the global package are retained, handy if stepping reveals things are going pear-shaped.");
    	System.out.println(QUIT_COMMAND + ": Quits the REPL.");
    	System.out.println("\nOtherwise, the command is interpreted as a flapjack program. If stepping is on, it is simply loaded into the instruction stack. If stepping is off, it is executed directly.");
    	System.out.println("Programs may also be run directly from the command line, see the options below:");
    	printUsage();
    }
    
    static String dropCommand(String input, String commandString)
    {
    	// Removes the command prefix from input, this was actually the command.
    	// Also trims whitespace off the resulting string.
    	return input.substring(commandString.length()).trim();
    }

    static boolean isCommand(String input, String command)
    {
    	String trimmed = input.trim();
    	
    	return (trimmed.equals(command) || trimmed.startsWith(command + " "));
    }
    
    static void interpret_command(String command) throws FJException {
    	String trimmed = command.trim();
    	      			
    	if (isCommand(trimmed,RUN_COMMAND)) {
    		executeAll();
    	} else if (isCommand(trimmed,LOAD_COMMAND)) { 	   	
    		String filename = dropCommand(trimmed,LOAD_COMMAND);
    		try {
    			loadString(stringFromFilename(filename));
    		} catch (IOException e) {
    			System.out.println("IOException loading file" + filename + ": " + e.getMessage());
    			return;
    		}
    		if (!step) executeAll();
    	} else if (isCommand(trimmed,INSTRUCT_COMMAND)) { 	   	
    		loadString(readCommand());
    		if (!step) executeAll();
    	} else if (isCommand(trimmed,HELP_COMMAND)) { 	   	
    		printHelp();
    	} else if (isCommand(trimmed,STATE_COMMAND)) {
    		printState();
    	} else if (isCommand(trimmed,RESET_COMMAND)) { 	   	
    		System.out.println("Creating a new virtual machine...");
    		machine = new FJMachine();
    	} else if (isCommand(trimmed,CLEAR_COMMAND)) { 	   	
    		System.out.println("Clearing instructions...");
    		machine.clearInstructions();
    	} else if (isCommand(trimmed,STEP_COMMAND)) {
    		step = !step;
    		if (step)
    			System.out.println("Stepping enabled. Press enter to execute an instruction.");
    		else
    			System.out.println("Stepping disabled. Press enter to execute all instructions.");
    	} else if (isCommand(trimmed,VERBOSE_COMMAND)) { 	   	
    	    verbosity_level = (verbosity_level % 3) + 1;
    	    switch (verbosity_level) {
    	    	case 1: System.out.println("Level 1: Only data stacks at end of step or run.");
    	    	break;
    	    	case 2: System.out.println("Level 2: Only data stacks after each instruction.");
    	    	break;
    	    	case 3: System.out.println("Level 3: All stacks after each instruction.");
    	    }
    	} else if (trimmed.equals("")) {
    		if (step)
    			{ if (!executeStep()) System.out.println("No more instructions."); }
    		else
    			executeAll();
    	} else {
    		loadString(command);
    		if (step) { printState(); System.out.println("Press enter to step.");} 
    		else executeAll();
    	}
    }
    
    static void clearInstructions()
    {
		try {
			machine.clearInstructions();
		} catch (InstructionAccessViolation e) {
			System.out.println("Instruction access violation, this should not happend :/");
			System.exit(1);
		}
    }
    
    static void repl() {
    	System.out.println("Flapjack REPL: type help for list of commands.");
    	System.out.print(prompt);
    	String current_input = readCommand();
    	while (!current_input.equals(QUIT_COMMAND)) {
    		try {
    			interpret_command(current_input);
    		} catch (FJException e) {
    			System.out.println(e.getClass().getSimpleName() + ": " + e.getMessage());
    			System.out.println("Clearing instructions...");
    			clearInstructions();
    		} catch (Throwable e) {
    			System.out.println("Non-FJException " + e.getClass().getSimpleName() + " (you should prevent this): " + e.getMessage());
    			System.out.println("Clearing instructions...");
    			clearInstructions();
    		}
    		System.out.print(prompt);
    		current_input = readCommand();
        }
    }

    private static String stringFromFilename(String filename) throws FileNotFoundException, IOException, NonASCIICharacterException
    {
        StringBuilder result = new StringBuilder();
        FileInputStream stream = new FileInputStream(filename);
        int readItem = stream.read();

        while (readItem != -1) {
            if (readItem > 127) {
                throw new NonASCIICharacterException();
            }
            result.append((char)readItem);
            readItem = stream.read();
        }        
        stream.close();
        return result.toString();
    }
   
    public static void main(String[] args) throws FJException
    {
        if (args.length == 0) {
            repl();
            return;
        } 
        
        if (args[0].equals("-f")) {
        	for (int n = 1; n < args.length ; n++) {
        		try {
        			loadString(stringFromFilename(args[n]));
        			machine.execute();
        		} catch (FileNotFoundException e) {
                    System.out.println("Could not find a file named " + args[n]);
                    System.out.println(e);
                    System.exit(1);
                } catch (IOException e) {
                    System.out.println("IO Exception while loading " + args[n]);
                    System.out.println(e);
                    System.exit(1);
                } catch (NonASCIICharacterException e) {
                    System.out.println("Non-ASCII character encountered while loading " + args[n]);
                    System.out.println(e);
                    System.exit(1);
                }
            }
        } else if (args[0].equals("-p")) {
        	loadString(readAllStdin());
        	machine.execute();
        } else {
        	printUsage();
        }        
    }

}
