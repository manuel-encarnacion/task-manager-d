package task.manager.service.impl;

import task.manager.service.Instruction;

import java.io.IOException;

public class Help implements Instruction {

    @Override
    public Instruction buildWithArgsFactory(String[] args) {
        return this;
    }

    @Override
    public void executeInstruction() throws IOException {
        System.out.println("""
                SIMPLE TASK MANAGER\s
                \t # add a new task
                \t task-manager \t add "[task-description]"\s
                \n
                \t # update task
                \t task-manager \t update [id] "[task-description]"\s
                \t delete \t "[id]"\s
                \n
                \t # update status in a task
                \t mark-in-progress \t "[id]"\s
                \t mark-done \t "[id]"\s
                \n
                \t # list tasks
                \t list \s
                \t list \t done \s
                \t list \t doing\s
                """);
    }
}
