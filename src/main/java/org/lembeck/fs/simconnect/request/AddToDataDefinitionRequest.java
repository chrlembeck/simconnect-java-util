package org.lembeck.fs.simconnect.request;

import org.lembeck.fs.simconnect.SimUtil;
import org.lembeck.fs.simconnect.constants.DataType;

import java.nio.ByteBuffer;

/**
 * The SimConnect_AddToDataDefinition function is used to add a Microsoft Flight Simulator simulation variable name
 * to a client defined object definition.
 *
 * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_AddToDataDefinition.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_AddToDataDefinition.htm</a>
 */
public class AddToDataDefinitionRequest extends SimRequest {

    /**
     * Internally used ID of this simconnect request.
     */
    public static final int TYPE_ID = 0xf000000c;

    /**
     * Specifies the unused datumID.
     */
    public static final int UNUSED = 0xffffffff;

    private final int defineID;

    private final String datumName;

    private final String unitsName;

    private final DataType datumType;

    private final float epsilon;

    private final int datumID;

    AddToDataDefinitionRequest(ByteBuffer buffer) {
        super(buffer);
        defineID = buffer.getInt();
        datumName = SimUtil.readString(buffer, 256);
        unitsName = SimUtil.readString(buffer, 256);
        datumType = DataType.ofId(buffer.getInt());
        epsilon = buffer.getFloat();
        datumID = buffer.getInt();
    }

    /**
     * The SimConnect_AddToDataDefinition function is used to add a Microsoft Flight Simulator simulation variable name
     * to a client defined object definition.
     *
     * @param defineID  Specifies the ID of the client defined data definition.
     * @param datumName Specifies the name of the Microsoft Flight Simulator simulation variable. See the Simulation
     *                  Variables documents for tables of variable names. If an index is required then it should be
     *                  appended to the variable name following a colon, see the example for DEFINITION_2 below.
     *                  Indexes are numbered from 1 (not zero). Simulation variable names are not case-sensitive
     *                  (so can be entered in upper or lower case).
     * @param unitsName Specifies the units of the variable. See Simulation Variable Units for tables of acceptable unit
     *                  names. It is possible to specify different units to receive the data in, from those specified in
     *                  the Simulation Variables document. The alternative units must come under the same heading (such
     *                  as Angular Velocity, or Volume, as specified in the Units of Measurement section of the
     *                  Simulation Variables document). For strings and structures enter "NULL" for this parameter.
     * @param datumType One member of the SIMCONNECT_DATATYPE enumeration type. This parameter is used to determine
     *                  what datatype should be used to return the data. The default is SIMCONNECT_DATATYPE_FLOAT64.
     *                  Note that the structure data types are legitimate parameters here.
     * @param epsilon   If data is requested only when it changes (see the flags parameter of
     *                  SimConnect_RequestDataOnSimObject) a change will only be reported if it is greater than the
     *                  value of this parameter (not greater than or equal to). The default is zero, so even the tiniest
     *                  change will initiate the transmission of data. Set this value appropriately so insignificant
     *                  changes are not transmitted. This can be used with integer data, the floating point fEpsilon
     *                  value is first truncated to its integer component before the comparison is made (for example,
     *                  an fEpsilon value of 2.9 truncates to 2, so a data change of 2 will not trigger a transmission,
     *                  and a change of 3 will do so).
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_AddToDataDefinition.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_AddToDataDefinition.htm</a>
     */
    public AddToDataDefinitionRequest(int defineID, String datumName, String unitsName, DataType datumType, float epsilon) {
        this(defineID, datumName, unitsName, datumType, epsilon, UNUSED);
    }

    /**
     * The SimConnect_AddToDataDefinition function is used to add a Microsoft Flight Simulator simulation variable name
     * to a client defined object definition.
     *
     * @param defineID  Specifies the ID of the client defined data definition.
     * @param datumName Specifies the name of the Microsoft Flight Simulator simulation variable. See the Simulation
     *                  Variables documents for tables of variable names. If an index is required then it should be
     *                  appended to the variable name following a colon, see the example for DEFINITION_2 below.
     *                  Indexes are numbered from 1 (not zero). Simulation variable names are not case-sensitive
     *                  (so can be entered in upper or lower case).
     * @param unitsName Specifies the units of the variable. See Simulation Variable Units for tables of acceptable unit
     *                  names. It is possible to specify different units to receive the data in, from those specified in
     *                  the Simulation Variables document. The alternative units must come under the same heading (such
     *                  as Angular Velocity, or Volume, as specified in the Units of Measurement section of the
     *                  Simulation Variables document). For strings and structures enter "NULL" for this parameter.
     * @param datumType One member of the SIMCONNECT_DATATYPE enumeration type. This parameter is used to determine
     *                  what datatype should be used to return the data. The default is SIMCONNECT_DATATYPE_FLOAT64.
     *                  Note that the structure data types are legitimate parameters here.
     * @param epsilon   If data is requested only when it changes (see the flags parameter of
     *                  SimConnect_RequestDataOnSimObject) a change will only be reported if it is greater than the
     *                  value of this parameter (not greater than or equal to). The default is zero, so even the tiniest
     *                  change will initiate the transmission of data. Set this value appropriately so insignificant
     *                  changes are not transmitted. This can be used with integer data, the floating point fEpsilon
     *                  value is first truncated to its integer component before the comparison is made (for example,
     *                  an fEpsilon value of 2.9 truncates to 2, so a data change of 2 will not trigger a transmission,
     *                  and a change of 3 will do so).
     * @param datumID   Specifies a client defined datum ID. The default is zero. Use this to identify the data received
     *                  if the data is being returned in tagged format (see the flags parameter of
     *                  SimConnect_RequestDataOnSimObject. There is no need to specify datum IDs if the data is not
     *                  being returned in tagged format.
     * @see <a href="https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_AddToDataDefinition.htm">https://docs.flightsimulator.com/html/Programming_Tools/SimConnect/API_Reference/Events_And_Data/SimConnect_AddToDataDefinition.htm</a>
     */
    public AddToDataDefinitionRequest(int defineID, String datumName, String unitsName, DataType datumType, float epsilon, int datumID) {
        super(TYPE_ID);
        this.defineID = defineID;
        this.datumName = datumName;
        this.unitsName = unitsName;
        this.datumType = datumType;
        this.epsilon = epsilon;
        this.datumID = datumID;
    }

    @Override
    protected void writeRequest(ByteBuffer outBuffer) {
        outBuffer.putInt(defineID);
        SimUtil.writeString(outBuffer, datumName, 256);
        SimUtil.writeString(outBuffer, unitsName, 256);
        outBuffer.putInt(datumType.ordinal());
        outBuffer.putFloat(epsilon);
        outBuffer.putInt(datumID);
    }

    /**
     * Returns the ID of the client defined data definition.
     *
     * @return ID of the client defined data definition.
     */
    public int getDefineID() {
        return defineID;
    }

    /**
     * Returns the name of the Microsoft Flight Simulator simulation variable. See the Simulation Variables documents
     * for tables of variable names. If an index is required then it should be appended to the variable name following
     * a colon, see the example for DEFINITION_2 below. Indexes are numbered from 1 (not zero). Simulation variable
     * names are not case-sensitive (so can be entered in upper or lower case).
     *
     * @return name of the Microsoft Flight Simulator simulation variable.
     */
    public String getDatumName() {
        return datumName;
    }

    /**
     * Returns the units of the variable. See Simulation Variable Units for tables of acceptable unit names. It is
     * possible to specify different units to receive the data in, from those specified in the Simulation Variables
     * document. The alternative units must come under the same heading (such as Angular Velocity, or Volume, as
     * specified in the Units of Measurement section of the Simulation Variables document). For strings and structures
     * enter "NULL" for this parameter.
     *
     * @return Units of the variable.
     */
    public String getUnitsName() {
        return unitsName;
    }

    /**
     * Returns one member of the SIMCONNECT_DATATYPE enumeration type. This parameter is used to determine what datatype
     * should be used to return the data. The default is SIMCONNECT_DATATYPE_FLOAT64. Note that the structure data types
     * are legitimate parameters here.
     *
     * @return Data type of the variable.
     */
    public DataType getDatumType() {
        return datumType;
    }

    /**
     * If data is requested only when it changes (see the flags parameter of SimConnect_RequestDataOnSimObject) a change
     * will only be reported if it is greater than the value of this parameter (not greater than or equal to). The
     * default is zero, so even the tiniest change will initiate the transmission of data. Set this value appropriately
     * so insignificant changes are not transmitted. This can be used with integer data, the floating point fEpsilon
     * value is first truncated to its integer component before the comparison is made (for example, an fEpsilon value
     * of 2.9 truncates to 2, so a data change of 2 will not trigger a transmission, and a change of 3 will do so).
     *
     * @return Epsilon value as described above.
     */
    public float getEpsilon() {
        return epsilon;
    }

    /**
     * Returns the client defined datum ID. The default is zero. Use this to identify the data received if the data is
     * being returned in tagged format (see the flags parameter of SimConnect_RequestDataOnSimObject. There is no need
     * to specify datum IDs if the data is not being returned in tagged format.
     *
     * @return Client defined datum ID.
     */
    public int getDatumID() {
        return datumID;
    }

    /**
     * Returns a string representation of the object.
     *
     * @return A string representation of the object.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() +
                " {typeID=" + Integer.toHexString(getTypeID()) +
                ", size=" + getSize() +
                ", version=" + getVersion() +
                ", identifier=" + getIdentifier() +
                ", defineID='" + defineID + "'" +
                ", datumName='" + datumName + "'" +
                ", unitsName='" + unitsName + "'" +
                ", datumType=" + datumType +
                ", fEpsilon=" + epsilon +
                ", datumId=" + datumID +
                "}";
    }
}
