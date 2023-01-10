import client.Client;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String msg = "";
//        String username = sc.nextLine();
        Client client = new Client("Hung");
        client.startConnect("localhost", 6789);
    }
}