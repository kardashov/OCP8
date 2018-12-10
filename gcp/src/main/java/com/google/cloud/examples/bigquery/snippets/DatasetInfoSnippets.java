package com.google.cloud.examples.bigquery.snippets;

import com.google.cloud.bigquery.Acl;
import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.DatasetInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains a number of snippets for the {@link DatasetInfo} interface.
 */
public class DatasetInfoSnippets {

    private final BigQuery bigquery;

    public DatasetInfoSnippets(BigQuery bigquery) {
        this.bigquery = bigquery;
    }

    /**
     * Update the ACLs for a dataset.
     */
    // [TARGET getAcl()]
    // [VARIABLE bigquery.getDataset(DatasetId.of("my_dataset"))]
    public List<Acl> updateDatasetAccess(DatasetInfo dataset) {
        // [START bigquery_update_dataset_access]
        List<Acl> beforeAcls = dataset.getAcl();

        // Make a copy of the ACLs so that they can be modified.
        ArrayList<Acl> acls = new ArrayList<>(beforeAcls);
        acls.add(Acl.of(new Acl.User("sample.bigquery.dev@gmail.com"), Acl.Role.READER));
        DatasetInfo.Builder builder = dataset.toBuilder();
        builder.setAcl(acls);

        bigquery.update(builder.build()); // API request.
        // [END bigquery_update_dataset_access]

        return beforeAcls;
    }

    /**
     * Update the default table expiration time for a dataset.
     */
    // [TARGET getDefaultTableLifetime()]
    // [VARIABLE bigquery.getDataset(DatasetId.of("my_dataset"))]
    public Long updateDatasetExpiration(DatasetInfo dataset) {
        // [START bigquery_update_dataset_expiration]
        Long beforeExpiration = dataset.getDefaultTableLifetime();

        Long oneDayMilliseconds = 24 * 60 * 60 * 1000L;
        DatasetInfo.Builder builder = dataset.toBuilder();
        builder.setDefaultTableLifetime(oneDayMilliseconds);
        bigquery.update(builder.build()); // API request.
        // [END bigquery_update_dataset_expiration]

        return beforeExpiration;
    }
}
