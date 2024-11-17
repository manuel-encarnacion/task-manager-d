package task.manager.service;

public enum Status {
    DONE("done"), TODO("todo"), DOING("doing");

    private final String value;

    Status(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean equals(final String value) {
        return this.value.equals(value);
    }
}
