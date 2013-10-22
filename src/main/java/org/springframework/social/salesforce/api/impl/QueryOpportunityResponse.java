package org.springframework.social.salesforce.api.impl;

import org.springframework.social.salesforce.api.SalesforceContact;
import org.springframework.social.salesforce.api.SalesforceOpportunity;

import java.util.Collection;

/**
 * Rest template response containing a list of Salesforce Opportunities
 * @author mattiaslevin
 */
public class QueryOpportunityResponse {

    private int totalSize;
    private Boolean done;
    private Collection<SalesforceOpportunity> records;


    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Collection<SalesforceOpportunity> getRecords() {
        return records;
    }

    public void setRecords(Collection<SalesforceOpportunity> records) {
        this.records = records;
    }

    public int getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
}
