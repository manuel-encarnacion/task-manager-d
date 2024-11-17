package task.manager.service.impl.delete;

import task.manager.repository.TasksRepository;
import task.manager.service.Instruction;
import task.manager.utils.Utils;

public class Delete implements Instruction {

    private static final int NUMBER_OF_ARGS_FOR_DELETE_OPERATION = 1;
    private final TasksRepository tasksRepository;
    private int id;

    public Delete(final TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @Override
    public Instruction buildWithArgsFactory(String[] args) {
        Utils.validateCorrectNumOfArgs(NUMBER_OF_ARGS_FOR_DELETE_OPERATION, args);
        this.id = Integer.parseInt(args[0]);
        return this;
    }

    @Override
    public void executeInstruction() {
        try {
            tasksRepository.deleteATaskById(this.id);
        } catch (final RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
