package com.google.cloud.examples.nio.snippets;

import com.google.cloud.storage.contrib.nio.CloudStorageFileSystem;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A snippet for Google Cloud Storage NIO that shows how to create a {@link CloudStorageFileSystem}
 * for a bucket. The snippet also shows how to create a file, given the file system.
 */
public class CreateCloudStorageFileSystem {

    public static void main(String... args) throws IOException {
        // Create a file system for the bucket
        CloudStorageFileSystem fs = CloudStorageFileSystem.forBucket("bucket");
        byte[] data = "hello world".getBytes(StandardCharsets.UTF_8);
        Path path = fs.getPath("/object");
        // Write a file in the bucket
        Files.write(path, data);
        // Read a file from the bucket
        data = Files.readAllBytes(path);
    }
}
