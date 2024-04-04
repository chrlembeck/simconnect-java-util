package org.lembeck.fs.simconnect.constants;

public enum SimObjectType {


    USER("User"),
    ALL("All"),
    AIRCRAFT("Airplane"),
    HELICOPTER("Helicopter"),
    BOAT("Boat"),
    GROUND("GroundVehicle");

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
}
