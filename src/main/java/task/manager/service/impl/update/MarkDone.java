package task.manager.service.impl.update;

import task.manager.repository.TasksRepository;
import task.manager.service.Status;


public class MarkDone extends MarkFather {

    public MarkDone(final TasksRepository tasksRepository) {
        super(Status.DONE, tasksRepository);
    }

}
