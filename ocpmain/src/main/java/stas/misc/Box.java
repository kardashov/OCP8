package stas.misc;

/**
 * Generic version of the Box class.
 *
 * @param <T> the type of the value being boxed
 */
public class Box<T> {
    // T stands for "Type"
    private T t;

    public void set(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }
}


/**
 * Generic version of the Box class.
 *
 * @param <T> the type of the value being boxed
 */
class Box1<Ts> {

    void main() {

        System.getSecurityManager();
    }

    // T stands for "Type"
    private Ts t;

    public void set(Ts t) {
        this.t = t;


    }

    public Ts get() {
        return t;
    }
}
 
 