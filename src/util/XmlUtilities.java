package util;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
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
import model.graph.TypeEdge;
import model.graph.TypeNode;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public final class XmlUtilities {

    /**
     * Constructeur prive - Empeche l'instanciation de cette classe
     */
    private XmlUtilities() {
    }

    /**
     * Permet de generer un fichier xml comprenant les informations d'un graph
     *
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
            for (Node n : graph.getNodes()) {
                element = doc.createElement("node");
                rootElement.appendChild(element);
                element.setAttribute("id", Integer.toString(n.getId()));
                element.setAttribute("x", Integer.toString(n.getX()));
                element.setAttribute("y", Integer.toString(n.getY()));
                element.setAttribute("type", n.getType().toString());
            }

            // Pour tous les edges du graph
            for (Edge e : graph.getEdges()) {
                element = doc.createElement("edge");
                rootElement.appendChild(element);
                element.setAttribute("nd1", Integer.toString(e.getA().getId()));
                element.setAttribute("nd2", Integer.toString(e.getB().getId()));
                element.setAttribute("type", e.getType().toString());
            }

            // Ecrit le contenu du fichier xml
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);

            // Fenetre du choix de la destination
            JFileChooser chooser = new JFileChooser(".");
            int retrival = chooser.showSaveDialog(null);
            StreamResult result;
            if (retrival == JFileChooser.APPROVE_OPTION) {
                result = new StreamResult(chooser.getSelectedFile() + ".xml");
                transformer.transform(source, result);

                System.out.println("File saved!");
            }

        } catch (ParserConfigurationException | TransformerException e) {
            System.err.println(e);
        }
    }

    /**
     * Permet de generer un graph a partir d'un fichier xml
     *
     * @return graph
     */
    public static Graph readXmlFile() {
        Graph graph = new Graph();

        // Fenetre de choix du fichier xml a importer
        JFileChooser chooser = new JFileChooser(".");
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            String filePath = chooser.getSelectedFile().getAbsolutePath();
            try {
                File fXmlFile = new File(filePath);
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.parse(fXmlFile);
                doc.getDocumentElement().normalize();

                // Import des noeuds
                NodeList nodeList = doc.getElementsByTagName("node");

                for (int temp = 0; temp < nodeList.getLength(); temp++) {
                    org.w3c.dom.Node nNode = nodeList.item(temp);
                    Element eElement = (Element) nNode;
                    graph.addNode(new Node(Integer.parseInt(eElement.getAttribute("x")), Integer.parseInt(eElement.getAttribute("y")), TypeNode.valueOf(eElement.getAttribute("type"))));
                }

                // Import des arcs
                NodeList edgeList = doc.getElementsByTagName("edge");

                for (int temp = 0; temp < edgeList.getLength(); temp++) {
                    org.w3c.dom.Node nEdge = edgeList.item(temp);
                    Element eElement = (Element) nEdge;
                    graph.addEdge(new Edge(graph.getNodeById(Integer.parseInt(eElement.getAttribute("nd1"))), graph.getNodeById(Integer.parseInt(eElement.getAttribute("nd2"))), TypeEdge.valueOf(eElement.getAttribute("type"))));
                }

            } catch (ParserConfigurationException e) {
                System.err.println(e);
            } catch (SAXException | IOException ex) {
                Logger.getLogger(XmlUtilities.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            // Cas ou on annule l'import
            return null;
        }
        return graph;
    }
}
