package lt.viko.eif.mstrimaitis.service;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * Service for transforming Java objects into XML files using JAXB (Java Architecture for XML Binding).
 * <p>
 * This service allows for converting POJOs (Plain Old Java Objects) into their XML representation, which can either
 * be printed to the console or saved to a file.
 */

@Service
public class XmlTransformationService {

    /**
     * Creates a JAXB marshaller for a given class type.
     *
     * @param clazz The class type of the object to be marshalled.
     * @return The created marshaller instance.
     * @throws JAXBException If an error occurs while creating the marshaller.
     */

    private Marshaller createMarshaller(Class<?> clazz) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        return marshaller;
    }

    /**
     * Transforms the given Java object into XML and prints it to the console.
     *
     * @param object The object to be transformed into XML.
     */
    public void transformToXml(Object object) {
        try {
            Marshaller marshaller = createMarshaller(object.getClass());
            marshaller.marshal(object, System.out);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * Transforms the given Java object into XML and saves it to a file.
     *
     * @param object   The object to be transformed into XML.
     * @param filePath The path where the XML file should be saved.
     */
    public void saveXml(Object object, String filePath) {
        try {
            Marshaller marshaller = createMarshaller(object.getClass());
            marshaller.marshal(object, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}