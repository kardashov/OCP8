package stas.inheritance;

//lesson  136
class Baap {
    public int h = 4;
    public int getH() {
        System.out.println("Baap " + h);
        return h;
    }
}

public class GoodExample extends Baap {
    public int h = 44;
    public int getH() {
        System.out.println("GoodExample " + h);
        return h;
    }
    public static void main(String[] args) {
        Baap b = new GoodExample();
        System.out.println(b.h + " " + b.getH());
        GoodExample bb = (GoodExample) b;
        System.out.println(bb.h + " " + bb.getH());
    }
}