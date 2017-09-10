import org.junit.Test;
import org.hamcrest.MatcherAssert;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertTrue;

/**
 * Created by Ameer on 9/10/17.
 */

public class ExampleTest {

    private String HELLOWORLD = "Hello World!";

    @Test
    public void sampleCheckString(){
        assertThat(HELLOWORLD, containsString("Hello"));
    }

    @Test
    public void alwaysTrue() {
        assertTrue(true);
    }
}
