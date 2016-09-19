package murex.test.rita.books;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;

import java.util.Map;

/**
 * Created by RCPopescu on 9/9/2016.
 */
public class BookComponent extends DefaultComponent {


    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        Endpoint endpoint = new BookEndpoint(uri, this);
        setProperties(endpoint, parameters);
        return endpoint;
    }
}
