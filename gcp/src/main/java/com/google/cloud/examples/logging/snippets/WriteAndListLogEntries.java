package com.google.cloud.examples.logging.snippets;

import com.google.api.gax.paging.Page;
import com.google.cloud.MonitoredResource;
import com.google.cloud.logging.LogEntry;
import com.google.cloud.logging.Logging;
import com.google.cloud.logging.Logging.EntryListOption;
import com.google.cloud.logging.LoggingOptions;
import com.google.cloud.logging.Payload.StringPayload;

import java.util.Collections;

/**
 * A snippet for Stackdriver Logging showing how to write a log entry. The snippet also shows how to
 * list all log entries with a given log name.
 *
 * @see <a href="https://cloud.google.com/logging/docs/api/#sinks">Sinks</a>
 */
public class WriteAndListLogEntries {

    public static void main(String... args) throws Exception {
        // Create a service object
        // Credentials are inferred from the environment
        LoggingOptions options = LoggingOptions.getDefaultInstance();
        try (Logging logging = options.getService()) {

            // Create a log entry
            LogEntry firstEntry =
                    LogEntry.newBuilder(StringPayload.of("message"))
                            .setLogName("test-log")
                            .setResource(
                                    MonitoredResource.newBuilder("global")
                                            .addLabel("project_id", options.getProjectId())
                                            .build())
                            .build();
            logging.write(Collections.singleton(firstEntry));

            // List log entries
            Page<LogEntry> entries =
                    logging.listLogEntries(
                            EntryListOption.filter(
                                    "logName=projects/" + options.getProjectId() + "/logs/test-log"));
            for (LogEntry logEntry : entries.iterateAll()) {
                System.out.println(logEntry);
            }
        }
    }
}
