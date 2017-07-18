package Client.ConnectionWithTeacher;

import server.ConnectionWithStudent.Database;

import java.io.DataInputStream;
import java.net.Socket;

public class Client {

    private Socket clientSocket;

    /**
     * Laczy sie z serwerem o podanym adresie na porcie 6666
     *
     * @param ipAdress adres komputera, na ktorym jest serwer.
     */
    public void connectToServer(String ipAdress) {
        try {
            clientSocket = new Socket(ipAdress, 6666);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void getData() {
        try {
            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());

            //while (dataInputStream.available() > 0) {

                String question = dataInputStream.readUTF();
                String answerA = dataInputStream.readUTF();
                String answerB = dataInputStream.readUTF();
                String answerC = dataInputStream.readUTF();
                String answerD = dataInputStream.readUTF();
                String category = dataInputStream.readUTF();
                System.out.println(question);
                System.out.println(category);
         //   }
            dataInputStream.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws Exception {
        Client c = new Client();
        c.connectToServer("localhost");
        c.getData();
    }
}
