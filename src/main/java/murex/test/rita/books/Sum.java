package murex.test.rita.books;

import murex.test.rita.books.model.BOOK;
import java.io.File;
import java.io.IOException;
import javax.xml.bind.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


import org.xml.sax.SAXException;


/**
 * Created by RCPopescu on 8/9/2016.
 */
public class Sum {
    private void parseXmlFile(){
        //get the factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {

            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            //parse using builder to get DOM representation of the XML file
    //        Document document = db.parse("bookxml.xml");
      //      Element element = document.getDocumentElement();

        }
        catch(ParserConfigurationException pce) {
            pce.printStackTrace();
        }
//        catch(SAXException se) {
//            se.printStackTrace();
//        }
//        catch(IOException ioe) {
//            ioe.printStackTrace();
//        }
    }

    public int add(int a, int b) {
        BOOK book = new BOOK();
        return a+b;
    }
}
