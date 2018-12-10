package com.google.cloud.examples.datastore.snippets;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;

/**
 * A snippet for Google Cloud Datastore showing how to create an entity.
 */
public class CreateEntity {

    public static void main(String... args) {
        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        KeyFactory keyFactory = datastore.newKeyFactory().setKind("keyKind");
        Key key = keyFactory.newKey("keyName");
        Entity entity =
                Entity.newBuilder(key)
                        .set("name", "John Doe")
                        .set("age", 30)
                        .set("access_time", Timestamp.now())
                        .build();
        datastore.put(entity);
    }
}
