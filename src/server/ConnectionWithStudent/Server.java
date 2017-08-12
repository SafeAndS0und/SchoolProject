package server.ConnectionWithStudent;

import javax.xml.crypto.Data;
import java.io.DataInputStream;
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
            //Connecting with the client
            OutputStream out = clientSocket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(out);

            //Connecting with the database
            Database db = new Database();
            db.connect();

            //Checking how many questions there is
            int howMany = db.checkHowMany();
            int counter = 0;
            String howManyQ = Integer.toString(howMany);
            dataOutputStream.writeUTF(howManyQ);

            //Transforming questions to the Questions class objects
            while(counter<=howMany){
                db.transformQuestions(counter);
                counter++;
            }

            counter=0;
            //Sending questions to client
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



        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void getFromClient() {
        try {
            System.out.println("Waiting for Information...");
            while(true){
                DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
                String username = dataInputStream.readUTF();
                System.out.println(username);
            }
            //           dataInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void serverStart() {
        Server s = new Server();
        s.startServer();

        //Uses Lambda to create new Thread, I guess.
        new Thread(() -> {
            while (true) {
                s.waitForConnection(); //Waiting for client to connect
                s.sendToClient(); //Sending questions to client
                s.getFromClient(); //Waiting for information about client
            }
        }) {{
            start();
        }};


    }

    public static void main(String[] args) {
        Server s = new Server();
        s.startServer();
        s.waitForConnection();
        s.sendToClient();
        s.getFromClient();


    }
}
