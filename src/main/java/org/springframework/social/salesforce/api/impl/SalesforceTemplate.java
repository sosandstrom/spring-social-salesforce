/*
 * INSERT COPYRIGHT HERE
 */

package org.springframework.social.salesforce.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.salesforce.api.*;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    protected final String      FIELDS_OPPORTUNITY = "Id,Name,Description,CloseDate,IsClosed";

    static final Logger         LOG              = LoggerFactory.getLogger(SalesforceTemplate.class);

    private String              instanceUrl;

    // Expose the ability to set a custom error handler that will map http error codes to exceptions
    private static ResponseErrorHandler responseErrorHandler;

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

        if (null != responseErrorHandler) {
            LOG.debug("Configure custom Salesforce error handler");
            getRestTemplate().setErrorHandler(responseErrorHandler);
        }

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
    public Iterable<SalesforceOpportunity> getOpportunityForAccountId(String accountId, SalesforceOpportunity.Status status, Date afterDate, int pageSize, String cursorKey) {

        final String url = String.format("%s/services/data/%s/query/?q={soql}", instanceUrl, VERSION);

        StringBuilder sb = new StringBuilder();

        // Filter on account id
        if (null != accountId) {
            // AccountId must always be provided
            sb.append(String.format("WHERE AccountId = '%s'", accountId));
        } else {
            return null;
        }

        // Filter on opportunity status if provided
        if (SalesforceOpportunity.Status.ALL != status) {
            String opportunityStatus = SalesforceOpportunity.Status.OPEN == status ? "true" : "false";
            sb.append(" AND ");
            sb.append(String.format("IsClosed = %s", opportunityStatus));
        }

        // Filter on date if provided
        if (null != afterDate) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = df.format(afterDate);
            if (null != dateString) {
                sb.append(" AND ");
                sb.append(String.format("CloseDate >= %s", dateString));
            }
        }

        // Filter on cursor key if provided
        if (null != cursorKey) {
            final String escapedCursorKey = escape(cursorKey);
            if (null != escapedCursorKey) {
                sb.append(" AND ");
                sb.append(String.format("Name >= '%s'", escapedCursorKey));
            }
        }

        String soql = String.format("SELECT %s FROM Opportunity %s ORDER BY Name LIMIT %d",
                FIELDS_OPPORTUNITY,
                sb.toString(),
                pageSize);
        LOG.debug("SOQL: {}", soql);

        QueryOpportunityResponse response = getRestTemplate().getForObject(url, QueryOpportunityResponse.class, soql);
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
        LOG.debug("Url {}", url);
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

    public static void setResponseErrorHandler(ResponseErrorHandler responseErrorHandler) {
        LOG.debug("Set Salesforce response error handler");
        SalesforceTemplate.responseErrorHandler = responseErrorHandler;
    }


}
