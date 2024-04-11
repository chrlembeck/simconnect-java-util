package org.lembeck.fs.simconnect.constants;

/**
 * The type of pavement used by the runway.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_AddToFacilityDefinition.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Facilities/SimConnect_AddToFacilityDefinition.htm</a>
 */
public enum RunwaySurface {

    /**
     * Specifies the runways surface to be concrete.
     */
    CONCRETE,

    /**
     * Specifies the runways surface to be grass.
     */
    GRASS,

    /**
     * Specifies the runways surface to be water.
     */
    WATER_FSX,

    /**
     * Specifies the runways surface to be bumpy grass.
     */
    GRASS_BUMPY,

    /**
     * Specifies the runways surface to be asphalt.
     */
    ASPHALT,

    /**
     * Specifies the runways surface to be short grass.
     */
    SHORT_GRASS,

    /**
     * Specifies the runways surface to be long grass.
     */
    LONG_GRASS,

    /**
     * Specifies the runways surface to be hard turf.
     */
    HARD_TURF,

    /**
     * Specifies the runways surface to be snow.
     */
    SNOW,

    /**
     * Specifies the runways surface to be ice.
     */
    ICE,

    /**
     * Specifies the runways surface to be urban.
     */
    URBAN,

    /**
     * Specifies the runways surface to be forest.
     */
    FOREST,

    /**
     * Specifies the runways surface to be dirt.
     */
    DIRT,

    /**
     * Specifies the runways surface to be coral.
     */
    CORAL,

    /**
     * Specifies the runways surface to be gravel.
     */
    GRAVEL,

    /**
     * Specifies the runways surface to be oil treated.
     */
    OIL_TREATED,

    /**
     * Specifies the runways surface to be steel mats.
     */
    STEEL_MATS,

    /**
     * Specifies the runways surface to be bituminous.
     */
    BITUMINUS,

    /**
     * Specifies the runways surface to be brick.
     */
    BRICK,

    /**
     * Specifies the runways surface to be macadam.
     */
    MACADAM,

    /**
     * Specifies the runways surface to be planks.
     */
    PLANKS,

    /**
     * Specifies the runways surface to be sand.
     */
    SAND,

    /**
     * Specifies the runways surface to be shale.
     */
    SHALE,

    /**
     * Specifies the runways surface to be tarmac.
     */
    TARMAC,

    /**
     * Specifies the runways surface to be wright flyer track.
     */
    WRIGHT_FLYER_TRACK,

    /**
     * Specifies the runways surface to be an ocean.
     */
    OCEAN,

    /**
     * Specifies the runways surface to be water.
     */
    WATER,

    /**
     * Specifies the runways surface to be a pond.
     */
    POND,

    /**
     * Specifies the runways surface to be a lake.
     */
    LAKE,

    /**
     * Specifies the runways surface to be a river.
     */
    RIVER,

    /**
     * Specifies the runways surface to be waste water.
     */
    WASTE_WATER,

    /**
     * Specifies the runways surface to be paint.
     */
    PAINT,

    /**
     * Specifies the runways surface to be unknown.
     */
    UNKNOWN,

    /**
     * Specifies the runways surface to be undefined (should not be used).
     */
    UNDEFINED;

    /**
     * Returns the runway surface specified by the given identifier.
     *
     * @param id Identifier of the runway surface.
     * @return The runway surface specified by the given identifier.
     */
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