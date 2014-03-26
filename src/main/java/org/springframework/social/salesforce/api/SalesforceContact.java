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
public class SalesforceContact extends SalesforceObject {

    // The account this contact belong to
    // Applications can use this information to create features that require a relation between the contact and acccount.
    private String accountId;

    // Personal info
    private String email;
    private String name;
    private String firstName;
    private String lastName;
    private String phone;
    private String mobilePhone;

    // Address information
    private String mailingStreet;
    private String mailingCity;
    private String mailingState;
    private String mailingPostalCode;
    private String mailingCountry;

    public String getAccountId() {
        return accountId;
    }

    @JsonProperty("AccountId")
    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    @JsonProperty("Email")
    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    @JsonProperty("Name")
    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("FirstName")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @JsonProperty("LastName")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMailingStreet() {
        return mailingStreet;
    }

    @JsonProperty("MailingStreet")
    public void setMailingStreet(String mailingStreet) {
        this.mailingStreet = mailingStreet;
    }

    public String getMailingCity() {
        return mailingCity;
    }

    @JsonProperty("MailingCity")
    public void setMailingCity(String mailingCity) {
        this.mailingCity = mailingCity;
    }

    public String getMailingState() {
        return mailingState;
    }

    @JsonProperty("MailingState")
    public void setMailingState(String mailingState) {
        this.mailingState = mailingState;
    }

    public String getMailingPostalCode() {
        return mailingPostalCode;
    }

    @JsonProperty("MailingPostalCode")
    public void setMailingPostalCode(String mailingPostalCode) {
        this.mailingPostalCode = mailingPostalCode;
    }

    public String getMailingCountry() {
        return mailingCountry;
    }

    @JsonProperty("MailingCountry")
    public void setMailingCountry(String mailingCountry) {
        this.mailingCountry = mailingCountry;
    }

    public String getPhone() {
        return phone;
    }

    @JsonProperty("Phone")
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    @JsonProperty("MobilePhone")
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }
}
