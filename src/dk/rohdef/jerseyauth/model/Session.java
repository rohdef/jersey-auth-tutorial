package dk.rohdef.jerseyauth.model;

import java.security.Principal;

/**
 * Represents a valid user session. This is intended to be used after a valid login, to prevent password leaks and
 * similar nastiness. The general idea is given a valid login give an randomly generated token to grant access, the
 * token can be stored in a database along with for instance a timeout for how long it's valid. This will also enable
 * a logout feature to limit the effect of a token leak, and admin functions such as kicking a user.
 *
 * License MIT
 * @author Rohde Fischer
 */
public class Session implements Principal {
    private String name, token;

    /*
     * Many frameworks doing serialization/deserialization unfortunately requires this
     */
    public Session() {
    }

    public Session(String username, String token) {
        this.name = username;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String username) {
        this.name = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
