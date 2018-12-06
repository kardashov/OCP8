package stas.inner.classes;

public class AnonymousInner {
    interface SaleTodayOnly {
        int dollarsOff();
    }

    int i = 5;

    public int admission(int basePrice) {
        SaleTodayOnly sale = new SaleTodayOnly() { //====anonymous class declaration start======
            int k = 1;
            final static int t = 2;// only FINAL static fields can be declared

            public int dollarsOff() {
                return 3 * i * this.k; //can refer to outer class properties
            }
        };                                           //====anonymous class declaration end======
        return basePrice - sale.dollarsOff();
    }
}
