package stas.exceptions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TryWithResources {
    public static void main(String[] args) throws Exception {

        Throwable t1 = new Exception(); // � ������ ����� Exception
//		throw t1; // �� ��� ������ ���������� Unhandled exception type Throwable


        try (JammedTurkeyCage t = new JammedTurkeyCage()) {
            throw new IllegalStateException("turkeys ran off");
        } catch (IllegalStateException e) {
            System.out.println("caught: " + e.getMessage());
            for (Throwable t : e.getSuppressed())
                System.out.println(t.getMessage());
				/*prints: caught: turkeys ran off
						  Cage door does not close*/
        }

        Path path1 = null, path2 = null;

        try (BufferedReader r = Files.newBufferedReader(path1);
             BufferedWriter w = Files.newBufferedWriter(path2)) {
            System.out.println("My protected code");

        } catch (IOException e) {
            // exeption handler
//			r.readLine(); // r is out of scope
        } finally {
            // finally block
//			w.write("ssd");// w is out of scope
        } // <-- Resources are closed at this point


    }
}


class StuckTurkeyCage implements AutoCloseable {
    public void close() throws Exception {
        throw new Exception("Cage door does not close");
    }

    public static void main(String[] args) throws Exception {
        try (StuckTurkeyCage t = new StuckTurkeyCage()) {//DOES NOT COMPILE. Exception must be caught or declared
            //Unhandled exception type Exception thrown by automatic close() invocation on t
            System.out.println("put turkeys in");
        }
    }
}


class JammedTurkeyCage implements AutoCloseable {
    public void close() throws IllegalStateException {
        throw new IllegalStateException("Cage door does not close");
    }

    public static void main(String[] args) {
        try (JammedTurkeyCage t = new JammedTurkeyCage()) {
            System.out.println("put turkeys in");
        } catch (IllegalStateException e) {
            System.out.println("caught: " + e.getMessage());
        }
    }
}


