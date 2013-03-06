/*
 * INSERT COPYRIGHT HERE
 */

package org.springframework.social.salesforce.api;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author sosandstrom
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesforceEmailServicesAddress extends SalesforceObject {
    private String EmailDomainName;

    public String getEmailDomainName() {
        return EmailDomainName;
    }

    @JsonProperty("EmailDomainName")
    public void setEmailDomainName(String EmailDomainName) {
        this.EmailDomainName = EmailDomainName;
    }

}
