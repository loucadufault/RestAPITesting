package ca.mcgill.ecse.group14.unit;

public class Resources {
    public static final String PORT = "4567";
    public static final String BASE_URL = "http://localhost:" + PORT;

    static class STATUS_CODE {
        public static final int OK = 200;
        public static final int CREATED = 201;
        public static final int BAD_REQUEST = 400;
        public static final int NOT_FOUND = 404;
        public static final int NOT_ALLOWED = 405;

    }
}
