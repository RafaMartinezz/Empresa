package com.mycompany.empresa;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * The DomParser class is responsible for parsing an XML document and processing
 * its structure.
 * It extends DefaultHandler to handle XML parsing events, such as start and end
 * of elements,
 * and uses DOM for document parsing and SAX for event handling.
 * This class can be used to display the structure of an XML document in a
 * formatted way.
 * 
 * Author: rafa
 */
public class DomParser extends DefaultHandler {

    private File inputFile; // File object representing the XML file to be parsed
    private Document doc; // DOM Document object for representing XML structure
    private int spacing = 0; // Controls indentation for nested elements

    /**
     * Constructor initializes the parser with a specified XML file.
     * 
     * @param inputFile The XML file to be parsed.
     * @throws ParserConfigurationException if a DocumentBuilder cannot be created.
     * @throws SAXException                 if any SAX errors occur during
     *                                      processing.
     * @throws IOException                  if any IO errors occur while accessing
     *                                      the file.
     */
    public DomParser(File inputFile) throws ParserConfigurationException, SAXException, IOException {
        this.inputFile = inputFile;

        // Initialize DocumentBuilder for DOM parsing
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Parse the XML file to build the DOM Document
        this.doc = builder.parse(this.inputFile);
    }

    /**
     * Called at the start of the XML document. Prints a header message indicating
     * the beginning of the document.
     */
    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start of XML document");
        System.out.println("-----------");
    }

    /**
     * Called at the end of the XML document. Prints a footer message indicating the
     * end of the document.
     */
    @Override
    public void endDocument() throws SAXException {
        System.out.println("-----------");
        System.out.println("End of XML document");
    }

    /**
     * Called at the start of an element in the XML. Prints the opening tag with
     * attributes, if any.
     * 
     * @param uri        The Namespace URI, or the empty string if the element has
     *                   no Namespace URI or if Namespace processing is not being
     *                   performed.
     * @param localName  The local name (without prefix), or the empty string if
     *                   Namespace processing is not being performed.
     * @param qName      The qualified name (with prefix), or the empty string if
     *                   qualified names are not available.
     * @param attributes The attributes attached to the element.
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        printSpaces(); // Print indentation based on element depth
        if (attributes.getLength() == 0) {
            System.out.println("<" + qName + ">");
        } else {
            System.out.print("<" + qName + ">");

            // If the element has attributes, print them
            for (int i = 0; i < attributes.getLength(); i++) {
                System.out.print(" Attribute: " + attributes.getQName(i) + " Value: " + attributes.getValue(i));
                System.out.println();
            }
        }
        this.spacing++;
    }

    /**
     * Called at the end of an element in the XML. Prints the closing tag.
     * 
     * @param uri       The Namespace URI, or the empty string if the element has no
     *                  Namespace URI or if Namespace processing is not being
     *                  performed.
     * @param localName The local name (without prefix), or the empty string if
     *                  Namespace processing is not being performed.
     * @param qName     The qualified name (with prefix), or the empty string if
     *                  qualified names are not available.
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        this.spacing--;
        printSpaces();
        System.out.println("</" + qName + ">");
    }

    /**
     * Called when character data is found inside an element. Prints the text
     * content if it is not empty.
     * 
     * @param ch     The characters from the XML document.
     * @param start  The start position in the character array.
     * @param length The number of characters to read from the array.
     */
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String content = new String(ch, start, length).trim();
        if (!content.isEmpty()) {
            printSpaces();
            System.out.println("Node text: " + content);
        }
    }

    /**
     * Gets the input file being parsed.
     * 
     * @return The XML file.
     */
    public File getInputFile() {
        return inputFile;
    }

    /**
     * Sets the XML file to be parsed.
     * 
     * @param inputFile The XML file.
     */
    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    /**
     * Gets the Document object representing the parsed XML structure.
     * 
     * @return The Document object.
     */
    public Document getDoc() {
        return doc;
    }

    /**
     * Sets the Document object representing the XML structure.
     * 
     * @param doc The Document object.
     */
    public void setDoc(Document doc) {
        this.doc = doc;
    }

    /**
     * Prints spaces for indentation based on the current level of nesting.
     */
    public void printSpaces() {
        for (int i = 0; i < this.spacing; i++) {
            System.out.print("    ");
        }
    }
}
