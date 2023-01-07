package myserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class Server {
    private Scanner sc = new Scanner(System.in);
    private ServerSocket server = null;
    int port;

    public Server() {
    }

    public void start(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println(server.getInetAddress().getHostName());
            System.out.println(server.getLocalPort());
        }
        catch (IOException e) {
            System.out.println("Port nay da duoc su dung, vui long nhap port khac: ");
        }
        finally {
            stop();
        }

    }

    public void stop() {
        try {
            if (server != null) server.close();
        } catch (Exception e) {

        }

    }
}
