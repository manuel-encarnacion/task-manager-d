package task.manager.presentation.impl;

import task.manager.domain.Task;

public class Presentation {

    public static final String HEADERS_FORMAT = "%-10s%-10s%-10s%n";
    public static final String ID_HEADER = "id";
    public static final String STATUS_HEADER = "status";
    public static final String DESCRIPTION_HEADER = "description";
    public static final String RECORDS_FORMAT = "%-10d%-10s%-10s%n";

    public static void printAsATable(java.util.List<Task> tasks) {
        System.out.printf(HEADERS_FORMAT, ID_HEADER, STATUS_HEADER, DESCRIPTION_HEADER);
        tasks.forEach(task -> System.out.printf(RECORDS_FORMAT, task.getId(), task.getStatus().getValue(),
                task.getDescription()));
    }
}
