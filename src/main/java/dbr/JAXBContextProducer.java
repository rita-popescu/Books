package dbr;

import murex.test.rita.books.model.BOOK;

import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * Created by RCPopescu on 8/22/2016.
 */

public class JAXBContextProducer {

    @Produces
    public JAXBContext produceJAXBContext(InjectionPoint ip)throws JAXBException {
        ClassBinding annotation = ip.getAnnotated().getAnnotation(ClassBinding.class);
        Class c = annotation.value();
        return JAXBContext.newInstance(c);
    }
}
