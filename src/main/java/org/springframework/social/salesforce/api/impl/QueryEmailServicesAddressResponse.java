/*
 * INSERT COPYRIGHT HERE
 */

package org.springframework.social.salesforce.api.impl;

import java.util.Collection;
import org.springframework.social.salesforce.api.SalesforceEmailServicesAddress;

/**
 *
 * @author sosandstrom
 */
public class QueryEmailServicesAddressResponse {
    private int totalSize;
    private Boolean done;
    private Collection<SalesforceEmailServicesAddress> records;

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Collection<SalesforceEmailServicesAddress> getRecords() {
        return records;
    }

    public void setRecords(Collection<SalesforceEmailServicesAddress> records) {
        this.records = records;
    }
    
    
}
