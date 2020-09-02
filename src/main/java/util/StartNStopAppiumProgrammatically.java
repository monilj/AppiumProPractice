package util;

import io.appium.java_client.service.local.AppiumDriverLocalService;

import java.io.IOException;
import java.net.ServerSocket;

public class StartNStopAppiumProgrammatically {
    private static AppiumDriverLocalService localService;

    public static void startAppium() {
        localService= AppiumDriverLocalService.buildDefaultService();
        localService.start();

    }

    public static void stopAppium() {
        localService.stop();

    }

    public static boolean checkIfServerIsRunnning(int port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }
}
