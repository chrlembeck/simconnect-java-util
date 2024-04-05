package org.lembeck.fs.copilot;

import org.lembeck.fs.copilot.instrument.*;
import org.lembeck.fs.simconnect.response.LatLonAlt;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import static javax.swing.SwingConstants.CENTER;
import static org.lembeck.fs.copilot.GraphicsUtil.DECIMAL_0;
import static org.lembeck.fs.copilot.GraphicsUtil.DECIMAL_2;

public class SimViewer extends JFrame implements SimListener {

    JTextField tfAltitude;

    JTextField tfLongitude;

    JTextField tfLatitude;
    JTextField tfAltitudeAboveGround;
    JTextField tfAltitudeAboveGroundMinusCG;
    JTextField tfBank;
    JTextField tfAirspeedTrue;
    JTextField tfAirspeed;
    JTextField tfHeadingTrue;
    JTextField tfHeadingMagnetic;
    JTextField tfVerticalSpeed;
    JTextField tfHeadingGyro;
    JTextField tfPitch;

    JToggleButton tbAutopilotMaster;
    JToggleButton tbAutopilotHeadingLock;
    JTextField tfAutopilotHeadingLockVar;
    JToggleButton tbAutopilotNav1Lock;
    JToggleButton tbAutopilotVerticalHold;
    JTextField tfAutopilotVerticalHoldVar;
    JToggleButton tbAutopilotAltitudeLock;
    JTextField tfAutopilotAltitudeLockVar;

    private final Compass compass;
    private final VerticalSpeedIndicator verticalSpeedIndicator;

    private final Altimeter altimeter;

    SimController controller;
    private final JToggleButton tbAvionicsMaster1;
    private final JToggleButton tbGeneralEngineFuelPump;
    private final JToggleButton tbLightLanding;
    private final JToggleButton tbGeneralEngineMasterAlternator1;
    private final JToggleButton tbLightStrobe;
    private final JToggleButton tbLightBeacon;
    private final JToggleButton tbElectricalMasterBattery;
    private final JToggleButton tbPitotHeat1;
    private final JToggleButton tbLightNav;
    private final JToggleButton tbLightRecognition;
    private final JToggleButton tbLightCabin;
    private final JToggleButton tbParkingBrakes;

    private final AirportsPanel airportsPanel;
    private final VORsPanel vorsPanel;

    private LatLonAlt position;

    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        SwingUtilities.invokeLater(SimViewer::start);
    }

    private static void start() {
        new SimViewer().setVisible(true);
    }

    private SimViewer() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        tfLatitude = new JTextField(10);
        tfLongitude = new JTextField(10);
        tfAltitude = new JTextField(10);
        tfAltitudeAboveGround = new JTextField(10);
        tfAltitudeAboveGroundMinusCG = new JTextField(10);
        tfPitch = new JTextField(10);
        tfBank = new JTextField(10);
        tfHeadingGyro = new JTextField(10);
        tfHeadingMagnetic = new JTextField(10);
        tfHeadingTrue = new JTextField(10);
        tfVerticalSpeed = new JTextField(10);
        tfAirspeed = new JTextField(10);
        tfAirspeedTrue = new JTextField(10);
        tbAutopilotMaster = createToggleButton("AP Master");
        tbAutopilotHeadingLock = createToggleButton("HDG");
        tfAutopilotHeadingLockVar = new JTextField(10);
        tbAutopilotNav1Lock = createToggleButton("NAV");
        tbAutopilotVerticalHold = createToggleButton("VS");
        tfAutopilotVerticalHoldVar = new JTextField(10);
        tbAutopilotAltitudeLock = createToggleButton("ALT");
        tfAutopilotAltitudeLockVar = new JTextField(10);
        tbAvionicsMaster1 = createToggleButton("Avionics Master");
        tbGeneralEngineFuelPump = createToggleButton("Fuel Pump");
        tbLightLanding = createToggleButton("LDG Light");
        tbGeneralEngineMasterAlternator1 = createToggleButton("Alternator");
        tbLightStrobe = createToggleButton("Strobe Light");
        tbLightBeacon = createToggleButton("Beacon Light");
        tbElectricalMasterBattery = createToggleButton("Battery Master");
        tbPitotHeat1 = createToggleButton("Pitot Heat");
        tbLightNav = createToggleButton("Nav Light");
        tbLightRecognition = createToggleButton("Recog Light");
        tbLightCabin = createToggleButton("Cabin Light");
        tbParkingBrakes = createToggleButton("Parking Brakes");


        tbAutopilotMaster.addActionListener(evt -> controller.toggleAutopilotMaster());
        tbAutopilotHeadingLock.addActionListener(event -> controller.toggleAutopilotHeadingHold());
        tbAutopilotAltitudeLock.addActionListener(event -> controller.toggleAutopilotAltitudeHold());
        tbAutopilotNav1Lock.addActionListener(event -> controller.toggleAutopilotNav1Hold());
        tbAutopilotVerticalHold.addActionListener(event -> controller.toggleAutopilotVerticalSpeedHold());

        tbLightNav.addActionListener(event -> controller.toggleNavLights());
        tbLightRecognition.addActionListener(event -> controller.toggleRecognitionLights());
        tbLightLanding.addActionListener(event -> controller.toggleLandingLights());
        tbAvionicsMaster1.addActionListener(event -> controller.toggleAvionicsMaster());
        tbGeneralEngineFuelPump.addActionListener(event -> controller.toggleFuelPump());
        tbGeneralEngineMasterAlternator1.addActionListener(event -> controller.toggleMasterBatteryAlternator());
        tbElectricalMasterBattery.addActionListener(event -> controller.toggleMasterBattery());
        tbPitotHeat1.addActionListener(event -> controller.togglePitotHeat());
        tbLightStrobe.addActionListener(event -> controller.toggleStrobeLights());
        tbLightBeacon.addActionListener(event -> controller.toggleBeaconLights());
        tbLightCabin.addActionListener(event -> controller.cabineLightsSet(tbLightCabin.isSelected() ? 1 : 0, 0));
        tbParkingBrakes.addActionListener(event -> controller.toggleParkingBrakes());

        JPanel valuesPanel = new JPanel();
        valuesPanel.setLayout(new GridBagLayout());
        valuesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Values"));

        valuesPanel.add(new JLabel("latitude"), new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        valuesPanel.add(tfLatitude, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        valuesPanel.add(new JLabel("longitude"), new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        valuesPanel.add(tfLongitude, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        valuesPanel.add(new JLabel("altitude above ground (ft)"), new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        valuesPanel.add(tfAltitudeAboveGround, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));

        valuesPanel.add(new JLabel("airspeed"), new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        valuesPanel.add(tfAirspeed, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        valuesPanel.add(new JLabel("airspeed true"), new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        valuesPanel.add(tfAirspeedTrue, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        valuesPanel.add(new JLabel("bank"), new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        valuesPanel.add(tfBank, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        valuesPanel.add(new JLabel("pitch"), new GridBagConstraints(0, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
        valuesPanel.add(tfPitch, new GridBagConstraints(1, GridBagConstraints.RELATIVE, 1, 1, 0, 0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));


        JPanel switchesPanel1 = new JPanel();
        switchesPanel1.setLayout(new FlowLayout());
        switchesPanel1.add(tbElectricalMasterBattery);
        switchesPanel1.add(tbGeneralEngineMasterAlternator1);
        switchesPanel1.add(tbGeneralEngineFuelPump);
        switchesPanel1.add(tbLightLanding);
        switchesPanel1.add(tbLightNav);
        switchesPanel1.add(tbLightStrobe);
        switchesPanel1.add(tbLightBeacon);
        switchesPanel1.add(tbLightRecognition);
        switchesPanel1.add(tbPitotHeat1);



        compass = new Compass();
        compass.setSize(compass.getPreferredSize());
        compass.getModel().addChangeListener(new CompassChangeListener() {
            @Override
            public void directionChanged(float newDirection) {
            }

            @Override
            public void headingBugChanged(float headingBug) {
                controller.setHeadingBug(Math.round(headingBug));
            }
        });

        verticalSpeedIndicator = new VerticalSpeedIndicator();
        verticalSpeedIndicator.setSize(verticalSpeedIndicator.getPreferredSize());
        verticalSpeedIndicator.getModel().addChangeListener(new VerticalSpeedChangeListener() {
            @Override
            public void verticalSpeedChanged(double verticalSpeed) {
            }

            @Override
            public void verticalSpeedAutopilitChanged(double verticalSpeedAutopilot) {
                controller.setVerticalSpeedAutopilotVar((int)Math.round(verticalSpeedAutopilot), 0);
            }
        });

        JPanel compassPanel = new JPanel();
        compassPanel.setBorder(BorderFactory.createEtchedBorder());
        compassPanel.setLayout(new BorderLayout());
        compassPanel.add(compass, BorderLayout.CENTER);
        JPanel headingPanel = new JPanel();
        headingPanel.setLayout(new GridLayout(1, 2));
        compassPanel.add(headingPanel, BorderLayout.SOUTH);

        headingPanel.add(tfAutopilotHeadingLockVar);
        headingPanel.add(tfHeadingGyro);
        tfAutopilotHeadingLockVar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createLoweredBevelBorder()));
        tfHeadingGyro.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createLoweredBevelBorder()));
        tfAutopilotHeadingLockVar.setOpaque(false);
        tfAutopilotHeadingLockVar.setFocusable(false);
        tfHeadingGyro.setFocusable(false);
        tfHeadingGyro.setOpaque(false);
        tfHeadingGyro.setHorizontalAlignment(CENTER);
        tfAutopilotHeadingLockVar.setHorizontalAlignment(CENTER);


        JPanel vsPanel = new JPanel();
        vsPanel.setBorder(BorderFactory.createEtchedBorder());
        vsPanel.setLayout(new BorderLayout());
        vsPanel.add(verticalSpeedIndicator, BorderLayout.CENTER);
        JPanel vsValuesPanel = new JPanel();
        vsPanel.add(vsValuesPanel, BorderLayout.SOUTH);
        vsValuesPanel.setLayout(new GridLayout(1,2));
        vsValuesPanel.add(tfVerticalSpeed);
        vsValuesPanel.add(tfAutopilotVerticalHoldVar);
        tfVerticalSpeed.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createLoweredBevelBorder()));
        tfAutopilotVerticalHoldVar.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createLoweredBevelBorder()));
        tfVerticalSpeed.setOpaque(false);
        tfAutopilotVerticalHoldVar.setOpaque(false);
        tfVerticalSpeed.setFocusable(false);
        tfAutopilotVerticalHoldVar.setFocusable(false);
        tfVerticalSpeed.setHorizontalAlignment(CENTER);
        tfAutopilotVerticalHoldVar.setHorizontalAlignment(CENTER);


        altimeter = new Altimeter();
        JPanel altPanel = new JPanel();
        altPanel.setLayout(new BorderLayout());
        altPanel.add(altimeter, BorderLayout.CENTER);
        altPanel.setBorder(BorderFactory.createEtchedBorder());
        altPanel.add(tfAltitude, BorderLayout.SOUTH);
        tfAltitude.setFocusable(false);
        tfAltitude.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5), BorderFactory.createLoweredBevelBorder()));
        tfAltitude.setHorizontalAlignment(CENTER);
        tfAltitude.setOpaque(false);

        JPanel instrumentPanel = new JPanel();
        instrumentPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Instruments"));
        instrumentPanel.setLayout(new GridLayout(1, 3));
        instrumentPanel.add(compassPanel);

        instrumentPanel.add(vsPanel);
        instrumentPanel.add(altPanel);

        JPanel switchesPanel2 = new JPanel();
        switchesPanel2.setLayout(new FlowLayout());
        switchesPanel2.add(tbAvionicsMaster1);
        switchesPanel2.add(tbAutopilotMaster);
        switchesPanel2.add(tbAutopilotHeadingLock);
        switchesPanel2.add(tbAutopilotNav1Lock);
        switchesPanel2.add(tbAutopilotVerticalHold);
        switchesPanel2.add(tbAutopilotAltitudeLock);
        switchesPanel2.add(tbLightCabin);
        switchesPanel2.add(tbParkingBrakes);

        JPanel switchesPanel = new JPanel();
        switchesPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Switches"));
        switchesPanel.setLayout(new GridLayout(2, 1));
        switchesPanel.add(switchesPanel1);
        switchesPanel.add(switchesPanel2);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.add(instrumentPanel);
        leftPanel.add(switchesPanel);
        leftPanel.add(switchesPanel);


        leftPanel.add(valuesPanel);

        controller = new SimController();
        airportsPanel = new AirportsPanel(controller.getSimConnect(), this);
        airportsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Nearest Airports"));
        vorsPanel = new VORsPanel(controller.getSimConnect(), this);
        vorsPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED), "Nearest VOR stations"));

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2, 1));
        rightPanel.add(airportsPanel);
        rightPanel.add(vorsPanel);


        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        splitPane.setDividerLocation(0.67d);
        getContentPane().add(splitPane, BorderLayout.CENTER);
        pack();
        controller.addListener(this);

    }

    JToggleButton createToggleButton(String text) {
        JToggleButton btn = new JToggleButton(text);
        btn.setIcon(new LedIcon(15, new Color(200, 0, 0), Color.DARK_GRAY));
        btn.setSelectedIcon(new LedIcon(15, new Color(0, 200, 0), Color.DARK_GRAY));
        btn.setHorizontalTextPosition(CENTER);
        btn.setVerticalTextPosition(JButton.BOTTOM);
        btn.setMargin(new Insets(2, 2, 2, 2));
        return btn;
    }

    @Override
    public void handlePlanePositionEvent(final PlanePositionEvent planePositionEvent) {
        NumberFormat nf = DecimalFormat.getNumberInstance();
        nf.setMinimumFractionDigits(8);
        nf.setMaximumFractionDigits(8);
        SwingUtilities.invokeLater(() -> {
            tfAltitude.setText(DECIMAL_0.format(planePositionEvent.getAltitudeInFeet()) + " ft");
            tfLongitude.setText(nf.format(planePositionEvent.getLongitude()));
            tfLatitude.setText(nf.format(planePositionEvent.getLatitude()));
            this.position = new LatLonAlt(planePositionEvent.getLatitude(), planePositionEvent.getLongitude(), planePositionEvent.getAltitudeInFeet());
            tfAltitudeAboveGround.setText(nf.format(planePositionEvent.getAltitudeAboveGroundInFeet()));
            tfAltitudeAboveGroundMinusCG.setText(nf.format(planePositionEvent.getAltitudeAboveGroundMinusCenterOfGravityInFeet()));
            tfAirspeed.setText(nf.format(planePositionEvent.getAirspeedIndicated()));
            tfAirspeedTrue.setText(nf.format(planePositionEvent.getAirspeedTrue()));
            tfVerticalSpeed.setText(DECIMAL_0.format(planePositionEvent.getVerticalSpeedFeetPerSecond()*60));
            tfBank.setText(nf.format(planePositionEvent.getBankDegrees()));
            tfPitch.setText(nf.format(planePositionEvent.getPitchDegrees()));
            tfHeadingGyro.setText(DECIMAL_0.format(planePositionEvent.getHeadingDegreesGyro())+"°");
            tfHeadingMagnetic.setText(DECIMAL_2.format(planePositionEvent.getHeadingDegreesMagnetic()));
            tfHeadingTrue.setText(DECIMAL_2.format(planePositionEvent.getHeadingDegreesTrue()));
            compass.getModel().setDirection((float) planePositionEvent.getHeadingDegreesGyro());
            verticalSpeedIndicator.getModel().setVerticalSpeedFeetPerMinute(planePositionEvent.getVerticalSpeedFeetPerSecond() * 60);
            altimeter.getModel().setAltitude(planePositionEvent.getAltitudeInFeet());
        });
    }

    @Override
    public void handleAutopilotEvent(AutopilotEvent autopilotEvent) {
        NumberFormat nf = DecimalFormat.getNumberInstance();
        nf.setMinimumFractionDigits(8);
        nf.setMaximumFractionDigits(8);
        SwingUtilities.invokeLater(() -> {
            tbAutopilotMaster.setSelected(autopilotEvent.isAutopilotMaster());
            tbAutopilotHeadingLock.setSelected(autopilotEvent.isHeadingLock());
            tfAutopilotHeadingLockVar.setText(DECIMAL_0.format(autopilotEvent.getHeadingLockVar()) + "°");
            tbAutopilotNav1Lock.setSelected(autopilotEvent.isNav1Lock());
            tbAutopilotVerticalHold.setSelected(autopilotEvent.isVerticalHold());
            tfAutopilotVerticalHoldVar.setText(DECIMAL_0.format(autopilotEvent.getVerticalHoldVar()));
            tbAutopilotAltitudeLock.setSelected(autopilotEvent.isAltitudeHold());
            tfAutopilotAltitudeLockVar.setText(nf.format(autopilotEvent.getAltitudeHoldVar()));
            compass.getModel().setHeadingBug((float) autopilotEvent.getHeadingLockVar(), false);
            verticalSpeedIndicator.getModel().setVerticalSpeedAutopilot(autopilotEvent.getVerticalHoldVar(), false);
        });
    }

    @Override
    public void handleSwitchesEvent(SwitchesEvent switchesEvent) {
        SwingUtilities.invokeLater(() -> {
            tbAvionicsMaster1.setSelected(switchesEvent.isAvionicsMasterSwitch1());
            tbGeneralEngineFuelPump.setSelected(switchesEvent.isGeneralEngineFuelPumpSwitch1());
            tbElectricalMasterBattery.setSelected(switchesEvent.isElectricalMasterBattery());
            tbPitotHeat1.setSelected(switchesEvent.isPitotHeatSwitch1());
            tbLightLanding.setSelected(switchesEvent.isLightLanding());
            tbLightNav.setSelected(switchesEvent.isLightNav());
            tbLightRecognition.setSelected(switchesEvent.isLightRecognition());
            tbLightStrobe.setSelected(switchesEvent.isLightStrobe());
            tbLightBeacon.setSelected(switchesEvent.isLightBeacon());
            tbGeneralEngineMasterAlternator1.setSelected(switchesEvent.isGeneralEngineMasterAlternator1());
            tbLightCabin.setSelected(switchesEvent.isLightCabin());
            tbParkingBrakes.setSelected(switchesEvent.isBrakeParkingPosition());
        });
    }

    public LatLonAlt getLastPosition() {
        return position;
    }
}