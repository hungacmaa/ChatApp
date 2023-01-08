package threadhandle;

import model.RequestObject;
import model.ResponseObject;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ReceiveThreadHandle extends Thread {
    private ObjectInputStream in;
    private ResponseObject sendObject = null;
    private RequestObject receiveObject = null;

    public ReceiveThreadHandle() {
    }

    public ReceiveThreadHandle(ObjectInputStream in) {
        this.in = in;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            while (true) {
                receiveObject = (RequestObject) in.readObject();
                if (receiveObject.getMessage().equals("chat")) {
                    System.out.println("Client: " + (String) receiveObject.getData());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
