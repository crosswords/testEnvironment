package GUI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javafx.scene.control.TableView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Filipolo on 2014-11-15.
 */
public class Controller implements Initializable {

    @FXML private AnchorPane mainPane;
    @FXML private Button showCrosswordButton;
    @FXML public Button chooseLibraryButton;
    @FXML private Button libraryAddButton;
    @FXML private Button libraryDeleteButton;
    @FXML private Button runButton;
    @FXML private Button stopButton;
    @FXML private TableView librariesList;
    @FXML private TextField libraryTextField;
    @FXML private TableView resultsTable;
    @FXML private TextField heightParamField;
    @FXML private TextField widthParamField;
    @FXML private TextField spacesMetricField;
    @FXML private TextField wordsMetricField;

    private CrosswordWindow crosswordWindow;

    //FXCollections libraries;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showCrosswordButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Parent childRoot = null;
                try {
                    childRoot = FXMLLoader.load(getClass().getResource("CrosswordWindow.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage childStage = new Stage();
                childStage.setTitle("Generated crossword");
                childStage.setScene(new Scene(childRoot));
                childStage.showAndWait();
            }
        });

        chooseLibraryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fc = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Libraries (*.jar)", "*.jar");
                fc.getExtensionFilters().add(extFilter);

                File file = fc.showOpenDialog(chooseLibraryButton.getScene().getWindow());
                if (file != null) {
                    //openFile(file);
                    libraryTextField.setText(file.getName());
                    //librariesList.getItems().add();
                }
            }
        });
    }
}
