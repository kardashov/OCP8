package com.google.cloud.examples.storage.snippets;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

/**
 * A snippet for Google Cloud Storage showing how to update the blob's content if the blob exists.
 */
public class UpdateBlob {

    public static void main(String... args) throws IOException {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        BlobId blobId = BlobId.of("bucket", "blob_name");
        Blob blob = storage.get(blobId);
        if (blob != null) {
            byte[] prevContent = blob.getContent();
            System.out.println(new String(prevContent, UTF_8));
            WritableByteChannel channel = blob.writer();
            channel.write(ByteBuffer.wrap("Updated content".getBytes(UTF_8)));
            channel.close();
        }
    }
}
