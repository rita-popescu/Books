package murex.test.rita.books;

import org.junit.*;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by RCPopescu on 8/10/2016.
 */
public class LibraryTest {
    Library library;

    @Before
    public void setUp() throws Exception {
        library = new Library();
    }

    @Test
    public void testGetTitlesOfGivenAuthor() throws Exception {
        List<String> expected = Arrays.asList("Effective Java1", "Effective Java2", "Effective Java3");
        Assert.assertEquals(expected, library.getTitlesOfGivenAuthor("Joshua Bloch"));

    }
    @Test
     public void testGetTitlesFromCategory() throws Exception {
        List<String> expected = Arrays.asList("agile software development1", "agile software development2", "agile software development3", "Effective Java1", "Head First Java2");
        Assert.assertEquals(expected, library.getTitlesFromCategory("Self-Help"));
    }
    @Test
    public void testGetTitleByIsbn() throws Exception {
        String expected = "agile software development4";
        Assert.assertEquals(expected, library.getTitleByISBN("123456"));
        Assert.assertEquals(expected, library.getTitleByISBN("123456"));
    }
}