package murex.test.rita.books;

import murex.test.rita.books.BookComponent;
import murex.test.rita.books.BookConsumer;
import murex.test.rita.books.BookProducer;
import org.apache.camel.Consumer;
import org.apache.camel.Processor;
import org.apache.camel.Producer;
import org.apache.camel.impl.DefaultEndpoint;

/**
 * Created by RCPopescu on 9/9/2016.
 */
public class BookEndpoint extends DefaultEndpoint {

    public BookEndpoint(){

    }
    public BookEndpoint(String uri){
        super(uri);
    }
    public BookEndpoint(String uri, BookComponent component) {
        super(uri, component);
    }

    public Producer createProducer() throws Exception {
        return new BookProducer(this);
        //throw new RuntimeCamelException("Cannot produce to a BookEndpoint: " + getEndpointUri());
    }

    public Consumer createConsumer(Processor processor) throws Exception {
        BookConsumer consumer = new BookConsumer(this, processor);
        //configureConsumer(consumer);
        return consumer;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
