package org.lembeck.fs.simconnect.request;

public class Priority {

    public static final Priority HIGHEST = new Priority(1); // 1
    public static final Priority HIGHEST_MASKABLE = new Priority(10000000); // 10000000
    public static final Priority STANDARD = new Priority(1900000000); // 1900000000
    public static final Priority DEFAULT = new Priority(2000000000); // 2000000000
    public static final Priority LOWEST = new Priority(0xee6b2800); // 4000000000

    private final int priorityValue;

    public Priority(int priorityValue) {
        this.priorityValue = priorityValue;
    }

    public int getPriorityValue() {
        return priorityValue;
    }
}