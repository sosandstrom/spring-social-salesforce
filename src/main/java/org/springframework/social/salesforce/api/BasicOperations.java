/*
 * INSERT COPYRIGHT HERE
 */

package org.springframework.social.salesforce.api;

/**
 *
 * @author sosandstrom
 */
public interface BasicOperations {
    
    Iterable<SalesforceAccount> getAccounts(int pageSize, String cursorKey);
    
    Iterable<SalesforceContact> getContacts(int pageSize, String cursorKey);
    
    SalesforceProfile getUserProfile();

    String getEmail2SalesforceAddress();

    int getAccountCount();

    int getContactCount();
}
