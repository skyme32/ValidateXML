package factory;

import log.Logging;
import org.xml.sax.SAXException;
import java.io.File;
import java.io.IOException;

public class Controller {

    // GETTER & SETTERS
    private File fileInputDir;
    private File fileInputXsd;


    // PRIVATE VARIABLES
    private Integer countErrors = 0;

    /**
     * BEGIN THE APP
     */
    public void begin() {
        // PREPARE THE FILE AND DIRECTORY
        Logging.logMessage(Logging.INFOLEVEL, "PATH: \t" + fileInputDir.getAbsolutePath());
        Logging.logMessage(Logging.INFOLEVEL, "XSD: \t" + fileInputXsd.getName());
        Logging.logMessage(Logging.INFOLEVEL, "");
        Logging.logMessage(Logging.INFOLEVEL, "----------------------------------------------------------------------------");

        if (fileInputDir.isDirectory() && fileInputXsd.isFile()) {
            try {
                recFilesInDir(fileInputDir.getAbsolutePath(), fileInputXsd.getAbsolutePath());
                Logging.logMessage(Logging.INFOLEVEL, "Find " + this.countErrors + " ERROR(S) to the XML's.");
            } catch (Exception e) {
                System.out.println();
                Logging.logMessage(Logging.WARNINGLEVEL, e.getMessage());
            }
        } else {
            Logging.logMessage(Logging.SEVERELEVEL, "ERROR, the file o directory don't exist.\n");
            Logging.logMessage(Logging.SEVERELEVEL, Usage.usageString());
        }

    } // Method BEGIN


    /**
     * @param path
     * @param xsd
     */
    public void recFilesInDir(String path, String xsd) {
        File directory = new File(path);

        if (directory.exists()) {
            for (File fileXML : directory.listFiles()) {
                if (fileXML.isFile()) {
                    if (fileXML.getName().toLowerCase().lastIndexOf(".xml") > 0 && fileXML.getName().toLowerCase().contains("pdi")) {
                        validatedXML(fileXML.getAbsolutePath(), xsd);
                    }
                }

                if (fileXML.isDirectory()) {
                    recFilesInDir(fileXML.getAbsolutePath(), xsd);
                }
            }
        }
    }


    /**
     * @param pathXml
     * @param pathXsd
     */
    public void validatedXML(String pathXml, String pathXsd) {
        File file = new File(pathXml);
        File fileXSD = new File(pathXsd);

        //debug("VALIDATE FILE:");
        Logging.logMessage(Logging.INFOLEVEL, "XML: " + file.getAbsolutePath());
        Logging.logMessage(Logging.INFOLEVEL, "XSD: " + fileXSD.getAbsolutePath());

        try {
            XmlObjectXsd.validarXml(fileXSD, file);
            Logging.logMessage(Logging.CONFIGLEVEL, "Succesfully Validated");

        } catch (IOException e) {
            Logging.logMessage(Logging.WARNINGLEVEL, XmlObjectXsd.debugXML(e.toString()));
            this.countErrors++;
        } catch (SAXException e) {
            Logging.logMessage(Logging.WARNINGLEVEL, XmlObjectXsd.debugXML(e.toString()));
            this.countErrors++;
        }

        Logging.logMessage(Logging.INFOLEVEL, "----------------------------------------------------------------------------");
    }


    // GETTER & SETTERS
    public File getFileInputDir() {
        return fileInputDir;
    }

    public void setFileInputDir(File fileInputDir) {
        this.fileInputDir = fileInputDir;
    }

    public File getFileInputXsd() {
        return fileInputXsd;
    }

    public void setFileInputXsd(File fileInputXsd) {
        this.fileInputXsd = fileInputXsd;
    }
}
