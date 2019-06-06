package factory;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlObjectXsd {

    /**
     * ----- CONSTANTS -----
     **/
    private static final String CHAR_EXECPTION = ";";


    /**
     * @param pathXsd
     * @param xml
     * @throws IOException
     * @throws SAXException
     */
    public static void validarXml(File pathXsd, File xml) throws IOException, SAXException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(pathXsd);
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(xml));
    }

    /**
     * @param str
     * @return
     */
    public static String debugXML(String str) {
        String xmlerror = str.toString();
        StringBuilder strError = new StringBuilder();

        if (xmlerror.indexOf(CHAR_EXECPTION) > 0) {
            String[] strSplit = xmlerror.split(CHAR_EXECPTION);
            strError.append("ERROR:\t");
            strError.append(strSplit[4] + "\n");
            strError.append("\t\t\t\t\t\t\t\t\t\t\t" + strSplit[2] + "\n");
            strError.append("\t\t\t\t\t\t\t\t\t\t\t" + strSplit[3] + "\n");
            strError.append("\t\t\t\t\t\t\t\t\t\t\t" + strSplit[1]);
        } else {
            strError.append(xmlerror);
        }

        return strError.toString();
    }


}
