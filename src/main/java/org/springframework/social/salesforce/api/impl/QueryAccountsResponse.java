/*
 * INSERT COPYRIGHT HERE
 */

package org.springframework.social.salesforce.api.impl;

import java.util.Collection;
import org.springframework.social.salesforce.api.SalesforceAccount;

/**
 *
 * @author sosandstrom
 */
public class QueryAccountsResponse {
    private int totalSize;
    private Boolean done;
    private Collection<SalesforceAccount> records;

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

    public Collection<SalesforceAccount> getRecords() {
        return records;
    }

    public void setRecords(Collection<SalesforceAccount> records) {
        this.records = records;
    }
    
    
}
