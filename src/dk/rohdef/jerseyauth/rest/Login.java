package dk.rohdef.jerseyauth.rest;

import dk.rohdef.jerseyauth.MockDao;
import dk.rohdef.jerseyauth.model.*;

import javax.annotation.security.PermitAll;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * A simple login service as proof of concept. In a real world example this would query a database, ldap or another
 * service to validate the user. This one by design returns a session token to prevent passing along the password more
 * than necessary.
 *
 * WARNINE!!! This setup is insecure since no encryption is being used. Passwords should NEVER! be passed along an
 * un-encrypted line. Encryption of a web service though is beyond the scope of this guide, look up how to use SSL or
 * similar on your web service.
 *
 * License MIT
 * @author Rohde Fischer
 */
@Path("/auth")
@PermitAll
public class Login {
    /**
     * Used to log the user in, in a real world example this would check against a database, ldap or some other way of
     * validating the user, and creating a validation token.
     *
     * @param loginRequest
     * @return if the login is successful a session containing the login token, null otherwise.
     */
    @POST
    @Path("/login")
    public Session login(LoginRequest loginRequest) {
        MockDao dao = MockDao.getInstance();

        // Clumsy, I know, remember, this is just a quick and dirty example
        for (User user : dao.getUsers()) {
            if (user.getUsername().equals(loginRequest.getUsername()) &&
                    user.getPassword().equals(loginRequest.getPassword())) {
                for (Session session : dao.getSessions()) {
                    if (session.getName().equals(loginRequest.getUsername())) {
                        return session;
                    }
                }
            }
        }

        return null;
    }

    /**
     * Here just a dummy method, would handle revoking the token in a real world scenario.
     * @param session
     */
    @POST
    @Path("/logout")
    public void logut(Session session) {

    }
}
