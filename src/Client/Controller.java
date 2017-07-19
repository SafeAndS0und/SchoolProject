package Client;

import Client.ConnectionWithTeacher.Client;
import Client.ConnectionWithTeacher.Questions;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public Label question;
    public Label a;
    public Label b;
    public Label c;
    public Label d;
    public Label category;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        question.setVisible(false);
        a.setVisible(false);
        b.setVisible(false);
        c.setVisible(false);
        d.setVisible(false);
        category.setVisible(false);

    }

    public void readQuestion() {
        Client client = new Client();
        Questions q = new Questions();

        client.connectToServer("127.0.0.1");
        client.getData();

        int counter = 1;
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


    }
}
