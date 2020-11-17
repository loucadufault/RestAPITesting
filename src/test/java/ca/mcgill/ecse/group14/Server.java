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
        System.out.print("Starting server...");
        if (status == ServerStatus.RUNNING && ping() == 0) {
            System.out.println("   already running.");
            return;
        }
        final ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", "runTodoManagerRestAPI-1.5.5.jar");
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        started = true;
        System.out.println("   successful.");
    }

    public static void stop() {
        System.out.print("Stopping server...");
        if (status == ServerStatus.DOWN) {
            System.out.println("   already down.");
            return;
        }
        process.destroy();
        process = null;
        status = ServerStatus.DOWN;
        System.out.println("   stopped.");
    }

    public static void shutdown() {
        System.out.print("Shutting down server...");
        if (status == ServerStatus.DOWN) {
            System.out.println("   already down.");
            return;
        }
        try {
            get(BASE_URL + "/shutdown");
        } catch (Exception e) {
            // suppress
        }
        stop();
        System.out.println("   shutdown.");
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
        System.out.print("Checking server...");

        if(!started) {
            System.out.println("Server has not been started!");
            return 1;
        }

        try {
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream())); // consume process stdout
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null && line.contains("Running on 4567")) {
                    status = ServerStatus.RUNNING;
                    System.out.println("   running.");
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
        System.out.print("Waiting for server...");
        final int MAX_PINGS = 10;
        try {
            int pings = 0;
            while (pings < MAX_PINGS) {
                if (ping() == 0) {
                    status = ServerStatus.RUNNING;
                    System.out.println("   ready.");
                    return 0;
                } else {
                    System.out.print("   not yet ready...");
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
