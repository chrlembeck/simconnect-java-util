# simconnect-java-util

The simconnect-java-util project contains a java client for the simconnect interface of the microsoft flight simulator
2020.
The client connects to the flight simulator directly over an IP network connection, so that it is able to run on the
same machine as
the simulator or on another machine in the local network.
As this java client communicates directly with the flight simulator, it is not necessary to install the Flight
Simulators
simconnect API written in C++ or C#.

The project is inspired by [Marko Harjulas jsimconnect](https://github.com/mharj/jsimconnect) project, which supports
the API for Flight Simulator X but does not contain some of the newer methods for Flight Simulator 2020.

https://github.com/EvenAR/node-simconnect/tree/master

https://github.com/EvenAR/node-simconnect/blob/master/src/SimConnectConnection.ts

## Supported methods of the simconnect API

| ID | Name                                      | Comment                                                      |
|----|-------------------------------------------|--------------------------------------------------------------|
| 01 | Open                                      | :heavy_check_mark:                                           |
| 02 | _unknown_                                 | :x:                                                          |
| 03 | RequestResponseTimes                      | :x: not yet implemented                                      |
| 04 | MapClientEventToSimEvent                  | :heavy_check_mark:                                           |
| 05 | TransmitClientEvent                       | :heavy_check_mark:                                           |
| 06 | SetSystemEventState                       | :heavy_check_mark:                                           |
| 07 | AddClientEventToNotificationGroup         | :heavy_check_mark:                                           |
| 08 | RemoveClientEvent                         | :heavy_check_mark:                                           |
| 09 | SetNotificationGroupPriority              | :heavy_check_mark:                                           |
| 0a | ClearNotificationGroup                    | :heavy_check_mark:                                           |
| 0b | RequestNotificationGroup                  | :heavy_check_mark:                                           |
| 0c | AddToDataDefinition                       | :heavy_check_mark:                                           |
| 0d | ClearDataDefinition                       | :heavy_check_mark:                                           |
| 0e | RequestDataOnSimObject                    | :heavy_check_mark:                                           |
| 0f | RequestDataOnSimObjectType                | :heavy_check_mark:                                           |
| 10 | SetDataOnSimObject                        | :heavy_check_mark:                                           |
| 11 | MapInputEventToClientEvent                | :x: _Deprecated_, use MapInputEventToClientEvent_EX1 instead |
| 12 | SetInputGroupPriority                     | :heavy_check_mark:                                           |
| 13 | RemoveInputEvent                          | :heavy_check_mark:                                           |
| 14 | ClearInputGroup                           | :heavy_check_mark:                                           |
| 15 | SetInputGroupState                        | :heavy_check_mark:                                           |
| 16 | RequestReservedKey                        | :heavy_check_mark:                                           |
| 17 | SubscribeToSystemEvent                    | :heavy_check_mark:                                           |
| 18 | UnsubscribeFromSystemEvent                | :heavy_check_mark:                                           |
| 19 | WeatherRequestInterpolatedObservation     | :x: _Deprecated_, not implemented here                       |
| 1a | WeatherRequestObservationAtStation        | :x: _Deprecated_, not implemented here                       |
| 1b | WeatherRequestObservationAtNearestStation | :x: _Deprecated_, not implemented here                       |
| 1c | WeatherCreateStation                      | :x: _Deprecated_, not implemented here                       |
| 1d | WeatherRemoveStation                      | :x: _Deprecated_, not implemented here                       |
| 1e | WeatherSetObservation                     | :x: _Deprecated_, not implemented here                       |
| 1f | WeatherSetModeServer                      | :x: _Deprecated_, not implemented here                       |
| 20 | WeatherSetModeTheme                       | :x: _Deprecated_, not implemented here                       |
| 21 | WeatherSetModeGlobal                      | :x: _Deprecated_, not implemented here                       |
| 22 | WeatherSetModeCustom                      | :x: _Deprecated_, not implemented here                       |
| 23 | WeatherSetDynamicUpdateRate               | :x: _Deprecated_, not implemented here                       |
| 24 | WeatherRequestCloudState                  | :x: _Deprecated_, not implemented here                       |
| 25 | WeatherCreateThermal                      | :x: _Deprecated_, not implemented here                       |
| 26 | WeatherRemoveThermal                      | :x: _Deprecated_, not implemented here                       |
| 27 | AICreateParkedATCAircraft                 | :heavy_check_mark:                                           |
| 28 | AICreateEnrouteATCAircraft                | :heavy_check_mark:                                           |
| 29 | AICreateNonATCAircraft                    | :heavy_check_mark:                                           |
| 2a | AICreateSimulatedObject                   | :heavy_check_mark:                                           |
| 2b | AIReleaseControl                          | :heavy_check_mark:                                           |
| 2c | AIRemoveObject                            | :heavy_check_mark:                                           |
| 2d | AISetAircraftFlightPlan                   | :heavy_check_mark:                                           |
| 2e | ExecuteMissionAction                      | :x: _Deprecated_, not implemented here                       |
| 2f | CompleteCustomMissionAction               | :x: _Deprecated_, not implemented here                       |
| 30 | CameraSetRelative6DOF                     | :heavy_check_mark:                                           |
| 31 | SimConnect_MenuAddItem                    | :x: _Deprecated_, not implemented here                       |
| 32 | SimConnect_MenuDeleteItem                 | :x: _Deprecated_, not implemented here                       |
| 33 | SimConnect_MenuAddSubItem                 | :x: _Deprecated_, not implemented here                       |
| 34 | SimConnect_MenuDeleteSubItem              | :x: _Deprecated_, not implemented here                       |
| 35 | RequestSystemState                        | :heavy_check_mark:                                           |
| 36 | SetSystemState                            | :heavy_check_mark:                                           |
| 37 | MapClientDataNameToID                     | :heavy_check_mark:                                           |
| 38 | CreateClientData                          | :heavy_check_mark:                                           |
| 39 | AddToClientDataDefinition                 | :heavy_check_mark:                                           |
| 3a | ClearClientDataDefinition                 | :heavy_check_mark:                                           |
| 3b | RequestClientData                         | :heavy_check_mark:                                           |  
| 3c | SetClientData                             | :heavy_check_mark:                                           |
| 3d | FlightLoad                                | :heavy_check_mark:                                           |
| 3e | FlightSave                                | :heavy_check_mark:                                           |
| 3f | FlightPlanLoad                            | :heavy_check_mark:                                           |
| 40 | Text                                      | :x: _Deprecated_, not implemented here                       |
| 41 | SubscribeToFacilities                     | :heavy_check_mark:                                           |
| 42 | UnsubscribeToFacilities                   | :heavy_check_mark:                                           |
| 43 | RequestFacilitiesList                     | :heavy_check_mark:                                           |
| 44 | TransmitClientEvent_EX1                   | :heavy_check_mark:                                           |
| 45 |                                           |                                                              |
| 46 |                                           |                                                              |
| 47 |                                           |                                                              |
| 48 |                                           |                                                              |
| 49 |                                           |                                                              |
| 4a |                                           |                                                              |
| 4b |                                           |                                                              |
| 4c | EnumerateControllers                      | :heavy_check_mark:                                           |
| 4d | MapInputEventToClientEvent_EX1            | :heavy_check_mark:                                           |
| 4e |                                           |                                                              |
| 4f | EnumerateInputEvents                      | :heavy_check_mark:                                           |
| 50 | GetInputEvent                             | :heavy_check_mark:                                           |
| 51 | SetInputEvent                             | :heavy_check_mark:                                           |
| 52 | SubscribeInputEvent                       | :heavy_check_mark:                                           |
| 53 | UnsubscribeInputEvent                     | :heavy_check_mark:                                           |
| 54 | EnumerateInputEventParams                 | :heavy_check_mark:                                           |

* AddToFacilityDefinition
* RequestFacilityData
* SubscribeToFacilities_EX1
* UnsubscribeToFacilities_EX1
* RequestFacilitiesList_EX1
* RequestFacilityData_EX1
* RequestJetwayData
*
* ExecuteAction
*
* AddFacilityDataDefinitionFilter
* ClearAllFacilityDataDefinitionFilters

* Close
* RetrieveString
* GetLastSentPacketID
* Open
* CallDispatch
* GetNextDispatch
* RequestResponseTimes
* InsertString

## Supported Events sent from the simconnect API

| ID | Name                                                | Comment                                |
|----|-----------------------------------------------------|----------------------------------------|
| 01 | SIMCONNECT_RECV_ID_EXCEPTION                        | :heavy_check_mark:                     |
| 02 | SIMCONNECT_RECV_ID_OPEN                             | :heavy_check_mark:                     |
| 03 | SIMCONNECT_RECV_ID_QUIT                             | :heavy_check_mark:                     |
| 04 | SIMCONNECT_RECV_ID_EVENT                            | :heavy_check_mark:                     |
| 05 | SIMCONNECT_RECV_ID_EVENT_OBJECT_ADDREMOVE           | :heavy_check_mark:                     |
| 06 | SIMCONNECT_RECV_ID_EVENT_FILENAME                   | :heavy_check_mark:                     |
| 07 | SIMCONNECT_RECV_ID_EVENT_FRAME                      | :heavy_check_mark:                     |
| 08 | SIMCONNECT_RECV_ID_SIMOBJECT_DATA                   | :heavy_check_mark:                     |
| 09 | SIMCONNECT_RECV_ID_SIMOBJECT_DATA_BYTYPE            | :heavy_check_mark:                     |
| 0a | SIMCONNECT_RECV_ID_WEATHER_OBSERVATION              | :x: _Deprecated_, not implemented here |
| 0b | SIMCONNECT_RECV_ID_CLOUD_STATE                      | :x: _Deprecated_, not implemented here |
| 0c | SIMCONNECT_RECV_ID_ASSIGNED_OBJECT_ID               | :heavy_check_mark:                     |
| 0d | SIMCONNECT_RECV_ID_RESERVED_KEY                     | :heavy_check_mark:                     |
| 0e | SIMCONNECT_RECV_ID_CUSTOM_ACTION                    | :x: _Deprecated_, not implemented here |
| 0f | SIMCONNECT_RECV_ID_SYSTEM_STATE                     | :heavy_check_mark:                     |
| 10 | SIMCONNECT_RECV_ID_CLIENT_DATA                      | :heavy_check_mark:                     |
| 11 | SIMCONNECT_RECV_ID_EVENT_WEATHER_MODE               | :x: _Deprecated_, not implemented here |
| 12 | SIMCONNECT_RECV_ID_AIRPORT_LIST                     | :heavy_check_mark:                     |
| 13 | SIMCONNECT_RECV_ID_VOR_LIST                         | :heavy_check_mark:                     |
| 14 | SIMCONNECT_RECV_ID_NDB_LIST                         | :heavy_check_mark:                     |
| 15 | SIMCONNECT_RECV_ID_WAYPOINT_LIST                    | :heavy_check_mark:                     |
| 16 | SIMCONNECT_RECV_ID_EVENT_MULTIPLAYER_SERVER_STARTED | :heavy_check_mark:                     |
| 17 | SIMCONNECT_RECV_ID_EVENT_MULTIPLAYER_CLIENT_STARTED | :heavy_check_mark:                     |
| 18 | SIMCONNECT_RECV_ID_EVENT_MULTIPLAYER_SESSION_ENDED  | :heavy_check_mark:                     |
| 19 | SIMCONNECT_RECV_ID_EVENT_RACE_END                   |                                        |
| 1a | SIMCONNECT_RECV_ID_EVENT_RACE_LAP                   |                                        |
| 1b | SIMCONNECT_RECV_ID_EVENT_EX1                        | :heavy_check_mark:                     |
| 1c | SIMCONNECT_RECV_ID_FACILITY_DATA                    |                                        |
| 1d | SIMCONNECT_RECV_ID_FACILITY_DATA_END                | :heavy_check_mark:                     |
| 1e | SIMCONNECT_RECV_ID_FACILITY_MINIMAL_LIST            |                                        |
| 1f | SIMCONNECT_RECV_ID_JETWAY_DATA                      |                                        |
| 20 | SIMCONNECT_RECV_ID_CONTROLLERS_LIST                 | :heavy_check_mark:                     |
| 21 | SIMCONNECT_RECV_ID_ACTION_CALLBACK                  |                                        |
| 22 | SIMCONNECT_RECV_ID_ENUMERATE_INPUT_EVENTS           |                                        |
| 23 | SIMCONNECT_RECV_ID_GET_INPUT_EVENT                  |                                        |
| 24 | SIMCONNECT_RECV_ID_SUBSCRIBE_INPUT_EVENT            |                                        |
| 25 | SIMCONNECT_RECV_ID_ENUMERATE_INPUT_EVENT_PARAMS     |                                        |

## How to compile the project?

Just clone the project and compile it with your local maven installation:

```Shell
git clone https://github.com/chrlembeck/simconnect-java-util.git
cd simconnect-java-util.git
mvn install
```

## How to use the utils in your own java project?

To integrate the simconnect-java-util in your own project just add it to the maven dependencies in the
projects `pom.xml`:

```Maven POM
<depenencies>
    <!-- ... other dependencies ... --> 
    <dependency>
        <groupId>org.lembeck</groupId>
        <artifactId>simconnect-java-util</artifactId>
        <version><!-- Replace this with the lates version of the dependency --></version>
    </dependency>
</dependencies
```

## Are there any examples to demonstrate the usage of this project?

Yes, the project contains some example programs to show some of its features.
The examples can be found in the
package [`org.lembeck.fs.simconnect.examples`](src/main/java/org/lembeck/fs/simconnect/examples).

## What do I have to do to establish a connection to my flight simulator instance?

To be able to connect to your instance of Flight Simulator 2020, you have to tell the integrated simconnect server
interface to listen for incoming IP connections out of your local network. To do so, you have to inspect and possibly
edit the
`simconnect.xml` file of your Flight Simulator installation. This file should be located on the computer, the Flight
Simulator is running on.
The detailed location is depending on the way, the Flight Simulator was installed.

If you installed the simulator out of the Microsoft Store, the `simconnect.xml` file ist located here:

```
C:\Users\<user_name>\AppData\Local\Packages\Microsoft.FlightSimulator_8wekyb3d8bbwe\LocalCache\
```

Installations of the Steam edition have the `simconnect.xml` file here:

```
C:\Users\<user_name>\AppData\Roaming\Microsoft Flight Simulator\
```

The `simconnect.xml` file is structured as a usual xml file and should have some `<SimConnect.Comm>` sections in it.
Each of them defines a way to get into contact with the server. To open a listener on a specific network port (for
example 12345),
you have to add a new `SimConnect.Comm` entry into the file as shown below:

```XML
<?xml version="1.0" encoding="Windows-1252"?>
<SimBase.Document Type="SimConnect" version="1,0">
    <Descr>SimConnect Server Configuration</Descr>
    <Filename>SimConnect.xml</Filename>
    <SimConnect.Comm>
        ...
    </SimConnect.Comm>
    ...
    <SimConnect.Comm>
        <Descr>Static Open IP4 port</Descr>
        <Protocol>IPv4</Protocol>
        <Scope>global</Scope>
        <Port>12345</Port>
        <MaxClients>64</MaxClients>
        <MaxRecvSize>41088</MaxRecvSize>
    </SimConnect.Comm>
</SimBase.Document>
```

Feel free to use any unused port that is available on your local installation. For connections from outside the local
computer it may be necessary to open the port in the firewall of the machine, the flight simulator is running on.

Remember the port, you entered into the `simconnect.xml` file as it will be required when you want to call the `connect`
method of the simconnect-java-util API.

For more information see the official
documentation: [https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/SimConnect_XML_Definition.htm](https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/SimConnect_XML_Definition.htm)

## Does this project support connections to Flight Simulator X (FSX)?

This project mainly aims to implement a java client for the newer Flight Simulator 2020.
Therefore, methods that were originally used for FSX but are marked as deprecated in the current simconnect API
specification are not implemented.

It is possible that some methods are backwards compatible with FSX and these can be used for connections to FSX.
However, this has not been tested and correct functioning cannot be guaranteed.