package flightsim.simconnect.recv;

import flightsim.simconnect.SimConnect;

public interface RecvMultiplayerSessionEndedHandler {
	void handleMultiplayerSessionEnded(SimConnect sender, RecvEventMultiplayerSessionEnded e);

}
