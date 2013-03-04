/*
 * INSERT COPYRIGHT HERE
 */

package org.springframework.social.salesforce.api;

import org.codehaus.jackson.annotate.JsonProperty;

/**
 *
 * @author sosandstrom
 */
public abstract class SalesforceObject {
    private String id;
    private SalesforceAttributes attributes;

    public String getId() {
        return id;
    }

    @JsonProperty("Id")
    public void setId(String id) {
        this.id = id;
    }

    public SalesforceAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(SalesforceAttributes attributes) {
        this.attributes = attributes;
    }

}
