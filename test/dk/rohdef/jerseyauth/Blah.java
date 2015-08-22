package dk.rohdef.jerseyauth;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * License MIT
 *
 * @author Rohde Fischer
 */
public class Blah {
    @Test
    public void fooBar() {
        assertThat("True is true", true);
    }

    @Test
    public void fail() {
        assertThat("No this fails", false);
    }
}
