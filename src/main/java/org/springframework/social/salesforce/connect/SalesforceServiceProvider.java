/*
 * INSERT COPYRIGHT HERE
 */

package org.springframework.social.salesforce.connect;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.salesforce.api.Salesforce;
import org.springframework.social.salesforce.api.impl.SalesforceTemplate;

/**
 *
 * @author sosandstrom
 */
public class SalesforceServiceProvider extends AbstractOAuth2ServiceProvider<Salesforce> {
    
    private static final ThreadLocal<String> INSTANCE_URL = new ThreadLocal<String>();

    public SalesforceServiceProvider(String consumerKey, String consumerSecret) {
        this(consumerKey, consumerSecret,
                "https://login.salesforce.com/services/oauth2/authorize",
                "https://login.salesforce.com/services/oauth2/token");
         
    }

    public SalesforceServiceProvider(String clientId, String clientSecret, String authorizeUrl, String tokenUrl) {
        super(new SalesforceOAuth2Template(clientId, clientSecret, authorizeUrl, tokenUrl));
    }

    @Override
    public Salesforce getApi(String accessToken) {
        final String instanceUrl = INSTANCE_URL.get();
        final SalesforceTemplate template = null != instanceUrl ?
                new SalesforceTemplate(accessToken, instanceUrl) :
                new SalesforceTemplate(accessToken);
        return template;
    }
    
    public static final void setInstanceUrl(String instanceUrl) {
        INSTANCE_URL.set(instanceUrl);
    }
}
