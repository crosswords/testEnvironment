package DataModel;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TestLibrary {
    private StringProperty name;
    private StringProperty version;
    private IntegerProperty score;
    private IntegerProperty width;
    private IntegerProperty height;
    private StringProperty status;
    private StringProperty directory;
    private char[][] words;

    public TestLibrary(String nname, String vversion, String ffilePath){
        this.name = new SimpleStringProperty(nname);
        this.version = new SimpleStringProperty(vversion);
        this.score = new SimpleIntegerProperty(0);
        this.width = new SimpleIntegerProperty(0);
        this.height = new SimpleIntegerProperty(0);
        this.status = new SimpleStringProperty("Not_started");
        this.directory = new SimpleStringProperty(ffilePath);
    }

    public TestLibrary(String nname, String ffilePath, String vversion, int wwidth, int hheight){
        this.name = new SimpleStringProperty(nname);
        this.directory = new SimpleStringProperty(ffilePath);
        this.version = new SimpleStringProperty(vversion);
        this.score = new SimpleIntegerProperty(0);
        this.width = new SimpleIntegerProperty(wwidth);
        this.height = new SimpleIntegerProperty(hheight);
        this.status = new SimpleStringProperty("Not_started");
    }

    @Override
    public String toString() {
        return (this.getName() + ", " + this.getVersion() + ", " + this.getDirectory());
    }

    public char[][] getWords() {
        return words;
    }

    public String getStatus() {
        return status.get();
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    public StringProperty statusProperty(){
        return status;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty nameProperty(){
        return this.name;
    }

    public String getVersion() {
        return version.get();
    }

    public void setVersion(String version) {
        this.version.set(version);
    }

    public StringProperty versionProperty() {
        return version;
    }

    public Integer getScore() {
        return score.get();
    }

    public void setScore(Integer score) {
        this.score.set(score);
    }

    public IntegerProperty scoreProperty(){
        return score;
    }

    public Integer getWidth() {
        return width.get();
    }

    public void setWidth(Integer width) {
        this.width.set(width);
    }

    public IntegerProperty widthProperty(){
        return width;
    }

    public Integer getHeight() {
        return height.get();
    }

    public void setHeight(Integer height) {
        this.height.set(height);
    }

    public IntegerProperty heightProperty(){
        return height;
    }

    public String getDirectory() {
        return directory.get();
    }

    public void setDirectory(String directory) {
        this.directory.set(directory);
    }

    public StringProperty directoryProperty() {
        return directory;
    }

    public void setWords(char[][] words) {
        this.words = words;
    }
}
