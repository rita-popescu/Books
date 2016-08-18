package murex.test.rita.books;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RCPopescu on 8/18/2016.
 */
public class ListMaker {
    private List<Book> booksRepo = new ArrayList<Book>();
    public List<Book> createBookList(){

        Book b1 = new Book(Collections.asList("Joshua Bloch"), "Effective Java1", "1234567", "Self-Help");
        Book b2 = new Book(Collections.asList("Joshua Bloch"), "Effective Java2", "12345678", "Tutorial");
        Book b3 = new Book(Collections.asList("Joshua Bloch"), "Effective Java3", "123456789", "Tutorial");
        Book b4 = new Book(Collections.asList("Robert C. Martin"), "agile software development1", "123", "Self-Help");
        Book b5 = new Book(Collections.asList("Robert C. Martin"), "agile software development2", "1234", "Self-Help");
        Book b6 = new Book(Collections.asList("Robert C. Martin"), "agile software development3", "12345", "Self-Help");
        Book b7 = new Book(Collections.asList("Kathy Sierra","Bert Bates"), "Head First Java2", "12345678912", "Self-Help");
        booksRepo.add(b1);
        booksRepo.add(b2);
        booksRepo.add(b3);
        booksRepo.add(b4);
        booksRepo.add(b5);
        booksRepo.add(b6);
        booksRepo.add(b7);
        return booksRepo;
    }


}
