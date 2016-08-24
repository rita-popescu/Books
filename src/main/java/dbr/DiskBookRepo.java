package dbr;

import murex.test.rita.books.Book;
import murex.test.rita.books.model.BOOK;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by RCPopescu on 8/11/2016.
 */
public class DiskBookRepo implements Iterable<Book> {

    private File rootDir;

    public DiskBookRepo(File rootDir)throws JAXBException{

        this.rootDir = rootDir;
    }

    public Iterator<Book> iterator(){
        try {
            return new BOOKIterator(rootDir);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    private static class BOOKIterator implements Iterator<Book> {



        private final File[] files;
        private int index;
        private JAXBContext c;
        private Unmarshaller unmarshaller;

        public BOOKIterator(File rootDir) throws JAXBException{

            files = rootDir.listFiles();
            c = JAXBContext.newInstance(BOOK.class);
            unmarshaller = c.createUnmarshaller();
            index = 0;
        }

        public boolean hasNext() {
            return index < files.length;
        }

        public Book next(){

            BOOK xmlBook = null;
            try {
                xmlBook = (BOOK) unmarshaller.unmarshal(new File(String.valueOf(files[index++])));
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }

            return convert(xmlBook);

        }

        private Book convert(BOOK xmlBook) {
            List<String> xmlAuthors = xmlBook.getAUTHORS().getAUTHOR();
            Book book = new Book(new ArrayList<String>(xmlAuthors), xmlBook.getTITLE(), xmlBook.getISBN(), xmlBook.getCATEGORY());
            return book;
        }


        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
