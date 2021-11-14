import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyOwnXmlDomParser {

    private static final Logger logger = Logger.getLogger(MyOwnXmlDomParser.class.getName());

    private static final String filePath = ".\\Files\\BookStore.xml";
    private static DocumentBuilder documentBuilder;
    private static Document document;

    public static void main(String[] args){

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        }catch(ParserConfigurationException pce){
            pce.printStackTrace();
        }

        try {
            document = documentBuilder.parse(new File(filePath));
        } catch (SAXException| IOException exception) {
            exception.printStackTrace();
        }

        document.normalizeDocument();

        logger.log(Level.INFO, "*************XML****************");
        System.out.println("Root element: " + document.getDocumentElement().getNodeName());
        NodeList nodeList = document.getElementsByTagName("book");
        for(int i = 0; i < nodeList.getLength(); i++){
            System.out.println("###############Book################");
            String title = document.getElementsByTagName("title").item(0).getTextContent();
            String author = document.getElementsByTagName("author").item(0).getTextContent();
            String year = document.getElementsByTagName("year").item(0).getTextContent();
            String price = document.getElementsByTagName("price").item(0).getTextContent();
            System.out.print(title + '\n' + author + '\n' + year + '\n' + price + '\n');
        }
    }
}
