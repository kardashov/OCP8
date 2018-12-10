package com.google.cloud.examples.bigquery.snippets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobConfiguration;
import com.google.cloud.bigquery.JobInfo;
import com.google.cloud.bigquery.JobStatus;
import com.google.cloud.bigquery.QueryJobConfiguration;
import com.google.cloud.bigquery.testing.RemoteBigQueryHelper;
import org.junit.BeforeClass;
import org.junit.Test;

public class ITJobSnippets {

    private static final String QUERY =
            "SELECT corpus FROM `publicdata.samples.shakespeare` GROUP BY corpus;";

    private static BigQuery bigquery;

    @BeforeClass
    public static void beforeClass() {
        bigquery = RemoteBigQueryHelper.create().getOptions().getService();
    }

    @Test
    public void testExists() throws Exception {
        JobConfiguration jobConfig =
                QueryJobConfiguration.newBuilder(QUERY).setUseLegacySql(false).build();
        JobInfo jobInfo = JobInfo.newBuilder(jobConfig).build();
        Job job = bigquery.create(jobInfo);
        JobSnippets jobSnippets = new JobSnippets(job);
        boolean result = jobSnippets.exists();
        assertTrue(result);
    }

    @Test
    public void testIsDone() throws Exception {
        JobConfiguration jobConfig =
                QueryJobConfiguration.newBuilder(QUERY).setUseLegacySql(false).build();
        JobInfo jobInfo = JobInfo.newBuilder(jobConfig).build();
        Job job = bigquery.create(jobInfo);
        JobSnippets jobSnippets = new JobSnippets(job);
        jobSnippets.isDone();
        assertTrue(job.isDone());
    }

    @Test
    public void testWaitFor() throws Exception {
        JobConfiguration jobConfig =
                QueryJobConfiguration.newBuilder(QUERY).setUseLegacySql(false).build();
        JobInfo jobInfo = JobInfo.newBuilder(jobConfig).build();
        Job job = bigquery.create(jobInfo);
        JobSnippets jobSnippets = new JobSnippets(job);
        boolean result = jobSnippets.waitFor();
        assertTrue(result);
    }

    @Test
    public void testWaitForWithOptions() throws Exception {
        JobConfiguration jobConfig =
                QueryJobConfiguration.newBuilder(QUERY).setUseLegacySql(false).build();
        JobInfo jobInfo = JobInfo.newBuilder(jobConfig).build();
        Job job = bigquery.create(jobInfo);
        JobSnippets jobSnippets = new JobSnippets(job);
        boolean result = jobSnippets.waitForWithOptions();
        assertTrue(result);
    }

    @Test
    public void testReload() throws Exception {
        JobConfiguration jobConfig =
                QueryJobConfiguration.newBuilder(QUERY).setUseLegacySql(false).build();
        JobInfo jobInfo = JobInfo.newBuilder(jobConfig).build();
        Job job = bigquery.create(jobInfo);
        JobSnippets jobSnippets = new JobSnippets(job);
        JobStatus.State result = jobSnippets.reload();
        assertEquals(JobStatus.State.DONE, result);
    }

    @Test
    public void testReloadStatus() throws Exception {
        JobConfiguration jobConfig =
                QueryJobConfiguration.newBuilder(QUERY).setUseLegacySql(false).build();
        JobInfo jobInfo = JobInfo.newBuilder(jobConfig).build();
        Job job = bigquery.create(jobInfo);
        JobSnippets jobSnippets = new JobSnippets(job);
        JobStatus.State result = jobSnippets.reloadStatus();
        assertEquals(JobStatus.State.DONE, result);
    }

    @Test
    public void testCancel() {
        JobConfiguration jobConfig =
                QueryJobConfiguration.newBuilder(QUERY).setUseLegacySql(false).build();
        JobInfo jobInfo = JobInfo.newBuilder(jobConfig).build();
        Job job = bigquery.create(jobInfo);
        JobSnippets jobSnippets = new JobSnippets(job);
        boolean result = jobSnippets.cancel();
        assertTrue(result);
    }
}
