package stas.misc;

public class PrimeNumbers {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        int count = 0;
        num:
        for (int num = 2; num <= Short.MAX_VALUE * 512; num++) {
            int n = (int) Math.sqrt(num) + 1;
            while (--n != 1) {
                if (num % n == 0) {
                    continue num;
                }
            }
            count++;
        }

        System.out.print(count + " ");
    }

}
