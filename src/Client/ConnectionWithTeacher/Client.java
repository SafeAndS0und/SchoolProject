package Client.ConnectionWithTeacher;

import java.io.DataInputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {

    private Socket clientSocket;
    public ArrayList<Questions> questionsList = new ArrayList<>();

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
        boolean eof = false;
        try {
            DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());

            while (!eof) {
                Questions q = new Questions();

                q.setQuestion(dataInputStream.readUTF());
                q.setAnswerA(dataInputStream.readUTF());
                q.setAnswerB(dataInputStream.readUTF());
                q.setAnswerC(dataInputStream.readUTF());
                q.setAnswerD(dataInputStream.readUTF());
                q.setCategory(dataInputStream.readUTF());
                q.setCorrectAnswer(dataInputStream.readUTF());
                questionsList.add(q);

            }
            System.out.println("Got the questions and answers.");
            dataInputStream.close();


        } catch (Exception e) {
            eof = true;
        }
    }



}
