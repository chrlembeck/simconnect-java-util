package org.lembeck.fs.copilot;

public class SwitchesEvent {
    private final boolean electricalMasterBattery;
    private final boolean generalEngineMasterAlternator1;
    private final boolean generalEngineFuelPumpSwitch1;
    private final boolean lightLanding;
    private final boolean lightNav;
    private final boolean lightCabin;
    private final boolean lightStrobe;
    private final boolean lightBeacon;
    private final boolean lightTaxi;
    private final boolean lightRecognition;
    private final boolean pitotHeatSwitch1;
    private final boolean avionicsMasterSwitch1;
    private final boolean brakeParkingIndicator;
    private final boolean brakeParkingPosition;

    public SwitchesEvent(boolean electricalMasterBattery, boolean generalEngineMasterAlternator1,
            boolean generalEngineFuelPumpSwitch1, boolean lightLanding, boolean lightNav, boolean lightCabin,
            boolean lightStrobe,
            boolean lightBeacon, boolean lightTaxi, boolean lightRecognition, boolean pitotHeatSwitch1,
            boolean avionicsMasterSwitch1, boolean brakeParkingIndicator, boolean brakeParkingPosition) {
        this.electricalMasterBattery = electricalMasterBattery;
        this.generalEngineMasterAlternator1 = generalEngineMasterAlternator1;
        this.generalEngineFuelPumpSwitch1 = generalEngineFuelPumpSwitch1;
        this.lightLanding = lightLanding;
        this.lightNav = lightNav;
        this.lightCabin = lightCabin;
        this.lightStrobe = lightStrobe;
        this.lightBeacon = lightBeacon;
        this.lightTaxi = lightTaxi;
        this.lightRecognition = lightRecognition;
        this.pitotHeatSwitch1 = pitotHeatSwitch1;
        this.avionicsMasterSwitch1 = avionicsMasterSwitch1;
        this.brakeParkingIndicator = brakeParkingIndicator;
        this.brakeParkingPosition = brakeParkingPosition;
    }

    public boolean isElectricalMasterBattery() {
        return electricalMasterBattery;
    }

    public boolean isGeneralEngineMasterAlternator1() {
        return generalEngineMasterAlternator1;
    }

    public boolean isGeneralEngineFuelPumpSwitch1() {
        return generalEngineFuelPumpSwitch1;
    }

    public boolean isLightLanding() {
        return lightLanding;
    }

    public boolean isLightNav() {
        return lightNav;
    }

    public boolean isLightStrobe() {
        return lightStrobe;
    }

    public boolean isLightRecognition() {
        return lightRecognition;
    }

    public boolean isPitotHeatSwitch1() {
        return pitotHeatSwitch1;
    }

    public boolean isAvionicsMasterSwitch1() {
        return avionicsMasterSwitch1;
    }

    public boolean isLightBeacon() {
        return lightBeacon;
    }

    public boolean isLightTaxi() {
        return lightTaxi;
    }

    public boolean isLightCabin() {
        return lightCabin;
    }

    public boolean isBrakeParkingIndicator() {
        return brakeParkingIndicator;
    }

    public boolean isBrakeParkingPosition() {
        return brakeParkingPosition;
    }

    @Override
    public String toString() {
        return "SwitchesEvent{" +
                "electricalMasterBattery=" + electricalMasterBattery +
                ", generalEngineMasterAlternator1=" + generalEngineMasterAlternator1 +
                ", generalEngineFuelPumpSwitch1=" + generalEngineFuelPumpSwitch1 +
                ", lightLanding=" + lightLanding +
                ", lightNav=" + lightNav +
                ", lightCabin=" + lightCabin +
                ", lightStrobe=" + lightStrobe +
                ", lightBeacon=" + lightBeacon +
                ", lightTaxi=" + lightTaxi +
                ", lightRecognition=" + lightRecognition +
                ", pitotHeatSwitch1=" + pitotHeatSwitch1 +
                ", avionicsMasterSwitch1=" + avionicsMasterSwitch1 +
                ", brakeParkingIndicator=" + brakeParkingIndicator +
                ", brakeParkingPosition=" + brakeParkingPosition +
                '}';
    }
}
