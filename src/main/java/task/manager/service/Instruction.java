package task.manager.service;

import java.io.IOException;

public interface Instruction {
    Instruction buildWithArgsFactory(final String[] args);

    void executeInstruction() throws IOException;
}
