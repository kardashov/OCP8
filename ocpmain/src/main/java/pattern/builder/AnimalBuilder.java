package pattern.builder;

import pattern.immutable.Animal;

import java.util.Arrays;
import java.util.List;

public class AnimalBuilder {
    private String species;
    private int age;
    private List<String> favoriteFoods;

    public AnimalBuilder setAge(int age) {
        this.age = age;
        return this;
    }

    public AnimalBuilder setSpecies(String species) {
        this.species = species;
        return this;
    }

    public AnimalBuilder setFavoriteFoods(List<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
        return this;
    }

    public Animal build() {
        return new Animal(species, age, favoriteFoods);
    }

    public static void main(String[] args) {

        //example usage of Builder pattern
        AnimalBuilder duckBuilder = new AnimalBuilder();
        duckBuilder.setAge(4)
                .setFavoriteFoods(Arrays.asList("grass", "fish"))
                .setSpecies("duck");
        Animal duck = duckBuilder.build();
        Animal flamingo = new AnimalBuilder()
                .setFavoriteFoods(Arrays.asList("algae", "insects"))
                .setSpecies("flamingo")
                .build();
    }
}
/*builder class is often packaged alongside its target class, either as a static
inner class within the target class or within the same Java package. One advantage of
packing them together is that if one is changed, then the other can be quickly updated.
Another advantage is that writers of the target class can then choose to make the
constructor a private or default package, forcing the user to rely on the builder object
to obtain instances of the target class. For example, if the Animal class did not have a
public constructor, programs calling it from other packages would be required to use the
AnimalBuilder class to create instances of Animal.
*/
















