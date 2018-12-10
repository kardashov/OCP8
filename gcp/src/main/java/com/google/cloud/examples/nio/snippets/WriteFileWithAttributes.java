package com.google.cloud.examples.nio.snippets;

import static com.google.cloud.storage.contrib.nio.CloudStorageOptions.withMimeType;
import static com.google.cloud.storage.contrib.nio.CloudStorageOptions.withoutCaching;

import com.google.cloud.storage.contrib.nio.CloudStorageOptions;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * A snippet showing how to write a file to Google Cloud Storage using NIO. This example also shows
 * how to set file attributes, using {@link CloudStorageOptions} static helpers.
 */
public class WriteFileWithAttributes {

    private static final String[] LINES = {"value1,", "value"};

    public static void main(String... args) throws IOException {
        List<String> csvLines = Arrays.asList(LINES);
        Path path = Paths.get(URI.create("gs://bucket/lolcat.csv"));
        Files.write(
                path,
                csvLines,
                StandardCharsets.UTF_8,
                withMimeType("text/csv; charset=UTF-8"),
                withoutCaching());
    }
}
