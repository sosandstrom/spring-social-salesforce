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
import org.springframework.social.salesforce.api.MeetrOperations;
import org.springframework.social.salesforce.api.Salesforce;
import org.springframework.social.salesforce.api.SalesforceProfile;

/**
 *
 * @author sosandstrom
 */
public class SalesforceTemplate extends AbstractOAuth2ApiBinding 
        implements Salesforce, MeetrOperations {
    
    public static final String VERSION_24 = "v24.0";
    public static final String VERSION = VERSION_24;
    
    private static final String INSTANCE_URL_NA1 = "https://na1.salesforce.com";
    
    static final Logger LOG = LoggerFactory.getLogger(SalesforceTemplate.class);
    
    private String instanceUrl;

    @Override
    public MeetrOperations meetrOperations() {
        return this;
    }

    public SalesforceTemplate(String accessToken) {
        this(accessToken, INSTANCE_URL_NA1);
    }

    public SalesforceTemplate(final String accessToken, String instanceUrl) {
        super(accessToken);
        setRequestFactory(new SimpleClientHttpRequestFactory() {

            @Override
            protected void prepareConnection(HttpURLConnection connection, String httpMethod) throws IOException {
                super.prepareConnection(connection, httpMethod);
                connection.setRequestProperty("Authorization", String.format("OAuth %s", accessToken));
                LOG.debug("Authorization: OAuth {}", accessToken);
            }
            
        });
        this.instanceUrl = instanceUrl;
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

    public void setInstanceUrl(String instanceUrl) {
        this.instanceUrl = instanceUrl;
    }
    
}
