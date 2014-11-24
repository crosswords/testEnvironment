package DataModel;

public class TestLibrary {
    private String name;
    private String filePath;
    private String version;
    private int score;
    private int width;
    private int height;
    private Status status;
    private char[][] words;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public char[][] getWords() {
        return words;
    }

    public void setWords(char[][] words) {
        this.words = words;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public TestLibrary(){
    }

    public TestLibrary(String nname, String ffilePath, String vversion){
        this.setName(nname);
        this.setFilePath(ffilePath);
        this.setVersion(vversion);
    }

    public TestLibrary(String nname, String ffilePath, String vversion, int wwidth, int hheight){
        this.setName(nname);
        this.setFilePath(ffilePath);
        this.setVersion(vversion);
        this.setWidth(wwidth);
        this.setHeight(hheight);
        this.setScore(0);
        this.setStatus(Status.Not_started);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return (this.getName() + ", " + this.getVersion() + ", " + this.getFilePath());
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
