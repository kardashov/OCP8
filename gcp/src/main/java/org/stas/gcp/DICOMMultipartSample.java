package org.stas.gcp;

import org.apache.commons.fileupload.MultipartStream;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class DICOMMultipartSample {

    static String token = "yourtoken_here";

    static String myURL = "https://healthcare.googleapis.com/v1alpha/projects/gcp-health/locations/europe-west2/datasets/dicom-demo-dataset3/dicomStores/ct-testing/dicomWeb/studies/1.3.6.1.4.1.14519.5.2.1.4320.7007.203059346048546067166621241946/series/1.3.6.1.4.1.14519.5.2.1.4320.7007.113686129632252779806152571225/instances/1.3.6.1.4.1.14519.5.2.1.4320.7007.336446501686665177500847532496";
    static String myURL2 = "https://healthcare.googleapis.com/v1alpha/projects/gcp-health/locations/europe-west2/datasets/dicom-demo-dataset3/dicomStores/ct-testing/dicomWeb/studies/1.3.6.1.4.1.14519.5.2.1.4320.7007.203059346048546067166621241946/series/1.3.6.1.4.1.14519.5.2.1.4320.7007.113686129632252779806152571225/";

    public static void main(String[] args) throws Exception {
//        localExample();
        remoteExample(myURL);

    }

    private static void remoteExample(String url) {

        try {

            HttpURLConnection httpConn = (HttpURLConnection) new URL(myURL2).openConnection();
            httpConn.setRequestProperty("Authorization", "Bearer " + token);
//            httpConnn.setRequestProperty("Accept", "multipart/related; type=application/dicom; transfer-syntax=*");
            int responseCode = httpConn.getResponseCode();
            System.out.println(responseCode);
            String contentType = httpConn.getContentType();
            System.out.println(contentType);
            int index = contentType.indexOf("boundary=");
            String boundary = contentType.substring(index + 10, contentType.length() - 1);

            System.out.println(boundary);
//            urlInputStream = NetworkUtil.getUrlInputStream(new URL(url).openConnection());

            System.out.println(httpConn.getContentLength());
            System.out.println(boundary.getBytes());

            MultipartStream multipart = new MultipartStream(httpConn.getInputStream(), boundary.getBytes(), 5_000_000);
            boolean nextPart = multipart.skipPreamble();

            int counter = 0;
            long start = System.currentTimeMillis();
//                OutputStream output = new FileOutputStream("myfile.dcm");
            while (nextPart) {
                OutputStream output = new FileOutputStream("myfile_" + counter + ".dcm");
                String header = multipart.readHeaders();
                System.out.println(header);
                // process headers
                // create some output stream
                multipart.readBodyData(output);
                output.close();
                counter++;
                nextPart = multipart.readBoundary();
            }
            System.out.println("Elapsed time: " + (System.currentTimeMillis() - start));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    // Lines should end with CRLF
    public static final String MULTIPART_BODY =
            "Content-Type: multipart/form-data; boundary=--AaB03x\r\n"
                    + "\r\n"
                    + "----AaB03x\r\n"
                    + "Content-Disposition: form-data; name=\"submit-name\"\r\n"
                    + "\r\n"
                    + "Larry\r\n"
                    + "----AaB03x\r\n"
                    + "Content-Disposition: form-data; name=\"files\"; filename=\"file1.txt\"\r\n"
                    + "Content-Type: text/plain\r\n"
                    + "\r\n"
                    + "HELLO WORLD!\r\n"
                    + "----AaB03x--\r\n";

    public static void localExample() throws Exception {

        byte[] boundary = "--AaB03x".getBytes();

        ByteArrayInputStream content = new ByteArrayInputStream(MULTIPART_BODY.getBytes());

        @SuppressWarnings("deprecation")
        MultipartStream multipartStream =
                new MultipartStream(content, boundary);

        boolean nextPart = multipartStream.skipPreamble();
        while (nextPart) {
            String header = multipartStream.readHeaders();
            System.out.println("");
            System.out.println("Headers:");
            System.out.println(header);
            System.out.println("Body:");
            multipartStream.readBodyData(System.out);
            System.out.println("");
            nextPart = multipartStream.readBoundary();
        }
    }
}
