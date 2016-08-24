package murex.test.rita.books;

import dbr.ClassBinding;
import dbr.DiskBookRepo;
import murex.test.rita.books.model.BOOK;
import org.junit.*;
import org.junit.Test;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by RCPopescu on 8/10/2016.
 */
public class LibraryTest {

    @Inject
    @ClassBinding(value = BOOK.class)
    private JAXBContext context;


    @Test
    public void x() throws JAXBException {
        Marshaller marshaller = context.createMarshaller();
        BOOK b = new BOOK();
        b.setTITLE("Jurnalul pantofului rosu.");
        StringWriter s = new StringWriter(hashCode());
        marshaller.marshal(b, s);
        System.out.println(s);
    }

    Library library;
    //private File rootDir = new File("C:\\Users\\rcpopescu\\IdeaProjects\\Books\\booksxml");
    private List<Book> booksRepo = new ArrayList<Book>();
    ListMaker lm = new ListMaker();
    @Before
    public void setUp() throws Exception {

        booksRepo = lm.createBookList();
        library = new Library(booksRepo);

    }

    @Test
    public void testGetBooksOfGivenAuthor() throws Exception {
        List<Book> expected = new ArrayList<Book>();
        booksRepo = new ArrayList<Book>();
        Book b1 = new Book(Collections.asList("Joshua Bloch"), "Effective Java1", "1234567", "Self-Help");
        Book b2 = new Book(Collections.asList("Joshua Bloch"), "Effective Java2", "12345678", "Tutorial");
        Book b3 = new Book(Collections.asList("Joshua Bloch"), "Effective Java3", "123456789", "Tutorial");
        expected.add(b1);
        expected.add(b2);
        expected.add(b3);
        Assert.assertEquals(expected, library.getBooksOfGivenAuthor("Joshua Bloch"));


    }

    @Test
     public void testGetBooksFromCategory() throws Exception {

        List<Book> expected = new ArrayList<Book>();
        Book b1 = new Book(Collections.asList("Joshua Bloch"), "Effective Java1", "1234567", "Self-Help");
        Book b2 = new Book(Collections.asList("Robert C. Martin"), "agile software development1", "123", "Self-Help");
        Book b3 = new Book(Collections.asList("Robert C. Martin"), "agile software development2", "1234", "Self-Help");
        Book b4 = new Book(Collections.asList("Robert C. Martin"), "agile software development3", "12345", "Self-Help");
        Book b5 = new Book(Collections.asList("Kathy Sierra","Bert Bates"), "Head First Java2", "12345678912", "Self-Help");
        expected.add(b1);
        expected.add(b2);
        expected.add(b3);
        expected.add(b4);
        expected.add(b5);
        Assert.assertEquals(expected,library.getBooksFromCategory("Self-Help"));
    }

    @Test
    public void testGetBookByIsbn() {

        Book expectedBook = new Book(Collections.asList("Robert C. Martin"), "agile software development1", "123", "Self-Help");
        Assert.assertEquals(expectedBook, library.getBookByISBN("123"));
        Assert.assertEquals(expectedBook, library.getBookByISBN("123"));
        Assert.assertEquals(expectedBook, library.getBookByISBN("123"));
        Assert.assertEquals(expectedBook, library.getBookByISBN("123"));
    }

}