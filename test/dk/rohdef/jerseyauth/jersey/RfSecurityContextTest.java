package dk.rohdef.jerseyauth.jersey;

import dk.rohdef.jerseyauth.model.Session;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.SecurityContext;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * License MIT
 *
 * @author Rohde Fischer
 */
public class RfSecurityContextTest {
    private RfSecurityContext adminSecurityContext, userSecurityContext, invalidSecurityContext;
    private Session adminLoginSession, userLoginSession;

    @Before
    public void setup() {
        adminLoginSession = new Session("fiktivus", "Asteroid B-612");
        userLoginSession = new Session("realis", "the lamplighter");

        adminSecurityContext = new RfSecurityContext(adminLoginSession);
        userSecurityContext = new RfSecurityContext(userLoginSession);
        invalidSecurityContext = new RfSecurityContext(null);
    }

    @Test
    public void shouldHaveUserPrincipalOnValidContext() {
        assertThat(adminSecurityContext.getUserPrincipal(), is(notNullValue()));
        assertThat("Principal name matches user name", adminSecurityContext.getUserPrincipal().getName(), is(adminLoginSession.getName()));

        assertThat(userSecurityContext.getUserPrincipal(), is(notNullValue()));
        assertThat("Principal name matches user name", userSecurityContext.getUserPrincipal().getName(), is(userLoginSession.getName()));
    }

    @Test
    public void shouldHaveAdminRoleOnAdminContext() {
        assertThat(adminSecurityContext.isUserInRole(""), is(false));
        assertThat(adminSecurityContext.isUserInRole("admin"), is(true));
        assertThat(adminSecurityContext.isUserInRole("user"), is(false));
        assertThat(adminSecurityContext.isUserInRole("superuser"), is(false));
        assertThat(adminSecurityContext.isUserInRole("god"), is(false));
        assertThat(adminSecurityContext.isUserInRole(null), is(false));
    }

    @Test
    public void shouldHaveUserRoleOnUserContext() {
        assertThat(userSecurityContext.isUserInRole(""), is(false));
        assertThat(userSecurityContext.isUserInRole("admin"), is(false));
        assertThat(userSecurityContext.isUserInRole("user"), is(true));
        assertThat(userSecurityContext.isUserInRole("master"), is(false));
        assertThat(userSecurityContext.isUserInRole("universe"), is(false));
        assertThat(userSecurityContext.isUserInRole(null), is(false));
    }

    @Test
    public void shouldNotHaveRolesOnInvalidContext() {
        assertThat(invalidSecurityContext.isUserInRole(""), is(false));
        assertThat(invalidSecurityContext.isUserInRole("admin"), is(false));
        assertThat(invalidSecurityContext.isUserInRole("user"), is(false));
        assertThat(invalidSecurityContext.isUserInRole("magic"), is(false));
        assertThat(invalidSecurityContext.isUserInRole("root"), is(false));
        assertThat(invalidSecurityContext.isUserInRole(null), is(false));
    }

    @Test
    public void shouldNotHavePrincipalOnInvalidContext() {
        assertThat(invalidSecurityContext.getUserPrincipal(), nullValue());
    }

    @Test
    public void shouldAlwaysBeNotSecure() {
        // Due to this being a simple example, please do encrypt on your own system
        assertThat(adminSecurityContext.isSecure(), is(false));
        assertThat(userSecurityContext.isSecure(), is(false));
        assertThat(invalidSecurityContext.isSecure(), is(false));
    }

    @Test
    public void shouldUseBasicAuth() {
        // Due to configuration in this example
        assertThat(SecurityContext.BASIC_AUTH, is(adminSecurityContext.getAuthenticationScheme()));
        assertThat(SecurityContext.BASIC_AUTH, is(userSecurityContext.getAuthenticationScheme()));
        assertThat(SecurityContext.BASIC_AUTH, is(invalidSecurityContext.getAuthenticationScheme()));
    }
}
