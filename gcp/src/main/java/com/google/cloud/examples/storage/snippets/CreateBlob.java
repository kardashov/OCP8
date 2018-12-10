package com.google.cloud.examples.storage.snippets;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

/**
 * A snippet for Google Cloud Storage showing how to create a blob.
 */
public class CreateBlob {

    public static void main(String... args) {
        // [START storage_upload_file]
        Storage storage = StorageOptions.getDefaultInstance().getService();
        BlobId blobId = BlobId.of("bucket", "blob_name");
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("text/plain").build();
        Blob blob = storage.create(blobInfo, "Hello, Cloud Storage!".getBytes(UTF_8));
        // [END storage_upload_file]
    }
}
