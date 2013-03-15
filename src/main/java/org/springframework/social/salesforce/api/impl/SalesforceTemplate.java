/*
 * INSERT COPYRIGHT HERE
 */

package org.springframework.social.salesforce.api.impl;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.salesforce.api.BasicOperations;
import org.springframework.social.salesforce.api.Salesforce;
import org.springframework.social.salesforce.api.SalesforceAccount;
import org.springframework.social.salesforce.api.SalesforceContact;
import org.springframework.social.salesforce.api.SalesforceEmailServicesAddress;
import org.springframework.social.salesforce.api.SalesforceProfile;

/**
 * 
 * @author sosandstrom
 */
public class SalesforceTemplate extends AbstractOAuth2ApiBinding implements Salesforce, BasicOperations {

    public static final String  VERSION_24       = "v24.0";
    public static final String  VERSION          = VERSION_24;

    private static final String INSTANCE_URL_NA1 = "https://na1.salesforce.com";
    public static final String KIND_ACCOUNT = "Account";
    public static final String KIND_CONTACT = "Contact";
    protected final String      FIELDS_CONTACT   = "Id,Email,Name,FirstName,LastName,Phone,MobilePhone,MailingStreet,MailingCity,MailingState,MailingPostalCode,MailingCountry";
    protected final String      FIELDS_ACCOUNT   = "Id,Name,Phone,BillingCity,BillingCountry,BillingPostalCode,BillingState,BillingStreet,ShippingCity,ShippingCountry,ShippingPostalCode,ShippingState,ShippingStreet";

    static final Logger         LOG              = LoggerFactory.getLogger(SalesforceTemplate.class);

    private String              instanceUrl;

    @Override
    public BasicOperations basicOperations() {
        return this;
    }

    public SalesforceTemplate(String accessToken) {
        this(accessToken, INSTANCE_URL_NA1);
    }

    public SalesforceTemplate(final String accessToken, String instanceUrl) {
        super(accessToken);
        final SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory() {
            @Override
            protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
                super.prepareConnection(connection, httpMethod);
                connection.setRequestProperty("Authorization", String.format("OAuth %s", accessToken));
                LOG.debug("Authorization: OAuth {}", accessToken);
            }
        };
        requestFactory.setConnectTimeout(15000);
        requestFactory.setReadTimeout(15000);
        setRequestFactory(requestFactory);
        this.instanceUrl = instanceUrl;
    }

    @Override
    public Iterable<SalesforceAccount> getAccounts(int pageSize, String cursorKey) {
        final String url = String.format("%s/services/data/%s/query/?q={soql}", instanceUrl, VERSION);
        final String escapedCursorKey = escape(cursorKey);
        String soql = String.format("SELECT %s FROM Account %s ORDER BY Name LIMIT %d", 
                FIELDS_ACCOUNT, 
                null != cursorKey ? String.format("WHERE Name >= '%s'", escapedCursorKey) : "",
                pageSize);
        LOG.debug("SOQL: {}", soql);
        QueryAccountsResponse response = getRestTemplate().getForObject(url, QueryAccountsResponse.class, soql);
        return response.getRecords();
    }
    
    public static final String escape(String s) {
        if (null == s) {
            return null;
        }
        StringBuffer sb = new StringBuffer(s);
        
        int beginIndex = -2;
        while (-1 < (beginIndex = sb.indexOf("'", beginIndex+2))) {
            sb.insert(beginIndex, '\\');
        }
        
        return sb.toString();
    }

    @Override
    public Iterable<SalesforceContact> getContacts(int pageSize, String cursorKey) {
        final String url = String.format("%s/services/data/%s/query/?q={soql}", instanceUrl, VERSION);
        final String escapedCursorKey = escape(cursorKey);
        String soql = String.format("SELECT %s FROM Contact %s ORDER BY Name LIMIT %d", 
                FIELDS_CONTACT, 
                null != cursorKey ? String.format("WHERE Name >= '%s'", escapedCursorKey) : "",
                pageSize);
        LOG.debug("SOQL: {}", soql);
        QueryContactsResponse response = getRestTemplate().getForObject(url, QueryContactsResponse.class, soql);
        return response.getRecords();
    }

    @Override
    public String getEmail2SalesforceAddress() {
        SalesforceProfile me = getUserProfile();
        final String url = String.format("%s/services/data/%s/query/?q={soql}", instanceUrl, VERSION);
        String soql = String
                .format("SELECT Id,EmailDomainName FROM EmailServicesAddress WHERE LocalPart='emailtosalesforce' AND AuthorizedSenders LIKE '%s%s%s'",
                        "%", me.getEmail(), "%");
        LOG.debug("Email: {}, SOQL: {}", me.getEmail(), soql);
        QueryEmailServicesAddressResponse response = getRestTemplate().getForObject(url, QueryEmailServicesAddressResponse.class,
                soql);
        for(SalesforceEmailServicesAddress sesa : response.getRecords()) {
            LOG.debug("EmailServicesAddress.EmailDomainName={}", sesa.getEmailDomainName());
            if (null != sesa.getEmailDomainName()) {
                return String.format("emailtosalesforce@%s", sesa.getEmailDomainName());
            }
        }
        return null;
    }

    @Override
    public SalesforceProfile getUserProfile() {
        return getUserProfile("me");
    }

    public SalesforceProfile getUserProfile(String userId) {
        final String url = String.format("%s/services/data/%s/chatter/users/{id}", instanceUrl, VERSION);
        SalesforceProfile profile = getRestTemplate().getForObject(url, SalesforceProfile.class, userId);

        return profile;
    }

    @Override
    public int getAccountCount() {
        return getCount(KIND_ACCOUNT);
    }

    @Override
    public int getContactCount() {
        return getCount(KIND_CONTACT);
    }
    
    protected int getCount(String kind) {
        final String url = String.format("%s/services/data/%s/query/?q={soql}", instanceUrl, VERSION);
        String soql = String.format("SELECT count() FROM %s", kind);
        QueryCountResponse count = getRestTemplate().getForObject(url, QueryCountResponse.class, soql);
        LOG.debug("count() {}: {}", kind, count.getTotalSize());
        return count.getTotalSize();
    }

    public void setInstanceUrl(String instanceUrl) {
        this.instanceUrl = instanceUrl;
    }

}
