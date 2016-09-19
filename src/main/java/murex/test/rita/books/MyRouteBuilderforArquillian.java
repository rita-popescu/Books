package murex.test.rita.books;

import murex.test.rita.books.model.BOOK;
import org.apache.camel.*;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.impl.DefaultCamelContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

/**
 * Created by RCPopescu on 9/13/2016.
 */
public class MyRouteBuilderforArquillian extends RouteBuilder{

    private static final Logger logger = LoggerFactory.getLogger(MyRouteBuilderforArquillian.class);

    CamelContext camelContext = new DefaultCamelContext();


    public void configure() throws Exception {
        // this is just the generic error handler where we set the destination
        //errorHandler(deadLetterChannel("seda:queue:dead"));

        from("file:booksxml?noop=true").autoStartup(false).routeId("ruta1")
                //.log("${body}")
                .convertBodyTo(BOOK.class)
                .convertBodyTo(Book.class)
                .choice()
                .when(simple("${body.category} == 'Self-Help'"))
                .to("seda:selfHelp")
                .log("Book with Self-Help category: ${body}")
                .otherwise()
                .to("seda:tutorial")
                .log("Book with Tutorial category: ${body}")
                .end();

        from("seda:selfHelp").routeId("ruta2")
                .log(LoggingLevel.INFO, "*****************  ${body} ");

        from("seda:tutorial").routeId("ruta3")
                .log(LoggingLevel.INFO, "*****************  ${body} ")
                .aggregate(simple("${body.category}"))
                .aggregationStrategy(new SetAggregationStrategy())
                .completionTimeout(100 * 1000L)
                .completionSize(4)
                .log(LoggingLevel.INFO, "***************** Body after aggregate: ${body}")
                .id("mockBookListAggregatedId")
                .to("book://bookListAggregated")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        //exchange.getIn().setHeader("listOfBooks",exchange.getIn().getBody());

                    }
                });
        from("book://bookListAggregated").routeId("ruta4")
                .split().body()
                .log(LoggingLevel.INFO, "***************** Body after split: ${body}")
                .to("book://BookListAfterSplit")
                .aggregate(simple("${body.category}"))
                .aggregationStrategy(new SetAggregationStrategy())
                .completionTimeout(100 * 1000L)
                .completionSize(4)
                .log(LoggingLevel.INFO, "***************** Body after second aggregate: ${body}")
                .id("secondMockBookListAggregatedId")
                .to("book://secondBookListAggregated")

        ;
        from("book://secondBookListAggregated").routeId("ruta5")
                .split().body()
                .log(LoggingLevel.INFO, "***************** Body after split: ${body}")
                .to("book://SecondBookListAfterSplit")
                .aggregate(simple("${body.category}"))
                .aggregationStrategy(new SetAggregationStrategy())
                .completionTimeout(100 * 1000L)
                .completionSize(4)
                .log(LoggingLevel.INFO, "***************** Body after second aggregate: ${body}")
                .id("thirdMockBookListAggregatedId")
                .to("book://thirdBookListAggregated");


    }

}
