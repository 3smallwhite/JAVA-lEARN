import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args){
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("bug.txt"));
            Test test = new Test("tests");
            Class clazz = test.getClass();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method1: methods) {
                int cnt = method1.getAnnotation(MyAnnotation.class).cnt();
                for (int i = 0; i < cnt; ++i) {
                    try {
                        method1.invoke(test);
                    } catch (Exception e) {
                        bw.write("出异常了");
                        bw.flush();
                    }
                }
            }
            bw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}