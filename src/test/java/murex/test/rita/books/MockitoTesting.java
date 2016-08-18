package murex.test.rita.books;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RCPopescu on 8/18/2016.
 */
public class MockitoTesting {
    Library library;
    private List<Book> booksRepo = new ArrayList<Book>();
    ListMaker lm = new ListMaker();
    private Book expectedBook,actualBook;
    @Before
    public void setUp() throws Exception {
        actualBook = new Book(Collections.asList("Robert C. Martin"), "agile software development1", "123", "Self-Help");
        expectedBook = new Book(Collections.asList("Robert C. Martin"), "agile software development1", "123", "Self-Help");

        booksRepo = lm.createBookList();
        library = new Library(booksRepo);

    }
    @Test
    public void testBooksHaveEqualIsbns(){
        // create mock
        CachingLibrary cl = mock(CachingLibrary.class);

        // define return value for method getBookByISBN
        when(cl.getBookByISBN("123")).thenReturn(expectedBook);

        // use mock in test
        Assert.assertEquals(expectedBook,actualBook);

    }
    @Test
    public void testBooksHaveDifferentIsbns(){
        //same values but different memory references
        CachingLibrary cl = mock(CachingLibrary.class);
        when(cl.getBookByISBN("123")).thenReturn(expectedBook);
        Assert.assertNotSame(expectedBook, actualBook);

    }
    @Test
    public void testCachedBooksAreEqual(){

        BooksCache cl = mock(BooksCache.class);
        when(cl.getCachedBook("123")).thenReturn(expectedBook);
        Assert.assertEquals(expectedBook,actualBook);

    }
}
