package task.manager.repository;

import task.manager.domain.Task;
import task.manager.service.Status;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TasksRepositoryJson implements TasksRepository {

    public static final String DATA_PATH = "database.json";
    public static final String REGEX_TO_GET_STRING_BETWEEN_CURLY_BRACES = "\\{(.*?)}";
    public static final String ERROR_FORMAT_IN_FILE = "there has been an error reading the file, check the format in " +
            "file";
    public static final String ERROR_WRITING_IN_THE_FILE = "there has been an error writing in the file";
    public static final String ERROR_CREATING_FILE = "not able to create the file";
    public static final String ID = "id";
    public static final String DESCRIPTION = "description";
    public static final String STATUS = "status";
    public static final String CREATED_AT = "createdAt";
    public static final String UPDATED_AT = "updatedAt";
    public static final String UNABLE_TO_DELETE_A_TASK = "unable to delete a task";
    private List<Task> tasksFromFile = new ArrayList<>();

    private static List<String> getTasksIntoStringListFromString(String fileAsAString) {
        List<String> list = new ArrayList<>();
        Pattern pattern = Pattern.compile(REGEX_TO_GET_STRING_BETWEEN_CURLY_BRACES);
        Matcher matcher = pattern.matcher(fileAsAString);
        while (matcher.find()) {
            list.add(matcher.group(1));
        }
        return list;
    }

    private static String getAllTasksFromFileFormatted() {
        try {
            return Files.readString(Path.of(DATA_PATH)).replace("\n", "").replace("\t", "").replace("[", "")
                    .replace("\" ", "\"").replace(" \"", "\"").replace("]", "");
        } catch (final Exception ignored) {
            throw new RuntimeException(ERROR_FORMAT_IN_FILE);
        }
    }

    private static Task getTask(final String task) {
        int id = 0;
        String description = "";
        Status status = null;
        LocalDate createdAt = null;
        LocalDate updatedAt = null;

        String[] parameters = task.replace("\"", "").split(",");
        for (String parameter : parameters) {
            String[] keyValue = parameter.split(":");
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();

            switch (key) {
                case ID -> id = Integer.parseInt(value);
                case DESCRIPTION -> description = value;
                case STATUS -> {
                    status = Arrays.stream(Status.values()).filter(st -> st.getValue().equals(value)).findFirst()
                            .orElseThrow();
                }
                case CREATED_AT -> createdAt = LocalDate.parse(value);
                case UPDATED_AT -> updatedAt = LocalDate.parse(value);
            }
        }

        return Task.builder()
                .withId(id)
                .withDescription(description)
                .withStatus(status)
                .withCreatedAt(createdAt)
                .withUpdatedAt(updatedAt)
                .build();
    }

    private Stream<Task> getTasksListFromStringList(List<String> list) {
        return list.stream().map(TasksRepositoryJson::getTask);
    }

    public String transformToJson(final List<Task> tasks) {
        return "[" + tasks.stream().map(Task::toString).collect(Collectors.joining(",")) + "\n]";
    }

    private void createFile() {
        try {
            new File(DATA_PATH).createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(ERROR_CREATING_FILE);
        }
    }

    private boolean fileNotExists() {
        return !new File(DATA_PATH).exists();
    }

    @Override
    public List<Task> getAllTasks() {
        if (fileNotExists()) return Collections.emptyList();
        final String fileAsAString = getAllTasksFromFileFormatted();
        List<String> list = getTasksIntoStringListFromString(fileAsAString);
        return getTasksListFromStringList(list).sorted().collect(Collectors.toList());
    }

    @Override
    public void saveATask(final Task taskToAdd) throws IOException {
        if (fileNotExists()) createFile();
        this.tasksFromFile = getAllTasks();
        this.tasksFromFile.add(taskToAdd);
        this.saveTasks(tasksFromFile.stream().sorted().toList());
    }

    @Override
    public void deleteATaskById(final int id) {
        try {
            if (fileNotExists()) createFile();
            this.tasksFromFile = getAllTasks();
            this.tasksFromFile.remove(Task.builder().withId(id).build());
            this.saveTasks(tasksFromFile.stream().sorted().toList());
        } catch (final Exception ignored) {
            throw new RuntimeException(UNABLE_TO_DELETE_A_TASK);
        }
    }

    @Override
    public Task getATaskById(int id) {
        if (fileNotExists()) createFile();
        return getAllTasks().stream().filter(task -> task.getId() == id).findFirst().get();
    }

    private void saveTasks(final List<Task> tasks) {
        if (fileNotExists()) createFile();
        final String tasksFormattedToJson = transformToJson(tasks);
        try {
            final FileWriter myWriter = new FileWriter(DATA_PATH);
            myWriter.write(tasksFormattedToJson);
            myWriter.close();
        } catch (final IOException ignored) {
            throw new RuntimeException(ERROR_WRITING_IN_THE_FILE);
        }
    }
}
