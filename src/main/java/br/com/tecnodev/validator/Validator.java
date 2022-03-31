package br.com.tecnodev.validator;

public class Validator {

    public static void isNotNullOrEmpty(String field, String error) {
        if(field == null) {
            throw new NullPointerException(error);
        }

        if (field.isEmpty()) {
            throw new IllegalArgumentException(error);
        }
    }

    public static void isNotEmpty(String field, String error) {
        if (field.isEmpty()) {
            throw new NullPointerException(error);
        }
    }

    public static void isNotNull(Object field, String error) {
        if (field == null) {
            throw new NullPointerException(error);
        }
    }

    public static void timeInterval(int field, int start, int end, String error) {
        if (field < start || field > end) {
            throw new IllegalArgumentException(error);
        }
    }

    public static void regexValidatorAndNotEmpty(String field, String error) {
        isNotNull(field, error);
        if (!field.matches("[a-z0-9-]+") || field.isEmpty()) {
            throw new IllegalArgumentException(error);
        }

    }

    public static void hexadecimalValidator(String field, String error) {
        if (!field.matches("^#([a-fA-F0-9]){3}(([a-fA-F0-9]){3})?$")) {
            throw new IllegalArgumentException(error);
        }
    }

}
