package murex.test.rita.books;


import cucumber.api.java.After;
import junit.framework.Assert;
import org.apache.camel.*;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.cdi.CdiCamelExtension;
import org.apache.camel.cdi.Uri;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.model.ModelCamelContext;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Filters;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by RCPopescu on 9/13/2016.
 */
@RunWith(Arquillian.class)
public class ArquillianTesting {

    ArrayList<Book> expectedBooksRepo = new ArrayList<Book>();

    private static final Logger logger = LoggerFactory.getLogger(MyRouteBuilderforArquillian.class);

    @Inject
    @Uri("direct:bookListAggregated")
    ProducerTemplate mainIn;

    @Inject
    @Uri("mock:ruta3")
    private MockEndpoint mockBookListAggregated;

    @Inject
    @Uri("mock:ruta4")
    private MockEndpoint secondMockBookListAggregated;

    @Inject
    private CamelContext camelcontext;

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(murex.test.rita.books.model.BOOK.class.getPackage())
                .addPackage(CdiCamelExtension.class.getPackage())
                .addPackages(true, Filters.exclude(".*Test.*"), MyRouteBuilderforArquillian.class.getPackage())
                .addPackage(Book.class.getPackage())
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }
    private void weaveRoutes(CamelContext camelcontext) throws Exception {

        camelcontext.getRouteDefinition("ruta3").adviceWith((ModelCamelContext)camelcontext, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {

                weaveById("mockBookListAggregatedId").after().to(mockBookListAggregated);

            }
        });
        camelcontext.getRouteDefinition("ruta4").adviceWith((ModelCamelContext)camelcontext, new AdviceWithRouteBuilder() {
            @Override
            public void configure() throws Exception {

                replaceFromWith(mainIn.getDefaultEndpoint());
                weaveById("secondMockBookListAggregatedId").before().to(secondMockBookListAggregated);
            }
        });
    }
    @Before
    public void setUp() throws Exception {


        //Thread.sleep(10000);

        try {
            //J-
            weaveRoutes(camelcontext);
            //J+



        } catch (Exception cause) {
            throw new RuntimeException(cause);
        }
        if(camelcontext.getStatus().isStarted()){
            return;
        }

        else{
            camelcontext.start();
        }

    }
    @After
    public void after()throws Exception{
        camelcontext.stop();
    }


    @Test
    public void verifyReceivedMessage2()throws InterruptedException{

        Book b1 = new Book(Collections.asList("Robert C. Martin"), "agile software development4", "123456", "Tutorial");
        Book b2 = new Book(Collections.asList("Joshua Bloch"), "Effective Java2", "12345678", "Tutorial");
        Book b3 = new Book(Collections.asList("Joshua Bloch"), "Effective Java3", "123456789", "Tutorial");
        Book b4 = new Book(Collections.asList("Kathy Sierra","Bert Bates"), "Head First Java1", "1234567891", "Tutorial");
        expectedBooksRepo.add(b1);
        expectedBooksRepo.add(b2);
        expectedBooksRepo.add(b3);
        expectedBooksRepo.add(b4);


        mainIn.sendBody(expectedBooksRepo);


        secondMockBookListAggregated.setExpectedCount(1);
        secondMockBookListAggregated.assertIsSatisfied(20*1000L);
        ArrayList<Book> actualExchange = (ArrayList<Book>)secondMockBookListAggregated.getExchanges().get(0).getIn().getBody(List.class);


        assertEquals(true, camelcontext.getStatus().isStarted());

        org.junit.Assert.assertEquals(expectedBooksRepo, actualExchange);

    }

}
