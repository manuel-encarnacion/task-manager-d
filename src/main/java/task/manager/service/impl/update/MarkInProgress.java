package task.manager.service.impl.update;

import task.manager.repository.TasksRepository;
import task.manager.service.Status;

public class MarkInProgress extends MarkFather {

    public MarkInProgress(final TasksRepository tasksRepository) {
        super(Status.DOING, tasksRepository);
    }

}