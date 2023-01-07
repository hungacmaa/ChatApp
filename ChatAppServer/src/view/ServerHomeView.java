package view;

import enums.ViewAction;
import model.RequestObject;
import model.ResponseObject;

import java.io.IOException;

public class ServerHomeView {

    private RequestObject request = null;
    private ResponseObject response = null;

    private String message = "";

    public ServerHomeView() {
    }

    public ServerHomeView(RequestObject request, ResponseObject response) {
        if (this.request == null) this.request = request;
        if (this.response == null) this.response = response;
    }

    public void start(){

    }
    public void show(ViewAction a) {
        switch (a) {
            case MSG: {
                // Handle
                break;
            }
            case UPDATE: {
                // Handle
                break;
            }
            default:
                System.out.println("Roi kieu del gi cung chay vao day");
                break;
        }

    }

    public void show(String s) {
        System.out.println(s);
    }
}
