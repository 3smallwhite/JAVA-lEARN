package entity;

public class Statu implements java.io.Serializable {
    private static final long serialVersionUID = 3L;
    String statu;

    public String getStatu() {
        return statu;
    }

    public void setStatu(String statu) {
        this.statu = statu;
    }

    public Statu(String statu) {
        this.statu = statu;
    }
}
