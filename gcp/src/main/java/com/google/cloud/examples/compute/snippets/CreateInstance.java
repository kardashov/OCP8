package com.google.cloud.examples.compute.snippets;

import com.google.cloud.compute.deprecated.AttachedDisk;
import com.google.cloud.compute.deprecated.Compute;
import com.google.cloud.compute.deprecated.ComputeOptions;
import com.google.cloud.compute.deprecated.ImageId;
import com.google.cloud.compute.deprecated.Instance;
import com.google.cloud.compute.deprecated.InstanceId;
import com.google.cloud.compute.deprecated.InstanceInfo;
import com.google.cloud.compute.deprecated.MachineTypeId;
import com.google.cloud.compute.deprecated.NetworkId;
import com.google.cloud.compute.deprecated.NetworkInterface;
import com.google.cloud.compute.deprecated.Operation;

import java.util.concurrent.TimeoutException;

/**
 * A snippet for Google Cloud Compute Engine showing how to create a virtual machine instance.
 */
public class CreateInstance {

    public static void main(String... args) throws InterruptedException, TimeoutException {
        Compute compute = ComputeOptions.getDefaultInstance().getService();
        ImageId imageId = ImageId.of("debian-cloud", "debian-8-jessie-v20160329");
        NetworkId networkId = NetworkId.of("default");
        AttachedDisk attachedDisk = AttachedDisk.of(AttachedDisk.CreateDiskConfiguration.of(imageId));
        NetworkInterface networkInterface = NetworkInterface.of(networkId);
        InstanceId instanceId = InstanceId.of("us-central1-a", "instance-name");
        MachineTypeId machineTypeId = MachineTypeId.of("us-central1-a", "n1-standard-1");
        Operation operation =
                compute.create(InstanceInfo.of(instanceId, machineTypeId, attachedDisk, networkInterface));
        operation = operation.waitFor();
        if (operation.getErrors() == null) {
            // use instance
            Instance instance = compute.getInstance(instanceId);
        }
    }
}
