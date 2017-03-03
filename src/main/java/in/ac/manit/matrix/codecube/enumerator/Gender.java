package in.ac.manit.matrix.codecube.enumerator;

/**
 * Enum to Represent Gender
 *
 * @author Mohit Godwani
 */
public enum Gender {
    Male("Male"),
    Female("Female"),
    Other("other");

    private String value;

    Gender(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        System.out.println(Gender.valueOf("Male").toString());
    }

    @Override
    public String toString() {
        return "{\'Gender\': \'" + this.value + "\'}";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
