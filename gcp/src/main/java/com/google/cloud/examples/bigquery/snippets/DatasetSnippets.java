package com.google.cloud.examples.bigquery.snippets;

import com.google.api.gax.paging.Page;
import com.google.cloud.bigquery.Dataset;
import com.google.cloud.bigquery.Dataset.Builder;
import com.google.cloud.bigquery.Field;
import com.google.cloud.bigquery.LegacySQLTypeName;
import com.google.cloud.bigquery.Schema;
import com.google.cloud.bigquery.StandardTableDefinition;
import com.google.cloud.bigquery.Table;
import com.google.cloud.bigquery.TimePartitioning;

/**
 * This class contains a number of snippets for the {@link Dataset} interface.
 */
public class DatasetSnippets {

    private final Dataset dataset;

    public DatasetSnippets(Dataset dataset) {
        this.dataset = dataset;
    }

    /**
     * Example of checking whether a dataset exists.
     */
    // [TARGET exists()]
    public boolean doesDatasetExist() {
        // [START ]
        boolean exists = dataset.exists();
        if (exists) {
            // the dataset exists
        } else {
            // the dataset was not found
        }
        // [END ]
        return exists;
    }

    /**
     * Example of reloading a dataset.
     */
    // [TARGET reload(DatasetOption...)]
    public Dataset reloadDataset() {
        // [START ]
        Dataset latestDataset = dataset.reload();
        if (latestDataset == null) {
            // The dataset was not found
        }
        // [END ]
        return latestDataset;
    }

    /**
     * Example of updating a dataset.
     */
    // [TARGET update(DatasetOption...)]
    // [VARIABLE "my_friendly_name"]
    public Dataset updateDataset(String friendlyName) {
        // [START ]
        Builder builder = dataset.toBuilder();
        builder.setFriendlyName(friendlyName);
        Dataset updatedDataset = builder.build().update();
        // [END ]
        return updatedDataset;
    }

    /**
     * Example of deleting a dataset.
     */
    // [TARGET delete(DatasetDeleteOption...)]
    public boolean deleteDataset() {
        // [START ]
        boolean deleted = dataset.delete();
        if (deleted) {
            // The dataset was deleted
        } else {
            // The dataset was not found
        }
        // [END ]
        return deleted;
    }

    /**
     * Example of listing tables in the dataset.
     */
    // [TARGET list(TableListOption...)]
    public Page<Table> list() {
        // [START ]
        Page<Table> tables = dataset.list();
        for (Table table : tables.iterateAll()) {
            // do something with the table
        }
        // [END ]
        return tables;
    }

    /**
     * Example of getting a table in the dataset.
     */
    // [TARGET get(String, TableOption...)]
    // [VARIABLE “my_table”]
    public Table getTable(String tableName) {
        // [START ]
        Table table = dataset.get(tableName);
        // [END ]
        return table;
    }

    /**
     * Example of creating a table in the dataset with schema and time partitioning.
     */
    // [TARGET create(String, TableDefinition, TableOption...)]
    // [VARIABLE “my_table”]
    // [VARIABLE “my_field”]
    public Table createTable(String tableName, String fieldName) {
        // [START ]
        Schema schema = Schema.of(Field.of(fieldName, LegacySQLTypeName.STRING));
        StandardTableDefinition definition =
                StandardTableDefinition.newBuilder()
                        .setSchema(schema)
                        .setTimePartitioning(TimePartitioning.of(TimePartitioning.Type.DAY))
                        .build();
        Table table = dataset.create(tableName, definition);
        // [END ]
        return table;
    }
}
