package dk.rohdef.jerseyauth.jersey;

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
public class RfSecurityContextTest {
    private RfSecurityContext securityContext;
    private Session loginSession;

    @Before
    public void setup() {
        loginSession = new Session("user", "token");
        securityContext = new RfSecurityContext(loginSession);
    }

    @Test
    public void shouldHaveUserPrincipal() {
        assertThat("Has user principal", securityContext.getUserPrincipal(), is(notNullValue()));
    }
}
