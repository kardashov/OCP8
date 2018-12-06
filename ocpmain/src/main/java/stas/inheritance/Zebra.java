package stas.inheritance;

public class Zebra extends MainClassAnimal {
    public Zebra(int age) {
        super(age); //call to constructor of the superclass
    }

    public Zebra() {
        this(4); //call to another constructor in the same class
    }
}