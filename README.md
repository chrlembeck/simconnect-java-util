# simconnect


**Wichtig**: Vor dem compilieren des Projekts muss die jsimconnect-Library ins lokale maven-repository installiert werden.
Hierzu das Projekt [jsimconnect aus gitlab](https://github.com/mharj/jsimconnect) clonen und das Modul compilieren und installieren:

```
git clone https://github.com/mharj/jsimconnect.git
cd jsimconnect
mvn install
```


https://github.com/mharj/jsimconnect

https://github.com/EvenAR/node-simconnect/tree/master

https://github.com/EvenAR/node-simconnect/blob/master/src/SimConnectConnection.ts


# Protokoll-Infos:

Format der gesendeten Pakete:
Alle gesendeten Werte sind im little-endian-Format. Jedes Paket beginnt mit einem 4 int großen Header:
* int1: Größe des Pakets in Bytes (inklusive header)
* int2: Protokollversion
* int3: id des Pakettyps. Die Id wird vor dem Senden mit 0xf0000000 or-verknüpft.
* int4: fortlaufende Nummer. Diese Nummer wird vom Server verwendet, um diese bei zurückgesendeten Exceptions mitzugeben.

## List of simconnect methods

| ID | Name                                      | Comment                                                      |
|----|-------------------------------------------|--------------------------------------------------------------|
| 00 | Open                                      | :heavy_check_mark:                                           |
| 01 |                                           |                                                              |
| 02 |                                           |                                                              |
| 03 |                                           |                                                              |
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
| 37 | MapClientDataNameToID                     |                                                              |
| 38 | CreateClientData                          |                                                              |
| 39 | AddToClientDataDefinition                 |                                                              |
| 3a | ClearClientDataDefinition                 |                                                              |
| 3b | RequestClientData                         |                                                              |
| 3c | SetClientData                             |                                                              |
| 3d | FlightLoad                                |                                                              |
| 3e | FlightSave                                |                                                              |
| 3f | FlightPlanLoad                            |                                                              |
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
| 4c |                                           |                                                              |
| 4d | MapInputEventToClientEvent_EX1            |                                                              |
| 4e |                                           |                                                              |
| 4f |                                           |                                                              |
|    |                                           |                                                              |

* AddToFacilityDefinition
* RequestFacilityData
* SubscribeToFacilities_EX1
* UnsubscribeToFacilities_EX1
* RequestFacilitiesList_EX1
* RequestFacilityData_EX1
* RequestJetwayData
* EnumerateControllers
*
* ExecuteAction
* EnumerateInputEvents
* GetInputEvent
* SetInputEvent
* SubscribeInputEvent
* UnsubscribeInputEvent
* EnumerateInputEventParams
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
 
