package dk.rohdef.jerseyauth.jersey;

import dk.rohdef.jerseyauth.MockDao;
import dk.rohdef.jerseyauth.model.Session;
import dk.rohdef.jerseyauth.model.User;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

/**
 * Simple SecurityContext, it's used for the framework to validate the users access. In this case we only really use
 * the isUserInRole, but using this rather sofisticated schemes can be employed. For instance you could in stead of
 * asking if the user is in a group ask for a certain type of permission such as "edit-products" and then ask the
 * database if the user or one of the users groups grants that permission.
 *
 * License MIT
 * @author Rohde Fischer
 */
public class RfSecurityContext implements SecurityContext {
    private Session loginSession;

    public RfSecurityContext(Session loginSession) {
        this.loginSession = loginSession;
    }

    // Returns the session if the user is logged on
    @Override
    public Principal getUserPrincipal() {
        if (loginSession == null)
            return null;

        return loginSession;
    }

    // Checks if the role and the users group is the same
    @Override
    public boolean isUserInRole(String role) {
        if (loginSession == null) return false;

        MockDao dao = MockDao.getInstance();

        for (User user : dao.getUsers()) {
            if (user.getUsername().equals(loginSession.getName())) {
                // In our simplification each user only has one group
                return user.getGroup().name().equalsIgnoreCase(role);
            }
        }

        return false;
    }

    // Simple check for if the request is done via a secure channel such as https
    @Override
    public boolean isSecure() {
        return false;
    }

    // Just basic auth here, you can look into the others, if interested
    @Override
    public String getAuthenticationScheme(){
        return this.BASIC_AUTH;
    }
}