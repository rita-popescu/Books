package murex.test.rita.books;

import murex.test.rita.books.model.BOOK;

/**
 * Created by RCPopescu on 9/5/2016.
 */
public class MyTransformer {
    public String transformMethod(BOOK body){
        String category = body.getCATEGORY();
        return category;
    }
}
