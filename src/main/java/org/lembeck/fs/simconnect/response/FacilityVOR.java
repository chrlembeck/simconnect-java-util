package org.lembeck.fs.simconnect.response;


/**
 * The SIMCONNECT_DATA_FACILITY_VOR structure is used to return information on a single VOR station in the facilities
 * cache.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_DATA_FACILITY_VOR.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Structures_And_Enumerations/SIMCONNECT_DATA_FACILITY_VOR.htm</a>
 */
public class FacilityVOR extends FacilityNDB {

    /**
     * Flags indicating whether the other fields are valid or not.
     * <ul>
     * <li>SIMCONNECT_RECV_ID_VOR_LIST_HAS_NAV_SIGNAL(0x1): Set if the station has a NAV transmitter, and if so, GlideLat, GlideLon and GlideAlt contain valid data.</li>
     * <li>SIMCONNECT_RECV_ID_VOR_LIST_HAS_LOCALIZER(0x2): Set if the station transmits an ILS localizer angle, and if so fLocalizer contains valid data.</li>
     * <li>SIMCONNECT_RECV_ID_VOR_LIST_HAS_GLIDE_SLOPE(0x4): Set if the station transmits an ILS approach angle, and if so fGlideSlopeAngle contains valid data.</li>
     * <li>SIMCONNECT_RECV_ID_VOR_LIST_HAS_DME(0x8): Set if the station t transmits a DME signal, and if so the inherited DME fFrequency contains valid data.</li>
     * </ul>
     */
    protected final int flags;

    /**
     * The ILS localizer angle in degrees.
     */
    protected final float localizer;

    /**
     * The latitude of the glide slope transmitter in degrees.
     */
    protected final double glideLat;

    /**
     * The longitude of the glide slope transmitter in degrees.
     */
    protected final double glideLon;

    /**
     * The altitude of the glide slope transmitter in degrees.
     */
    protected final double glideAlt;

    /**
     * The ILS approach angle in degrees.
     */
    protected final float glideSlopeAngle;

    /**
     * Creates a new VOR instance.
     *
     * @param icao            The VOR ICAO code.
     * @param regionCode      The VOR region code.
     * @param latitude        Latitude of the VOR facility in degrees.
     * @param longitude       Longitude of the VOR facility in degrees.
     * @param altitude        Altitude of the VOR facility in meters.
     * @param magVar          The magnetic variation of the VOR in degrees.
     * @param frequency       Frequency of the station in Hz.
     * @param flags           Flags indicating whether the other fields are valid or not.
     *                        <ul>
     *                        <li>SIMCONNECT_RECV_ID_VOR_LIST_HAS_NAV_SIGNAL(0x1): Set if the station has a NAV transmitter, and if so, GlideLat, GlideLon and GlideAlt contain valid data.</li>
     *                        <li>SIMCONNECT_RECV_ID_VOR_LIST_HAS_LOCALIZER(0x2): Set if the station transmits an ILS localizer angle, and if so fLocalizer contains valid data.</li>
     *                        <li>SIMCONNECT_RECV_ID_VOR_LIST_HAS_GLIDE_SLOPE(0x4): Set if the station transmits an ILS approach angle, and if so fGlideSlopeAngle contains valid data.</li>
     *                        <li>SIMCONNECT_RECV_ID_VOR_LIST_HAS_DME(0x8): Set if the station t transmits a DME signal, and if so the inherited DME fFrequency contains valid data.</li>
     *                        </ul>
     * @param localizer       The ILS localizer angle in degrees.
     * @param glideLat        The latitude of the glide slope transmitter in degrees.
     * @param glideLon        The longitude of the glide slope transmitter in degrees.
     * @param glideAlt        The altitude of the glide slope transmitter in degrees.
     * @param glideSlopeAngle The ILS approach angle in degrees.
     */
    public FacilityVOR(String icao, String regionCode, double latitude, double longitude, double altitude, float magVar,
            int frequency, int flags, float localizer, double glideLat, double glideLon, double glideAlt,
            float glideSlopeAngle) {
        super(icao, regionCode, latitude, longitude, altitude, magVar, frequency);
        this.flags = flags;
        this.localizer = localizer;
        this.glideLat = glideLat;
        this.glideLon = glideLon;
        this.glideAlt = glideAlt;
        this.glideSlopeAngle = glideSlopeAngle;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + "icao='" + icao + '\'' + ", regionCode='" + regionCode + '\'' + ", latitude=" + latitude + ", longitude=" + longitude + ", altitude=" + altitude + ", magVar=" + magVar + ", frequency=" + frequency + ", flags=" + flags + ", localizer=" + localizer + ", glideLat=" + glideLat + ", glideLon=" + glideLon + ", glideAlt=" + glideAlt + ", glideSlopeAngle=" + glideSlopeAngle + '}';
    }
}