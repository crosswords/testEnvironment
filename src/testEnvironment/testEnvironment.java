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
        libraryLoader library = new libraryLoader(testLibrary.getFilePath());
        library.loadLibrary(className);
        String[] args = new String[]{Integer.toString(testLibrary.getWidth()),Integer.toString(testLibrary.getHeight())};
        Object returnValue = library.invokeMethod(methodName,args);
        testLibrary.setWords((char[][])returnValue);
        //async -> loader.library.generateCrossword(....);
        //
    }

}
