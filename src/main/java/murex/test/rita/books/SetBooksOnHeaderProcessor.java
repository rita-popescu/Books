package murex.test.rita.books;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

/**
 * Created by RCPopescu on 9/5/2016.
 */
public class SetBooksOnHeaderProcessor implements Processor {


    public void process(Exchange exchange)throws Exception{

        exchange.getIn().setHeader("listOfBooks",exchange.getIn().getBody());

    }

}
