package view;

import model.RequestObject;
import model.ResponseObject;

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

    public void show() {
        System.out.println("D A N G        M O         S E R V E R !!!!!!!!!");
    }
}
