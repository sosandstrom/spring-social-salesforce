/*
 * INSERT COPYRIGHT HERE
 */

package org.springframework.social.salesforce.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;
import org.springframework.social.salesforce.api.Salesforce;
import org.springframework.social.salesforce.api.SalesforceProfile;

/**
 *
 * @author sosandstrom
 */
public class SalesforceAdapter implements ApiAdapter<Salesforce> {

    public SalesforceAdapter() {
    }

    @Override
    public boolean test(Salesforce a) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setConnectionValues(Salesforce salesforce, ConnectionValues cv) {
        SalesforceProfile profile = salesforce.basicOperations().getUserProfile();
        cv.setDisplayName(profile.getName());
        cv.setProviderUserId(profile.getId());
    }

    @Override
    public UserProfile fetchUserProfile(Salesforce salesforce) {
        SalesforceProfile profile = salesforce.basicOperations().getUserProfile();
        final UserProfileBuilder BUILDER = new UserProfileBuilder();
        return BUILDER
                .setUsername(profile.getId())
                .setEmail(profile.getEmail())
                .setFirstName(profile.getFirstName())
                .setLastName(profile.getLastName())
                .setName(profile.getName())
                .build();
    }

    @Override
    public void updateStatus(Salesforce a, String string) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
