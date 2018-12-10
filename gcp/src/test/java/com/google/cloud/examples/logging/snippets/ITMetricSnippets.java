package com.google.cloud.examples.logging.snippets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.google.cloud.logging.Logging;
import com.google.cloud.logging.Metric;
import com.google.cloud.logging.MetricInfo;
import com.google.cloud.logging.testing.RemoteLoggingHelper;

import java.util.concurrent.ExecutionException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ITMetricSnippets {

    private static final String METRIC_NAME = RemoteLoggingHelper.formatForTest("it_metric_snippets");
    private static final String METRIC_FILTER = "severity>=ERROR";
    private static final String DESCRIPTION = "description";
    private static final String UPDATED_DESCRIPTION = "A more detailed description";

    private static Logging logging;
    private static MetricSnippets metricSnippets;

    @BeforeClass
    public static void beforeClass() {
        RemoteLoggingHelper helper = RemoteLoggingHelper.create();
        logging = helper.getOptions().getService();
        MetricInfo metricInfo =
                MetricInfo.newBuilder(METRIC_NAME, METRIC_FILTER).setDescription(DESCRIPTION).build();
        metricSnippets = new MetricSnippets(logging.create(metricInfo));
    }

    @AfterClass
    public static void afterClass() throws Exception {
        if (logging != null) {
            logging.close();
        }
    }

    @Test
    public void testMetric() throws InterruptedException, ExecutionException {
        Metric metric = metricSnippets.reload();
        assertNotNull(metric);
        Metric updatedMetric = metricSnippets.update();
        assertEquals(UPDATED_DESCRIPTION, updatedMetric.getDescription());
        updatedMetric = metricSnippets.reloadAsync();
        assertNotNull(updatedMetric);
        assertEquals(UPDATED_DESCRIPTION, updatedMetric.getDescription());
        metric.update();
        updatedMetric = metricSnippets.updateAsync();
        assertEquals(UPDATED_DESCRIPTION, updatedMetric.getDescription());
        assertTrue(metricSnippets.delete());
        assertFalse(metricSnippets.deleteAsync());
    }
}
