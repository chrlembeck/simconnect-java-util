package org.lembeck.fs.simconnect.constants;

public enum RunwaySurface {

    CONCRETE,
    GRASS,
    WATER_FSX,
    GRASS_BUMPY,
    ASPHALT,
    SHORT_GRASS,
    LONG_GRASS,
    HARD_TURF,
    SNOW,
    ICE,
    URBAN,
    FOREST,
    DIRT,
    CORAL,
    GRAVEL,
    OIL_TREATED,
    STEEL_MATS,
    BITUMINUS,
    BRICK,
    MACADAM,
    PLANKS,
    SAND,
    SHALE,
    TARMAC,
    WRIGHT_FLYER_TRACK,
    OCEAN,
    WATER,
    POND,
    LAKE,
    RIVER,
    WASTE_WATER,
    PAINT,
    UNKNOWN,
    UNDEFINED;

    public static RunwaySurface ofId(int id) {
        return switch (id) {
            case 0 -> CONCRETE;
            case 1 -> GRASS;
            case 2 -> WATER_FSX;
            case 3 -> GRASS_BUMPY;
            case 4 -> ASPHALT;
            case 5 -> SHORT_GRASS;
            case 6 -> LONG_GRASS;
            case 7 -> HARD_TURF;
            case 8 -> SNOW;
            case 9 -> ICE;
            case 10 -> URBAN;
            case 11 -> FOREST;
            case 12 -> DIRT;
            case 13 -> CORAL;
            case 14 -> GRAVEL;
            case 15 -> OIL_TREATED;
            case 16 -> STEEL_MATS;
            case 17 -> BITUMINUS;
            case 18 -> BRICK;
            case 19 -> MACADAM;
            case 20 -> PLANKS;
            case 21 -> SAND;
            case 22 -> SHALE;
            case 23 -> TARMAC;
            case 24 -> WRIGHT_FLYER_TRACK;
            case 26 -> OCEAN;
            case 27 -> WATER;
            case 28 -> POND;
            case 29 -> LAKE;
            case 30 -> RIVER;
            case 31 -> WASTE_WATER;
            case 32 -> PAINT;
            case 254 -> UNKNOWN;

            default -> UNDEFINED;
        };
    }
}
