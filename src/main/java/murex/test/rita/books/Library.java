package murex.test.rita.books;

import murex.test.rita.books.model.BOOK;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by RCPopescu on 8/9/2016.
 */
public class Library {
    private HashMap<String, String> hm;
    private File rootDir;
    private JAXBContext c;
    private Unmarshaller unmarshaller;
    private static int cashedBooksNumber;
    private static final Logger logger = LoggerFactory.getLogger(Library.class);

    public Library(File rootDir) throws JAXBException {
        this.rootDir = rootDir;
        c = JAXBContext.newInstance(BOOK.class);
        unmarshaller = c.createUnmarshaller();
        hm = new HashMap<String, String>();
    }

    public List<String> getTitlesOfGivenAuthor(String author) throws JAXBException {
        List<String> titleList = new ArrayList<String>();
        File[] xmlFiles = rootDir.listFiles();
        for (int i = 0; i < xmlFiles.length; i++) {
            BOOK book = (BOOK) unmarshaller.unmarshal(new File(String.valueOf(xmlFiles[i])));
            for (String currentBookAuthor : book.getAUTHORS().getAUTHOR()) {
                if (author.equals(currentBookAuthor)) {
                    titleList.add(book.getTITLE());
                }
            }
        }
        return titleList;
    }

    public List<String> getTitlesFromCategory(String category) throws JAXBException {
        List<String> titleList = new ArrayList<String>();
        File[] xmlFiles = rootDir.listFiles();
        for (int i = 0; i < xmlFiles.length; i++) {

            BOOK book = (BOOK) unmarshaller.unmarshal(new File(String.valueOf(xmlFiles[i])));
            if (book.getCATEGORY().equals(category)) {
                titleList.add(book.getTITLE());
            }
        }
        return titleList;
    }

    public String getTitleByISBN(String isbn) throws JAXBException {
        String bookTitle = new String();
        File[] xmlFiles = rootDir.listFiles();
        if (hm.containsKey(isbn)) {
            bookTitle = hm.get(isbn);
            logger.info("Got from cache.");

        } else {
                for (int i = 0; i < xmlFiles.length; i++) {
                    BOOK book = (BOOK) unmarshaller.unmarshal(new File(String.valueOf(xmlFiles[i])));
                    if (book.getISBN().equals(isbn)) {
                            bookTitle = book.getTITLE();
                            hm.put(isbn, bookTitle);
                            logger.info("Got from cache.");
                            break;
                    }

                    }
            }

        return bookTitle;
    }
}