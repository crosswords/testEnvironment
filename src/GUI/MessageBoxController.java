package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Filipolo on 2014-11-24.
 */
public class MessageBoxController implements Initializable{
    @FXML private Label messageLabel;
    @FXML private Button okButton;

    public void initLabel(String message){
        this.messageLabel.setText(message);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        okButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage)okButton.getScene().getWindow();
                stage.close();
            }
        });
    }
}
