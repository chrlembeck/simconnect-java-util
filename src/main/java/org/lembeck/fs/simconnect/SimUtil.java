package org.lembeck.fs.simconnect;

import java.nio.ByteBuffer;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.nio.charset.StandardCharsets.UTF_8;

public class SimUtil {

    public static final double RADIUS_EARTH_M = 6378137;

    public static final int OBJECT_ID_USER = 0;

    public final static int MAX_PATH = 260;

    public static final int UNKNOWN_GROUP = 0xffffffff;

    public static final int SIMCONNECT_PROTOCOL_FS2020 = 5;

    public static String readString(ByteBuffer buffer, int length) {
        byte[] data = new byte[length];
        buffer.get(data);
        int realLength = 0;
        while (realLength < data.length && data[realLength] != 0) {
            realLength++;
        }
        return new String(data, 0, realLength, UTF_8);
    }

    public static void writeString(ByteBuffer buffer, String text, int length) {
        byte[] bytes = text == null ? new byte[0] : text.getBytes(UTF_8);
        buffer.put(bytes, 0, Math.min(length, bytes.length));
        if (bytes.length < length) {
            byte[] padding = new byte[length - bytes.length];
            buffer.put(padding);
        }
    }

    /**
     * Distance in meters between two points.
     */
    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);

        // see https://en.wikipedia.org/wiki/Great-circle_distance
        return RADIUS_EARTH_M * theta(lat1, lon1, lat2, lon2);
    }

    /**
     * Heading in degrees from one pont to another.
     */
    public static double heading(double lat1, double lon1, double lat2, double lon2) {
        // https://de.wikipedia.org/wiki/Orthodrome
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);

        double x = cos(lat1) * sin(lat2) - sin(lat1) * cos(lat2) * cos(lon2 - lon1);
        double y = cos(lat2) * sin(lon2 - lon1);
        double beta = Math.atan2(y, x);
        return Math.toDegrees(beta);
    }

    private static double theta(double lat1, double lon1, double lat2, double lon2) {
        final double num = Math.sqrt(Math.pow(cos(lat2) * sin(lon2 - lon1), 2) + Math.pow(cos(lat1) * sin(lat2) - sin(lat1) * cos(lat2) * cos(lon2 - lon1), 2));
        final double denom = sin(lat1) * sin(lat2) + cos(lat1) * cos(lat2) * cos(lon2 - lon1);
        final double theta = Math.atan2(num, denom);
        return theta;
    }

    public static double meterToNM(double meter) {
        return meter / 1852d;
    }

}