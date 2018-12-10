package com.google.cloud.examples.bigquery.snippets;

import com.google.api.gax.retrying.PollException;
import com.google.cloud.RetryOption;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.BigQueryException;
import com.google.cloud.bigquery.Job;
import com.google.cloud.bigquery.JobStatus;
import org.threeten.bp.Duration;

public class JobSnippets {

    private Job job;

    public JobSnippets(Job job) {
        this.job = job;
    }

    /**
     * Example of checking that a job exists.
     */
    // [TARGET exists()]
    public boolean exists() throws InterruptedException {
        // [START ]
        if (!job.exists()) {
            // job doesn't exist
        }
        // [END ]
        return job.exists();
    }

    /**
     * Example of waiting for a job until it reports that it is done.
     */
    // [TARGET isDone()]
    public void isDone() throws InterruptedException {
        // [START ]
        while (!job.isDone()) {
            Thread.sleep(1000L);
        }
        // [END ]
    }

    /**
     * Example usage of {@code waitFor()}.
     */
    // [TARGET waitFor(RetryOption...)]
    public boolean waitFor() throws InterruptedException {
        try {
            // [START ]
            Job completedJob = job.waitFor();
            if (completedJob == null) {
                // job no longer exists
            } else if (completedJob.getStatus().getError() != null) {
                // job failed, handle error
            } else {
                // job completed successfully
            }
            // [END ]
        } catch (BigQueryException e) {
            // Timeouts shouldn't happen without a timeout option.
            if (e.getCause() instanceof PollException) {
                return false;
            }
            throw e;
        }
        return true;
    }

    /**
     * Example usage of {@code waitFor()} with checking period and timeout.
     */
    // [TARGET waitFor(RetryOption...)]
    public boolean waitForWithOptions() throws InterruptedException {
        try {
            // [START ]
            Job completedJob =
                    job.waitFor(
                            RetryOption.initialRetryDelay(Duration.ofSeconds(1)),
                            RetryOption.totalTimeout(Duration.ofMinutes(1)));
            if (completedJob == null) {
                // job no longer exists
            } else if (completedJob.getStatus().getError() != null) {
                // job failed, handle error
            } else {
                // job completed successfully
            }
            // [END ]
        } catch (BigQueryException e) {
            if (e.getCause() instanceof PollException) {
                return false;
            }
            throw e;
        }
        return true;
    }

    /**
     * Example of reloading all fields until job status is DONE.
     */
    // [TARGET reload(JobOption...)]
    public JobStatus.State reload() throws InterruptedException {
        // [START ]
        while (job.getStatus().getState() != JobStatus.State.DONE) {
            Thread.sleep(1000L);
            job = job.reload();
        }
        // [END ]
        return job.getStatus().getState();
    }

    /**
     * Example of reloading status field until job status is DONE.
     */
    // [TARGET reload(JobOption...)]
    public JobStatus.State reloadStatus() throws InterruptedException {
        // [START ]
        while (job.getStatus().getState() != JobStatus.State.DONE) {
            Thread.sleep(1000L);
            job = job.reload(BigQuery.JobOption.fields(BigQuery.JobField.STATUS));
        }
        // [END ]
        return job.getStatus().getState();
    }

    /**
     * Example of cancelling a job.
     */
    // [TARGET cancel()]
    public boolean cancel() {
        // [START ]
        if (job.cancel()) {
            return true; // job successfully cancelled
        } else {
            // job not found
        }
        // [END ]
        return false;
    }
}
