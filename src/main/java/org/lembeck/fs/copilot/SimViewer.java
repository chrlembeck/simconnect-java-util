package org.lembeck.fs.copilot;

import org.lembeck.fs.copilot.instrument.*;
import javax.swing.*;
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

    private Compass compass;
    private VerticalSpeedIndicator verticalSpeedIndicator;

    private Altimeter altimeter;

    SimController controller;
    private JToggleButton tbAvionicsMaster1;
    private JToggleButton tbGeneralEngineFuelPump;
    private JToggleButton tbLightLanding;
    private JToggleButton tbGeneralEngineMasterAlternator1;
    private JToggleButton tbLightStrobe;
    private JToggleButton tbLightBeacon;
    private JToggleButton tbElectricalMasterBattery;
    private JToggleButton tbPitotHeat1;
    private JToggleButton tbLightNav;
    private JToggleButton tbLightRecognition;
    private JToggleButton tbLightCabin;
    private JToggleButton tbParkingBrakes;

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
        JPanel valuesPanel = new JPanel();
        valuesPanel.setLayout(new FlowLayout());
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

        valuesPanel.add(new JLabel("latitude"));
        valuesPanel.add(tfLatitude);
        valuesPanel.add(new JLabel("longitude"));
        valuesPanel.add(tfLongitude);
        valuesPanel.add(new JLabel("altitude above ground (ft)"));
        valuesPanel.add(tfAltitudeAboveGround);
        valuesPanel.add(new JLabel("altitude above ground minus CG (ft)"));
        valuesPanel.add(tfAltitudeAboveGroundMinusCG);

        valuesPanel.add(new JLabel("airspeed"));
        valuesPanel.add(tfAirspeed);
        valuesPanel.add(new JLabel("airspeed true"));
        valuesPanel.add(tfAirspeedTrue);
        valuesPanel.add(new JLabel("bank"));
        valuesPanel.add(tfBank);
        valuesPanel.add(new JLabel("pitch"));
        valuesPanel.add(tfPitch);
        valuesPanel.add(new JLabel("heading magnetic"));
        valuesPanel.add(tfHeadingMagnetic);
        valuesPanel.add(new JLabel("heading true"));
        valuesPanel.add(tfHeadingTrue);


        valuesPanel.add(tbAutopilotHeadingLock);
        valuesPanel.add(tbAutopilotNav1Lock);
        valuesPanel.add(tbAutopilotVerticalHold);
        valuesPanel.add(tbAutopilotAltitudeLock);
        valuesPanel.add(new JLabel("altitude lock var"));
        valuesPanel.add(tfAutopilotAltitudeLockVar);

        JPanel switchesPanel1 = new JPanel();
        switchesPanel1.setBorder(BorderFactory.createEtchedBorder());
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
        valuesPanel.add(switchesPanel1);

        valuesPanel.add(tbAvionicsMaster1);
        valuesPanel.add(tbAutopilotMaster);
        valuesPanel.add(tbLightCabin);
        valuesPanel.add(tbParkingBrakes);

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

        valuesPanel.add(compassPanel);

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

        valuesPanel.add(vsPanel);

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


        valuesPanel.add(altPanel);


        getContentPane().add(valuesPanel, BorderLayout.CENTER);
        pack();
        controller = new SimController();
        controller.addListener(this);
        controller.start();


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
}