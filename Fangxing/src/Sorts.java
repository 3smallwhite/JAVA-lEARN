
import java.util.Arrays;

public class Sorts {

    public <T> void sorts(T[] t) {
        for (T t1: t) System.out.println(t1);
        Arrays.sort(t);
        for (T t1: t) System.out.println(t1);
    }
}
