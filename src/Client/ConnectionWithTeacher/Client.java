package Client.ConnectionWithTeacher;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.net.Socket;
import java.util.ArrayList;

import Client.ClientState;

public class Client {

    private Socket clientSocket;
    public ArrayList<Questions> questionsList = new ArrayList<>();
    OutputStream out;
    DataOutputStream dataOutputStream;


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

    int counter = 0;
    Questions q = new Questions();
    int howManyQuestions = 0;

    public void getData() {
        DataInputStream dataInputStream;
        try {
            dataInputStream = new DataInputStream(clientSocket.getInputStream());
            if (counter == 0) {
                String howMany = dataInputStream.readUTF();
                howManyQuestions = Integer.parseInt(howMany);
                Questions.setHowMany(howManyQuestions);
            }

            if (counter >= howManyQuestions) {
                return;
            }

            q.setQuestion(dataInputStream.readUTF());
            q.setAnswerA(dataInputStream.readUTF());
            q.setAnswerB(dataInputStream.readUTF());
            q.setAnswerC(dataInputStream.readUTF());
            q.setAnswerD(dataInputStream.readUTF());
            q.setCategory(dataInputStream.readUTF());
            q.setCorrectAnswer(dataInputStream.readUTF());
            questionsList.add(q);
            counter++;


        } catch (Exception e) {
            return;
        }
    }

    public void sendInfo() {
        try {
            ClientState clientState = ClientState.getInstance();
            out = clientSocket.getOutputStream();
            dataOutputStream = new DataOutputStream(out);

            dataOutputStream.writeUTF(clientState.getGoodAnswers());
            dataOutputStream.writeUTF(clientState.getUsername());

            //                dataOutputStream.writeUTF("flower");

            System.out.println("Sent");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws Exception {
        Client c = new Client();
        c.connectToServer("127.0.0.1");
        c.getData();
        c.sendInfo();


    }


}
