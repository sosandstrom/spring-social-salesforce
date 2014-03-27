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
    private String isClosed;

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


    public String getIsClosed() {
        return isClosed;
    }

    @JsonProperty("IsClosed")
    public void setIsClosed(String closed) {
        isClosed = closed;
    }

    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return String.format("Opportunity name: %s description: %s close date: %s isClosed: %s", name, description, closeDate, isClosed);
    }
}
