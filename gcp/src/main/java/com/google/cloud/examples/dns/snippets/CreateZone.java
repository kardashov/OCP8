package com.google.cloud.examples.dns.snippets;

import com.google.cloud.dns.Dns;
import com.google.cloud.dns.DnsOptions;
import com.google.cloud.dns.Zone;
import com.google.cloud.dns.ZoneInfo;

/**
 * A snippet for Google Cloud DNS showing how to create a zone. You will need to change the {@code
 * domainName} to a domain name, the ownership of which you should verify with Google.
 */
public class CreateZone {

    public static void main(String... args) {
        // Create a service object
        // The project ID and credentials will be inferred from the environment.
        Dns dns = DnsOptions.getDefaultInstance().getService();

        // Create a zone metadata object
        String zoneName = "my-unique-zone"; // Change this zone name which is unique within your project
        String domainName = "someexampledomain.com."; // Change this to a domain which you own
        String description = "This is a google-cloud-dns sample zone.";
        ZoneInfo zoneInfo = ZoneInfo.of(zoneName, domainName, description);

        // Create zone in Google Cloud DNS
        Zone zone = dns.create(zoneInfo);
        System.out.printf("Zone was created and assigned ID %s.%n", zone.getGeneratedId());
    }
}
