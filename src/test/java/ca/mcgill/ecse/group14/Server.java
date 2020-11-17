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
    private static final int DELAY_MS = 2000;

    public static void boot() {
        start();
        if (waitUntilReady() + ping() != 0) {
            System.out.println("Failed to boot");
            shutdown();
        }
        waitUntilReady();
    }

    public static void start() {
        if (status == ServerStatus.RUNNING && ping() == 0) {
            System.out.println("Server already running.");
            return;
        }
        System.out.print("Starting server...");
        final ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", "runTodoManagerRestAPI-1.5.5.jar");
        if (process != null) {
            process.destroy();
        }
        try {
            process = processBuilder.start();
        } catch (IOException e) {
            process.destroy();
            process = null;
            e.printStackTrace();
        }
        started = true;
        System.out.println("   successful.");
        try {
            Thread.sleep(DELAY_MS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void stop() {
        System.out.print("Stopping server...");
        if (status == ServerStatus.DOWN) {
            System.out.print("   already down."); //, still stopping...");
            return;
        }
        process.destroy();
        status = ServerStatus.DOWN;
        System.out.println("   stopped.");
        try {
            Thread.sleep(DELAY_MS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * @deprecated
     */
    public static void shutdown() {
        System.out.print("Shutting down server...");
        if (status == ServerStatus.DOWN) {
            System.out.println("   already down, still shutdown...");
        }
        try {
            Runtime.getRuntime().exec("curl " + BASE_URL + "/shutdown");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("   shutdown.");
        stop();
    }

    private static void silentPing() throws Exception {
        get(BASE_URL).then().assertThat().statusCode(STATUS_CODE.OK);
    }

    public static int ping() {
        try {
            silentPing();
            System.out.println("   successful");
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return 1;
        }
    }

    /**
     * @deprecated
     */
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
                System.out.println(line);
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
        System.out.print("Waiting for server...");
        final int MAX_PINGS = 10;
        final int SLEEP_MS = 200;

        int pings = 0;
        try {
            while (pings < MAX_PINGS) {
                try {
                    silentPing();
                    status = ServerStatus.RUNNING;
                    System.out.println("   ready.");
                    return 0;
                } catch (Exception e) {
                    System.out.print("   not yet ready...");
                    Thread.sleep(SLEEP_MS);
                }
                pings++;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        status = ServerStatus.DOWN;
        return pings;
    }
}
