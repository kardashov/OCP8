package com.google.cloud.examples.datastore.snippets;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.ProjectionEntity;
import com.google.cloud.datastore.QueryResults;
import com.google.common.base.Function;
import com.google.common.collect.Iterators;
import com.google.common.collect.Sets;

import java.util.Set;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class ITQuerySnippets {

    private static Datastore datastore;
    private static Entity entity1;
    private static Entity entity2;
    private static final String KIND = "kind_" + UUID.randomUUID().toString().replace("-", "");
    private static final Function<ProjectionEntity, String> ENTITY_TO_DESCRIPTION_FUNCTION =
            new Function<ProjectionEntity, String>() {
                @Override
                public String apply(ProjectionEntity entity) {
                    return entity.getString("description");
                }
            };

    @Rule
    public Timeout globalTimeout = Timeout.seconds(60);

    @BeforeClass
    public static void beforeClass() {
        datastore = DatastoreOptions.getDefaultInstance().getService();
        Key key1 = Key.newBuilder(datastore.getOptions().getProjectId(), KIND, "key1").build();
        Key key2 = Key.newBuilder(datastore.getOptions().getProjectId(), KIND, "key2").build();
        entity1 = Entity.newBuilder(key1).set("description", "entity1").build();
        entity2 = Entity.newBuilder(key2).set("description", "entity2").build();
        datastore.put(entity1, entity2);
    }

    @AfterClass
    public static void afterClass() {
        datastore.delete(entity1.getKey(), entity2.getKey());
    }

    @Test
    public void testNewQuery() throws InterruptedException {
        QuerySnippets transactionSnippets = new QuerySnippets(datastore);
        QueryResults<?> results = transactionSnippets.newQuery(KIND);
        Set<?> resultSet = Sets.newHashSet(results);
        while (!resultSet.contains(entity1) || !resultSet.contains(entity2)) {
            Thread.sleep(500);
            resultSet = Sets.newHashSet(results);
        }
    }

    @Test
    public void testNewTypedQuery() throws InterruptedException {
        QuerySnippets transactionSnippets = new QuerySnippets(datastore);
        QueryResults<Entity> results = transactionSnippets.newTypedQuery(KIND);
        Set<Entity> resultSet = Sets.newHashSet(results);
        while (!resultSet.contains(entity1) || !resultSet.contains(entity2)) {
            Thread.sleep(500);
            resultSet = Sets.newHashSet(results);
        }
    }

    @Test
    public void testNewEntityQuery() throws InterruptedException {
        QuerySnippets transactionSnippets = new QuerySnippets(datastore);
        QueryResults<Entity> results = transactionSnippets.newEntityQuery(KIND);
        Set<Entity> resultSet = Sets.newHashSet(results);
        while (!resultSet.contains(entity1) || !resultSet.contains(entity2)) {
            Thread.sleep(500);
            resultSet = Sets.newHashSet(results);
        }
    }

    @Test
    public void testNewKeyQuery() throws InterruptedException {
        QuerySnippets transactionSnippets = new QuerySnippets(datastore);
        QueryResults<Key> results = transactionSnippets.newKeyQuery(KIND);
        Set<Key> resultSet = Sets.newHashSet(results);
        while (!resultSet.contains(entity1.getKey()) || !resultSet.contains(entity2.getKey())) {
            Thread.sleep(500);
            resultSet = Sets.newHashSet(results);
        }
    }

    @Test
    public void testNewProjectionEntityQuery() throws InterruptedException {
        QuerySnippets transactionSnippets = new QuerySnippets(datastore);
        QueryResults<ProjectionEntity> results =
                transactionSnippets.newProjectionEntityQuery(KIND, "description");
        Set<String> resultSet =
                Sets.newHashSet(Iterators.transform(results, ENTITY_TO_DESCRIPTION_FUNCTION));
        while (!resultSet.contains(entity1.getString("description"))
                || !resultSet.contains(entity2.getString("description"))) {
            Thread.sleep(500);
            resultSet = Sets.newHashSet(Iterators.transform(results, ENTITY_TO_DESCRIPTION_FUNCTION));
        }
    }
}
