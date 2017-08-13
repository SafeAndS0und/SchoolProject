package server.ConnectionWithStudent;

import java.net.Socket;

public class ClientWorker implements Runnable {

    private Socket client;
    private Server server = new Server();

    public ClientWorker(Socket socket){
        socket = client;
    }
    @Override
    public void run() {
        server.sendToClient();
        server.getFromClient();

    }
}
