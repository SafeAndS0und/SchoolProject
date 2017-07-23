package server.ConnectionWithStudent;

import javax.xml.crypto.Data;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private java.net.ServerSocket serverSocket;
    private Socket clientSocket;

    /**
     * Stawia serwer na porcie 6666.
     */
    public void startServer() {
        try {
            serverSocket = new java.net.ServerSocket(6666);
            System.out.println("Server on port " + serverSocket.getLocalPort() + " has started.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitForConnection() {
        try {
            System.out.println("Waiting for client...");

            clientSocket = serverSocket.accept();
            System.out.println(clientSocket.getInetAddress().getHostAddress() + " connected.");


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendToClient() {
        try {

            Database db = new Database();
            db.connect();

            db.transformQuestions(1);
            db.transformQuestions(2);
            db.transformQuestions(3);
            db.transformQuestions(4);
            db.transformQuestions(5);


            OutputStream out = clientSocket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(out);
            int counter = 0;
            while (counter < db.questionsList.size()) {
                dataOutputStream.writeUTF(db.questionsList.get(counter).getQuestion());
                dataOutputStream.writeUTF(db.questionsList.get(counter).getAnswerA());
                dataOutputStream.writeUTF(db.questionsList.get(counter).getAnswerB());
                dataOutputStream.writeUTF(db.questionsList.get(counter).getAnswerC());
                dataOutputStream.writeUTF(db.questionsList.get(counter).getAnswerD());
                dataOutputStream.writeUTF(db.questionsList.get(counter).getCategory());
                dataOutputStream.writeUTF(db.questionsList.get(counter).getCorrectAnswer());
                counter++;

            }
            dataOutputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void serverStart() {

        Server s = new Server();
        s.startServer();

        while (true) {

            s.waitForConnection();
            s.sendToClient();
        }


    }
}
