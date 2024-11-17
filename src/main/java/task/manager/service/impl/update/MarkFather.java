package task.manager.service.impl.update;

import task.manager.domain.Task;
import task.manager.repository.TasksRepository;
import task.manager.service.Instruction;
import task.manager.service.Status;
import task.manager.utils.Utils;

import java.io.IOException;

abstract class MarkFather implements Instruction {

    public static final int NUM_OF_ARGS_FOR_MARK = 1;
    private final TasksRepository tasksRepository;
    private final Status status;
    private int id;

    public MarkFather(final Status status, final TasksRepository tasksRepository) {
        this.status = status;
        this.tasksRepository = tasksRepository;
    }

    @Override
    public Instruction buildWithArgsFactory(String[] args) {
        Utils.validateCorrectNumOfArgs(NUM_OF_ARGS_FOR_MARK, args);
        this.id = Integer.parseInt(args[0]);
        return this;
    }

    @Override
    public void executeInstruction() throws IOException {
        final Task taskToUpdate = tasksRepository.getATaskById(this.id);
        tasksRepository.deleteATaskById(this.id);
        tasksRepository.saveATask(Task.builderUpdate(taskToUpdate).withStatus(this.status).build());
    }
}
