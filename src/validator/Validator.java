package validator;

import java.util.regex.Pattern;

public class Validator {

    //public static final Pattern CODEFORMAT = Pattern.compile();

    public static void isNotNull(String field, String error) {
        if (field == null || field.equals("")) {
            throw new IllegalArgumentException(error);
        }
    }

    public static void isNotBlank(String field, String error) {
        if (field.isBlank()) {
            throw new IllegalArgumentException(error);
        }
    }

    public static void isNotBlankAndNotSpecialCaracters(String field, String error){
        //CODEFORMAT.matcher(field).matches();
    }
}
