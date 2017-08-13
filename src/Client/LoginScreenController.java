package Client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginScreenController {

    public Button login;
    public TextField username;
    public TextField ipAddress;

    public void changeScene(){
        try{
            Stage stage2 = (Stage)login.getScene().getWindow();
            stage2.close();

            Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root,340,500));
            stage.setTitle("Quiz");
            stage.show();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void login(){
        if(!username.getText().equals("")&&!ipAddress.getText().equals("")){
            changeScene();
            ClientState.getInstance().setIpAddress(ipAddress.getText());
            ClientState.getInstance().setUsername(username.getText());

        }
    }

}
