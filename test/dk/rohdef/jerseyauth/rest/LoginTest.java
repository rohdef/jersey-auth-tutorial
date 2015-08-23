package dk.rohdef.jerseyauth.rest;

import dk.rohdef.jerseyauth.model.LoginRequest;
import dk.rohdef.jerseyauth.model.Session;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * License MIT
 *
 * @author Rohde Fischer
 */
public class LoginTest {
    private Login loginService;
    private LoginRequest adminRequest, userRequest;

    @Before
    public void setUp() {
        loginService = new Login();
        adminRequest = new LoginRequest("fiktivus", "maximus");
        userRequest = new LoginRequest("realis", "minimalis");
    }

    @Test
    public void shouldLogValidAdminIn() {
        assertThat(loginService.login(adminRequest), notNullValue());
        assertThat(loginService.login(adminRequest).getName(), is("fiktivus"));
        assertThat(loginService.login(adminRequest).getToken(), is("Asteroid B-612"));
    }

    @Test
    public void shouldLogValidUserIn() {
        assertThat(loginService.login(userRequest), notNullValue());
        assertThat(loginService.login(userRequest).getName(), is("realis"));
        assertThat(loginService.login(userRequest).getToken(), is("the lamplighter"));
    }

    @Test
    public void shouldNotLogInvalidUserIn() {
        LoginRequest fakeRequest;

        fakeRequest = new LoginRequest("", "");
        assertThat(loginService.login(fakeRequest), nullValue());

        fakeRequest = new LoginRequest(null, null);
        assertThat(loginService.login(fakeRequest), nullValue());

        fakeRequest = new LoginRequest("su", "master");
        assertThat(loginService.login(fakeRequest), nullValue());

        fakeRequest = new LoginRequest("admin", "password");
        assertThat(loginService.login(fakeRequest), nullValue());
    }

    @Test
    public void shouldDoNothingOnLogout() {
        loginService.logut(new Session("user", "token"));
        loginService.logut(null);
        assertThat("No exceptions was thrown", true);
    }
}
