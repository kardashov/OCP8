package com.google.cloud.examples.spanner.snippets;

import com.google.cloud.spanner.BatchClient;
import com.google.cloud.spanner.DatabaseAdminClient;
import com.google.cloud.spanner.DatabaseClient;
import com.google.cloud.spanner.DatabaseId;
import com.google.cloud.spanner.InstanceAdminClient;
import com.google.cloud.spanner.Spanner;
import com.google.cloud.spanner.SpannerOptions;

/**
 * This class contains snippets for {@link com.google.cloud.spanner.Spanner} interface.
 */
public class SpannerSnippets {

    DatabaseAdminClient getDatabaseAdminClient() {
        // [START get_dbadmin_client]
        SpannerOptions options = SpannerOptions.newBuilder().build();
        Spanner spanner = options.getService();
        DatabaseAdminClient dbAdminClient = spanner.getDatabaseAdminClient();
        // [END get_dbadmin_client]

        return dbAdminClient;
    }

    DatabaseClient getDatabaseClient() {
        // [START get_db_client]
        SpannerOptions options = SpannerOptions.newBuilder().build();
        Spanner spanner = options.getService();
        final String project = "test-project";
        final String instance = "test-instance";
        final String database = "example-db";
        DatabaseId db = DatabaseId.of(project, instance, database);
        DatabaseClient dbClient = spanner.getDatabaseClient(db);
        // [END get_db_client]

        return dbClient;
    }

    InstanceAdminClient getInstanceAdminClient() {
        // [START get_instance_admin_client]
        SpannerOptions options = SpannerOptions.newBuilder().build();
        Spanner spanner = options.getService();
        InstanceAdminClient instanceAdminClient = spanner.getInstanceAdminClient();
        // [END get_instance_admin_client]

        return instanceAdminClient;
    }

    BatchClient getBatchClient() {
        // [START get_batch_client]
        SpannerOptions options = SpannerOptions.newBuilder().build();
        Spanner spanner = options.getService();
        final String project = "test-project";
        final String instance = "test-instance";
        final String database = "example-db";
        DatabaseId db = DatabaseId.of(project, instance, database);
        BatchClient batchClient = spanner.getBatchClient(db);
        // [END get_batch_client]

        return batchClient;
    }
}
