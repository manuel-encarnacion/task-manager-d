package task.manager.repository;

import task.manager.domain.Task;

import java.io.IOException;
import java.util.List;

public interface TasksRepository {
    List<Task> getAllTasks() throws IOException;

    void saveATask(final Task task) throws IOException;

    void deleteATaskById(final int id);

    Task getATaskById(final int id);

}
