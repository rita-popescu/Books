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
import java.util.Scanner;

/**
 * Created by RCPopescu on 8/9/2016.
 */
public class XMLtasks {
    public List<String> getTitlesOfGivenAuthor(String author, File rootDir)throws JAXBException{
        List<String> titleList = new ArrayList<String>();
        JAXBContext c = JAXBContext.newInstance(BOOK.class);
        Unmarshaller unmarshaller = c.createUnmarshaller();
        File[] files = rootDir.listFiles();
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
                        Element authorElement = (Element) a;
                        String aut = authorElement.getTextContent();
                        if(aut.equals("Joshua Bloch"))
                            titleList.add(unmarshal.getTITLE());
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

        return titleList;
    }
    public List<String> getTitlesFromCategory(String category, File directory)throws JAXBException{
        List<String> titleList = new ArrayList<String>();
        JAXBContext c = JAXBContext.newInstance(BOOK.class);
        Unmarshaller unmarshaller = c.createUnmarshaller();
        File[] xmlFiles = directory.listFiles();
        for(int i = 0; i < xmlFiles.length; i++){

            BOOK book = (BOOK)unmarshaller.unmarshal(new File(String.valueOf(xmlFiles[i])));
            if(book.getCATEGORY().equals(category)) {
                titleList.add(book.getTITLE());
            }
        }
        return titleList;
    }
    public static void main(String [] args)throws JAXBException {
//        JAXBContext c = JAXBContext.newInstance(BOOK.class);
//        Unmarshaller unmarshaller = c.createUnmarshaller();
//
        File file = new File("C:\\Users\\rcpopescu\\IdeaProjects\\Books\\booksxml");
//        File[] files = file.listFiles();
//
//        for (int i = 0; i < files.length; i++) {
//            System.out.println(files[i]);
//
//            BOOK unmarshal = (BOOK)unmarshaller.unmarshal(new File(String.valueOf(files[i])));
//
//
//                System.out.println(unmarshal.getTITLE());
//                System.out.println(unmarshal.getCATEGORY());
//                System.out.println(unmarshal.getISBN());
//                System.out.println(unmarshal.getPRICE());
//                System.out.println(unmarshal.getAUTHORS().getAUTHOR());
//
//        }
//
//
        XMLtasks x = new XMLtasks();
//        String autor_cautat = x.getAuthorWanted();
//        System.out.println("Titlurile cartilor scrise de autorul dat sunt urmatoarele: ");
//        System.out.println(x.getTitlesOfGivenAuthor(autor_cautat,file));
//





    }

}
