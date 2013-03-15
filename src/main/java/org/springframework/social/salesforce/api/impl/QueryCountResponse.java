/*
 * INSERT COPYRIGHT HERE
 */

package org.springframework.social.salesforce.api.impl;

import java.util.Collection;

/**
 *
 * @author sosandstrom
 */
public class QueryCountResponse {
    private int totalSize;
    private Boolean done;
    private Collection records;

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

    public Collection getRecords() {
        return records;
    }

    public void setRecords(Collection records) {
        this.records = records;
    }
    
    
}
