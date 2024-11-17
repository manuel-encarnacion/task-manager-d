package task.manager.service.impl.read;

import task.manager.domain.Task;
import task.manager.presentation.impl.Presentation;
import task.manager.repository.TasksRepository;
import task.manager.service.Instruction;
import task.manager.utils.Utils;

public class List implements Instruction {

    public static final int NUMBER_OF_ARGS_FOR_LIST_ALL = 0;
    private final TasksRepository tasksRepository;

    public List(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @Override
    public Instruction buildWithArgsFactory(String[] args) {
        if (args.length == 1) return new ListFiltered(tasksRepository, args);
        Utils.validateCorrectNumOfArgs(NUMBER_OF_ARGS_FOR_LIST_ALL, args);
        return this;
    }

    @Override
    public void executeInstruction() {
        try {
            java.util.List<Task> tasks = tasksRepository.getAllTasks();
            Presentation.printAsATable(tasks);
        } catch (final Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
