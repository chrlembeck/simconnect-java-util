package org.lembeck.fs.simconnect.constants;

/**
 * The SIMCONNECT_EXCEPTION enumeration type is used with the SIMCONNECT_RECV_EXCEPTION structure to return information
 * on an error that has occurred.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_EXCEPTION.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_EXCEPTION.htm</a>
 */
public enum ExceptionType {

    /**
     * Specifies that there has not been an error. This value is not currently used.
     */
    SIMCONNECT_EXCEPTION_NONE,

    /**
     * An unspecific error has occurred. This can be from incorrect flag settings, null or incorrect parameters, the
     * need to have at least one up or down event with an input event, failed calls from the SimConnect server to the
     * operating system, among other reasons.
     */
    SIMCONNECT_EXCEPTION_ERROR,

    /**
     * Specifies the size of the data provided does not match the size required. This typically occurs when the wrong
     * string length, fixed or variable, is involved.
     */
    SIMCONNECT_EXCEPTION_SIZE_MISMATCH,

    /**
     * Specifies that the client event, request ID, data definition ID, or object ID was not recognized.
     */
    SIMCONNECT_EXCEPTION_UNRECOGNIZED_ID,

    /**
     * Specifies that communication with the SimConnect server has not been opened. This error is not currently used.
     */
    SIMCONNECT_EXCEPTION_UNOPENED,

    /**
     * Specifies a versioning error has occurred. Typically this will occur when a client built on a newer version of
     * the SimConnect client dll attempts to work with an older version of the SimConnect server.
     */
    SIMCONNECT_EXCEPTION_VERSION_MISMATCH,

    /**
     * Specifies that the maximum number of groups allowed has been reached. The maximum is 20.
     */
    SIMCONNECT_EXCEPTION_TOO_MANY_GROUPS,

    /**
     * Specifies that the simulation event name (such as "brakes") is not recognized.
     */
    SIMCONNECT_EXCEPTION_NAME_UNRECOGNIZED,

    /**
     * Specifies that the maximum number of event names allowed has been reached. The maximum is 1000.
     */
    SIMCONNECT_EXCEPTION_TOO_MANY_EVENT_NAMES,

    /**
     * Specifies that the event ID has been used already. This can occur with calls to
     * SimConnect_MapClientEventToSimEvent, or SimConnect_SubscribeToSystemEvent.
     */
    SIMCONNECT_EXCEPTION_EVENT_ID_DUPLICATE,

    /**
     * Specifies that the maximum number of mappings allowed has been reached. The maximum is 20.
     */
    SIMCONNECT_EXCEPTION_TOO_MANY_MAPS,

    /**
     * Specifies that the maximum number of objects allowed has been reached. The maximum is 1000.
     */
    SIMCONNECT_EXCEPTION_TOO_MANY_OBJECTS,

    /**
     * Specifies that the maximum number of requests allowed has been reached. The maximum is 1000.
     */
    SIMCONNECT_EXCEPTION_TOO_MANY_REQUESTS,

    /**
     * Specifies an invalid port number was requested.
     */
    SIMCONNECT_EXCEPTION_WEATHER_INVALID_PORT,

    /**
     * Specifies that the metar data supplied did not match the required format.
     */
    SIMCONNECT_EXCEPTION_WEATHER_INVALID_METAR,

    /**
     * Specifies that the weather observation requested was not available.
     */
    SIMCONNECT_EXCEPTION_WEATHER_UNABLE_TO_GET_OBSERVATION,

    /**
     * Specifies that the weather station could not be created.
     */
    SIMCONNECT_EXCEPTION_WEATHER_UNABLE_TO_CREATE_STATION,

    /**
     * Specifies that the weather station could not be removed.
     */
    SIMCONNECT_EXCEPTION_WEATHER_UNABLE_TO_REMOVE_STATION,

    /**
     * Specifies that the data type requested does not apply to the type of data requested. Typically, this occurs with
     * a fixed length string of the wrong length.
     */
    SIMCONNECT_EXCEPTION_INVALID_DATA_TYPE,

    /**
     * Specifies that the size of the data provided is not what is expected. This can occur when the size of a structure
     * provided does not match the size given, or a null string entry is made for a menu or sub-menu entry text, or data
     * with a size of zero is added to a data definition. It can also occur with an invalid request to
     * SimConnect_CreateClientData.
     */
    SIMCONNECT_EXCEPTION_INVALID_DATA_SIZE,

    /**
     * Specifies a generic data error. This error is used by the SimConnect_WeatherCreateThermal function to report
     * incorrect parameters, such as negative radii or values greater than the maximum allowed. It is also used by the
     * SimConnect_FlightSave and SimConnect_FlightLoad functions to report incorrect file types. It is also used by
     * other functions to report that flags or reserved parameters have not been set to zero.
     */
    SIMCONNECT_EXCEPTION_DATA_ERROR,

    /**
     * Specifies an invalid array has been sent to the SimConnect_SetDataOnSimObject function.
     */
    SIMCONNECT_EXCEPTION_INVALID_ARRAY,

    /**
     * Specifies that the attempt to create an AI object failed.
     */
    SIMCONNECT_EXCEPTION_CREATE_OBJECT_FAILED,

    /**
     * Specifies that the specified flight plan could not be found, or did not load correctly.
     */
    SIMCONNECT_EXCEPTION_LOAD_FLIGHTPLAN_FAILED,

    /**
     * Specifies that the operation requested does not apply to the object type, for example trying to set a flight plan
     * on an object that is not an aircraft will result in this error.
     */
    SIMCONNECT_EXCEPTION_OPERATION_INVALID_FOR_OBJECT_TYPE,

    /**
     * Specifies that the AI operation requested cannot be completed, such as requesting that an object be removed when
     * the client did not create that object. This error also applies to the Weather system.
     */
    SIMCONNECT_EXCEPTION_ILLEGAL_OPERATION,

    /**
     * Specifies that the client has already subscribed to that event.
     */
    SIMCONNECT_EXCEPTION_ALREADY_SUBSCRIBED,

    /**
     * Specifies that the member of the enumeration provided was not valid. Currently this is only used if an unknown
     * type is provided to SimConnect_RequestDataOnSimObjectType.
     */
    SIMCONNECT_EXCEPTION_INVALID_ENUM,

    /**
     * Specifies that there is a problem with a data definition. Currently this is only used if a variable length
     * definition is sent with SimConnect_RequestDataOnSimObject.
     */
    SIMCONNECT_EXCEPTION_DEFINITION_ERROR,

    /**
     * Specifies that the ID has already been used. This can occur with menu IDs, or with the IDs provided to
     * SimConnect_AddToDataDefinition, SimConnect_AddClientEventToNotificationGroup or SimConnect_MapClientDataNameToID.
     */
    SIMCONNECT_EXCEPTION_DUPLICATE_ID,

    /**
     * Specifies that the datum ID is not recognized. This currently occurs with a call to the
     * SimConnect_SetDataOnSimObject function.
     */
    SIMCONNECT_EXCEPTION_DATUM_ID,

    /**
     * Specifies that the radius given in the SimConnect_RequestDataOnSimObjectType was outside the acceptable range,
     * or with an invalid request to SimConnect_CreateClientData.
     */
    SIMCONNECT_EXCEPTION_OUT_OF_BOUNDS,

    /**
     * Specifies that a client data area with the name requested by a call to SimConnect_MapClientDataNameToID has
     * already been created by another addon. Try again with a different name.
     */
    SIMCONNECT_EXCEPTION_ALREADY_CREATED,

    /**
     * Specifies that an attempt to create an ATC controlled AI object failed because the location of the object is
     * outside the reality bubble.
     */
    SIMCONNECT_EXCEPTION_OBJECT_OUTSIDE_REALITY_BUBBLE,

    /**
     * Specifies that an attempt to create an AI object failed because of an error with the container system for the
     * object.
     */
    SIMCONNECT_EXCEPTION_OBJECT_CONTAINER,

    /**
     * Specifies that an attempt to create an AI object failed because of an error with the AI system for the object.
     */
    SIMCONNECT_EXCEPTION_OBJECT_AI,

    /**
     * Specifies that an attempt to create an AI object failed because of an error with the ATC system for the object.
     */
    SIMCONNECT_EXCEPTION_OBJECT_ATC,

    /**
     * Specifies that an attempt to create an AI object failed because of a scheduling problem.
     */
    SIMCONNECT_EXCEPTION_OBJECT_SCHEDULE,

    /**
     * Specifies that an attempt to retrieve jetway data using SimConnect_RequestJetwayData has caused an exception.
     */
    SIMCONNECT_EXCEPTION_JETWAY_DATA,

    /**
     * Specifies that the given action cannot be found when using the SimConnect_ExecuteAction function.
     */
    SIMCONNECT_EXCEPTION_ACTION_NOT_FOUND,

    /**
     * Specifies that the given action does not exist when using the SimConnect_ExecuteAction function.
     */
    SIMCONNECT_EXCEPTION_NOT_AN_ACTION,

    /**
     * Specifies that the wrong parameters have been given to the function SimConnect_ExecuteAction.
     */
    SIMCONNECT_EXCEPTION_INCORRECT_ACTION_PARAMS,

    /**
     * This means that the wrong name/hash has been passed to the SimConnect_GetInputEvent function.
     */
    SIMCONNECT_EXCEPTION_GET_INPUT_EVENT_FAILED,

    /**
     * This means that the wrong name/hash has been passed to the SimConnect_SetInputEvent function.
     */
    SIMCONNECT_EXCEPTION_SET_INPUT_EVENT_FAILED,

    /**
     * Specifies an unknown exception type.
     */
    UNKNOWN;

    /**
     * Returns the exception type specified by the given identifier.
     *
     * @param id Identifier of the exception type.
     * @return Exception type of the identifier.
     */
    public static ExceptionType ofId(int id) {
        return switch (id) {
            case 0 -> SIMCONNECT_EXCEPTION_NONE;
            case 1 -> SIMCONNECT_EXCEPTION_ERROR;
            case 2 -> SIMCONNECT_EXCEPTION_SIZE_MISMATCH;
            case 3 -> SIMCONNECT_EXCEPTION_UNRECOGNIZED_ID;
            case 4 -> SIMCONNECT_EXCEPTION_UNOPENED;
            case 5 -> SIMCONNECT_EXCEPTION_VERSION_MISMATCH;
            case 6 -> SIMCONNECT_EXCEPTION_TOO_MANY_GROUPS;
            case 7 -> SIMCONNECT_EXCEPTION_NAME_UNRECOGNIZED;
            case 8 -> SIMCONNECT_EXCEPTION_TOO_MANY_EVENT_NAMES;
            case 9 -> SIMCONNECT_EXCEPTION_EVENT_ID_DUPLICATE;
            case 10 -> SIMCONNECT_EXCEPTION_TOO_MANY_MAPS;
            case 11 -> SIMCONNECT_EXCEPTION_TOO_MANY_OBJECTS;
            case 12 -> SIMCONNECT_EXCEPTION_TOO_MANY_REQUESTS;
            case 13 -> SIMCONNECT_EXCEPTION_WEATHER_INVALID_PORT;
            case 14 -> SIMCONNECT_EXCEPTION_WEATHER_INVALID_METAR;
            case 15 -> SIMCONNECT_EXCEPTION_WEATHER_UNABLE_TO_GET_OBSERVATION;
            case 16 -> SIMCONNECT_EXCEPTION_WEATHER_UNABLE_TO_CREATE_STATION;
            case 17 -> SIMCONNECT_EXCEPTION_WEATHER_UNABLE_TO_REMOVE_STATION;
            case 18 -> SIMCONNECT_EXCEPTION_INVALID_DATA_TYPE;
            case 19 -> SIMCONNECT_EXCEPTION_INVALID_DATA_SIZE;
            case 20 -> SIMCONNECT_EXCEPTION_DATA_ERROR;
            case 21 -> SIMCONNECT_EXCEPTION_INVALID_ARRAY;
            case 22 -> SIMCONNECT_EXCEPTION_CREATE_OBJECT_FAILED;
            case 23 -> SIMCONNECT_EXCEPTION_LOAD_FLIGHTPLAN_FAILED;
            case 24 -> SIMCONNECT_EXCEPTION_OPERATION_INVALID_FOR_OBJECT_TYPE;
            case 25 -> SIMCONNECT_EXCEPTION_ILLEGAL_OPERATION;
            case 26 -> SIMCONNECT_EXCEPTION_ALREADY_SUBSCRIBED;
            case 27 -> SIMCONNECT_EXCEPTION_INVALID_ENUM;
            case 28 -> SIMCONNECT_EXCEPTION_DEFINITION_ERROR;
            case 29 -> SIMCONNECT_EXCEPTION_DUPLICATE_ID;
            case 30 -> SIMCONNECT_EXCEPTION_DATUM_ID;
            case 31 -> SIMCONNECT_EXCEPTION_OUT_OF_BOUNDS;
            case 32 -> SIMCONNECT_EXCEPTION_ALREADY_CREATED;
            case 33 -> SIMCONNECT_EXCEPTION_OBJECT_OUTSIDE_REALITY_BUBBLE;
            case 34 -> SIMCONNECT_EXCEPTION_OBJECT_CONTAINER;
            case 35 -> SIMCONNECT_EXCEPTION_OBJECT_AI;
            case 36 -> SIMCONNECT_EXCEPTION_OBJECT_ATC;
            case 37 -> SIMCONNECT_EXCEPTION_OBJECT_SCHEDULE;
            case 38 -> SIMCONNECT_EXCEPTION_JETWAY_DATA;
            case 39 -> SIMCONNECT_EXCEPTION_ACTION_NOT_FOUND;
            case 40 -> SIMCONNECT_EXCEPTION_NOT_AN_ACTION;
            case 41 -> SIMCONNECT_EXCEPTION_INCORRECT_ACTION_PARAMS;
            case 42 -> SIMCONNECT_EXCEPTION_GET_INPUT_EVENT_FAILED;
            case 43 -> SIMCONNECT_EXCEPTION_SET_INPUT_EVENT_FAILED;
            default -> UNKNOWN;
        };
    }
}