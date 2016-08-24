package dbr;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Created by RCPopescu on 8/23/2016.
 */
public class MarshallerProducer {
    @Produces
    public Marshaller produceMarshaller(InjectionPoint ip)throws JAXBException {
        ClassBinding annotation = ip.getAnnotated().getAnnotation(ClassBinding.class);
        Class c = annotation.value();
        return JAXBContext.newInstance(c).createMarshaller();
    }
}
