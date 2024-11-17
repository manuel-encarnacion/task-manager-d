package task.manager.service.impl.add;


import task.manager.domain.Task;
import task.manager.repository.TasksRepository;
import task.manager.service.Instruction;
import task.manager.service.Status;
import task.manager.utils.Utils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

public class Add implements Instruction {

    public static final int NEXT_ID_INCREMENTED_BY_ONE = 1;
    public static final int NUMBER_OF_ARGS_FOR_ADD_OPERATION = 1;
    private final TasksRepository tasksRepository;
    private String description;

    public Add(final TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @Override
    public Instruction buildWithArgsFactory(String[] args) {
        Utils.validateCorrectNumOfArgs(NUMBER_OF_ARGS_FOR_ADD_OPERATION, args);
        this.description = args[0];
        return this;
    }

    private int getNewIdForTask() {
        try {
            final List<Task> tasks = tasksRepository.getAllTasks();
            return tasks.stream()
                    .max(Comparator.comparingInt(Task::getId))
                    .map(task -> task.getId() + NEXT_ID_INCREMENTED_BY_ONE).orElse(1);
        } catch (final Exception ignored) {
            return NEXT_ID_INCREMENTED_BY_ONE;
        }
    }

    @Override
    public void executeInstruction() throws IOException {
        tasksRepository.saveATask(Task.TaskBuilder.builder()
                .withId(getNewIdForTask())
                .withDescription(this.description)
                .withCreatedAt(LocalDate.now())
                .withUpdatedAt(LocalDate.now())
                .withStatus(Status.TODO).build());
    }
}
