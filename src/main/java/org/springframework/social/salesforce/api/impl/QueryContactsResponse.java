/*
 * INSERT COPYRIGHT HERE
 */

package org.springframework.social.salesforce.api.impl;

import java.util.Collection;
import org.springframework.social.salesforce.api.SalesforceContact;

/**
 *
 * @author sosandstrom
 */
public class QueryContactsResponse {
    private int totalSize;
    private Boolean done;
    private Collection<SalesforceContact> records;

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

    public Collection<SalesforceContact> getRecords() {
        return records;
    }

    public void setRecords(Collection<SalesforceContact> records) {
        this.records = records;
    }
    
    
}
