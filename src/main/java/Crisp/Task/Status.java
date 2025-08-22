package Crisp.Task;

public enum Status {
    NOT_DONE(" "),
    DONE("X");

    private final String icon;

    Status(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }
}
