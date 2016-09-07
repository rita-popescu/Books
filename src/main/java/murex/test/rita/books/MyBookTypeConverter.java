package murex.test.rita.books;

import murex.test.rita.books.model.BOOK;
import org.apache.camel.Converter;
import org.apache.camel.Exchange;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RCPopescu on 9/6/2016.
 */
@Converter
 public class MyBookTypeConverter{

    @Converter
    public Book convertTo(BOOK xmlBook, Exchange exchange){
        List<String> xmlAuthors = xmlBook.getAUTHORS().getAUTHOR();
        Book book = new Book(new ArrayList<String>(xmlAuthors), xmlBook.getTITLE(), xmlBook.getISBN(), xmlBook.getCATEGORY());
        return book;
    }
}
