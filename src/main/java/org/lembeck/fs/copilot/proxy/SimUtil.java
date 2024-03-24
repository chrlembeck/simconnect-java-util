package org.lembeck.fs.copilot.proxy;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class SimUtil {

    public static String readString(ByteBuffer buffer, int length) {
        byte[] data = new byte[length];
        buffer.get(data);
        int realLength = 0;
        while (realLength < data.length && data[realLength] !=0) {
            realLength++;
        }
        return new String(data, 0, realLength, StandardCharsets.ISO_8859_1);
    }
}