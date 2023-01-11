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
    private String hostIP;
    private int hostPort;
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private RequestObject req;
    private ResponseObject res;
    private Scanner sc;
    private String msg = "";

    public Client() {
        sc = new Scanner(System.in);
    }

    public Client(String username) {
        this.username = username;
        sc = new Scanner(System.in);
    }

    public void getInfo() {
        String userName = "";
        String serverIP = "";
        int port = 0;
        while (userName.isEmpty() || serverIP.isEmpty() || port == 0) {
            System.out.println("Vui long nhap dung dinh dang !!!");
            System.out.print("Ten hien thi: ");
            userName = sc.nextLine().trim();
            System.out.print("Server IP: ");
            serverIP = sc.nextLine().trim();
            System.out.print("Port: ");
            try {
                port = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                port = 0;
            }
        }
        this.username = userName;
        this.hostIP = serverIP;
        this.hostPort = port;
    }

    public void startConnect() {
//        System.out.println("Dang ket noi vao may chu " + IP + ":" + port);
        try {
            TimeWaiting t = new TimeWaiting("Dang ket noi den may chu");
            t.start();
            clientSocket = new Socket(hostIP, hostPort);
            System.out.println("\nDa ket noi vao may chu " + hostIP + ":" + hostPort + "!!!");
            try {
                out = new ObjectOutputStream(clientSocket.getOutputStream());
                in = new ObjectInputStream(clientSocket.getInputStream());
//            System.out.println("Da thiet lap luong vao ra !!!");

                ResponseHandler responseHandler = new ResponseHandler();
                responseHandler.start();

                req = new RequestObject("join", new JoinObject(username));
                request(req);

                while (true) {
                    msg = sc.nextLine();
                    req = new RequestObject("chat", msg);
                    request(req);
                }

            } catch (Exception e) {
                System.out.println("Khong the khoi tao luong !!!");
            }
        } catch (Exception e) {
            System.out.println("Khong the ket noi toi may chu !!!");
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
                System.out.println("co loi gi do");
            } finally {
                try {
                    out.close();
                    in.close();
                    clientSocket.close();
                    System.out.println("Dong ket noi den may chu");
                } catch (Exception e) {
                    System.out.println("");
                }
            }
        }
    }

    private class TimeWaiting extends Thread{
        private String notice;
        public TimeWaiting(String notice){
            this.notice = notice;
        }

        @Override
        public void run() {
            System.out.print(this.notice);
            while(clientSocket == null){
                try {
                    System.out.print(".");
                    Thread.sleep(1000);

                }catch (Exception e){

                }

            }
            return;
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
