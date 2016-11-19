package in.ac.manit.matrix.codecube.constants;

/**
 * Constants to represent the fields and columns corresponding to the model.
 *
 * @author Mohit Godwani
 */
public class UserConstants {

    /**
     * Class to access names of the fields in Model class
     */
    public static class ModelFields {
        public static final String scholarNumber = "scholarNumber";
        public static final String name = "name";
        public static final String emailAddress = "emailAddress";
        public static final String gender = "gender";
        public static final String phoneNumber = "phoneNumber";
        public static final String handle = "handle";
        public static final String branch = "branch";
        public static final String yearRating = "yearRating";
        public static final String overallRating = "overallRating";
        public static final String joiningDate = "joiningDate";
    }

    /**
     * Represents the column names in the database table
     */
    public static class TableColumns {
        public static final String scholarNumber = "ScholarNo";
        public static final String name = "Name";
        public static final String emailAddress = "Email";
        public static final String gender = "Gender";
        public static final String phoneNumber = "PhoneNo";
        public static final String handle = "Handle";
        public static final String branch = "Branch";
        public static final String yearRating = "YearRating";
        public static final String overallRating = "OverallRating";
        public static final String joiningDate = "JoiningDate";
    }

    /**
     * Fields on the basis of which user data can be sorted.
     */
    public static enum SortableFields {
        scholarNumber(ModelFields.scholarNumber),
        name(ModelFields.name),
        emailAddress(ModelFields.emailAddress),
        phoneNumber(ModelFields.phoneNumber),
        handle(ModelFields.handle),
        yearRating(ModelFields.yearRating),
        overallRating(ModelFields.overallRating);

        private String value;

        SortableFields(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}
