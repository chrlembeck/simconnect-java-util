package org.lembeck.fs.simconnect;


import org.lembeck.fs.simconnect.constants.*;
import org.lembeck.fs.simconnect.request.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicInteger;

import static java.nio.ByteOrder.LITTLE_ENDIAN;

public class SimConnect {

    private SocketChannel channel;

    private final ResponseReceiver responseReceiver;

    private final ByteBuffer outBuffer;

    private final AtomicInteger lastUniqueRequestIdentifier = new AtomicInteger(0);

    private final static AtomicInteger lastUserRequestIdentifier = new AtomicInteger(0);

    private final static AtomicInteger lastUserDefineIdentifier = new AtomicInteger(0);

    private final static AtomicInteger lastUserEventIdentifier = new AtomicInteger(0);

    private Thread responseReceiverThread;

    /**
     * Creates a new instance of the simconnect communication client. Even if it is possible to use multiple client
     * instances in an application, it is recommended to only create one instance of this class to avoid confusion
     * in communication.
     */
    public SimConnect() {
        responseReceiver = new ResponseReceiver();
        this.outBuffer = ByteBuffer.allocateDirect(64 * 1024);
        outBuffer.order(LITTLE_ENDIAN);
    }

    /**
     * Establishes the socket connection from this client to the simulator.
     *
     * @param hostname Name of the machine, the simulator is running on.
     * @param port     Port on wich the simconnect interface of the simulator is listening. This port can be configured
     *                 in the simulators <code>simconnect.xml</code> file.
     * @param appName  Name of this application.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/SimConnect_XML_Definition.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/SimConnect_XML_Definition.htm</a>
     * @see #connect(SocketAddress, String)
     */
    public void connect(String hostname, int port, String appName) throws IOException {
        connect(new InetSocketAddress(hostname, port), appName);
    }

    /**
     * Establishes the socket connection from this client to the simulator.
     *
     * @param address Address on wich the simconnect interface of the simulator is listening. This address can be
     *                configured in the simulators <code>simconnect.xml</code> file.
     * @param appName Name of this application.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/SimConnect_XML_Definition.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/SimConnect_XML_Definition.htm</a>
     * @see #connect(String, int, String)
     */
    public void connect(SocketAddress address, String appName) throws IOException {
        channel = SocketChannel.open(address);
        responseReceiver.setChannel(channel);
        write(new HelloRequest(appName));
        responseReceiverThread = new Thread(responseReceiver);
        responseReceiverThread.setDaemon(false);
        responseReceiverThread.setName("Simconnect Request Receiver");
        responseReceiverThread.start();
    }

    /**
     * Informs the response receiver to stop handling incoming responses and closes the socket connection to the
     * simulator.
     */
    public void close() {
        try {
            responseReceiver.stop();
            channel.close();
            channel = null;
        } catch (IOException ioe) {
            // ignore
        }
    }

    /**
     * Returns the response handler of this client. This is the place you can add listeners to receive the data that
     * will be sent by the simulator.
     *
     * @return The ResponseReceiver object that handles th incoming responses from the simulator.
     */
    public ResponseReceiver getRequestReceiver() {
        return responseReceiver;
    }

    /**
     * Sends a single request from this client to the simulator. Can be used to send custom requests. Normally this
     * method does not need to be called directly. Instead, the specific methods for each method can be used, which
     * generate the requests and then send them via this method.
     *
     * @param request The request object that should be sent to the server.
     * @param <T>     Type of the request object.
     * @return The object that was passed to this method. Each request to the simulator via the simconnect api will be
     * done with a unique request identifier. The Identifier will be generated within this method and set to the passed
     * request object. Also the size of the request will be calculated during the process of sending an updated in the
     * object. In order to be able to read the identifier and the calculated size, the object is returned again at this
     * point.
     */
    public <T extends SimRequest> T write(T request) throws IOException {
        if (channel == null) {
            throw new IllegalStateException("""
                    Can not write requests to the interface before the connection is not established. \
                    Please call the connect() method before.""");
        }
        request.setIdentifier(lastUniqueRequestIdentifier.incrementAndGet());
        synchronized (outBuffer) {
            outBuffer.clear();
            request.write(outBuffer);
            outBuffer.flip();
            channel.write(outBuffer);
        }
        return request;
    }

    /**
     * The SimConnect_MapClientEventToSimEvent function associates a client defined event ID with a Microsoft Flight
     * Simulator event name.
     *
     * @param eventID   Specifies the ID of the client event.
     * @param eventName Specifies the Microsoft Flight Simulator event name. Refer to the Event IDs document for a list
     *                  of event names (listed under String Name). If the event name includes one or more periods (such
     *                  as "Custom.Event" in the example below) then they are custom events specified by the client, and
     *                  will only be recognized by another client (and not Microsoft Flight Simulator) that has been
     *                  coded to receive such events. No Microsoft Flight Simulator events include periods. If no entry
     *                  is made for this parameter, the event is private to the client.
     *                  Alternatively enter a decimal number in the format "#nnnn" or a hex number in the format
     *                  "#0xnnnn", where these numbers are in the range THIRD_PARTY_EVENT_ID_MIN and
     *                  THIRD_PARTY_EVENT_ID_MAX, in order to receive events from third-party add-ons to Flight
     *                  Simulator X.
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_MapClientEventToSimEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_MapClientEventToSimEvent.htm</a>
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/Event_IDs/Event_IDs.htm">https://docs.flightsimulator.com/html/Programming_Tools/Event_IDs/Event_IDs.htm</a>
     */
    public MapClientEventToSimEventRequest mapClientEventToSimEvent(int eventID, String eventName) throws IOException {
        return write(new MapClientEventToSimEventRequest(eventID, eventName));
    }

    /**
     * The SimConnect_TransmitClientEvent function is used to request that the Microsoft Flight Simulator server
     * transmit to all SimConnect clients the specified client event.
     *
     * @param objectID      Specifies the ID of the server defined object. If this parameter is set to
     *                      SIMCONNECT_OBJECT_ID_USER, then the transmitted event will be sent to the other clients in
     *                      priority order. If this parameters contains another object ID, then the event will be sent
     *                      direct to that sim-object, and no other clients will receive it.
     * @param clientEventID Specifies the ID of the client event.
     * @param data          Double word containing any additional number required by the event. This is often zero. If
     *                      the event is a Microsoft Flight Simulator event, then refer to the Event IDs document for
     *                      information on this additional value. If the event is a custom event, then any value put in
     *                      this parameter will be available to the clients that receive the event.
     * @param priority      This specifies the priority to send the message to all clients with this priority.
     *                      To receive the event notification other SimConnect clients must have subscribed to receive
     *                      the event. See the explanation of SimConnect Priorities. The exception to the default
     *                      behavior is set by the SIMCONNECT_EVENT_FLAG_GROUPID_IS_PRIORITY flag, described below.
     * @param eventFlag     One or more of the flags shown in {@link EventFlag}.
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_TransmitClientEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_TransmitClientEvent.htm</a>
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/SimConnect_API_Reference.htm#simconnect-priorities">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/SimConnect_API_Reference.htm#simconnect-priorities</a>
     * @see EventFlag
     */
    public TransmitClientEventRequest transmitClientEvent(int objectID, int clientEventID, int data, Priority priority,
                                                          int eventFlag) throws IOException {
        return write(new TransmitClientEventRequest(objectID, clientEventID, data, priority, eventFlag));
    }

    /**
     * The SimConnect_SetSystemEventState function is used to turn requests for event information from the server on and
     * off.
     *
     * @param clientEventID Specifies the ID of the client event that is to have its state changed.
     * @param state         Double word containing the state (one member of SIMCONNECT_STATE).
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetSystemEventState.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetSystemEventState.htm</a>
     */
    public SetSystemEventStateRequest setSystemEventState(int clientEventID, State state) throws IOException {
        return write(new SetSystemEventStateRequest(clientEventID, state));
    }

    /**
     * The SimConnect_AddClientEventToNotificationGroup function is used to add an individual client defined event to a
     * notification group.
     *
     * @param notificationGroupID Specifies the ID of the client defined group.
     * @param clientEventID       Specifies the ID of the client defined event.
     * @param maskable            True indicates that the event will be masked by this client and will not be
     *                            transmitted to any more clients, possibly including Microsoft Flight Simulator itself
     *                            (if the priority of the client exceeds that of Flight Simulator).
     *                            False is the default. See the explanation of SimConnect Priorities.
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_AddClientEventToNotificationGroup.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_AddClientEventToNotificationGroup.htm</a>
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/SimConnect_API_Reference.htm#simconnect-priorities">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/SimConnect_API_Reference.htm#simconnect-priorities</a>
     */
    public AddClientEventToNotificationGroupRequest addClientEventToNotificationGroup(int notificationGroupID,
                                                                                      int clientEventID, boolean maskable) throws IOException {
        return write(new AddClientEventToNotificationGroupRequest(notificationGroupID, clientEventID, maskable));
    }

    /**
     * The SimConnect_RemoveClientEvent function is used to remove a client defined event from a notification group.
     *
     * @param notificationGroupID Specifies the ID of the client defined group.
     * @param clientEventID       Specifies the ID of the client defined event ID that is to be removed from the group.
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RemoveClientEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RemoveClientEvent.htm</a>
     */
    public RemoveClientEventRequest removeClientEvent(int notificationGroupID, int clientEventID) throws IOException {
        return write(new RemoveClientEventRequest(notificationGroupID, clientEventID));
    }

    /**
     * The SimConnect_SetNotificationGroupPriority function is used to set the priority for a notification group.
     *
     * @param notificationGroupID Specifies the ID of the client defined group.
     * @param priority            The group's priority. See the explanation of SimConnect Priorities.
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/General/SimConnect_SetNotificationGroupPriority.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/General/SimConnect_SetNotificationGroupPriority.htm</a>
     */
    public SetNotificationGroupPriorityRequest setNotificationGroupPriority(int notificationGroupID,
                                                                            Priority priority) throws IOException {
        return write(new SetNotificationGroupPriorityRequest(notificationGroupID, priority));
    }

    /**
     * The SimConnect_ClearNotificationGroup function is used to remove all the client defined events from a
     * notification group.
     *
     * @param notificationGroupID Specifies the ID of the client defined group that is to have all its events removed.
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_ClearNotificationGroup.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_ClearNotificationGroup.htm</a>
     */
    public ClearNotificationGroupRequest clearNotificationGroup(int notificationGroupID) throws IOException {
        return write(new ClearNotificationGroupRequest(notificationGroupID));
    }

    /**
     * The SimConnect_RequestNotificationGroup function is used to request events are transmitted from a notification
     * group, when the simulation is in Dialog Mode.
     *
     * @param notificationGroupID Specifies the ID of the client defined input group that is to have all its events removed.
     * @param reserved            Reserved for future use.
     * @param flags               Reserved for future use.
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestNotificationGroup.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestNotificationGroup.htm</a>
     */
    public RequestNotificationGroupRequest requestNotificationGroup(int notificationGroupID, int reserved,
                                                                    int flags) throws IOException {
        return write(new RequestNotificationGroupRequest(notificationGroupID, reserved, flags));
    }

    /**
     * The SimConnect_AddToDataDefinition function is used to add a Microsoft Flight Simulator simulation variable name
     * to a client defined object definition.
     *
     * @param defineID  Specifies the ID of the client defined data definition.
     * @param datumName Specifies the name of the Microsoft Flight Simulator simulation variable. See the Simulation
     *                  Variables documents for tables of variable names. If an index is required then it should be
     *                  appended to the variable name following a colon, see the example for DEFINITION_2 below.
     *                  Indexes are numbered from 1 (not zero). Simulation variable names are not case-sensitive
     *                  (so can be entered in upper or lower case).
     * @param unitsName Specifies the units of the variable. See Simulation Variable Units for tables of acceptable unit
     *                  names. It is possible to specify different units to receive the data in, from those specified in
     *                  the Simulation Variables document. The alternative units must come under the same heading (such
     *                  as Angular Velocity, or Volume, as specified in the Units of Measurement section of the
     *                  Simulation Variables document). For strings and structures enter "NULL" for this parameter.
     * @param datumType One member of the SIMCONNECT_DATATYPE enumeration type. This parameter is used to determine
     *                  what datatype should be used to return the data. The default is SIMCONNECT_DATATYPE_FLOAT64.
     *                  Note that the structure data types are legitimate parameters here.
     * @param epsilon   If data is requested only when it changes (see the flags parameter of
     *                  SimConnect_RequestDataOnSimObject) a change will only be reported if it is greater than the
     *                  value of this parameter (not greater than or equal to). The default is zero, so even the tiniest
     *                  change will initiate the transmission of data. Set this value appropriately so insignificant
     *                  changes are not transmitted. This can be used with integer data, the floating point fEpsilon
     *                  value is first truncated to its integer component before the comparison is made (for example,
     *                  an fEpsilon value of 2.9 truncates to 2, so a data change of 2 will not trigger a transmission,
     *                  and a change of 3 will do so).
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_AddToDataDefinition.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_AddToDataDefinition.htm</a>
     */
    public AddToDataDefinitionRequest addToDataDefinition(int defineID, String datumName, String unitsName,
                                                          DataType datumType, float epsilon) throws IOException {
        return write(new AddToDataDefinitionRequest(defineID, datumName, unitsName, datumType, epsilon));
    }

    /**
     * The SimConnect_AddToDataDefinition function is used to add a Microsoft Flight Simulator simulation variable name
     * to a client defined object definition.
     *
     * @param defineID  Specifies the ID of the client defined data definition.
     * @param datumName Specifies the name of the Microsoft Flight Simulator simulation variable. See the Simulation
     *                  Variables documents for tables of variable names. If an index is required then it should be
     *                  appended to the variable name following a colon, see the example for DEFINITION_2 below.
     *                  Indexes are numbered from 1 (not zero). Simulation variable names are not case-sensitive
     *                  (so can be entered in upper or lower case).
     * @param unitsName Specifies the units of the variable. See Simulation Variable Units for tables of acceptable unit
     *                  names. It is possible to specify different units to receive the data in, from those specified in
     *                  the Simulation Variables document. The alternative units must come under the same heading (such
     *                  as Angular Velocity, or Volume, as specified in the Units of Measurement section of the
     *                  Simulation Variables document). For strings and structures enter "NULL" for this parameter.
     * @param datumType One member of the SIMCONNECT_DATATYPE enumeration type. This parameter is used to determine
     *                  what datatype should be used to return the data. The default is SIMCONNECT_DATATYPE_FLOAT64.
     *                  Note that the structure data types are legitimate parameters here.
     * @param epsilon   If data is requested only when it changes (see the flags parameter of
     *                  SimConnect_RequestDataOnSimObject) a change will only be reported if it is greater than the
     *                  value of this parameter (not greater than or equal to). The default is zero, so even the tiniest
     *                  change will initiate the transmission of data. Set this value appropriately so insignificant
     *                  changes are not transmitted. This can be used with integer data, the floating point fEpsilon
     *                  value is first truncated to its integer component before the comparison is made (for example,
     *                  an fEpsilon value of 2.9 truncates to 2, so a data change of 2 will not trigger a transmission,
     *                  and a change of 3 will do so).
     * @param datumID   Specifies a client defined datum ID. The default is zero. Use this to identify the data received
     *                  if the data is being returned in tagged format (see the flags parameter of
     *                  SimConnect_RequestDataOnSimObject. There is no need to specify datum IDs if the data is not
     *                  being returned in tagged format.
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_AddToDataDefinition.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_AddToDataDefinition.htm</a>
     */
    public AddToDataDefinitionRequest addToDataDefinition(int defineID, String datumName, String unitsName,
                                                          DataType datumType, float epsilon, int datumID) throws IOException {
        return write(new AddToDataDefinitionRequest(defineID, datumName, unitsName, datumType, epsilon, datumID));
    }

    /**
     * The SimConnect_ClearDataDefinition function is used to remove all simulation variables from a client defined data
     * definition.
     *
     * @param dataDefinitionID Specifies the ID of the client defined data definition.
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_ClearDataDefinition.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_ClearDataDefinition.htm</a>
     */
    public ClearDataDefinitionRequest clearDataDefinition(int dataDefinitionID) throws IOException {
        return write(new ClearDataDefinitionRequest(dataDefinitionID));
    }

    /**
     * The SimConnect_RequestDataOnSimObject function is used to request when the SimConnect client is to receive data
     * values for a specific object.
     *
     * @param dataRequestID    Specifies the ID of the client defined group.
     * @param dataDefinitionID Specifies the ID of the client defined data definition.
     * @param objectID         Specifies the ID of the Microsoft Flight Simulator object that the data should be about.
     *                         This ID can be SIMCONNECT_OBJECT_ID_USER (to specify the user's aircraft) or obtained
     *                         from a SIMCONNECT_RECV_SIMOBJECT_DATA_BYTYPE structure after a call to
     *                         SimConnect_RequestDataOnSimObjectType. Also refer to the note on developing clients for
     *                         multiplayer mode in the Remarks section below.
     * @param period           One member of the SIMCONNECT_PERIOD enumeration type, specifying how often the data is to
     *                         be sent by the server and received by the client.
     * @param dataRequestFlags A DWORD containing one or more of the values shown in the table below this one.
     * @param origin           The number of Period events that should elapse before transmission of the data begins.
     *                         The default is zero, which means transmissions will start immediately.
     * @param interval         The number of Period events that should elapse between transmissions of the data. The
     *                         default is zero, which means the data is transmitted every Period.
     * @param limit            The number of times the data should be transmitted before this communication is ended.
     *                         The default is zero, which means the data should be transmitted endlessly.
     * @return The Object that represents the message that was sent to the simulator.
     * @see DataRequestFlag
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestDataOnSimObject.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestDataOnSimObject.htm</a>
     */
    public RequestDataOnSimObjectRequest requestDataOnSimObject(int dataRequestID, int dataDefinitionID, int objectID,
                                                                SimconnectPeriod period, int dataRequestFlags, int origin, int interval, int limit) throws IOException {
        return write(new RequestDataOnSimObjectRequest(dataRequestID, dataDefinitionID, objectID, period,
                dataRequestFlags, origin, interval, limit));
    }

    /**
     * The SimConnect_RequestDataOnSimObjectType function is used to retrieve information about simulation objects of a
     * given type that are within a specified radius of the user's aircraft.
     *
     * @param requestID    Specifies the ID of the client defined request. This is used later by the client to identify
     *                     which data has been received. This value should be unique for each request, re-using a
     *                     RequestID will overwrite any previous request using the same ID.
     * @param defineID     Specifies the ID of the client defined data definition.
     * @param radiusMeters Double word containing the radius in meters. If this is set to zero only information on the
     *                     user aircraft will be returned, although this value is ignored if type is set to
     *                     SIMCONNECT_SIMOBJECT_TYPE_USER. The error SIMCONNECT_EXCEPTION_OUT_OF_BOUNDS will be returned
     *                     if a radius is given and it exceeds the maximum allowed (200,000 meters, or 200 Km).
     * @param type         Specifies the type of object to receive information on. One member of the
     *                     SIMCONNECT_SIMOBJECT_TYPE enumeration type
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestDataOnSimObjectType.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestDataOnSimObjectType.htm</a>
     */
    public RequestDataOnSimObjectTypeRequest requestDataOnSimObjectType(int requestID, int defineID, int radiusMeters,
                                                                        SimObjectType type) throws IOException {
        return write(new RequestDataOnSimObjectTypeRequest(requestID, defineID, radiusMeters, type));
    }

    /**
     * The SimConnect_SetDataOnSimObject function is used to make changes to the data properties of an object.
     *
     * @param dataDefinitionID Specifies the ID of the client defined data definition.
     * @param objectID         Specifies the ID of the Microsoft Flight Simulator object that the data should be about.
     *                         This ID can be SIMCONNECT_OBJECT_ID_USER (to specify the user's aircraft) or obtained from
     *                         a SIMCONNECT_RECV_SIMOBJECT_DATA_BYTYPE structure after a call to
     *                         SimConnect_RequestDataOnSimObjectType. Also refer to the note on "multiplayer mode" at
     *                         the end of the remarks for SimConnect_RequestDataOnSimObject.
     * @param dataSetFlag      Default or tagged format.
     * @param arrayCount       Specifies the number of elements in the data array. A count of zero is interpreted as one
     *                         element. Ensure that the data array has been initialized completely before transmitting it
     *                         to Microsoft Flight Simulator. Failure to properly initialize all array elements may
     *                         result in unexpected behavior.
     * @param unitSize         Specifies the size of each element in the data array in bytes.
     * @param data             Pointer to the data that is to be written. If the data is not in tagged format, this
     *                         should point to the block of data. If the data is in tagged format this should point to
     *                         the first tag name (datumID), which is always four bytes long, which should be followed
     *                         by the data itself. Any number of tag name/value pairs can be specified this way, the
     *                         server will use the cbUnitSize parameter to determine how much data has been sent.
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetDataOnSimObject.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetDataOnSimObject.htm</a>
     */
    public SetDataOnSimObjectRequest setDataOnSimObject(int dataDefinitionID, int objectID, DataSetFlag dataSetFlag,
                                                        int arrayCount, int unitSize, byte[] data) throws IOException {
        return write(new SetDataOnSimObjectRequest(dataDefinitionID, objectID, dataSetFlag, arrayCount, unitSize, data));
    }

    /**
     * The SimConnect_SetInputGroupPriority function is used to set the priority for a specified input group object.
     *
     * @param groupID  Specifies the ID of the client defined input group that the priority setting is to apply to.
     * @param priority Specifies the priority setting for the input group. See the explanation of SimConnect Priorities.
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetInputGroupPriority.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetInputGroupPriority.htm</a>
     */
    public SetInputGroupPriorityRequest setInputGroupPriority(int groupID, Priority priority) throws IOException {
        return write(new SetInputGroupPriorityRequest(groupID, priority));
    }

    /**
     * The SimConnect_RemoveInputEvent function is used to remove an input event from a specified input group object.
     *
     * @param groupID         Specifies the ID of the client defined input group from which the event is to be removed.
     * @param inputDefinition String containing the input definition.
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RemoveInputEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RemoveInputEvent.htm</a>
     */
    public RemoveInputEventRequest removeInputEvent(int groupID, String inputDefinition) throws IOException {
        return write(new RemoveInputEventRequest(groupID, inputDefinition));
    }

    /**
     * The SimConnect_ClearInputGroup function is used to remove all the input events from a specified input group object.
     *
     * @param groupID Specifies the ID of the client defined input group that is to have all its events removed.
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_ClearInputGroup.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_ClearInputGroup.htm</a>
     */
    public ClearInputGroupRequest clearInputGroup(int groupID) throws IOException {
        return write(new ClearInputGroupRequest(groupID));
    }

    /**
     * The SimConnect_SetInputGroupState function is used to turn requests for input event information from the server
     * on and off.
     *
     * @param groupID Specifies the ID of the client defined input group that is to have its state changed.
     * @param state   Double word containing the new state. One member of the SIMCONNECT_STATE enumeration type.
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetInputGroupState.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SetInputGroupState.htm</a>
     * @see State
     */
    public SetInputGroupStateRequest SetInputGroupState(int groupID, State state) throws IOException {
        return write(new SetInputGroupStateRequest(groupID, state));
    }

    /**
     * The SimConnect_RequestReservedKey function is used to request a specific keyboard TAB-key combination applies
     * only to this client.
     *
     * @param eventID    Specifies the client defined event ID.
     * @param keyChoice1 String containing the first key choice. Refer to the list below for all the choices that can
     *                   be entered for these three parameters.
     * @param keyChoice2 String containing the second key choice.
     * @param keyChoice3 String containing the third key choice.
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestReservedKey.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_RequestReservedKey.htm</a>
     */
    public RequestReservedKeyRequest requestReservedKey(int eventID, String keyChoice1, String keyChoice2,
                                                        String keyChoice3) throws IOException {
        return write(new RequestReservedKeyRequest(eventID, keyChoice1, keyChoice2, keyChoice3));
    }

    /**
     * The SimConnect_SubscribeToSystemEvent function is used to request that a specific system event is notified to
     * the client.
     *
     * @param eventID   Specifies the ID of the client event.
     * @param eventName The string name for the requested system event (note that the event names are not
     *                  case-sensitive). Unless otherwise stated in the Description,
     *                  notifications of the event are returned in a SIMCONNECT_RECV_EVENT structure (identify the event
     *                  from the EventID given with this function).
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SubscribeToSystemEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SubscribeToSystemEvent.htm</a>
     */
    public SubscribeToSystemEventRequest subscribeToSystemEvent(int eventID,
                                                                SystemEventName eventName) throws IOException {
        return write(new SubscribeToSystemEventRequest(eventID, eventName));
    }

    /**
     * The SimConnect_SubscribeToSystemEvent function is used to request that a specific system event is notified to
     * the client.
     *
     * @param eventID   Specifies the ID of the client event.
     * @param eventName The system event. Unless otherwise stated in the Description,
     *                  notifications of the event are returned in a SIMCONNECT_RECV_EVENT structure (identify the event
     *                  from the EventID given with this function).
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SubscribeToSystemEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_SubscribeToSystemEvent.htm</a>
     */
    public SubscribeToSystemEventRequest subscribeToSystemEvent(int eventID, String eventName) throws IOException {
        return write(new SubscribeToSystemEventRequest(eventID, eventName));
    }

    /**
     * The SimConnect_UnsubscribeFromSystemEvent function is used to request that notifications are no longer received
     * for the specified system event.
     *
     * @param eventID Specifies the ID of the client event.
     * @return The Object that represents the message that was sent to the simulator.
     */
    public UnsubscribeFromSystemEventRequest unsubscribeFromSystemEvent(int eventID) throws IOException {
        return write(new UnsubscribeFromSystemEventRequest(eventID));
    }

    public AICreateParkedATCAircraftRequest aiCreateParkedATCAircraft(String containerTitle, String tailNumber,
                                                                      String airportIcaoID, int requestID) throws IOException {
        return write(new AICreateParkedATCAircraftRequest(containerTitle, tailNumber, airportIcaoID, requestID));
    }

    public AICreateEnrouteATCAircraftRequest aiCreateEnrouteATCAircraft(String containerTitle, String tailNumber,
                                                                        int flightNumber, String flightPlanPath, float flightPlanPosition, boolean touchAndGo,
                                                                        int requestID) throws IOException {
        return write(new AICreateEnrouteATCAircraftRequest(containerTitle, tailNumber, flightNumber, flightPlanPath,
                flightPlanPosition, touchAndGo, requestID));
    }

    public AICreateNonATCAircraftRequest aiCreateNonATCAircraft(String containerTitle, String tailNumber,
                                                                InitPosition initPosition, int requestID) throws IOException {
        return write(new AICreateNonATCAircraftRequest(containerTitle, tailNumber, initPosition, requestID));
    }

    public AICreateSimulatedObjectRequest aiCreateSimulatedObject(String containerTitle, InitPosition initPosition,
                                                                  int requestID) throws IOException {
        return write(new AICreateSimulatedObjectRequest(containerTitle, initPosition, requestID));
    }

    public AIReleaseControlRequest aiReleaseControl(int objectID, int requestID) throws IOException {
        return write(new AIReleaseControlRequest(objectID, requestID));
    }

    public AIRemoveObjectRequest aiRemoveObject(int objectID, int requestID) throws IOException {
        return write(new AIRemoveObjectRequest(objectID, requestID));
    }

    public AISetAircraftFlightPlanRequest aiSetAircraftFlightPlan(int objectID, String flightPlanPath,
                                                                  int requestID) throws IOException {
        return write(new AISetAircraftFlightPlanRequest(objectID, flightPlanPath, requestID));
    }

    public CameraSetRelative6DofRequest cameraSetRelative6DOF(float deltaX, float deltaY, float deltaZ, float pitchDeg,
                                                              float bankDeg, float headingDeg) throws IOException {
        return write(new CameraSetRelative6DofRequest(deltaX, deltaY, deltaZ, pitchDeg, bankDeg, headingDeg));
    }

    /**
     * The SimConnect_RequestSystemState function is used to request information from a number of Microsoft Flight
     * Simulator system components.
     *
     * @param requestID The client defined request ID.
     * @param state     A string identifying the system function. One of 'AircraftLoaded', 'DialogMode', 'FlightLoaded',
     *                  'FlightPlan' and 'Sim'.
     * @return The Object that represents the message that was sent to the simulator.
     * @see SystemState#getStateName()
     */
    public RequestSystemStateRequest requestSystemState(int requestID, String state) throws IOException {
        return write(new RequestSystemStateRequest(requestID, state));
    }

    /**
     * The SimConnect_RequestSystemState function is used to request information from a number of Microsoft Flight
     * Simulator system components.
     *
     * @param requestID The client defined request ID.
     * @param state     The enum identifying the system function.
     * @return The Object that represents the message that was sent to the simulator.
     * @see SystemState
     */
    public RequestSystemStateRequest requestSystemState(int requestID, SystemState state) throws IOException {
        return write(new RequestSystemStateRequest(requestID, state));
    }

    public SetSystemStateRequest setSystemState(String state, int intParam, float floatParam,
                                                String stringParam) throws IOException {
        return write(new SetSystemStateRequest(state, intParam, floatParam, stringParam));
    }

    public MapClientDataNameToIDRequest mapClientDataNameToID(String clientDataName,
                                                              int clientDataID) throws IOException {
        return write(new MapClientDataNameToIDRequest(clientDataName, clientDataID));
    }

    public CreateClientDataRequest createClientData(int clientDataID, int dataSize,
                                                    boolean readonly) throws IOException {
        return write(new CreateClientDataRequest(clientDataID, dataSize, readonly));
    }

    public AddToClientDataDefinitionRequest addToClientDataDefinition(int defineID, int offset, int sizeOrType,
                                                                      float epsilon, int datumID) throws IOException {
        return write(new AddToClientDataDefinitionRequest(defineID, offset, sizeOrType, epsilon, datumID));
    }

    public ClearClientDataDefinitionRequest clearClientDataDefinition(int defineID) throws IOException {
        return write(new ClearClientDataDefinitionRequest(defineID));
    }

    public RequestClientDataRequest requestClientData(int clientDataID, int requestID, int defineID,
                                                      SimconnectPeriod period, int flags, int origin, int interval, int limit) throws IOException {
        return write(new RequestClientDataRequest(clientDataID, requestID, defineID, period, flags, origin, interval,
                limit));
    }

    public SetClientDataDefinitionRequest setClientDataDefinition(int clientDataID, int defineID, boolean tagged,
                                                                  int reserved, int dataSize, byte[] data) throws IOException {
        return write(new SetClientDataDefinitionRequest(clientDataID, defineID, tagged, reserved, dataSize, data));
    }

    /**
     * The SimConnect_FlightLoad function is used to load an existing flight file.
     *
     * @param filename String containing the path to the flight file.
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Flights/SimConnect_FlightLoad.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Flights/SimConnect_FlightLoad.htm</a>
     */
    public FlightLoadRequest flightLoad(String filename) throws IOException {
        return write(new FlightLoadRequest(filename));
    }

    /**
     * The SimConnect_FlightSave function is used to save the current state of a flight to a flight file.
     *
     * @param filename    String containing the path to the flight file. Flight files have the extension .FLT, but no
     *                    need to enter an extension here.
     * @param title       String containing the title of the flight file. If this is NULL then the szFileName parameter
     *                    is used as the title.
     * @param description String containing the text to the description field of the flight file.
     * @param flags       Unused.
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Flights/SimConnect_FlightSave.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Flights/SimConnect_FlightSave.htm</a>
     */
    public FlightSaveRequest flightSave(String filename, String title, String description,
                                        int flags) throws IOException {
        return write(new FlightSaveRequest(filename, title, description, flags));
    }

    /**
     * The SimConnect_FlightPlanLoad function is used to load an existing flight plan file.
     *
     * @param filename String containing the path to the flight plan file. Flight plans have the extension .PLN, but no
     *                 need to enter an extension here. The easiest way to create flight plans is to create them from
     *                 within Microsoft Flight Simulator itself, and then save them off for use by the user or AI
     *                 controlled aircraft.
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Flights/SimConnect_FlightPlanLoad.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Flights/SimConnect_FlightPlanLoad.htm</a>
     */
    public FlightPlanLoadRequest flightPlanLoad(String filename) throws IOException {
        return write(new FlightPlanLoadRequest(filename));
    }

    public SubscribeToFacilitiesRequest subscribeToFacilities(FacilityListType facilityListType,
                                                              int requestId) throws IOException {
        return write(new SubscribeToFacilitiesRequest(facilityListType, requestId));
    }

    /**
     * The SimConnect_UnsubscribeToFacilities function is used to request that notifications of additions to the
     * facilities cache are no longer sent.
     *
     * @param facilityListType Specifies one member of the SIMCONNECT_FACILITY_LIST_TYPE enumeration type.
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_UnsubscribeToFacilities.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_UnsubscribeToFacilities.htm</a>
     */
    public UnsubscribeToFacilitiesRequest unsubscribeToFacilities(
            FacilityListType facilityListType) throws IOException {
        return write(new UnsubscribeToFacilitiesRequest(facilityListType));
    }

    /**
     * The SimConnect_RequestFacilitesList function is used to request a list of all the facilities of a given type
     * currently in the world, or within a radius of the aircraft depending on the requested type.
     *
     * @param facilityListType Specifies one member of the SIMCONNECT_FACILITY_LIST_TYPE enumeration type.
     * @param requestId        Specifies the client defined request ID. This will be returned along with the data.
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_RequestFacilitesList.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_RequestFacilitesList.htm</a>
     */
    public RequestFacilitesListRequest requestFacilitiesList(FacilityListType facilityListType,
                                                             int requestId) throws IOException {
        return write(new RequestFacilitesListRequest(facilityListType, requestId));
    }

    /**
     * The SimConnect_TransmitClientEvent_EX1 function is used to request that the Microsoft Flight Simulator server
     * transmit to all SimConnect clients the specified client event. This function is specifically designed to permit
     * the sending of multiple parameters for the key event (up to five), unlike the SimConnect_TransmitClientEvent
     * which only permits one.
     *
     * @param objectID  Specifies the ID of the server defined object. If this parameter is set to
     *                  SIMCONNECT_OBJECT_ID_USER, then the transmitted event will be sent to the other clients in
     *                  priority order. If this parameters contains another object ID, then the event will be sent
     *                  direct to that sim-object, and no other clients will receive it.
     * @param eventID   Specifies the ID of the client event.
     * @param priority  This specifies the priority to send the message to all clients with this priority. To receive
     *                  the event notification other SimConnect clients must have subscribed to receive the event. See
     *                  the explanation of SimConnect Priorities. The exception to the default behavior is set by the
     *                  SIMCONNECT_EVENT_FLAG_GROUPID_IS_PRIORITY flag, described below.
     * @param eventFlag One or more of the flags shown in {@link EventFlag}.
     * @param data0     Double word containing an additional number required by the event. If the event is a Microsoft
     *                  Flight Simulator event, then refer to the Event IDs document for information on this additional
     *                  value(s). If the event is a custom event, then any values put in these parameters will be
     *                  available to the clients that receive the event.
     * @param data1     Double word containing an additional number required by the event. If the event is a Microsoft
     *                  Flight Simulator event, then refer to the Event IDs document for information on this additional
     *                  value(s). If the event is a custom event, then any values put in these parameters will be
     *                  available to the clients that receive the event.
     * @param data2     Double word containing an additional number required by the event. If the event is a Microsoft
     *                  Flight Simulator event, then refer to the Event IDs document for information on this additional
     *                  value(s). If the event is a custom event, then any values put in these parameters will be
     *                  available to the clients that receive the event.
     * @param data3     Double word containing an additional number required by the event. If the event is a Microsoft
     *                  Flight Simulator event, then refer to the Event IDs document for information on this additional
     *                  value(s). If the event is a custom event, then any values put in these parameters will be
     *                  available to the clients that receive the event.
     * @param data4     Double word containing an additional number required by the event. If the event is a Microsoft
     *                  Flight Simulator event, then refer to the Event IDs document for information on this additional
     *                  value(s). If the event is a custom event, then any values put in these parameters will be
     *                  available to the clients that receive the event.
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_TransmitClientEvent_EX1.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_TransmitClientEvent_EX1.htm</a>
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/SimConnect_API_Reference.htm#simconnect-priorities">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/SimConnect_API_Reference.htm#simconnect-priorities</a>
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/Event_IDs/Event_IDs.htm">https://docs.flightsimulator.com/html/Programming_Tools/Event_IDs/Event_IDs.htm</a>
     */
    public TransmitClientEventEx1Request transmitClientEventEx1(int objectID, int eventID, Priority priority,
                                                                int eventFlag, int data0, int data1, int data2, int data3, int data4) throws IOException {
        return write(new TransmitClientEventEx1Request(objectID, eventID, priority, eventFlag, data0, data1, data2,
                data3, data4));
    }

    public AddToFacilityDefinitionRequest addToFacilityDefinition(int defineID, String fieldName) throws IOException {
        return write(new AddToFacilityDefinitionRequest(defineID, fieldName));
    }

    public RequestFacilityDataRequest RequestFacilityData(int defineID, int requestID, String icao,
                                                          String region) throws IOException {
        return write(new RequestFacilityDataRequest(defineID, requestID, icao, region));
    }

    public SubscribeToFacilitiesEx1Request subscribeToFacilitiesEx1(FacilityListType type, int newElemInRangeRequestID,
                                                                    int oldElemOutRangeRequestID) throws IOException {
        return write(new SubscribeToFacilitiesEx1Request(type, newElemInRangeRequestID, oldElemOutRangeRequestID));
    }

    /**
     * The SimConnect_UnsubscribeToFacilities_EX1 function is used to request that notifications of additions to the
     * facilities cache are no longer sent, with the ability to specify which event should be disabled.
     *
     * @param type                   Specifies one member of the SIMCONNECT_FACILITY_LIST_TYPE enumeration type.
     * @param unsubscribeNewInRange  Specifies that the "new element in range" event should be disabled.
     * @param unsubscribeOldOutRange Specifies that the "element out of range" event should be disabled.
     * @return The Object that represents the message that was sent to the simulator.
     */
    public UnsubscribeToFacilitiesEx1Request unsubscribeToFacilitiesEx1(FacilityListType type,
                                                                        boolean unsubscribeNewInRange, boolean unsubscribeOldOutRange) throws IOException {
        return write(new UnsubscribeToFacilitiesEx1Request(type, unsubscribeNewInRange, unsubscribeOldOutRange));
    }

    public RequestFacilitiesListEx1Request requestFacilitiesListEx1(FacilityListType type,
                                                                    int requestID) throws IOException {
        return write(new RequestFacilitiesListEx1Request(type, requestID));
    }

    public RequestFacilityDataEx1Request requestFacilityDataEx1(int defineID, int requestID, String icao, String region,
                                                                RequestFacilityDataEx1Request.FacilityType type) throws IOException {
        return write(new RequestFacilityDataEx1Request(defineID, requestID, icao, region, type));
    }

    public RequestJetwayDataRequest requestJetwayData(String icao, int... indexes) throws IOException {
        return write(new RequestJetwayDataRequest(icao, indexes));
    }

    public EnumerateControllersRequest enumerateControllers() throws IOException {
        return write(new EnumerateControllersRequest());
    }

    public MapInputEventToClientEventEx1Request mapInputEventToClientEventEx1(int groupID, String inputDefinition,
                                                                              int downEventID, int downValue, int upEventID, int upValue, boolean bMaskable) throws IOException {
        return write(new MapInputEventToClientEventEx1Request(groupID, inputDefinition, downEventID, downValue,
                upEventID, upValue, bMaskable));
    }

    public EnumerateInputEventsRequest enumerateInputEvents(int requestID) throws IOException {
        return write(new EnumerateInputEventsRequest(requestID));
    }

    public GetInputEventRequest getInputEvent(int requestID, long hash) throws IOException {
        return write(new GetInputEventRequest(requestID, hash));
    }

    public SetInputEventRequest setInputEvent(long hash, float floatValue) throws IOException {
        return write(new SetInputEventRequest(hash, floatValue));
    }

    public SetInputEventRequest setInputEvent(long hash, String stringValue) throws IOException {
        return write(new SetInputEventRequest(hash, stringValue));
    }

    public EnumerateInputEventParamsRequest enumerateInputEventParams(long hash) throws IOException {
        return write(new EnumerateInputEventParamsRequest(hash));
    }

    public SubscribeInputEventRequest subscribeInputEvent(long hash) throws IOException {
        return write(new SubscribeInputEventRequest(hash));
    }

    /**
     * The SimConnect_UnsubscribeInputEvent function is used to unsubscribe from an input event that has previously been
     * subscribed to.
     *
     * @param hash Hash ID that will identify the desired input event to unsubscribe from. You can use 0 here to
     *             unsubscribe from all input events.
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_UnsubscribeInputEvent.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/InputEvents/SimConnect_UnsubscribeInputEvent.htm</a>
     */
    public UnsubscribeInputEventRequest unsubscribeInputEvent(long hash) throws IOException {
        return write(new UnsubscribeInputEventRequest(hash));
    }

    /**
     * The SimConnect_AddFacilityDataDefinitionFilter function is used add a filter on a node in the
     * FacilityDataDefinition to block sending data according to this filter, thus reduce the amount of data received
     * and limit it to only that which is required.
     *
     * @param defineID   Specifies the ID of the client defined data definition.
     * @param filterPath Defines the node and member that you wish to apply the filter to.
     * @param filterData Filter data as bytes (will be cast to the right type later).
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_AddFacilityDataDefinitionFilter.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_AddFacilityDataDefinitionFilter.htm</a>
     */
    public AddFacilityDataDefinitionFilterRequest addFacilityDataDefinitionFilter(int defineID, String filterPath,
                                                                                  byte[] filterData) throws IOException {
        return write(new AddFacilityDataDefinitionFilterRequest(defineID, filterPath, filterData));
    }

    /**
     * The SimConnect_ClearAllFacilityDataDefinitionFilters function is used to clear all applied facility definition
     * filters.
     *
     * @param defineID Specifies the ID of the client defined data definition.
     * @return The Object that represents the message that was sent to the simulator.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_ClearAllFacilityDataDefinitionFilters.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_ClearAllFacilityDataDefinitionFilters.htm</a>
     */
    public ClearAllFacilityDataDefinitionFiltersRequest clearAllFacilityDataDefinitionFilters(
            int defineID) throws IOException {
        return write(new ClearAllFacilityDataDefinitionFiltersRequest(defineID));
    }

    /**
     * Generates a continuously increasing user request id every time this method is called. Some function calls require
     * a unique user request id so that the following responses can be clearly assigned to the respective request.
     * <b>Caution:</b> This mechanism only works as long as all user request ids used were created using this method.
     * When using self-made IDs, a collision cannot be ruled out.
     *
     * @return A new user request id for the usage in function calls which need such an id.
     */
    public static int getNextUserRequestID() {
        return lastUserRequestIdentifier.incrementAndGet();
    }

    /**
     * Generates a continuously increasing user define id every time this method is called. Some function calls require
     * a unique user define id so that all calls that deal with a coherent definition of data can can be recognized.
     * <b>Caution:</b> This mechanism only works as long as all user define ids used were created using this method.
     * When using self-made IDs, a collision cannot be ruled out.
     *
     * @return A new user define id for the usage in function calls which need such an id.
     */
    public static int getNextUserDefineID() {
        return lastUserDefineIdentifier.incrementAndGet();
    }

    /**
     * Generates a continuously increasing user event id every time this method is called. Some function calls require
     * a unique user event id so that the following responses can be clearly assigned to the respective event.
     * <b>Caution:</b> This mechanism only works as long as all user event ids used were created using this method.
     * When using self-made IDs, a collision cannot be ruled out.
     *
     * @return A new user event id for the usage in function calls which need such an id.
     */
    public static int getNextUserEventID() {
        return lastUserEventIdentifier.incrementAndGet();
    }
}