package org.springframework.social.salesforce.api;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * Implements a representation of a SalesForce opportunity
 * @author mattiaslevin
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesforceOpportunity extends SalesforceObject {

    public enum Status {
        OPEN, CLOSED, ALL
    }

    private String name;
    private String description;
    private String closeDate;
    private Boolean isClosed;

    public String getCloseDate() {
        return closeDate;
    }

    @JsonProperty("CloseDate")
    public void setCloseDate(String closeDate) {
        this.closeDate = closeDate;
    }

    public String getDescription() {
        return description;
    }

    @JsonProperty("Description")
    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsClosed() {
        return isClosed;
    }

    @JsonProperty("IsClose")
    public void setIsClosed(Boolean closed) {
        isClosed = closed;
    }

    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }
}
