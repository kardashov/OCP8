package stas.inheritance;

public class Lion extends MainClassAnimal {
    private void roar() {
        System.out.println("The " + getAge() + " year old lion says: Roar!");
        System.out.println("The " + age + " year old lion says: Roar!"); //DOES not compile
    }
}


//Despite the fact that age is inaccessible by the child class, if we have an instance of a
//Lion object, there is still an age value that exists within the instance. The age value just
//cannot be directly referenced by the child class nor any instance of the class. In this manner,
//the Lion object is actually �bigger� than the Animal object in the sense that it includes
//all the properties of the Animal object (although not all of those properties may be directly
//accessible) along with its own set of Lion attributes.