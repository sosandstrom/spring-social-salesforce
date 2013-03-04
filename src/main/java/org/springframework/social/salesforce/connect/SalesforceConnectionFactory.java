/*
 * INSERT COPYRIGHT HERE
 */

package org.springframework.social.salesforce.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.OAuth2ServiceProvider;
import org.springframework.social.salesforce.api.Salesforce;

/**
 *
 * @author sosandstrom
 */
public class SalesforceConnectionFactory extends OAuth2ConnectionFactory<Salesforce> {
    
    public static final String PROVIDER_ID = "salesforce";

    public SalesforceConnectionFactory(String consumerKey, String consumerSecret) {
        super(PROVIDER_ID, new SalesforceServiceProvider(consumerKey, consumerSecret), 
                new SalesforceAdapter());
    }

}
