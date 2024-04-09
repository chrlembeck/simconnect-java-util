package org.lembeck.fs.simconnect.constants;

public enum SimObjectType {

    USER("User"),
    ALL("All"),
    AIRCRAFT("Airplane"),
    HELICOPTER("Helicopter"),
    BOAT("Boat"),
    GROUND("GroundVehicle"),
    UNKNOWN("UNKNOWN");

    private final String identifier;

    SimObjectType(String identifier) {
        this.identifier = identifier;
    }

    public static SimObjectType fromIdentifier(String identifier) {
        for (SimObjectType type : values()) {
            if (type.identifier.equalsIgnoreCase(identifier)) {
                return type;
            }
        }
        throw new IllegalArgumentException("unkown simObjectTyp " + identifier);
    }

    public static SimObjectType ofId(int id) {
        return switch (id) {
            case 0 -> USER;
            case 1 -> ALL;
            case 2 -> AIRCRAFT;
            case 3 -> HELICOPTER;
            case 4 -> BOAT;
            case 5 -> GROUND;
            default -> UNKNOWN;
        };
    }
}
