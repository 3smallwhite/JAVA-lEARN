import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Integer[] a = new Integer[3];
        a[0] = 1; a[1] = 3 ;a[2] = 2;
        Sorts sorts = new Sorts();
        sorts.<Integer>sorts(a);
    }
}