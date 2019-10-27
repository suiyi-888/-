
public class ClassLoaderTest {
 
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ClassLoader myLoader = new MyClassLoader();
        Object obj = myLoader.loadClass("java.lang.Math").newInstance();
        System.out.println(obj);
    }
    
}
