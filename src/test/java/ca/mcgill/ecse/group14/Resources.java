package ca.mcgill.ecse.group14;

public class Resources {
    public static final String PORT = "4567";
    public static final String BASE_URL = "http://0.0.0.0:" + PORT;
    public static final String CLEAR_PATH = "admin/data/thingifier";
    public static final int[] OBJECT_COUNT_INCREASE_PROGRESSION = {1, 5, 10, 15, 25, 50, 75, 100, 125, 150, 200, 250, 300, 500, 750, 1000, 1500};

    public static class STATUS_CODE {
        public static final int OK = 200;
        public static final int CREATED = 201;
        public static final int BAD_REQUEST = 400;
        public static final int NOT_FOUND = 404;
        public static final int NOT_ALLOWED = 405;
    }

    public enum RequestMethod {
        GET, HEAD, POST, PUT, PATCH, DELETE;
    }
}
