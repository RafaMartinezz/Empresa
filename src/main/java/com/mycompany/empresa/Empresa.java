package com.mycompany.empresa;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * The Empresa class represents an example of managing employee data,
 * including reading and writing data to a random-access file and generating an
 * XML document.
 * Additionally, it parses an XML document using an external SAX parser.
 * 
 * Author: rafa
 */
public class Empresa {

    /**
     * Creates an XML Element with specified tag name and text content.
     * 
     * @param doc      The Document object to which the element belongs.
     * @param tagName  The tag name of the element.
     * @param textNode The text content of the element.
     * @return The newly created XML element.
     */
    public static Element crearElemento(Document doc, String tagName, String textNode) {
        Element elemento = doc.createElement(tagName);
        elemento.appendChild(doc.createTextNode(textNode));
        return elemento;
    }

    public static void main(String[] args) {
        // Create an instance of Plantilla to store employees
        Plantilla plantilla = new Plantilla(new ArrayList<>());

        // Create employees and add them to plantilla
        Empleado empleado1 = new Empleado(1, "Rafael", "Montero Rios", 2000.0f, 20.0f);
        Empleado empleado2 = new Empleado(2, "Manuel", "Republica del Salvador", 1500.0f, 10.0f);
        Empleado empleado3 = new Empleado(3, "Pablo", "Republica Argentina", 1800.0f, 30.0f);
        Empleado empleado4 = new Empleado(4, "Patricia", "Brion", 3500.0f, 40.0f);
        Empleado empleado5 = new Empleado(5, "Sabela", "Furgo Camper", 4000.0f, 10.0f);

        plantilla.addEmpleado(empleado1);
        plantilla.addEmpleado(empleado2);
        plantilla.addEmpleado(empleado3);
        plantilla.addEmpleado(empleado4);
        plantilla.addEmpleado(empleado5);

        // Create a random access file for writing employee data
        RandomAccessFile archivo = null;

        try {
            archivo = new RandomAccessFile("empleados.dat", "rw");

            // Write each employee's data to the file
            for (Empleado empleado : plantilla.empleados) {

                archivo.writeInt(empleado.getCodigo()); // Write employee code

                String nombre = String.format("%-20s", empleado.getNombre());
                archivo.write(nombre.getBytes(StandardCharsets.UTF_8)); // Write employee name

                String direccion = String.format("%-50s", empleado.getDireccion());
                archivo.write(direccion.getBytes(StandardCharsets.UTF_8)); // Write employee address

                archivo.writeFloat(empleado.getSalario()); // Write employee salary
                archivo.writeFloat(empleado.getComision()); // Write employee commission
            }

            // Move the file pointer to the beginning after writing data
            archivo.seek(0);

            // Create a new XML document and add the root element
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element rootElement = doc.createElement("Plantilla");
            doc.appendChild(rootElement);

            // Loop through the file to read employee data and add it to the XML document
            while (archivo.getFilePointer() < archivo.length()) {
                int codigo = archivo.readInt(); // Read employee code

                // Read employee name
                byte[] nombreBytes = new byte[20];
                archivo.readFully(nombreBytes);
                String nombre = new String(nombreBytes, StandardCharsets.UTF_8).trim();

                // Read employee address
                byte[] direccionBytes = new byte[50];
                archivo.readFully(direccionBytes);
                String direccion = new String(direccionBytes, StandardCharsets.UTF_8).trim();

                float salario = archivo.readFloat(); // Read employee salary
                float comision = archivo.readFloat(); // Read employee commission

                // Create an XML element for the employee and add sub-elements with data
                Element empleadoElement = doc.createElement("Empleado");
                empleadoElement.appendChild(crearElemento(doc, "codigo", Integer.toString(codigo)));
                empleadoElement.appendChild(crearElemento(doc, "nombre", nombre));
                empleadoElement.appendChild(crearElemento(doc, "direccion", direccion));
                empleadoElement.appendChild(crearElemento(doc, "salario", Float.toString(salario)));
                empleadoElement.appendChild(crearElemento(doc, "comision", Float.toString(comision)));
                rootElement.appendChild(empleadoElement);
            }

            // Create a transformer to output XML to a file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Set up source and result for XML transformation
            DOMSource source = new DOMSource(doc);
            File file = new File("empleados.xml");
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

        } catch (IOException e) {
            System.out.println("An I/O error occurred");
        } catch (TransformerException te) {
            System.out.println("A transformation error occurred");
        } catch (ParserConfigurationException pce) {
            System.out.println("A parser configuration error occurred");
        } finally {
            try {
                if (archivo != null) {
                    archivo.close(); // Close the random access file
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            // Create an XMLReader for parsing the XML file
            XMLReader xmlReader = XMLReaderFactory.createXMLReader();

            // Set our custom handler for parsing
            DomParser handler = new DomParser(new File("empleados.xml"));
            xmlReader.setContentHandler(handler);

            // Parse the XML file
            xmlReader.parse(handler.getInputFile().getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
