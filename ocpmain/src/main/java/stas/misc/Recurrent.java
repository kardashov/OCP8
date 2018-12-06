package stas.misc;

public class Recurrent {


    public Recurrent() {

        try {
            this.m = new Recurrent();

        } catch (Throwable e) {

            int i = 0;
            for (StackTraceElement stack_element : e.getStackTrace()) {
                System.out.println("Row " + i + ":  " + stack_element);
                i++;
            }
        }
    }

    private int[] f = new int[20000];

    private Recurrent m;

    static int count = 0;

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Recurrent f = new Recurrent();
    }

}
