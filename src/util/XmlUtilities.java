package util;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.graph.Edge;
import model.graph.Graph;
import model.graph.Node;
 
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public final class XmlUtilities {

    /**
     * Constructeur privee - Empeche l'instanciation de cette class
     */
    private XmlUtilities() {
    }

    /**
     * Permet de generer un fichier xml comprenant les informations d'un graph
     * @param graph - Graph a sauver
     */
    public static void writeXmlFile(Graph graph) {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("osm");
            doc.appendChild(rootElement);
            
            // Elements (Node et Edge)
            Element element;
            
            // Pour tous les noeud du graph
            for (Node n : graph.getNodes()){
                element = doc.createElement("node");
                rootElement.appendChild(element);
                element.setAttribute("id", Integer.toString(n.getId()));
                element.setAttribute("x", Integer.toString(n.getX()));
                element.setAttribute("y", Integer.toString(n.getX()));
                element.setAttribute("type", n.getType().toString());
            }
            
            // Pour tous les edges du graph
            for (Edge e : graph.getEdges()){
                element = doc.createElement("edge");
                rootElement.appendChild(element);
                element.setAttribute("nd1", Integer.toString(e.getA().getId()));
                element.setAttribute("nd2", Integer.toString(e.getB().getId()));
                element.setAttribute("type", e.getType().toString());
            }
            
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            
            DOMSource source = new DOMSource(doc);
            // TODO - Revoir le chemin ou on pose le fichier
            StreamResult result = new StreamResult(new File("test.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException | TransformerException e) {
            System.err.println(e);
        }
    }
    
    public static Graph readXmlFile(){
        return null;
    }
}
