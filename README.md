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

| ID | Name                                      | Comment                                                                         |
|----|-------------------------------------------|---------------------------------------------------------------------------------|
| 00 | Open                                      | :heavy_check_mark:                                                              |
| 01 |                                           |                                                                                 |
| 02 |                                           |                                                                                 |
| 03 |                                           |                                                                                 |
| 04 | MapClientEventToSimEvent                  |                                                                                 |
| 05 | TransmitClientEvent                       |                                                                                 |
| 06 | SetSystemEventState                       |                                                                                 |
| 07 | AddClientEventToNotificationGroup         |                                                                                 |
| 08 | RemoveClientEvent                         |                                                                                 |
| 09 | SetNotificationGroupPriority              |                                                                                 |
| 0a | ClearNotificationGroup                    |                                                                                 |
| 0b | RequestNotificationGroup                  |                                                                                 |
| 0c | AddToDataDefinition                       |                                                                                 |
| 0d | ClearDataDefinition                       |                                                                                 |
| 0e | RequestDataOnSimObject                    |                                                                                 |
| 0f | RequestDataOnSimObjectType                |                                                                                 |
| 10 | SetDataOnSimObject                        |                                                                                 |
| 11 | MapInputEventToClientEvent                |                                                                                 |
| 12 | SetInputGroupPriority                     |                                                                                 |
| 13 | RemoveInputEvent                          |                                                                                 |
| 14 | ClearInputGroup                           |                                                                                 |
| 15 | SetInputGroupState                        |                                                                                 |
| 16 | RequestReservedKey                        |                                                                                 |
| 17 | SubscribeToSystemEvent                    | :heavy_check_mark:                                                              |
| 18 | UnsubscribeFromSystemEvent                | :heavy_check_mark:                                                              |
| 19 | WeatherRequestInterpolatedObservation     | :x: _Deprecated_, not implemented here                                          |
| 1a | WeatherRequestObservationAtStation        | :x: _Deprecated_, not implemented here                                          |
| 1b | WeatherRequestObservationAtNearestStation | :x: _Deprecated_, not implemented here                                          |
| 1c | WeatherCreateStation                      | :x: _Deprecated_, not implemented here                                          |
| 1d | WeatherRemoveStation                      | :x: _Deprecated_, not implemented here                                          |
| 1e | WeatherSetObservation                     | :x: _Deprecated_, not implemented here                                          |
| 1f | WeatherSetModeServer                      | :x: _Deprecated_, not implemented here                                          |
| 20 | WeatherSetModeTheme                       | :x: _Deprecated_, not implemented here                                          |
| 21 | WeatherSetModeGlobal                      | :x: _Deprecated_, not implemented here                                          |
| 22 | WeatherSetModeCustom                      | :x: _Deprecated_, not implemented here                                          |
| 23 | WeatherSetDynamicUpdateRate               | :x: _Deprecated_, not implemented here                                          |
| 24 | WeatherRequestCloudState                  | :x: _Deprecated_, not implemented here                                          |
| 25 | WeatherCreateThermal                      | :x: _Deprecated_, not implemented here                                          |
| 26 | WeatherRemoveThermal                      | :x: _Deprecated_, not implemented here                                          |
| 27 | AICreateParkedATCAircraft                 |                                                                                 |
| 28 | AICreateEnrouteATCAircraft                |                                                                                 |
| 29 | AICreateNonATCAircraft                    |                                                                                 |
| 2a | AICreateSimulatedObject                   |                                                                                 |
| 2b | AIReleaseControl                          |                                                                                 |
| 2c | AIRemoveObject                            |                                                                                 |
| 2d | AISetAircraftFlightPlan                   |                                                                                 |
| 2e | ExecuteMissionAction                      | :x: _Deprecated_, not implemented here                                          |
| 2f | CompleteCustomMissionAction               | :x: _Deprecated_, not implemented here                                          |
| 30 | CameraSetRelative6DOF                     |                                                                                 |
| 31 | SimConnect_MenuAddItem                    | :x: _Deprecated_, not implemented here                                          |
| 32 | SimConnect_MenuDeleteItem                 | :x: _Deprecated_, not implemented here                                          |
| 33 | SimConnect_MenuAddSubItem                 | :x: _Deprecated_, not implemented here                                          |
| 34 | SimConnect_MenuDeleteSubItem              | :x: _Deprecated_, not implemented here                                          |
| 35 | RequestSystemState                        | :heavy_check_mark:                                                              |
| 36 | SetSystemState                            | :x: is no longer mentioned in the documentation, so it has not been implemented |
| 37 | MapClientDataNameToID                     |                                                                                 |
| 38 | CreateClientData                          |                                                                                 |
| 39 | AddToClientDataDefinition                 |                                                                                 |
| 3a | ClearClientDataDefinition                 |                                                                                 |
| 3b | RequestClientData                         |                                                                                 |
| 3c | SetClientData                             |                                                                                 |
| 3d | FlightLoad                                |                                                                                 |
| 3e | FlightSave                                |                                                                                 |
| 3f | FlightPlanLoad                            |                                                                                 |
| 40 | Text                                      | :x: _Deprecated_, not implemented here                                          |
| 41 | SubscribeToFacilities                     | :heavy_check_mark:                                                              |
| 42 | UnsubscribeToFacilities                   | :heavy_check_mark:                                                              |
| 43 | RequestFacilitiesList                     | :heavy_check_mark:                                                              |
| 44 | TransmitClientEvent_EX1                   |                                                                                 |
| 45 |                                           |                                                                                 |
| 46 |                                           |                                                                                 |
| 47 |                                           |                                                                                 |
| 48 |                                           |                                                                                 |
| 49 |                                           |                                                                                 |
| 4a |                                           |                                                                                 |
| 4b |                                           |                                                                                 |
| 4c |                                           |                                                                                 |
| 4d |                                           |                                                                                 |
| 4e |                                           |                                                                                 |
| 4f |                                           |                                                                                 |
|    |                                           |                                                                                 |

* AddToFacilityDefinition
* RequestFacilityData
* SubscribeToFacilities_EX1
* UnsubscribeToFacilities_EX1
* RequestFacilitiesList_EX1
* RequestFacilityData_EX1
* RequestJetwayData
* EnumerateControllers
* MapInputEventToClientEvent_EX1
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
 
