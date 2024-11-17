package task.manager.service.impl.update;

import task.manager.domain.Task;
import task.manager.repository.TasksRepository;
import task.manager.service.Instruction;
import task.manager.utils.Utils;

import java.time.LocalDate;
import java.util.List;

public class Update implements Instruction {

    public static final String NOT_ABLE_TO_UPDATE_DESCRIPTION = "not able to update description";
    private static final int NUM_OF_ARGS_FOR_UPDATE_INSTRUCION = 2;
    private final TasksRepository tasksRepository;
    private int id;
    private String description;

    public Update(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @Override
    public Instruction buildWithArgsFactory(String[] args) {
        Utils.validateCorrectNumOfArgs(NUM_OF_ARGS_FOR_UPDATE_INSTRUCION, args);
        this.id = Integer.parseInt(args[0]);
        this.description = args[1];
        return this;
    }

    @Override
    public void executeInstruction() {
        try {
            final List<Task> tasks = tasksRepository.getAllTasks();
            final Task taskToUpdate = tasks.stream().filter(task -> task.getId() == this.id).findFirst().get();
            final Task taskUpdated = Task.builder().withUpdatedAt(LocalDate.now()).withId(this.id)
                    .withStatus(taskToUpdate.getStatus()).withDescription(description)
                    .withCreatedAt(taskToUpdate.getCreatedAt()).build();
            tasksRepository.deleteATaskById(this.id);
            tasksRepository.saveATask(taskUpdated);
        } catch (final Exception ignored) {
            throw new RuntimeException(NOT_ABLE_TO_UPDATE_DESCRIPTION);
        }
    }
}
