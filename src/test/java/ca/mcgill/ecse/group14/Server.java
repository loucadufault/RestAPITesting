package ca.mcgill.ecse.group14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static io.restassured.RestAssured.get;

import static ca.mcgill.ecse.group14.Resources.*;

public class Server {
    private static Process process;
    public enum ServerStatus {RUNNING, DOWN};
    public static ServerStatus status = ServerStatus.DOWN;
    private static boolean started = false;

    public static void start() {
        final ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", "runTodoManagerRestAPI-1.5.5.jar");
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        started = true;
    }

    public static void stop() {
        process.destroy();
        process = null;
        status = ServerStatus.DOWN;
    }

    public static void shutdown() {
        try {
            get(BASE_URL + "/shutdown");
        } catch (Exception e) {
            return;
        }
        stop();
    }

    public static int ping() {
        try {
            get(BASE_URL);
            return 0;
        } catch (Exception e){
            return 1;
        }
    }

    public static int check() {
        if(!started) {
            System.out.println("Server has not been started!");
            return 0;
        }

        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream())); // consume process stdout
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null && line.contains("Running on 4567")) {
                    status = ServerStatus.RUNNING;
                    return 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        status = ServerStatus.DOWN;
        return 1;
    }

    public static int waitUntilReady() {
        final int MAX_PINGS = 10;
        try {
            int pings = 0;
            while (pings < MAX_PINGS) {
                if (ping() == 0) {
                    status = ServerStatus.RUNNING;
                    System.out.println("Ready!");
                    return 0;
                } else {
                    System.out.println("Not yet ready!");
                    Thread.sleep(200);
                }
                pings++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        status = ServerStatus.DOWN;
        return 1;
    }
}
