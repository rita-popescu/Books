package murex.test.rita.books;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by RCPopescu on 8/17/2016.
 */
public class LimitedCache extends BooksCache {

    public static <K, V> Map<K, V> createLeastRecentlyUsedMap(final int maxEntries) {
        return new LinkedHashMap<K, V>(maxEntries*10/7, 0.7f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > maxEntries;
            }
        };
    }
}
