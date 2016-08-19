package murex.test.rita.books;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by RCPopescu on 8/17/2016.
 */
public class LimitedCache extends BooksCache {

    public static <String, Book> Map<String, Book> createLeastRecentlyUsedMap(final int maxEntries) {
        return new LinkedHashMap<String, Book>(maxEntries*10/7, 0.7f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, Book> eldest) {
                return size() > maxEntries;
            }
        };
    }
}
