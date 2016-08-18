package murex.test.rita.books;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by RCPopescu on 8/12/2016.
 */
public class Book {
    private List<String> authors;
    private String title;
    private String isbn;
    private String category;
    private int price;

    public Book(List<String> authors, String title, String isbn, String category){
        this.authors = authors;
        this.title = title;
        this.isbn = isbn;
        this.category = category;
    }
    public List<String> getAuthors() {
        if (authors == null) {
            authors = new ArrayList<String>();
        }
        return this.authors;
    }
    public String getTitle() {
        return title;
    }
    public String getIsbn() {
        return isbn;
    }
    public String getCategory() {
        return category;
    }
    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

    @Override
    public String toString() {
        return "Book{" +
                "authors=" + authors +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                '}';
    }
}
