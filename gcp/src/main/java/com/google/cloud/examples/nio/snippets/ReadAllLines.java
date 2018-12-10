package com.google.cloud.examples.nio.snippets;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * A snippet showing how to read all lines of a Google Cloud Storage file using NIO.
 */
public class ReadAllLines {

    public static void main(String... args) throws IOException {
        Path path = Paths.get(URI.create("gs://bucket/lolcat.csv"));
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
    }
}
