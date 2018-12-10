package com.google.cloud.examples.nio.snippets;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A snippet showing how to create an input stream for a Google Cloud Storage file using NIO.
 */
public class CreateInputStream {

    public static void main(String... args) throws IOException {
        Path path = Paths.get(URI.create("gs://bucket/lolcat.csv"));
        try (InputStream input = Files.newInputStream(path)) {
            // use input stream
        }
    }
}
