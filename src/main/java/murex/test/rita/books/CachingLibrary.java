package murex.test.rita.books;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RCPopescu on 8/11/2016.
 */
public class CachingLibrary extends Library {

    private final BooksCache cache;
    private static final Logger logger = LoggerFactory.getLogger(Library.class);
    private Book bookByISBN;
    //private int contor;
    //List<String> isbnList;
    public CachingLibrary(Iterable<Book> booksRepo, BooksCache cache) {
        super(booksRepo);
        this.cache = cache;
        //contor = 0;
        //isbnList = new ArrayList<>();
    }

    public Book getBookByISBN(String isbn) {
        if (cache.isCached(isbn)) {
            return cache.getCachedBook(isbn);
            //logger.info("Got from cache.");
        } else {
                bookByISBN = super.getBookByISBN(isbn);
                cache.putBookInHashMap(isbn, bookByISBN);

            //isbnList.add(contor,isbn);
            //contor++;

            //if(contor == 100){

                //String isbnToDelete = isbnList.get(0);
                //isbnList.remove(0);
                //cache.deleteBookFromHashMap(isbnToDelete);
            }
            logger.info("Got from disk.");
            return bookByISBN;
        }
}