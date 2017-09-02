package server;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.ConnectionWithStudent.*;
import server.ConnectionWithStudent.Database;

public class TeacherPanelScreenController implements Initializable {

    @FXML
    private AnchorPane window;

    MainScreenController mainScreenController;
    server.ConnectionWithStudent.Database db = new Database();
    public Label startText = new Label();
    public CheckBox usePrevious = new CheckBox();
    public Label zaladuj;


    public void setMainController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	startText.setText("");
//    	startText.setText("W tym oknie masz dost�pne trzy mo�liwo�ci:" +
//                "\n" +
//                "\nUtw�rz Quiz - Pozwoli ci doda� nowe pytania, a na ko�cu postawi� serwer, do kt�rego b�d� mogli po��czy� si� uczniowie" +
//                "\n" +
//                "\nWyloguj - Wylogowuje Ci�" +
//                "\n" +
//                "\nWyniki - Po utworzeniu quizu i postawieniu serwera b�d� wy�wietla� si� tam wyniki uczni�w");
    	zaladuj.setText("Za�aduj poprzednie "
    			+ "\n"
    			+ " pytania");

    }

    @FXML
    public void startTheQuiz() {
        if(!usePrevious.isSelected()){
            db.connect();
            db.truncateTable();
        }
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("addQuestion.fxml"));
        Pane pane = null;
        try {
            pane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddQuestionController addQuestionController = loader.getController();
        addQuestionController.setMainScreenController(mainScreenController);
        setScene(pane);
    }

    @FXML
    public void options() {
    	try{
            Parent root = FXMLLoader.load(getClass().getResource("HelpWindow.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.setTitle("Quiz");
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void showResults(){

        try{
            Parent root = FXMLLoader.load(getClass().getResource("Results.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root,650,350));
            stage.setResizable(false);
            stage.setTitle("Quiz");
            stage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void logout() {
    	mainScreenController.loginScreen();
    }

    public void setScene(Pane pane) {
        window.getChildren().clear();
        window.getChildren().add(pane);
    }
}
