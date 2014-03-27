/*
 * INSERT COPYRIGHT HERE
 */

package org.springframework.social.salesforce.api;

import java.util.Date;

/**
 *
 * @author sosandstrom
 */
public interface BasicOperations {
    
    Iterable<SalesforceAccount> getAccounts(int pageSize, String cursorKey);
    
    Iterable<SalesforceContact> getContacts(int pageSize, String cursorKey);

    Iterable<SalesforceOpportunity> getOpportunityForAccountId(String accountId, SalesforceOpportunity.Status status, Date afterDate, int pageSize, String cursorKey);
    
    SalesforceProfile getUserProfile();

    String getEmail2SalesforceAddress();

    int getAccountCount();

    int getContactCount();
}
