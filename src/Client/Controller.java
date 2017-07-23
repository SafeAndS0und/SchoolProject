package Client;

import Client.ConnectionWithTeacher.Client;
import Client.ConnectionWithTeacher.Questions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public Label question;
    public RadioButton a;
    public RadioButton b;
    public RadioButton c;
    public RadioButton d;
    public Label category;
    public Button next;
    public Label ifCorrect;
    public TextField imie;
    public TextField adresIP;

    public boolean ifGood;
    int counter = 0;
    Client client = new Client();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        question.setVisible(false);
        a.setVisible(false);
        b.setVisible(false);
        c.setVisible(false);
        d.setVisible(false);
        category.setVisible(false);
        ifCorrect.setVisible(false);

    }

    public void readQuestion() {
        if (counter == 0) {
            client.connectToServer("127.0.0.1");
            client.getData();
        }
        imie.setVisible(false);
        adresIP.setVisible(false);

        question.setText(client.questionsList.get(counter).getQuestion());
        a.setText(client.questionsList.get(counter).getAnswerA());
        b.setText(client.questionsList.get(counter).getAnswerB());
        c.setText(client.questionsList.get(counter).getAnswerC());
        d.setText(client.questionsList.get(counter).getAnswerD());
        category.setText(client.questionsList.get(counter).getCategory());

        question.setVisible(true);
        a.setVisible(true);
        b.setVisible(true);
        c.setVisible(true);
        d.setVisible(true);
        category.setVisible(true);

        try {
            if (counter > 0) {
                String correct = client.questionsList.get(counter - 1).getCorrectAnswer();
                ifCorrect.setVisible(true);
                if (a.isSelected() && correct.equals("A")) {
                    ifCorrect.setText("Correct!");
                    ifGood = true;
                    counter++;
                }
                if (b.isSelected() && correct.equals("B")) {
                    ifCorrect.setText("Correct!");
                    ifGood = true;
                    counter++;
                }
                if (c.isSelected() && correct.equals("C")) {
                    ifCorrect.setText("Correct!");
                    ifGood = true;
                    counter++;
                }
                if (d.isSelected() && correct.equals("D")) {
                    ifCorrect.setText("Correct!");
                    ifGood = true;
                    counter++;
                } else {
                    ifCorrect.setText("Wrong!");
                    ifGood = false;
                    counter++;

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        counter++;

    }
}



