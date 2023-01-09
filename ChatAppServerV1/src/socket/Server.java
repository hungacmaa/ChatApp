package socket;

import model.ChatObject;
import model.JoinObject;
import model.RequestObject;
import model.ResponseObject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private ServerSocket server;
    private ArrayList<ClientHandler> listClient;

    public Server() {
        listClient = new ArrayList<>();
    }

    public void startConnection(int port){
        try{
            System.out.println("Da mo server tai port "+port+" !!!!!");
            server = new ServerSocket(port);
            while(true){
                Socket clientSocket = server.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                listClient.add(clientHandler);
                clientHandler.start();
            }
        }
        catch (IOException e){
            System.out.println("Co loi gi co khi tao doi tuong ServerSocket");
        }
        finally {
            try {
                server.close();
            } catch (Exception e){

            }
        }

    }

    private class ClientHandler extends Thread{
        private String clientName;
        private Socket clientSocket;
        private ObjectInputStream in;
        private ObjectOutputStream out;
        private RequestObject req;
        private ResponseObject res;

        public ClientHandler(Socket clientSocket){
            this.clientSocket = clientSocket;
            try{
                in = new ObjectInputStream(clientSocket.getInputStream());
                out = new ObjectOutputStream(clientSocket.getOutputStream());
            } catch (IOException e){

            }
        }

        public void response(ResponseObject res){
            try{
                out.writeObject(res);
                out.flush();
            } catch (IOException e){

            }

        }
        @Override
        public void run() {
            try{
                while(true){
                    req = (RequestObject) in.readObject();
                    String action = req.getAction();
                    if(action.equals("chat")){
                        ChatObject chatObject = (ChatObject) req.getData();
                        // Xử lý khi có yêu cầu chat
                        responseAll(new ResponseObject("ok", "chat", chatObject));
                        System.out.println(chatObject.getUsername()+": "+chatObject.getMessage());
                    }
                    else if(action.equals("join")){
                        // Xử lý khi có yêu cầu join
                        JoinObject data = (JoinObject) req.getData();
                        this.clientName = data.getUsername();
                        System.out.println(this.clientName+" da vao phong!!!");
                        responseAll(new ResponseObject("ok", "join", data));
                    }
                    else{
                        // Xử lý trong các trường hợp còn lại
                    }
                }
            } catch (Exception e){

            } finally {
                try{
                    in.close();
                    out.close();
                    clientSocket.close();
                } catch (Exception e){

                }
            }
        }

    }

    private void responseAll(ResponseObject res){
        for(ClientHandler client: listClient){
            client.response(res);
        }
    }

    private void response(ClientHandler client, ResponseObject res){}
}
