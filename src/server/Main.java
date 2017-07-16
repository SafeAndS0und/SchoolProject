package server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import server.ConnectionWithStudent.*;
import server.ConnectionWithStudent.Database;

import javax.xml.crypto.Data;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("MainScreen.fxml"));
        Pane pane = loader.load();
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        //       launch(args);

        server.ConnectionWithStudent.Database db = new Database();
        db.connect();
        db.transformQuestions(2);
        Questions q = new Questions();
        String toString = db.questionsList.get(0).toString();
        System.out.println(toString);
    }
}
