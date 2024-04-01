package org.lembeck.fs.simconnect;

import java.nio.ByteBuffer;

import static java.nio.charset.StandardCharsets.ISO_8859_1;

public class SimUtil {

    public static final int OBJECT_ID_USER = 0;

    public final static int MAX_PATH = 260;

    public static final int UNKNOWN_GROUP = 0xffffffff;

    public static final int SIMCONNECT_PROTOCOL_FS2020 = 5;



    public static String readString(ByteBuffer buffer, int length) {
        byte[] data = new byte[length];
        buffer.get(data);
        int realLength = 0;
        while (realLength < data.length && data[realLength] !=0) {
            realLength++;
        }
        return new String(data, 0, realLength, ISO_8859_1);
    }

    public static void writeString(ByteBuffer buffer, String text, int length) {
        byte[] bytes = text.getBytes(ISO_8859_1);
        buffer.put(bytes, 0, Math.min(length, bytes.length));
        if (bytes.length < length) {
            byte[] padding = new byte[length - bytes.length];
            buffer.put(padding);
        }
    }
}