package dk.rohdef.jerseyauth.jersey;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * License MIT
 *
 * @author Rohde Fischer
 */
public class BasicAuthTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldGiveNullOnEmptyString() {
        assertThat("Expect null on bad input", BasicAuth.decode(""), nullValue());
    }

    @Test
    public void shouldGiveExceptionOnNull() {
        thrown.expect(NullPointerException.class);
        BasicAuth.decode(null);
    }

    @Test
    public void shouldGiveTwoElementEmptyStringArrayOnOnlyColon() {
        // Encoded "" as Base64
        String decodeString = "Og==";
        assertThat("Two elements is present", BasicAuth.decode(decodeString), is(arrayWithSize(2)));
        assertThat("First element is emptyString", BasicAuth.decode(decodeString)[0], isEmptyString());
        assertThat("Second element is emptyString", BasicAuth.decode(decodeString)[1], isEmptyString());
    }

    @Test
    public void shouldGiveSingleElementArrayOnNoColonString() {
        // Encoded "Burma!" as Base64
        String decodeString = "QnVybWEh";
        assertThat("Only one element is present", BasicAuth.decode(decodeString), is(arrayWithSize(1)));
        assertThat("Input string is contained", BasicAuth.decode(decodeString), is(hasItemInArray("Burma!")));
    }

    @Test
    public void shouldGiveTwoElementsWithOneColonString() {
        // Encoded "Crash Override:I love Acid Burn" as Base64
        String decodeString = "Q3Jhc2ggT3ZlcnJpZGU6SSBsb3ZlIEFjaWQgQnVybg==";
        assertThat("Two elements is present", BasicAuth.decode(decodeString), is(arrayWithSize(2)));
        assertThat("Username is contained", BasicAuth.decode(decodeString), is(hasItemInArray("Crash Override")));
        assertThat("Password is contained", BasicAuth.decode(decodeString), is(hasItemInArray("I love Acid Burn")));
        assertThat("Username is first", BasicAuth.decode(decodeString)[0], is("Crash Override"));
        assertThat("Password is last", BasicAuth.decode(decodeString)[1], is("I love Acid Burn"));
    }

    @Test
    public void shouldIgnoreMoreThanOneColon() {
        // Encoded "Sergeant Colon:owed thirty years of happy marriage to the fact that Mrs. Colon:worked all day and Sargent Colon worked all night." as Base64
        String decodeString = "U2VyZ2VhbnQgQ29sb246b3dlZCB0aGlydHkgeWVhcnMgb2YgaGFwcHkgbWFycmlhZ2UgdG8gdGhlIGZhY3QgdGhhdCBNcnMuIENvbG9uOndvcmtlZCBhbGwgZGF5IGFuZCBTYXJnZW50IENvbG9uIHdvcmtlZCBhbGwgbmlnaHQu";
        assertThat("Two elements is present", BasicAuth.decode(decodeString), is(arrayWithSize(2)));
        assertThat("Username is contained", BasicAuth.decode(decodeString), is(hasItemInArray("Sergeant Colon")));
        assertThat("Password is contained", BasicAuth.decode(decodeString), is(hasItemInArray("owed thirty years of happy marriage to the fact that Mrs. Colon:worked all day and Sargent Colon worked all night.")));

        // Encoded "They:communicated:by:means:of:notes.:They:had:three:grown-up:children,:all:born,:Vimes:had:assumed,:as:a:result:of:extremely:persuasive:handwriting." as Base64
        decodeString = "VGhleTpjb21tdW5pY2F0ZWQ6Ynk6bWVhbnM6b2Y6bm90ZXMuOlRoZXk6aGFkOnRocmVlOmdyb3duLXVwOmNoaWxkcmVuLDphbGw6Ym9ybiw6VmltZXM6aGFkOmFzc3VtZWQsOmFzOmE6cmVzdWx0Om9mOmV4dHJlbWVseTpwZXJzdWFzaXZlOmhhbmR3cml0aW5nLg==";
        assertThat("Two elements is present", BasicAuth.decode(decodeString), is(arrayWithSize(2)));
        assertThat("Username is contained", BasicAuth.decode(decodeString), is(hasItemInArray("They")));
        assertThat("Password is contained", BasicAuth.decode(decodeString), is(hasItemInArray("communicated:by:means:of:notes.:They:had:three:grown-up:children,:all:born,:Vimes:had:assumed,:as:a:result:of:extremely:persuasive:handwriting.")));
    }
}
