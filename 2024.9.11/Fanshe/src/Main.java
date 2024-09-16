import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Class<?> cls = Class.forName("User");
        Object obj = cls.newInstance();

        Method method = cls.getMethod("setName", String.class);
        method.invoke(obj, "张三");
        cls.getMethod("show").invoke(obj);
    }
}