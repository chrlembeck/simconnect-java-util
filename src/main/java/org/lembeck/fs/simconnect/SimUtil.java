package org.lembeck.fs.simconnect;

import java.nio.ByteBuffer;

import static java.lang.Math.*;
import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Contains utility methods for simple calculations and some global constants.
 */
public final class SimUtil {

    /**
     * Radius of the earth in meters.
     */
    public static final double RADIUS_EARTH_M = 6378137;

    /**
     * Factor to convert from nautical miles to meters. One nautical mile has 1852 meters.
     */
    public static final double METERS_PER_NAUTICAL_MILE = 1852d;

    /**
     * Proxy value for User vehicle ObjectID.
     */
    public static final int OBJECT_ID_USER = 0;

    /**
     * Maximum length of Microsoft Flight Simulator path strings.
     */
    public final static int MAX_PATH = 260;

    /**
     * Constant to specify unknown groupIDs.
     */
    public static final int UNKNOWN_GROUP = 0xffffffff;

    /**
     * Version of the implemented simconnect protocol.
     */
    public static final int SIMCONNECT_PROTOCOL_FS2020 = 5;

    private SimUtil() {
        // utility class
    }

    /**
     * Reads length bytes from the ByteBuffer, interprets them as a null terminated string in UTF-8-notation and
     * converts it into a java string.
     *
     * @param buffer The ByteBuffer to read the string from.
     * @param length Number of bytes that are reserved for the contents of the string.
     * @return Java String after conversion from UTF-8-byte-buffer. If the length was 0, an empty String will be returned.
     */
    public static String readString(ByteBuffer buffer, int length) {
        byte[] data = new byte[length];
        buffer.get(data);
        int realLength = 0;
        while (realLength < data.length && data[realLength] != 0) {
            realLength++;
        }
        return new String(data, 0, realLength, UTF_8);
    }

    /**
     * Writes a Java String to the ByteBuffer and fills it with leading zeros up to the specified length.
     *
     * @param buffer ByteBuffer, the string will be written into.
     * @param text   The String that should be convertet into UTF-8 bytes.
     * @param length Number of bytes reserved for the string in the byte representation.
     */
    public static void writeString(ByteBuffer buffer, String text, int length) {
        byte[] bytes = text == null ? new byte[0] : text.getBytes(UTF_8);
        buffer.put(bytes, 0, Math.min(length, bytes.length));
        if (bytes.length < length) {
            byte[] padding = new byte[length - bytes.length];
            buffer.put(padding);
        }
    }

    /**
     * Calculates the distance between to geo coordinates in meters.
     *
     * @param lat1Deg Latitude of the first point in degrees.
     * @param lon1Deg Longitude of the second point in degrees.
     * @param lat2Deg Latitude of the first point in degrees.
     * @param lon2Deg Longitude of the second point in degrees.
     * @return Distance in meters
     * @see <a href="https://en.wikipedia.org/wiki/Great-circle_distance">https://en.wikipedia.org/wiki/Great-circle_distance</a>
     * @see <a href="https://en.wikipedia.org/wiki/Geographical_distance">https://en.wikipedia.org/wiki/Geographical_distance</a>
     */
    public static double distance(final double lat1Deg, final double lon1Deg, final double lat2Deg,
            final double lon2Deg) {
        final double lat1 = Math.toRadians(lat1Deg);
        final double lat2 = Math.toRadians(lat2Deg);
        final double lon1 = Math.toRadians(lon1Deg);
        final double lon2 = Math.toRadians(lon2Deg);
        return abs(RADIUS_EARTH_M * theta(lat1, lon1, lat2, lon2));
    }

    /**
     * Heading in degrees from one point to another.
     *
     * @param lat1Deg Latitude of the first point in degrees.
     * @param lon1Deg Longitude of the first point in degrees.
     * @param lat2Deg Latitude of the first point in degrees.
     * @param lon2Deg Longitude of the first point in degrees.
     * @return Heading from the first point to the second in degrees clockwise between 0 and 360. 0 means north, 90 east, ...
     * @see <a href="https://en.wikipedia.org/wiki/Great-circle_distance">https://en.wikipedia.org/wiki/Great-circle_distance</a>
     * @see <a href="https://en.wikipedia.org/wiki/Geographical_distance">https://en.wikipedia.org/wiki/Geographical_distance</a>
     */
    public static double heading(final double lat1Deg, final double lon1Deg, final double lat2Deg,
            final double lon2Deg) {
        final double lat1 = Math.toRadians(lat1Deg);
        final double lat2 = Math.toRadians(lat2Deg);
        final double lon1 = Math.toRadians(lon1Deg);
        final double lon2 = Math.toRadians(lon2Deg);

        final double x = cos(lat1) * sin(lat2) - sin(lat1) * cos(lat2) * cos(lon2 - lon1);
        final double y = cos(lat2) * sin(lon2 - lon1);
        double beta = atan2(y, x);
        if (beta < 0) {
            beta += 2 * Math.PI;
        }
        return toDegrees(beta);
    }

    /**
     * Calculates the angle between to points described by its geo coordinates seen from the middle of the earth.
     *
     * @param lat1Rad Latitude of the first point in radians.
     * @param lon1Rad Longitude of the first point in degrees.
     * @param lat2Rad Latitude of the second point in radians.
     * @param lon2Rad Longitude of the first point in radians.
     * @return angle between the two points in radians.
     * @see <a href="https://en.wikipedia.org/wiki/Great-circle_distance">https://en.wikipedia.org/wiki/Great-circle_distance</a>
     * @see <a href="https://en.wikipedia.org/wiki/Geographical_distance">https://en.wikipedia.org/wiki/Geographical_distance</a>
     */
    private static double theta(double lat1Rad, double lon1Rad, double lat2Rad, double lon2Rad) {
        final double num = sqrt(pow(cos(lat2Rad) * sin(lon2Rad - lon1Rad), 2) + pow(cos(lat1Rad) * sin(lat2Rad) - sin(lat1Rad) * cos(lat2Rad) * cos(lon2Rad - lon1Rad), 2));
        final double denom = sin(lat1Rad) * sin(lat2Rad) + cos(lat1Rad) * cos(lat2Rad) * cos(lon2Rad - lon1Rad);
        final double theta = atan2(num, denom);
        return theta;
    }

    /**
     * Converts a length in meters into a length in nautical miles.
     *
     * @param meter Length in meters.
     * @return Length in nautical miles.
     */
    public static double meterToNM(double meter) {
        return meter / METERS_PER_NAUTICAL_MILE;
    }

    /**
     * Converts a length in nautical miles into a length in meters.
     *
     * @param nauticalMiles Length in nautical miles.
     * @return Length in meters.
     */
    public static double nmToMeter(double nauticalMiles) {
        return nauticalMiles * METERS_PER_NAUTICAL_MILE;
    }
}