package dk.rohdef.jerseyauth;

import dk.rohdef.jerseyauth.model.Group;
import dk.rohdef.jerseyauth.model.Session;
import dk.rohdef.jerseyauth.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * A mockup data access object for a login service. This only serves to provide quick and dirty access to some test
 * values. In a real world examle this would most likely be hooked up to a database, for instance with generated tokens,
 * timeouts and support for stronger verification schemes.
 *
 * License MIT
 * @author Rohde Fischer
 */
public class MockDao {
    private static MockDao instance = new MockDao();
    private List<User> users = new ArrayList<>();
    private List<Session> sessions = new ArrayList<>();

    private MockDao() {
        users.add(new User("fiktivus", "maximus", Group.ADMIN));
        users.add(new User("realis", "minimalis", Group.USER));

        sessions.add(new Session("fiktivus", "Asteroid B-612"));
        sessions.add(new Session("realis", "the lamplighter"));
    }

    public static MockDao getInstance() {
        return instance;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Session> getSessions() {
        return sessions;
    }
}
