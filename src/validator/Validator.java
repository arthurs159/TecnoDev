package validator;

public class Validator {

    public static void isNotNull(String field, String error) {
        if (field == null || field.equals("")) {
            throw new IllegalArgumentException(error);
        }
    }

    public static void betweenOneAndTwenty(int field, String error) {
        if (field < 1 || field > 20){
            throw new IllegalArgumentException(error);
        }
    }

    public static void regexValidator (String field, String error){
        if(!field.matches("[a-z0-9^-]+")) {
            throw new IllegalArgumentException(error);
        }
    }
}
