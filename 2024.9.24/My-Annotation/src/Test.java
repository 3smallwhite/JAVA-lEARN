


public class Test {
    private String name;

    public Test() {
    }

    public Test(String name) {
        this.name = name;
    }

    @MyAnnotation(value = "print", cnt = 2)
    public void print() {
        int a = 1 / 0;
    }

    @MyAnnotation(value = "sout", cnt = 1)
    public void sout() {
        System.out.println("name:" + name);
    }
}
