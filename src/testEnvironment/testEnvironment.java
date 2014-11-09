package testEnvironment;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

/**
 * Created by Tomek on 2014-09-28.
 */
public class testEnvironment {
    public testEnvironment(/*int width,int height, args...*/){

    }
    public void testLibraries(String[] paths) {
        for (String name : paths) {
             testLibrary(name);
        }
    }
    public void testLibrary(String path){
        final String className = "SampleLibrary";
        final String methodName = "write";
        libraryLoader library = new libraryLoader(path);
        library.loadLibrary(className);
        library.invokeMethod(methodName);
        library.invokeMethod(methodName);
        //async -> loader.library.generateCrossword(....);
        //
    }

}
