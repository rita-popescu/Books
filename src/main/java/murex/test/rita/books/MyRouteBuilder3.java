package murex.test.rita.books;


import murex.test.rita.books.model.BOOK;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by RCPopescu on 9/7/2016.
 */
public class MyRouteBuilder3 {
    private static final long DEFAULT_COMPLETION_TIMEOUT = 100 * 1000L;

    public static void main(String[] args) {

        final Logger logger = LoggerFactory.getLogger(MyRouteBuilder.class);

        CamelContext camelContext = new DefaultCamelContext();
        camelContext.setTypeConverterStatisticsEnabled(true);

        try {
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("file:booksxml?noop=true")
                            .log("${body}")
                            .convertBodyTo(BOOK.class)
                            .log("${body}")
                            .convertBodyTo(Book.class)
                            .log("object body = ${body}")
                            .process(new Processor() {
                                @Override
                                public void process(Exchange exchange) throws Exception {
                                    exchange.getIn().getHeaders();
                                    exchange.getIn().getBody();
                                }
                            })

                            .aggregate(simple("${body.category}"))
                            .aggregationStrategy(new SetAggregationStrategy())
                            .completionTimeout(DEFAULT_COMPLETION_TIMEOUT)
                            .completionSize(2)
                            //.to("file:selfhelp")
                            .log("---------- ${body}")
                    ;


                }
            });

            camelContext.start();
            Thread.sleep(10000);
            camelContext.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
