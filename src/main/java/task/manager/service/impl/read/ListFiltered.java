package task.manager.service.impl.read;

import task.manager.domain.Task;
import task.manager.presentation.impl.Presentation;
import task.manager.repository.TasksRepository;
import task.manager.service.Instruction;
import task.manager.service.Status;

import java.util.Arrays;

class ListFiltered implements Instruction {
    public static final String USE_TODO_DONE_IN_PROGRESS = "not a valid status to filter, please use todo / done / " +
            "in-progress";
    private final TasksRepository tasksRepository;
    private Status status;

    public ListFiltered(final TasksRepository tasksRepository, final String[] args) {
        this.tasksRepository = tasksRepository;
        buildWithArgsFactory(args);
    }

    @Override
    public Instruction buildWithArgsFactory(String[] args) {
        this.status = Arrays.stream(Status.values()).filter(st -> st.equals(args[0])).findFirst().orElseThrow();
        return this;
    }

    @Override
    public void executeInstruction() {
        try {
            java.util.List<Task> tasks = tasksRepository.getAllTasks();
            Presentation.printAsATable(
                    tasks.stream().filter(type -> type.getStatus().getValue().equals(status.getValue())).toList());
        } catch (final Exception ignored) {
            throw new RuntimeException(USE_TODO_DONE_IN_PROGRESS);
        }
    }
}
