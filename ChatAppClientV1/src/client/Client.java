package client;

import model.ChatObject;
import model.JoinObject;
import model.RequestObject;
import model.ResponseObject;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private String username;
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private RequestObject req;
    private ResponseObject res;
    private Scanner sc;
    private String msg = "";

    public Client(String username) {
        this.username = username;
        sc = new Scanner(System.in);
    }

    public void startConnect(String IP, int port) {
        System.out.println("Dang ket noi vao may chu " + IP + ":" + port);
        try {
            clientSocket = new Socket(IP, port);
            System.out.println("Da ket noi vao may chu !!!");
        } catch (Exception e) {
            System.out.println("Khong the ket noi toi may chu !!!");
        }
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("Da thiet lap luong vao ra !!!");
        } catch (Exception e) {
            System.out.println("Khong the khoi tao luong !!!");
        }

        ResponseHandler responseHandler = new ResponseHandler();
        responseHandler.start();

        req = new RequestObject("join", new JoinObject(username));
        request(req);

        while (true) {
            msg = sc.nextLine();
            req = new RequestObject("chat", msg);
            request(req);
        }
    }

    private class ResponseHandler extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    res = (ResponseObject) in.readObject();
                    String status = res.getStatus();
                    if (status.equals("ok")) {
                        String action = res.getAction();
                        if (action.equals("chat")) {
                            // Xử lý khi nhận về response chat
                            ChatObject chatObject = (ChatObject) res.getData();
                            System.out.println(chatObject.getUsername() + ": " + chatObject.getMessage());
                        } else if (action.equals("join")) {
                            // Xử lý khi nhận về response join
                            JoinObject joinObject = (JoinObject) res.getData();
                            System.out.println(joinObject.getUsername() + " da vao phong !!!");
                        } else {
                            // Xử lý khi nhận về response khác
                        }
                    }
                }
            } catch (Exception e) {

            } finally {
                try {
                    out.close();
                    in.close();
                    clientSocket.close();
                } catch (Exception e) {
                    System.out.println("Co loi khi dong luong");
                }
            }
        }
    }

    public void request(RequestObject req) {
        try {
            out.writeObject(req);
            out.flush();
        } catch (Exception e) {
            System.out.println("Gui request khong thanh cong !!!");
        }

    }
}
