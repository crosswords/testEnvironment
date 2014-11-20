package GUI;
import DataModel.Status;
import DataModel.TestLibrary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Filipolo on 2014-11-15.
 */
public class MainController implements Initializable {

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
    @FXML private ProgressBar progressBar;

    ObservableList<TestLibrary> librariesRows = FXCollections.observableArrayList();
    ObservableList<TestLibrary> runningLibraries = FXCollections.observableArrayList();
    File selectedLibrary;
    private CrosswordWindow crosswordWindow;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<TableColumn> librariesColumns = librariesList.getColumns();
        librariesColumns.get(0).setCellValueFactory(new PropertyValueFactory<TestLibrary, String>("name"));
        librariesColumns.get(2).setCellValueFactory(new PropertyValueFactory<TestLibrary, String>("filePath"));
        librariesColumns.get(1).setCellValueFactory(new PropertyValueFactory<TestLibrary, String>("version"));
        librariesList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        librariesList.setItems(librariesRows);

        ObservableList<TableColumn> resultsColumns = resultsTable.getColumns();
        resultsColumns.get(0).setCellValueFactory(new PropertyValueFactory<TestLibrary, String>("name"));
        resultsColumns.get(2).setCellValueFactory(new PropertyValueFactory<TestLibrary, String>("height"));
        resultsColumns.get(1).setCellValueFactory(new PropertyValueFactory<TestLibrary, String>("version"));
        resultsColumns.get(3).setCellValueFactory(new PropertyValueFactory<TestLibrary, String>("width"));
        resultsColumns.get(4).setCellValueFactory(new PropertyValueFactory<TestLibrary, String>("status"));
        resultsColumns.get(5).setCellValueFactory(new PropertyValueFactory<TestLibrary, String>("score"));
        resultsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        resultsTable.setItems(runningLibraries);

        showCrosswordButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int index = resultsTable.getSelectionModel().getSelectedIndex();
                if (index >= 0) {
                    Parent childRoot = null;
                    try {
                        childRoot = FXMLLoader.load(getClass().getResource("CrosswordWindow.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // TODO - podpięcie pod tabelę wygenerowanej krzyżówki
                    Stage childStage = new Stage();
                    childStage.setTitle("Generated crossword");
                    childStage.setScene(new Scene(childRoot));
                    childStage.showAndWait();
                }
            }
        });

        chooseLibraryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fc = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Libraries (*.jar)", "*.jar");
                fc.getExtensionFilters().add(extFilter);
                fc.setInitialDirectory(new File(System.getProperty("user.dir")));
                selectedLibrary = fc.showOpenDialog(chooseLibraryButton.getScene().getWindow());
                if (selectedLibrary != null) {
                    libraryTextField.setText(selectedLibrary.getName());
                }
            }
        });

        libraryAddButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (libraryTextField.getText() != null && libraryTextField.getText() != "") {
                    String version = "1.0"; //file.getName().split("-").length > 0 ? file.getName().split("-")[1] : "";
                    TestLibrary testLibrary = new TestLibrary(selectedLibrary.getName(), version, selectedLibrary.getPath());
                    librariesRows.add(testLibrary);
                }
            }
        });

        libraryDeleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ObservableList<Integer> indexes = librariesList.getSelectionModel().getSelectedIndices();
                for (Integer index : indexes) {
                    librariesRows.remove(index);
                }
            }
        });

        runButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (! (heightParamField.getText().isEmpty() || widthParamField.getText().isEmpty() || librariesList.getSelectionModel().getSelectedIndices().isEmpty())){
                    ObservableList<Integer> indexes = librariesList.getSelectionModel().getSelectedIndices();
                    for (Integer index : indexes) {
                        String version = "1.0";
                        TestLibrary testLibrary = new TestLibrary(librariesRows.get(index).getName(), version, librariesRows.get(index).getFilePath(), Integer.parseInt(widthParamField.getText()), Integer.parseInt(heightParamField.getText()));
                        runningLibraries.add(testLibrary);
                        double spacesMetric = Double.parseDouble(spacesMetricField.getText());
                        double wordsMetric = Double.parseDouble(wordsMetricField.getText());
                        // TODO - dodać wywołanie algorytmu z biblioteki
                    }
                }
            }
        });

        stopButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int index = resultsTable.getSelectionModel().getSelectedIndex();
                if (index >= 0){
                    runningLibraries.get(index).setStatus(Status.Stopped);
                    // TODO - Logika - zatrzymanie wykonywania algorytmu
                    resultsTable.setItems(runningLibraries);
                }
            }
        });
    }
}