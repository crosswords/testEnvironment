package GUI;
import DataModel.TestLibrary;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
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
    @FXML private TableView<TestLibrary> resultsTable;
    @FXML private TextField heightParamField;
    @FXML private TextField widthParamField;
    @FXML private TextField spacesMetricField;
    @FXML private TextField wordsMetricField;
    @FXML private ProgressBar progressBar;

    @FXML private TableColumn<TestLibrary, String> nameColumn;
    @FXML private TableColumn<TestLibrary, String> versionColumn;
    @FXML private TableColumn<TestLibrary, String> directoryColumn;
    @FXML private TableColumn<TestLibrary, Integer> widthColumn;
    @FXML private TableColumn<TestLibrary, Integer> heightColumn;
    @FXML private TableColumn<TestLibrary, Integer> scoreColumn;
    @FXML private TableColumn<TestLibrary, String> statusColumn;
    @FXML private TableColumn<TestLibrary, String> resultNameColumn;
    @FXML private TableColumn<TestLibrary, String> resultVersionColumn;

    ObservableList<TestLibrary> librariesRows = FXCollections.observableArrayList();
    ObservableList<TestLibrary> runningLibraries = FXCollections.observableArrayList();
    File selectedLibrary;
    private CrosswordWindow crosswordWindow;

    @Override
    public void initialize(URL url, final ResourceBundle resourceBundle) {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        versionColumn.setCellValueFactory(cellData -> cellData.getValue().versionProperty());
        directoryColumn.setCellValueFactory(cellData -> cellData.getValue().directoryProperty());
        librariesList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        librariesList.setItems(librariesRows);

        statusColumn.setCellValueFactory(cellData -> cellData.getValue().statusProperty());
        resultNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        resultVersionColumn.setCellValueFactory(cellData -> cellData.getValue().versionProperty());
        heightColumn.setCellValueFactory(new PropertyValueFactory<TestLibrary, Integer>("height"));
        widthColumn.setCellValueFactory(new PropertyValueFactory<TestLibrary, Integer>("width"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<TestLibrary, Integer>("score"));
        resultsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        resultsTable.setItems(runningLibraries);

        showCrosswordButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int index = resultsTable.getSelectionModel().getSelectedIndex();
                String resourceFile = "";
                Stage childStage = new Stage();
                if (index >= 0) {
                    resourceFile = "CrosswordWindow.fxml";
                    childStage.setTitle("Generated crossword");
                }else{
                    resourceFile = "MessageBoxWindow.fxml";
                    childStage.setTitle("Warning");
                }
                Parent childRoot = null;
                try {
                    childRoot = FXMLLoader.load(getClass().getResource(resourceFile));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // TODO - podpięcie pod tabelę wygenerowanej krzyżówki
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
                ObservableList<TestLibrary> selectedLibraries = librariesList.getSelectionModel().getSelectedItems();
                for (TestLibrary library : selectedLibraries) {
                    librariesRows.remove(library);
                    librariesList.setItems(librariesRows);
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
                        TestLibrary testLibrary = new TestLibrary(librariesRows.get(index).getName(), version, librariesRows.get(index).getDirectory(), Integer.parseInt(widthParamField.getText()), Integer.parseInt(heightParamField.getText()));
                        runningLibraries.add(testLibrary);
                        double spacesMetric = spacesMetricField.getText().isEmpty() ? 0.0 : Double.parseDouble(spacesMetricField.getText());
                        double wordsMetric = wordsMetricField.getText().isEmpty() ? 0.0 : Double.parseDouble(wordsMetricField.getText());
                        // TODO - dodać wywołanie algorytmu z biblioteki
                    }
                } else{
                    Stage childStage = new Stage();
                    childStage.setTitle("Warning");
                    Parent childRoot = null;
                    try {
                        childRoot = FXMLLoader.load(getClass().getResource("MessageBoxWindow.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    childStage.setScene(new Scene(childRoot));
                    childStage.showAndWait();
                }
            }
        });

        stopButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                TestLibrary library = (TestLibrary) resultsTable.getSelectionModel().getSelectedItem();
                if (library != null){
                    library.setStatus("Stopped");
                    // TODO - Logika - zatrzymanie wykonywania algorytmu
                } else{
                    Stage childStage = new Stage();
                    childStage.setTitle("Warning");
                    Parent childRoot = null;
                    try {
                        childRoot = FXMLLoader.load(getClass().getResource("MessageBoxWindow.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    childStage.setScene(new Scene(childRoot));
                    childStage.showAndWait();
                }
            }
        });
    }
}