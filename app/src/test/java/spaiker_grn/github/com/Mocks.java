package spaiker_grn.github.com;



import org.junit.Assert;

import java.io.InputStream;

public class Mocks {

    public static InputStream sStream(final String string){

        Assert.assertNotNull("string null", string);
        final InputStream stream = Mocks.class.getClassLoader().getResourceAsStream(string);
        Assert.assertNotNull("stream resources not found", stream);
        return stream;
    }

}
