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
 
