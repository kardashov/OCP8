package stas.methods.polymorphism;

class Reptile {
    public String getName() {
        return "Reptile";
    }
}

class Alligator extends Reptile {
    public String getName() {
        return "Alligator";
    }
}

class Crocodile extends Reptile {
    public String getName() {
        return "Crocodile";
    }
}

public class PolymorphParameters {
    public static void feed(Reptile reptile) {
        System.out.println("Feeding reptile " + reptile.getName());
    }

    public static void main(String[] args) {
        feed(new Alligator());//  prints	Feeding reptile Alligator
        feed(new Crocodile());//  prints	Feeding reptile Crocodile
        feed(new Reptile());  //  prints	Feeding reptile Reptile
    }
}
