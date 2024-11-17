package task.manager.domain;

import task.manager.service.Status;

import java.time.LocalDate;
import java.util.Objects;

public class Task implements Comparable<Task> {
    private int id;
    private String description;
    private Status status;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public static TaskBuilder builder() {
        return new TaskBuilder();
    }

    private static TaskBuilder builder(Task task) {
        return new TaskBuilder().withCreatedAt(task.getCreatedAt()).withDescription(task.description)
                .withStatus(task.status).withId(task.id).withUpdatedAt(task.updatedAt);
    }

    public static TaskBuilder builderUpdate(final Task task) {
        return builder(task).withUpdatedAt(LocalDate.now());
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("\n  {\n    \"id\": ")
                .append(this.id)
                .append(",\n    ")
                .append("\"description\": \"")
                .append(this.description)
                .append("\",\n    ")
                .append("\"status\": \"")
                .append(this.status.getValue())
                .append("\",\n    ")
                .append("\"createdAt\": \"")
                .append(this.createdAt)
                .append("\",\n    ")
                .append("\"updatedAt\": \"")
                .append(this.updatedAt)
                .append("\"\n  }").toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, status, createdAt, updatedAt);
    }

    @Override
    public int compareTo(Task o) {
        return Integer.compare(this.id, o.id);
    }

    public static final class TaskBuilder {
        private int id;
        private String description;
        private Status status;
        private LocalDate createdAt;
        private LocalDate updatedAt;

        private TaskBuilder() {
        }

        public static TaskBuilder builder() {
            return new TaskBuilder();
        }

        public TaskBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public TaskBuilder withDescription(String description) {
            this.description = description;
            return this;
        }

        public TaskBuilder withStatus(Status status) {
            this.status = status;
            return this;
        }

        public TaskBuilder withCreatedAt(LocalDate createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public TaskBuilder withUpdatedAt(LocalDate updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Task build() {
            Task task = new Task();
            task.id = this.id;
            task.createdAt = this.createdAt;
            task.description = this.description;
            task.updatedAt = this.updatedAt;
            task.status = this.status;
            return task;
        }
    }
}
