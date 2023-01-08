import model.RequestObject;
import model.ResponseObject;
import threadhandle.ReceiveThreadHandle;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Socket mySocket = null;
        ObjectOutputStream out = null;
        ObjectInputStream in = null;
        RequestObject sendObject = new RequestObject();
        ResponseObject receiveObject = new ResponseObject();
        String msg = "";
        Scanner sc = new Scanner(System.in);
        try {
            mySocket = new Socket("localhost", 6789);
            System.out.println("Da ket noi voi server !!!");
            out = new ObjectOutputStream(mySocket.getOutputStream());
            in = new ObjectInputStream(mySocket.getInputStream());
            ReceiveThreadHandle receiveThread = new ReceiveThreadHandle(in);
            receiveThread.start();
            while (true) {
                msg = sc.nextLine();
                sendObject = new RequestObject("chat", msg);
                out.writeObject(sendObject);
                out.flush();
            }
        } catch (Exception e) {

        } finally {
            try {
                out.close();
                in.close();
                mySocket.close();
                System.out.println("Da ngat ket noi toi server !!!");
            } catch (Exception e) {
            }

        }
    }
}