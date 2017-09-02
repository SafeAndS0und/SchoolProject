package server;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import server.ConnectionWithStudent.Results;

import java.net.URL;
import java.util.ResourceBundle;

public class ResultsController implements Initializable {

    public Label name = new Label();
    public Label result = new Label();
    Results results = Results.getInstance();

    public Label getName() {
        return name;
    }

    public void setName(Label name) {
        this.name = name;
    }

    public Label getResult() {
        return result;
    }

    public void setResult(Label result) {
        this.result = result;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void refresh() {
        try {
            Thread.sleep(250);
            if (results.getName() != null) {
                name.setText(results.getName());
                result.setText(results.getResult());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
