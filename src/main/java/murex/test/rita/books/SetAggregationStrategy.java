package murex.test.rita.books;

import murex.test.rita.books.model.BOOK;
import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by RCPopescu on 9/6/2016.
 */
public class SetAggregationStrategy implements AggregationStrategy{

  private static final Logger LOGGER = LoggerFactory.getLogger(SetAggregationStrategy.class);

    @Override
    public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
        if (newExchange == null) {
            return oldExchange;
        }

        final Book book = (Book) newExchange.getIn().getBody();
        LOGGER.debug("Try to aggregate books by category [{}]");

        if(oldExchange == null) {
            oldExchange = newExchange;
            oldExchange.getIn().setBody(new ArrayList<Book>());
        }

        final ArrayList<Book> list = (ArrayList<Book>) oldExchange.getIn().getBody(ArrayList.class);
        list.add(book);

        return oldExchange;
    }
}
