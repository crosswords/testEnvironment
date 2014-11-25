package testEnvironment;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by Tomek on 2014-09-28.
 */
public class libraryLoader {
    //public/private library ...
    private URLClassLoader jarUrl;
    private Class classToLoad;

    public libraryLoader(String libPath) {
        URL url = null;
        classToLoad = null;
        try {
            url = new URL("file:"+libPath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URL urlTab[] = {url};
        jarUrl = new URLClassLoader (urlTab, this.getClass().getClassLoader());
    }

    public void loadLibrary(String className) {

        try {
            classToLoad = jarUrl.loadClass (className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Object invokeMethod(String methodName,String[] args) {
        Method method = null;
        Object result = null;
        try {
            Object instance = classToLoad.newInstance();
            method = classToLoad.getMethod(methodName, new Class[] { args.getClass() });
            result = method.invoke (instance, new Object[] { args });
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return result;
    }
}
