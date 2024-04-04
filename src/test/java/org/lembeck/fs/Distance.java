package org.lembeck.fs;

import org.junit.jupiter.api.Test;
import org.lembeck.fs.simconnect.SimUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Distance {

    @Test
    void zeroDistance() {
        assertEquals(0d, SimUtil.distance(52, 5, 52, 5));
        assertEquals(0d, SimUtil.distance(-20, 178, -20, 178));
    }

    @Test
    void eddgEddm() {
        assertEquals(512054, SimUtil.distance(52.1346, 7.68483, 48.353783, 11.786086), 1);
    }

    @Test
    void berlinTokyo() {
        assertEquals(8928941, SimUtil.distance(52.517, 13.40, 35.70, 139.767), 1);
        assertEquals(41.5736, SimUtil.heading(52.517, 13.40, 35.70, 139.767), 0.001);
    }

    @Test
    void kansasStLoius() {
        assertEquals(8928941, SimUtil.distance(52.517, 13.40, 35.70, 139.767), 1);
        assertEquals(96.5126, SimUtil.heading(39.099912, -94.581213, 38.627089, -90.200203), 0.001);
    }
}