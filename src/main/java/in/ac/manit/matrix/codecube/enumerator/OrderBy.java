package in.ac.manit.matrix.codecube.enumerator;

/**
 * Created by root on 9/13/16.
 *
 * Enumerator for order by in SQL query
 */
public enum OrderBy {
    Ascending("asc"),
    Descending("desc");

    private String value;

    /**
     * Constructor for enum object
     * @param value
     */
    OrderBy(String value)
    {
        this.value = value;
    }

    /**
     *
     * @return returns the string corresponding to the enum value
     */
    public String getValue()
    {
        return this.value;
    }

}