package dk.rohdef.jerseyauth.model;

/**
 * Representing a user in the system, here er very simple mock only having a username, password and a group. In a real
 * world example this would probably also include other user details. Feel free to expand according to your needs.
 *
 * For security reasons I do not recommend using this for verification after login, for that @see {dk.rohdef.jerseyauth.model.Session}
 * which is intended to have a username and some session ID to verify a valid login.
 *
 * License MIT
 * @author Rohde Fischer
 */
public class User {
    private String username, password;
    private Group group;

    /*
     * Many frameworks doing serialization/deserialization unfortunately requires this
     */
    public User() {
    }

    public User(String username, String password, Group group) {
        this.username = username;
        this.password = password;
        this.group = group;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
