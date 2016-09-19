package murex.test.rita.books;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultConsumer;

import java.util.Date;

/**
 * Created by RCPopescu on 9/9/2016.
 */
public class BookConsumer extends DefaultConsumer{

    private final BookEndpoint endpoint;

    public BookConsumer(BookEndpoint endpoint, Processor processor){

        super(endpoint, processor);
        this.endpoint = endpoint;
    }


    protected void consume() throws Exception {

        Exchange exchange = endpoint.createExchange();

        // create a message body
        exchange.getIn().setBody(changeToUpperCase());
        exchange.getIn().setHeader("autor_preferat","Robert C. Martin");

        try {
            // send message to next processor in the route
            getProcessor().process(exchange);

        } finally {
            // log exception if an exception occurred and was not handled
            if (exchange.getException() != null) {
                getExceptionHandler().handleException(
                        "Error processing exchange", exchange,
                        exchange.getException());
            }
        }
    }

    private String changeToUpperCase(){
        Date now = new Date();
        return "******************************The time is "+ now;
    }

}
