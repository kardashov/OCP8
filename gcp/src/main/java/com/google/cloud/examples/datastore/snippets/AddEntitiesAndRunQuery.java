package com.google.cloud.examples.datastore.snippets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;
import com.google.cloud.datastore.Query;
import com.google.cloud.datastore.QueryResults;
import com.google.cloud.datastore.StructuredQuery.PropertyFilter;

/**
 * A snippet for Google Cloud Datastore showing how to create and get entities. The snippet also
 * shows how to run a query against Datastore.
 */
public class AddEntitiesAndRunQuery {

    public static void main(String... args) {
        // Create datastore service object.
        // By default, credentials are inferred from the runtime environment.
        Datastore datastore = DatastoreOptions.getDefaultInstance().getService();

        // Add an entity to Datastore
        KeyFactory keyFactory = datastore.newKeyFactory().setKind("Person");
        Key key = keyFactory.newKey("john.doe@gmail.com");
        Entity entity =
                Entity.newBuilder(key)
                        .set("name", "John Doe")
                        .set("age", 51)
                        .set("favorite_food", "pizza")
                        .build();
        datastore.put(entity);

        // Get an entity from Datastore
        Entity johnEntity = datastore.get(key);

        // Add a couple more entities to make the query results more interesting
        Key janeKey = keyFactory.newKey("jane.doe@gmail.com");
        Entity janeEntity =
                Entity.newBuilder(janeKey)
                        .set("name", "Jane Doe")
                        .set("age", 44)
                        .set("favorite_food", "pizza")
                        .build();
        Key joeKey = keyFactory.newKey("joe.shmoe@gmail.com");
        Entity joeEntity =
                Entity.newBuilder(joeKey)
                        .set("name", "Joe Shmoe")
                        .set("age", 27)
                        .set("favorite_food", "sushi")
                        .build();
        datastore.put(janeEntity, joeEntity);

        // Run a query
        Query<Entity> query =
                Query.newEntityQueryBuilder()
                        .setKind("Person")
                        .setFilter(PropertyFilter.eq("favorite_food", "pizza"))
                        .build();
        QueryResults<Entity> results = datastore.run(query);
        while (results.hasNext()) {
            Entity currentEntity = results.next();
            System.out.println(currentEntity.getString("name") + ", you're invited to a pizza party!");
        }
    }
}
