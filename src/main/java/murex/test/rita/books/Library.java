package murex.test.rita.books;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RCPopescu on 8/9/2016.
 */
public class Library {
    private static final Logger logger = LoggerFactory.getLogger(Library.class);

    private final Iterable<Book> booksRepo;

    public Library(Iterable<Book> booksRepo) {
        this.booksRepo = booksRepo;
    }


    public List<Book> getBooksOfGivenAuthor(String author) {
        List<Book> bookList = new ArrayList<Book>();
        for (Book book : booksRepo) {
            for (String currentBookAuthor : book.getAuthors()) {
                if (author.equals(currentBookAuthor)) {
                    bookList.add(book);
                }
            }
        }
        return bookList;
    }

    public List<Book> getBooksFromCategory(String category) {
        List<Book> bookList = new ArrayList<Book>();
        for (Book book : booksRepo) {
            if (book.getCategory().equals(category)) {
                bookList.add(book);
            }
        }
        return bookList;
    }

    public Book getBookByISBN(String isbn) {
        for (Book book : booksRepo) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }
}




