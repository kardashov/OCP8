package com.google.cloud.examples.storage.snippets;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

/**
 * A snippet for Google Cloud Storage showing how to create a bucket and a blob in it. The snippet
 * also shows how to get a blob's content, list buckets and list blobs.
 */
public class CreateAndListBucketsAndBlobs {

    public static void main(String... args) {
        // Create a service object
        // Credentials are inferred from the environment.
        Storage storage = StorageOptions.getDefaultInstance().getService();

        // Create a bucket
        String bucketName = "my_unique_bucket"; // Change this to something unique
        Bucket bucket = storage.create(BucketInfo.of(bucketName));

        // Upload a blob to the newly created bucket
        Blob blob = bucket.create("my_blob_name", "a simple blob".getBytes(UTF_8), "text/plain");

        // Read the blob content from the server
        String blobContent = new String(blob.getContent(), UTF_8);

        // List all your buckets
        System.out.println("My buckets:");
        for (Bucket currentBucket : storage.list().iterateAll()) {
            System.out.println(currentBucket);
        }

        // List the blobs in a particular bucket
        System.out.println("My blobs:");
        for (Blob currentBlob : bucket.list().iterateAll()) {
            System.out.println(currentBlob);
        }
    }
}
