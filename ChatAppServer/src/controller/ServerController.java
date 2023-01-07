package controller;

import model.RequestObject;
import model.ResponseObject;
import view.ServerHomeView;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerController {
    private ServerSocket serverSocket = null;
    private ServerHomeView view = null;
    private RequestObject request = null;
    private ResponseObject response = null;

    public ServerController() {
    }

    public ServerController(ServerHomeView view, RequestObject request, ResponseObject response) {
        if(this.serverSocket == null){
            try{
                this.serverSocket = new ServerSocket(6789);
            } catch (IOException e) {
                // handle server socket error
            }
        }
        if (this.view == null) this.view = view;
        if (this.request == null) this.request = request;
        if (this.response == null) this.response = response;
    }

    public ServerHomeView getView() {
        return view;
    }

    public void setView(ServerHomeView view) {
        this.view = view;
    }

    public RequestObject getRequest() {
        return request;
    }

    public void setRequest(RequestObject request) {
        this.request = request;
    }

    public ResponseObject getResponse() {
        return response;
    }

    public void setResponse(ResponseObject response) {
        this.response = response;
    }
}
