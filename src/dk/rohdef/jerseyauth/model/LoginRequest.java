package dk.rohdef.jerseyauth.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * A request to be logged in, separated from the user to isolate username and password, which is the only needed
 * details, in a real world scenario separating will help prevent serialization issues and similar.
 *
 * License MIT
 * @author Rohde Fischer
 */
public class LoginRequest {
    private String username, password;

    public LoginRequest() {
    }

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
