package org.stas.gcp;

import com.google.api.gax.core.CredentialsProvider;
import com.google.auth.Credentials;
import com.google.auth.oauth2.ServiceAccountCredentials;

import java.io.InputStream;

public class GCPAuthProvider implements CredentialsProvider {
    private static String pathname = "/service_account.json";

    @Override
    public Credentials getCredentials() {
        try (InputStream serviceAccountStream = this.getClass().getResourceAsStream(pathname)) {
            return ServiceAccountCredentials.fromStream(serviceAccountStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
