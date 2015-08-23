package dk.rohdef.jerseyauth.jersey;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.container.ContainerRequestContext;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * License MIT
 *
 * @author Rohde Fischer
 */
public class AuthFilterTest {
    private AuthFilter authFilter;
    private ContainerRequestContext containerRequestContext;

    @Before
    public void setup() {
        authFilter = new AuthFilter();
        containerRequestContext = null;
    }

    @Test
    public void shouldSetSecurityContextOnValidSessions() throws IOException {
        // Encoded "fiktivus:Asteroid B-612"
        containerRequestContext = new ContainerRequestContextSpy("ZmlrdGl2dXM6QXN0ZXJvaWQgQi02MTI=");
        authFilter.filter(containerRequestContext);
        assertThat(containerRequestContext.getSecurityContext(), notNullValue());
        assertThat(containerRequestContext.getSecurityContext().getUserPrincipal().getName(),
                is("fiktivus"));

        // Encoded "realis:the lamplighter"
        containerRequestContext = new ContainerRequestContextSpy("cmVhbGlzOnRoZSBsYW1wbGlnaHRlcg==");
        authFilter.filter(containerRequestContext);
        assertThat(containerRequestContext.getSecurityContext(), notNullValue());
        assertThat(containerRequestContext.getSecurityContext().getUserPrincipal().getName(),
                is("realis"));
    }

    @Test
    public void shouldNotAuthorizeInvalidSessions() throws IOException {
        // Encoded "cask:strength"
        containerRequestContext = new ContainerRequestContextSpy("Y2FzazpzdHJlbmd0aA==");
        authFilter.filter(containerRequestContext);
        assertThat(containerRequestContext.getSecurityContext(), nullValue());

        // Encoded "realis:maximalis"
        containerRequestContext = new ContainerRequestContextSpy("cmVhbGlzOm1heGltYWxpcw==");
        authFilter.filter(containerRequestContext);
        assertThat(containerRequestContext.getSecurityContext(), nullValue());
    }

    @Test
    public void shouldNotAuthorizeOnStrangeContexts() throws IOException {
        containerRequestContext = new ContainerRequestContextSpy(null);
        authFilter.filter(containerRequestContext);
        assertThat(containerRequestContext.getSecurityContext(), nullValue());

        containerRequestContext = new ContainerRequestContextSpy("gibberish");
        authFilter.filter(containerRequestContext);
        assertThat(containerRequestContext.getSecurityContext(), nullValue());
    }
}
