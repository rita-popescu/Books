package murex.test.rita.books;

import dbr.ClassBinding;
import dbr.JAXBContextProducer;
import dbr.MarshallerProducer;
import murex.test.rita.books.model.BOOK;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;

@RunWith(Arquillian.class)
public class TestDependencyInjection {

	@Inject
	@ClassBinding(BOOK.class)
	private JAXBContext context;

	@Inject
	@ClassBinding(BOOK.class)
	private Marshaller marshaller;

	@Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
				.addClass(JAXBContextProducer.class)
				.addClass(MarshallerProducer.class)
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

	@Test
	 public void x() throws JAXBException {

		BOOK b = new BOOK();
		b.setTITLE("Great expectations");
		StringWriter s = new StringWriter();
		marshaller.marshal(b, s);
		System.out.println(s);
	}



}
