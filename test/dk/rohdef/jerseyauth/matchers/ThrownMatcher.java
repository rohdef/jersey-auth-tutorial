package dk.rohdef.jerseyauth.matchers;

import org.hamcrest.Description;
import org.hamcrest.Factory;
import org.hamcrest.Matcher;
import org.junit.internal.matchers.TypeSafeMatcher;

/**
 * User: Renato
 */
public class ThrownMatcher extends TypeSafeMatcher<Runnable> {

    private final String expected;
    private String actual;

    public ThrownMatcher( String s ) {
        expected = s;
    }

    @Factory
    public static Matcher<Runnable> thrown( Class<? extends Throwable> expected ) {
        return new ThrownMatcher( expected.getName() );
    }

    @Override
    public boolean matchesSafely( Runnable action ) {
        try {
            action.run();
            actual = "nothing";
            return false;
        } catch ( Throwable t ) {
            actual = t.getClass().getName();
            return actual.equals( expected );
        }
    }

    @Override
    public void describeTo( Description description ) {
        description.appendText( "Should have thrown " + expected + " but threw " + actual );
    }

}
