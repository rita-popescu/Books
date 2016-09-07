package murex.test.rita.books;

import org.apache.camel.Exchange;

/**
 * Created by RCPopescu on 9/5/2016.
 */
public class MyLogProcessor {
    Exchange ex;
    public void process(Exchange exchange)throws Exception{
        System.out.println("Now processing the string:"+exchange.getIn().getBody(String.class));
    }

}
