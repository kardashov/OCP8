package com.google.cloud.examples.datastore.snippets;

import com.google.cloud.Timestamp;
import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;

/**
 * A snippet for Google Cloud Datastore showing how to get an entity and update it if it exists.
 */
public class UpdateEntity {

    public static void main(String... args) {
        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
        KeyFactory keyFactory = datastore.newKeyFactory().setKind("keyKind");
        Key key = keyFactory.newKey("keyName");
        Entity entity = datastore.get(key);
        if (entity != null) {
            System.out.println("Updating access_time for " + entity.getString("name"));
            entity = Entity.newBuilder(entity).set("access_time", Timestamp.now()).build();
            datastore.update(entity);
        }
    }
}
