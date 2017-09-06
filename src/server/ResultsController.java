package server;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import server.ConnectionWithStudent.Results;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ResultsController implements Initializable {

    public Label n1, n2,n3,n4,n5,n6,n7,n8,n9,n10,n11,n12,r1,r2,r3,r4,r5,r6,r7,r8,r9,r10,r11,r12 = new Label();
    public Label howMany = new Label();
    Results results = Results.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<Label> labels = new ArrayList<>();

    }

    public void refresh() {
        try {
            Thread.sleep(250);
            if (results.getList().size()>0) {
                String studentNumber = Integer.toString(results.getHowManyStudents());
                howMany.setText(studentNumber);
                n1.setText(results.getList().get(0).getName());
                r1.setText(results.getList().get(0).getResult());
                n2.setText(results.getList().get(1).getName());
                r2.setText(results.getList().get(1).getResult());
                n3.setText(results.getList().get(2).getName());
                r3.setText(results.getList().get(2).getResult());
                n4.setText(results.getList().get(3).getName());
                r4.setText(results.getList().get(3).getResult());
                n5.setText(results.getList().get(4).getName());
                r5.setText(results.getList().get(4).getResult());
                n6.setText(results.getList().get(5).getName());
                r6.setText(results.getList().get(5).getResult());
                n7.setText(results.getList().get(6).getName());
                r7.setText(results.getList().get(6).getResult());
                n8.setText(results.getList().get(7).getName());
                r8.setText(results.getList().get(7).getResult());
                n9.setText(results.getList().get(8).getName());
                r9.setText(results.getList().get(8).getResult());
                n10.setText(results.getList().get(9).getName());
                r10.setText(results.getList().get(9).getResult());
                n11.setText(results.getList().get(10).getName());
                r11.setText(results.getList().get(10).getResult());
                n12.setText(results.getList().get(11).getName());
                r12.setText(results.getList().get(11).getResult());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
