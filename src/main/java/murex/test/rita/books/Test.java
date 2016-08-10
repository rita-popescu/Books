package murex.test.rita.books;

import murex.test.rita.books.model.BOOK;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;

/**
 * Created by RCPopescu on 8/9/2016.
 */
public class Test {

    public static void main(String[] args) throws JAXBException {
        JAXBContext c = JAXBContext.newInstance(BOOK.class);
        Unmarshaller unmarshaller = c.createUnmarshaller();
        BOOK unmarshal = (BOOK)unmarshaller.unmarshal(new File("C:\\Users\\rcpopescu\\IdeaProjects\\Books\\bookxml"));
        System.out.println(unmarshal.getTITLE());

    }
}
