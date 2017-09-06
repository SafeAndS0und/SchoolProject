package Client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 415, 264));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            System.out.println("exiting");
            Platform.exit();
            System.exit(0);
        });
    }


    public static void main(String[] args)  {
        launch(args);

    }

}
