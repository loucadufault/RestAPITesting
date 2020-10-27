package ca.mcgill.ecse.group14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static ca.mcgill.ecse.group14.Resources.*;

public class Server {
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
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        process.destroy();
    }

    public static void shutdown() {
        try {
            Runtime.getRuntime().exec("curl " + BASE_URL + "/shutdown");
        } catch (IOException e) {
            e.printStackTrace();
        }
        stop();
    }

    public static int check() {
        try {
            String line;
            while ((line = out.readLine()) != null) {
                System.out.println(line);

                if (line!=null && line.contains("Running on 4567")) {
                    return 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
