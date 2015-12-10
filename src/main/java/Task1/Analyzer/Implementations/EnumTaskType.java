package Task1.Analyzer.Implementations;

/**
 * Created by invincible_g_d on 12/7/15.
 */
public enum EnumTaskType {
    FREQUENCY("frequency"),
    LENGTH("length"),
    DUPLICATES("duplicates");

    private String value;

    EnumTaskType(String value) {
        this.value = value;
    }

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
