package task.manager;

import task.manager.service.Instruction;
import task.manager.utils.Utils;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try{
            final Instruction initializedInstruction = Utils.initializeInstructionWithArgs(args);
            initializedInstruction.executeInstruction();
        }catch(final Exception ignored){
            //TODO do a good handle of errors
            System.out.println("Bad arguments, please use task-manager --help");
        }
    }
}
