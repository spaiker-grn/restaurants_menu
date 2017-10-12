package spaiker_grn.github.com.MockStream;



import org.junit.Assert;

import java.io.InputStream;

public final class MockStream {

    public static InputStream sStream(final String string){

        Assert.assertNotNull("string null", string);
        final InputStream stream = MockStream.class.getClassLoader().getResourceAsStream(string);
        Assert.assertNotNull("stream resources not found", stream);
        return stream;
    }

}
