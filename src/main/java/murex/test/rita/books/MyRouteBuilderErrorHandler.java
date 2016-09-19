package murex.test.rita.books;

import murex.test.rita.books.model.BOOK;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by RCPopescu on 9/12/2016.
 */
public class MyRouteBuilderErrorHandler {
    private static final long DEFAULT_COMPLETION_TIMEOUT = 100 * 1000L;

    public static void main(String[] args) {

        final Logger logger = LoggerFactory.getLogger(MyRouteBuilder.class);

        CamelContext camelContext = new DefaultCamelContext();
        camelContext.setTypeConverterStatisticsEnabled(true);

        try {
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    // this is just the generic error handler where we set the destination
                    errorHandler(deadLetterChannel("seda:queue:dead"));
                    from("file:booksxml?noop=true")
                            //.log("${body}")
                            .convertBodyTo(BOOK.class)
                            .convertBodyTo(Book.class)
                            .choice()
                                .when(simple("${in.header.CamelFileName} == 'xml1'"))

                            .to("book://Books")
                                    .to("seda:testxml1")
                                    .log("Book from xml1: ${body}")
                                .otherwise()
                                    .to("book://MyBooks")
                                    .log("All books except xml1: ${body}")
                            .end()
                            .log("object body = ${body}")
                            .process(new Processor() {
                                @Override
                                public void process(Exchange exchange) throws Exception {
                                    exchange.getIn().getHeaders();
                                    exchange.getIn().getBody();
                                }
                            })
                            .end();
                    ;

                    from("seda:testxml1")
                        .log("***Test*** ${body}")
                            .from("book://Books")
                                .log("###Test### ${body}")
                                .errorHandler(loggingErrorHandler("mylogger.name").level(LoggingLevel.DEBUG))
                    .to("seda:firstBook")
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
