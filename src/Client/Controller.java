package Client;

import Client.ConnectionWithTeacher.Client;
import Client.ConnectionWithTeacher.Questions;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

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
    private int goodAnswers = 0;
    private int allQ = 0;



    public boolean ifGood;
    private int counter = 0;
    private Client client = new Client();

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
        ClientState clientState = ClientState.getInstance();

        if (counter == 0) {
            client.connectToServer(clientState.getIpAddress());
            client.getData();
        }

        try {
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

            ToggleGroup group = new ToggleGroup();
            a.setToggleGroup(group);
            b.setToggleGroup(group);
            c.setToggleGroup(group);
            d.setToggleGroup(group);
            allQ++;
            if (counter > 0) {
                String correct = client.questionsList.get(counter - 1).getCorrectAnswer();
                ifCorrect.setVisible(true);

                if (a.isSelected() && correct.equals("A")) {
                    ifCorrect.setText("Dobrze!");
                    ifGood = true;
                    goodAnswers++;
                    System.out.println(goodAnswers);
                } else if (b.isSelected() && correct.equals("B")) {
                    ifCorrect.setText("Dobrze!");
                    ifGood = true;
                    goodAnswers++;
                    System.out.println(goodAnswers);
                } else if (c.isSelected() && correct.equals("C")) {
                    ifCorrect.setText("Dobrze!");
                    ifGood = true;
                    goodAnswers++;
                    System.out.println(goodAnswers);
                } else if (d.isSelected() && correct.equals("D")) {
                    ifCorrect.setText("Dobrze!");
                    ifGood = true;
                    goodAnswers++;
                    System.out.println(goodAnswers);

                } else {
                    ifCorrect.setText("Źle!");
                    ifGood = false;
                }
            }
        } catch (Exception e) {
            goodAnswers++;
            question.setVisible(false);
            a.setVisible(false);
            b.setVisible(false);
            c.setVisible(false);
            d.setVisible(false);
            category.setVisible(false);
            clientState.setGoodAnswers(goodAnswers + " dobrych odpowiedzi na " + allQ + "." );

            ifCorrect.setText("Koniec pytań, ilosc poprawnych odpowiedzi to: " + goodAnswers + "/" + allQ);
            client.sendInfo();

            e.printStackTrace();
        }
        counter++;
    }
}



