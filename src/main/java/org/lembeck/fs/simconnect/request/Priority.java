package org.lembeck.fs.simconnect.request;

/**
 * Notification Group priority values.
 */
public class Priority {

    /**
     * Highest priority.
     */
    public static final Priority HIGHEST = new Priority(1); // 1

    /**
     * Highest priority that allows events to be masked.
     */
    public static final Priority HIGHEST_MASKABLE = new Priority(10000000); // 10000000

    /**
     * Standard priority.
     */
    public static final Priority STANDARD = new Priority(1900000000); // 1900000000

    /**
     * Default priority.
     */
    public static final Priority DEFAULT = new Priority(2000000000); // 2000000000

    /**
     * Priorities lower than this will be ignored.
     */
    public static final Priority LOWEST = new Priority(0xee6b2800); // 4000000000

    private final int priorityValue;

    /**
     * Creates a new priority object.
     *
     * @param priorityValue Priority for the notification group.
     * @see Priority#LOWEST
     * @see Priority#HIGHEST
     */
    public Priority(int priorityValue) {
        this.priorityValue = priorityValue;
    }

    /**
     * Returns the priority value.
     *
     * @return Value of the priority.
     */
    public int getPriorityValue() {
        return priorityValue;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return Integer.toString(priorityValue);
    }
}