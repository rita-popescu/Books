package murex.test.rita.books;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by RCPopescu on 8/11/2016.
 */
public class BooksCache {
    LimitedCache lc = new LimitedCache();
    private Map<String, Book> hm;
    private static final Logger logger = LoggerFactory.getLogger(Library.class);

    public BooksCache() {
        hm = lc.createLeastRecentlyUsedMap(100);

    }

    public boolean isCached(String isbn){
        if (hm.containsKey(isbn))
            return true;
        return false;
    }

    public Book getCachedBook(String isbn) {
        Book book;
        if (isCached(isbn)) {
            book = hm.get(isbn);
            return book;
            //logger.info("Got from cache.");
        }

        return null;
    }
    public void putBookInHashMap(String isbn, Book book){

        hm.put(isbn,book);
    }


}
