package com.google.cloud.examples.compute.snippets;

import com.google.cloud.compute.deprecated.Compute;
import com.google.cloud.compute.deprecated.ComputeOptions;
import com.google.cloud.compute.deprecated.Disk;
import com.google.cloud.compute.deprecated.DiskId;
import com.google.cloud.compute.deprecated.Operation;
import com.google.cloud.compute.deprecated.Snapshot;

import java.util.concurrent.TimeoutException;

/**
 * A snippet for Google Cloud Compute Engine showing how to create a snapshot of a disk if the disk
 * exists.
 */
public class CreateSnapshot {

    public static void main(String... args) throws InterruptedException, TimeoutException {
        Compute compute = ComputeOptions.getDefaultInstance().getService();
        DiskId diskId = DiskId.of("us-central1-a", "disk-name");
        Disk disk = compute.getDisk(diskId, Compute.DiskOption.fields());
        if (disk != null) {
            String snapshotName = "disk-name-snapshot";
            Operation operation = disk.createSnapshot(snapshotName);
            operation = operation.waitFor();
            if (operation.getErrors() == null) {
                // use snapshot
                Snapshot snapshot = compute.getSnapshot(snapshotName);
            }
        }
    }
}
