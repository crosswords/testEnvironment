package testEnvironment;

import DataModel.TestLibrary;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

/**
 * Created by Tomek on 2014-09-28.
 */
public class testEnvironment {
    private final String className = "Algorithm";
    private final String methodName = "generate";
    private TestLibrary testLibrary;
    public testEnvironment(TestLibrary aTestLibrary){
        testLibrary = aTestLibrary;
    }

    public void testLibrary(){

        new Thread(new Runnable() {
            public void run() {
                libraryLoader library = new libraryLoader(testLibrary.getDirectory());
                library.loadLibrary(className);
                String[] args = new String[]{Integer.toString(testLibrary.getWidth()),Integer.toString(testLibrary.getHeight())};
                Object returnValue = library.invokeMethod(methodName,args);
                testLibrary.setWords((char[][])returnValue);

                if(testLibrary.getWords()==null)
                    testLibrary.setStatus("Failed");
                else
                    testLibrary.setStatus("Finished");
                testLibrary.setScore(String.format("%.2f", countMetricEmptySpaces())+"% "+Integer.toString(countMetricWords()));
            }
        }).start();

        //async -> loader.library.generateCrossword(....);
        //
    }

    public int countMetricWords(){
        char[][]matrix= testLibrary.getWords();
        int wordsNumber=0;
        boolean prevSign=true;
        boolean prevprevSign=true;
        boolean currSign=true;
        for(int i=0;i<testLibrary.getWidth();++i) {
            prevprevSign = isLetter(matrix[i][0]);
            prevSign = isLetter(matrix[i][1]);
            if(prevSign==true&&prevprevSign==true)++wordsNumber;
            for (int j = 2; j < testLibrary.getHeight();++j) {
                currSign=isLetter(matrix[i][j]);
                if (currSign==true&&prevSign==true&&prevprevSign==false){
                    ++wordsNumber;
                }
                prevprevSign = prevSign;
                prevSign = currSign;
            }
        }
        for(int i=0;i<testLibrary.getHeight();++i) {
            prevprevSign = isLetter(matrix[0][i]);
            prevSign = isLetter(matrix[1][i]);
            if(prevSign==true&&prevprevSign==true)++wordsNumber;
            for (int j = 2; j < testLibrary.getWidth();++j) {
                currSign=isLetter(matrix[j][i]);
                if (currSign==true&&prevSign==true&&prevprevSign==false){
                    ++wordsNumber;
                }
                prevprevSign = prevSign;
                prevSign = currSign;
            }
        }
        return wordsNumber;
    }
    private boolean isLetter(char a){
        return !(a==' '||a=='\u0000');
    }

    public double countMetricEmptySpaces(){
        char[][]matrix= testLibrary.getWords();
        double percent=0.0;
        for(int i=0;i<testLibrary.getWidth();++i)
            for(int j=0;j<testLibrary.getHeight();++j){
                if(matrix[i][j]==' '||matrix[i][j]=='\u0000')
                    ++percent;
            }
        return (percent/(testLibrary.getWidth()*testLibrary.getHeight()));
    }

}
