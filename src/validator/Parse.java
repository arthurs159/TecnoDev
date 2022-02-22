package validator;

import tecnodev.course.Status;

public class Parse {

    public static boolean transformToBoolean(String active) {
        return active.equals("ATIVA");
    }

    public static Status transformEnumToString(String teste) {
        if (teste.equals("PRIVADA")) {
            return Status.PRIVATE;
        }
        return Status.PUBLIC;
    }
}
