package murex.test.rita.books;

import org.junit.*;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by RCPopescu on 8/10/2016.
 */
public class XMLtasksTest {
    File file = new File("C:\\Users\\rcpopescu\\IdeaProjects\\Books\\booksxml");
    @Test
    public void testGetTitlesOfGivenAuthor() throws Exception {
        XMLtasks x = new XMLtasks();

    }

    @Test
    public void testGetTitlesFromCategory() throws Exception {
        XMLtasks x = new XMLtasks();
        Assert.assertEquals("[agile software development1, agile software development2, agile software development3, Effective Java1, Head First Java2]".toString(),x.getTitlesFromCategory("Self-Help", file).toString());
    }
}