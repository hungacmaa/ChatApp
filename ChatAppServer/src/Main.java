import enums.ViewAction;
import model.RequestObject;
import threadhandle.ReceiveThreadHandle;
import view.ServerHomeView;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ServerSocket server = null;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        Socket client = null;
        RequestObject sendObject = new RequestObject();
        String msg;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Mo server tai port 6789");
            server = new ServerSocket(6789);
            client = server.accept();
            System.out.println("Da co ket noi tu client");
            in = new ObjectInputStream(client.getInputStream());
            out = new ObjectOutputStream(client.getOutputStream());
            ReceiveThreadHandle receiveThread = new ReceiveThreadHandle(in);
            receiveThread.start();
            while (true) {
                msg = sc.nextLine();
                if (msg.equals("exit")) {
                    sendObject.setMessage("exit");
                    sendObject.setData(null);
                    out.writeObject(sendObject);
                    try {
                        if (in != null) in.close();
                        if (out != null) out.close();
                        if (client != null) client.close();
                        if (server != null) server.close();
                    } catch (Exception e) {

                    }
                } else {
                    sendObject.setMessage("chat");
                    sendObject.setData(msg);
                    out.writeObject(sendObject);
                    out.flush();
                }
            }
        } catch (Exception e) {

        } finally {
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (client != null) client.close();
                if (server != null) server.close();
            } catch (Exception e) {

            }

        }

    }


}