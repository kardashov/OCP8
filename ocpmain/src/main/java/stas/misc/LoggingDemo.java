package stas.misc;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.*;

public class LoggingDemo {

    public static void main(String[] args) throws SecurityException,
            IOException {
        // Logger setup starts here.
        Logger myLogger = Logger.getLogger("com.stas.read");
        myLogger.setLevel(Level.FINEST);
        myLogger.setUseParentHandlers(false);
        Handler handler = new ConsoleHandler();
        handler.setLevel(Level.FINEST);

        myLogger.addHandler(handler);

        Handler handler1 = new FileHandler();
        myLogger.addHandler(handler1);

        WindowHandler windowHandler = new WindowHandler();
        myLogger.addHandler(windowHandler);
        // Logger setup completed.

        myLogger.entering("com.stas.read", "main", new Object[]{"asdfd",
                "sdfsdfe", 345, 234, 34});

        myLogger.info("File->Open menu item selected");

        myLogger.exiting("com.stas.read", "main", 45);
    }
}

class WindowHandler extends StreamHandler {
    public WindowHandler() {
        // . . .
        final JTextArea output = new JTextArea();
        setOutputStream(new OutputStream() {
            public void write(int b) {
            } // not called

            public void write(byte[] b, int off, int len) {
                output.append(new String(b, off, len));
            }
        });
    }

    public void publish(LogRecord record) {
        super.publish(record);
        flush();
    }
    // . . .
}
