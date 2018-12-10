package com.google.cloud.examples.bigquery.snippets;

import static org.junit.Assert.*;

import com.google.cloud.bigquery.Acl;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQuery.DatasetDeleteOption;
import com.google.cloud.bigquery.Dataset;
import com.google.cloud.bigquery.DatasetInfo;
import com.google.cloud.bigquery.testing.RemoteBigQueryHelper;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class ITDatasetInfoSnippets {

    private static final String DATASET = RemoteBigQueryHelper.generateDatasetName();

    private static BigQuery bigquery;
    private static DatasetInfoSnippets datasetInfoSnippets;
    private static ByteArrayOutputStream bout;
    private static PrintStream out;

    @Rule
    public Timeout globalTimeout = Timeout.seconds(300);

    @BeforeClass
    public static void beforeClass() {
        bigquery = RemoteBigQueryHelper.create().getOptions().getService();
        datasetInfoSnippets = new DatasetInfoSnippets(bigquery);
        bigquery.create(DatasetInfo.newBuilder(DATASET).build());
        bout = new ByteArrayOutputStream();
        out = new PrintStream(bout);
        System.setOut(out);
    }

    @AfterClass
    public static void afterClass() throws ExecutionException, InterruptedException {
        bigquery.delete(DATASET, DatasetDeleteOption.deleteContents());
        System.setOut(null);
    }

    @Test
    public void testUpdateDatasetAccess() throws InterruptedException {
        Dataset dataset = bigquery.getDataset(DATASET);
        List<Acl> beforeAcls = datasetInfoSnippets.updateDatasetAccess(dataset);
        dataset = bigquery.getDataset(DATASET);
        List<Acl> afterAcls = dataset.getAcl();
        assertEquals(beforeAcls.size() + 1, afterAcls.size());
    }

    @Test
    public void testUpdateDatasetExpiration() throws InterruptedException {
        Dataset dataset = bigquery.getDataset(DATASET);
        Long beforeExpiration = datasetInfoSnippets.updateDatasetExpiration(dataset);
        dataset = bigquery.getDataset(DATASET);
        Long afterExpiration = dataset.getDefaultTableLifetime();
        assertNotEquals(beforeExpiration, afterExpiration);
    }
}
