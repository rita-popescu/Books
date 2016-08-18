package murex.test.rita.books;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RCPopescu on 8/16/2016.
 */
public class Collections {
    public static <T> List<T> asList(T ... items) {
        List<T> list = new ArrayList<T>();
        for (T item : items) {
            list.add(item);
        }

        return list;
    }
}
