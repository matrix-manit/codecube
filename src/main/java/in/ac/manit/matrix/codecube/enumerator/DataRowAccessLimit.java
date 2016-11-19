package in.ac.manit.matrix.codecube.enumerator;

/**
 * Enum to represent the limit for accessing
 * the number of rows from a database table.
 *
 * @author Mohit Godwani
 */
public enum DataRowAccessLimit {

    SMALL(10),
    MEDIUM(25),
    LARGE(50),
    VERY_LARGE(100);

    private int value;

    DataRowAccessLimit(int value) {
        this.value = value;
    }

    /**
     * Given a value, returns the enum corresponding
     * to that value.
     * @param value the integer value for which enum is required
     * @return the enum which represents the given value
     */
    public static DataRowAccessLimit getEnum(Integer value) {
        for (DataRowAccessLimit limit : DataRowAccessLimit.values()) {
            if (limit.value == value) {
                return limit;
            }
        }
        return null;
    }

    /**
     * Returns the value which this enum represents
     *
     * @return value corresponding to that enum
     */
    public int getValue() {
        return this.value;
    }

}
