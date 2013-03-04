/*
 * INSERT COPYRIGHT HERE
 */

package org.springframework.social.salesforce.connect;

import org.springframework.social.oauth2.OAuth2Template;

/**
 *
 * @author sosandstrom
 */
class SalesforceOAuth2Template extends OAuth2Template {

    public SalesforceOAuth2Template(String consumerKey, String consumerSecret, String authorizeUrl, String tokenUrl) {
        this(consumerKey, consumerSecret, authorizeUrl, null, tokenUrl);
    }

    public SalesforceOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String authenticateUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, authenticateUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }

}
