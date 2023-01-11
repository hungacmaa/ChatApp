import client.Client;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Client client = new Client("Hung");
        client.startConnect("localhost", 6789);
    }
}