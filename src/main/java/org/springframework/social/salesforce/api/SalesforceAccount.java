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
public class SalesforceAccount extends SalesforceObject {
    private String Name;
    private String Phone;
    private String BillingCity;
    private String BillingCountry;
    private String BillingPostalCode;
    private String BillingState;
    private String BillingStreet;
    private String ShippingCity;
    private String ShippingCountry;
    private String ShippingPostalCode;
    private String ShippingState;
    private String ShippingStreet;

    public String getName() {
        return Name;
    }

    @JsonProperty("Name")
    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPhone() {
        return Phone;
    }

    @JsonProperty("Phone")
    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getShippingStreet() {
        return ShippingStreet;
    }

    @JsonProperty("ShippingStreet")
    public void setShippingStreet(String ShippingStreet) {
        this.ShippingStreet = ShippingStreet;
    }

    public String getShippingCity() {
        return ShippingCity;
    }

    @JsonProperty("ShippingCity")
    public void setShippingCity(String ShippingCity) {
        this.ShippingCity = ShippingCity;
    }

    public String getShippingCountry() {
        return ShippingCountry;
    }

    @JsonProperty("ShippingCountry")
    public void setShippingCountry(String ShippingCountry) {
        this.ShippingCountry = ShippingCountry;
    }

    public String getShippingPostalCode() {
        return ShippingPostalCode;
    }

    @JsonProperty("ShippingPostalCode")
    public void setShippingPostalCode(String ShippingPostalCode) {
        this.ShippingPostalCode = ShippingPostalCode;
    }

    public String getShippingState() {
        return ShippingState;
    }

    @JsonProperty("ShippingState")
    public void setShippingState(String ShippingState) {
        this.ShippingState = ShippingState;
    }

    public String getBillingCity() {
        return BillingCity;
    }

    @JsonProperty("BillingCity")
    public void setBillingCity(String BillingCity) {
        this.BillingCity = BillingCity;
    }

    public String getBillingCountry() {
        return BillingCountry;
    }

    @JsonProperty("BillingCountry")
    public void setBillingCountry(String BillingCountry) {
        this.BillingCountry = BillingCountry;
    }

    public String getBillingPostalCode() {
        return BillingPostalCode;
    }

    @JsonProperty("BillingPostalCode")
    public void setBillingPostalCode(String BillingPostalCode) {
        this.BillingPostalCode = BillingPostalCode;
    }

    public String getBillingState() {
        return BillingState;
    }

    @JsonProperty("BillingState")
    public void setBillingState(String BillingState) {
        this.BillingState = BillingState;
    }

    public String getBillingStreet() {
        return BillingStreet;
    }

    @JsonProperty("BillingStreet")
    public void setBillingStreet(String BillingStreet) {
        this.BillingStreet = BillingStreet;
    }
    
    
}
