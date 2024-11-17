package task.manager.service;

public enum InstructionType {
    ADD("add"), UPDATE("update"), DELETE("delete"), MARK_IN_PROGRESS("mark-in-progress"), MARK_DONE("mark-done"),
    LIST("list"), HELP("help"), ONE_DASH_HELP("-h"), DASHED_HELP("--help");

    private final String value;

    InstructionType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean equals(final String value) {
        return this.value.equals(value);
    }
}
