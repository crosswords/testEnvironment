package testEnvironment;

import java.io.File;

/**
 * Created by Tomek on 2014-09-28.
 */
public class libraryLoader {
    public libraryLoader() {

    }
    public void loadMultipleLibrary(String directory){
        File file = new File(directory);
        String[] names = file.list();

        for(String name : names)
        {
            if (! new File(directory + "\\" + name).isDirectory())
            {
                loadLibrary(name);
            }
        }
    }
    public void loadLibrary(String libName){
        // TO DO
    }
}
