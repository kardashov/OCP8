package stas.io.serialization;

import java.io.*;

class ExternalizablePair implements Externalizable {
    private String key;
    private String value;
    private int myInt = 33;
    private Animal animal = new Animal("Tiger", 23, 'c');

    public ExternalizablePair(int s) {
        this.key = null;
        this.value = null;
    }

    public ExternalizablePair(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "key=" + this.key + " value=" + this.value + " myInt " + this.myInt +
                " animal=" + this.animal.toString();
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeUTF(key);
        out.writeUTF(value);
        out.writeInt(myInt);
        out.writeObject(animal);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

        this.value = in.readUTF(); //key and value read order is REVERSED!!!
        this.key = in.readUTF();
        this.myInt = in.readInt();
        this.animal = (Animal) in.readObject();

    }

}

public class ExternalizableExample {
    private final static String OUTPUT_FILE = "externalizable_file";

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        ExternalizablePair pair = new ExternalizablePair("Hello", "World");
        System.out.println("Before: " + pair.toString());

        // Serialize the pair to a file.
        FileOutputStream outputStream = new FileOutputStream(OUTPUT_FILE);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        pair.writeExternal(objectOutputStream);

        // Close all resources.
        objectOutputStream.flush();
        outputStream.close();

        // Read the contents from the file and create a new instance.
        ExternalizablePair copyOfPair = new ExternalizablePair(2);

        FileInputStream inputStream = new FileInputStream(OUTPUT_FILE);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        copyOfPair.readExternal(objectInputStream);

        // Close all resources.
        objectInputStream.close();
        inputStream.close();
        System.out.println("After : " + copyOfPair.toString());
    }
}