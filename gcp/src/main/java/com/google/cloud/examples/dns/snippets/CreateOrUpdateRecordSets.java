package com.google.cloud.examples.dns.snippets;

import com.google.cloud.dns.ChangeRequestInfo;
import com.google.cloud.dns.Dns;
import com.google.cloud.dns.DnsOptions;
import com.google.cloud.dns.RecordSet;
import com.google.cloud.dns.Zone;

import java.util.concurrent.TimeUnit;

/**
 * A snippet for Google Cloud DNS showing how to create and update a resource record set.
 */
public class CreateOrUpdateRecordSets {

    public static void main(String... args) {
        // Create a service object.
        // The project ID and credentials will be inferred from the environment.
        Dns dns = DnsOptions.getDefaultInstance().getService();

        // Change this to a zone name that exists within your project
        String zoneName = "my-unique-zone";

        // Get zone from the service
        Zone zone = dns.getZone(zoneName);

        // Prepare a <i>www.<zone-domain>.</i> type A record set with ttl of 24 hours
        String ip = "12.13.14.15";
        RecordSet toCreate =
                RecordSet.newBuilder("www." + zone.getDnsName(), RecordSet.Type.A)
                        .setTtl(24, TimeUnit.HOURS)
                        .addRecord(ip)
                        .build();

        // Make a change
        ChangeRequestInfo.Builder changeBuilder = ChangeRequestInfo.newBuilder().add(toCreate);

        // Verify a www.<zone-domain>. type A record does not exist yet.
        // If it does exist, we will overwrite it with our prepared record.
        for (RecordSet current : zone.listRecordSets().iterateAll()) {
            if (toCreate.getName().equals(current.getName())
                    && toCreate.getType().equals(current.getType())) {
                changeBuilder.delete(current);
            }
        }

        // Build and apply the change request to our zone
        ChangeRequestInfo changeRequest = changeBuilder.build();
        zone.applyChangeRequest(changeRequest);
    }
}
