package murex.test.rita.books;

import murex.test.rita.books.model.BOOK;
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
import java.util.List;

/**
 * Created by RCPopescu on 8/10/2016.
 */
public class XMLReader {
    public static void main(String [] args)throws JAXBException {
        List<String> list = new ArrayList<String>();
        JAXBContext c = JAXBContext.newInstance(BOOK.class);
        Unmarshaller unmarshaller = c.createUnmarshaller();
        File file = new File("C:\\Users\\rcpopescu\\IdeaProjects\\Books\\booksxml");
        File[] files = file.listFiles();
        for (int i = 0; i < files.length; i++) {
            BOOK unmarshal = (BOOK)unmarshaller.unmarshal(new File(String.valueOf(files[i])));
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            try
            {
                DocumentBuilder builder = builderFactory.newDocumentBuilder();
                Document doc = builder.parse(files[i]);
                NodeList authorsList = doc.getElementsByTagName("AUTHOR");
                for(int j=0; j< authorsList.getLength(); j++){
                    Node a = authorsList.item(j);
                    if(a.getNodeType() == Node.ELEMENT_NODE){
                        Element author = (Element) a;
                        String aut = author.getTextContent();
                        if(aut.equals("Joshua Bloch"))
                            list.add(unmarshal.getTITLE());
                    }
                }

            }
            catch(ParserConfigurationException e)
            {
                e.printStackTrace();
            }
            catch(SAXException e)
            {
                e.printStackTrace();
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println(list);

    }

}
