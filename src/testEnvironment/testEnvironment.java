package testEnvironment;

import java.io.File;

/**
 * Created by Tomek on 2014-09-28.
 */
public class testEnvironment {
    public testEnvironment(/*int width,int height, args...*/){

    }
    public void testMultipleLibraries(String directory) {
        File file = new File(directory);
        String[] names = file.list();

        for (String name : names) {
            if (!new File(directory + "\\" + name).isDirectory()) {
                testLibrary(name);
            }
        }
    }
    public void testLibrary(String name){
        libraryLoader loader = new libraryLoader();

        //async -> loader.library.generateCrossword(....);
        //
    }

}
