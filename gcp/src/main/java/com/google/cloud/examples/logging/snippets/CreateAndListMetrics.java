package com.google.cloud.examples.logging.snippets;

import com.google.api.gax.paging.Page;
import com.google.cloud.logging.Logging;
import com.google.cloud.logging.LoggingOptions;
import com.google.cloud.logging.Metric;
import com.google.cloud.logging.MetricInfo;

/**
 * A snippet for Stackdriver Logging showing how to create a metric. The snippet also shows how to
 * list all metrics.
 *
 * @see <a href="https://cloud.google.com/logging/docs/view/logs_based_metrics">Log-based metrics
 * </a>
 */
public class CreateAndListMetrics {

    public static void main(String... args) throws Exception {
        // Create a service object
        // Credentials are inferred from the environment
        try (Logging logging = LoggingOptions.getDefaultInstance().getService()) {

            // Create a metric
            MetricInfo metricInfo =
                    MetricInfo.newBuilder("test-metric", "severity >= ERROR")
                            .setDescription("Log entries with severity higher or equal to ERROR")
                            .build();
            logging.create(metricInfo);

            // List metrics
            Page<Metric> metrics = logging.listMetrics();
            for (Metric metric : metrics.iterateAll()) {
                System.out.println(metric);
            }
        }
    }
}
