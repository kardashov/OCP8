package com.google.cloud.examples.logging.snippets;

import com.google.api.gax.paging.Page;
import com.google.cloud.logging.Logging;
import com.google.cloud.logging.LoggingOptions;
import com.google.cloud.logging.Sink;
import com.google.cloud.logging.SinkInfo;
import com.google.cloud.logging.SinkInfo.Destination.DatasetDestination;

/**
 * A snippet for Stackdriver Logging showing how to create a sink to backs log entries to BigQuery.
 * The snippet also shows how to list all sinks.
 *
 * @see <a href="https://cloud.google.com/logging/docs/api/#sinks">Sinks</a>
 */
public class CreateAndListSinks {

    public static void main(String... args) throws Exception {
        // Create a service object
        // Credentials are inferred from the environment
        try (Logging logging = LoggingOptions.getDefaultInstance().getService()) {

            // Create a sink to back log entries to a BigQuery dataset
            SinkInfo sinkInfo =
                    SinkInfo.newBuilder("test-sink", DatasetDestination.of("test-dataset"))
                            .setFilter("severity >= ERROR")
                            .build();
            logging.create(sinkInfo);

            // List sinks
            Page<Sink> sinks = logging.listSinks();
            for (Sink sink : sinks.iterateAll()) {
                System.out.println(sink);
            }
        }
    }
}
