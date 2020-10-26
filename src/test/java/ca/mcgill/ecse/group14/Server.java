package ca.mcgill.ecse.group14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static  io.restassured.RestAssured.get;

public class Server {
    public static final String BASE_URL = "http://localhost:4567";
    private static Process process;
    private static BufferedReader out;

    public static void start() {
        final Runtime runtime = Runtime.getRuntime();

        try {
            process = runtime.exec("java -jar runTodoManagerRestAPI-1.5.5.jar");
        } catch (IOException e) {
            e.printStackTrace();
        }

        final InputStream in = process.getInputStream();
        out = new BufferedReader(new InputStreamReader(in));
    }

    public static void stop() {
        process.destroy();
    }

    public static void check() throws InterruptedException {
//        while (true) {
//            try {
//                get(BASE_URL);
//                return;
//            } catch (Exception e) {
//                e.printStackTrace();
//                Thread.sleep(200); // Busy Wait
//            }
//        }

        try {
            String line;
            while ((line = out.readLine()) != null) {
                System.out.println(line);

                if (line!=null && line.contains("Running on 4567")) {
                    return;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
