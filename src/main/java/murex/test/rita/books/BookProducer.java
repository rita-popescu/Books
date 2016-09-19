package murex.test.rita.books;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;

import org.slf4j.LoggerFactory;


/**
 * Created by RCPopescu on 9/9/2016.
 */
public class BookProducer extends DefaultProducer{

   // private static final transient  Log LOG = LogFactory.getLog(BookProducer.class);
    final org.slf4j.Logger logger = LoggerFactory.getLogger(BookProducer.class);

    private BookEndpoint endpoint;

    public BookProducer(BookEndpoint endpoint){

        super(endpoint);
        this.endpoint=endpoint;
    }

    public void process(Exchange exchange)throws Exception{

        System.out.println("*****************************"+exchange.getIn().getBody());
       // exchange.getIn().setHeader("autor_preferat","Robert C. Martin");
    }

}
