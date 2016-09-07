package murex.test.rita.books;
import murex.test.rita.books.model.BOOK;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.dataformat.JaxbDataFormat;
import org.apache.camel.spi.DataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by RCPopescu on 9/5/2016.
 */
public class MyRouteBuilder {



    public static void main(String[] args) {

   final JaxbDataFormat jaxb = new JaxbDataFormat();
        final Logger logger = LoggerFactory.getLogger(MyRouteBuilder.class);
        jaxb.setContextPath("murex.test.rita.books.model");

        CamelContext camelContext = new DefaultCamelContext();
        try {
            camelContext.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("file:booksxml?noop=true")
                        .log("${body}")
                        .convertBodyTo(BOOK.class)
                        .log("${body}")
                        .convertBodyTo(Book.class)
                        .log("Body-category: ${body.category}")
                        .process(new Processor() {
                            @Override
                            public void process(Exchange exchange) throws Exception {
                                exchange.getIn().getHeaders();

                            }
                        })
                            .choice()
                                .when(simple("${body.category} == 'Self-Help'"))
                                    .log("Book with Self-Help category: ${body}")
                                .otherwise()
                                    .log("Book with Tutorial category: ${body}")
                            .end();

                }
            });

            camelContext.start();
            Thread.sleep(10000);
            camelContext.stop();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}


