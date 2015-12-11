package Task1.Analyzer.Implementations;

/**
 * Created by Gorobets Dmitriy on 12/5/15.
 */
public enum EnumTaskType {
    FREQUENCY("frequency"),
    LENGTH("length"),
    DUPLICATES("duplicates");

    private String value;

    EnumTaskType(String value) {
        this.value = value;
    }

    /**
     * @param value
     * @return
     */
    public static EnumTaskType fromString(String value) {

        if (value != null) {
            for (EnumTaskType etp : EnumTaskType.values()) {
                if (value.equalsIgnoreCase(etp.value)) {
                    return etp;
                }
            }
        }
        throw new IllegalArgumentException("No such value");

    }


}
