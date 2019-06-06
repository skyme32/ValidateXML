package factory;

public class Usage {

    /**
     * @return
     */
    public static String usageString() {
        StringBuilder strError = new StringBuilder();
        String space = "\t\t\t\t\t\t\t\t\t\t";
        strError.append("USAGE:\n");
        strError.append(space + "java -jar validate [input_DIR]\n");
        strError.append(space + "java -jar validate.jar [input DIR] [XSD_FILE]\n");
        strError.append(space + "java -jar validate.jar -d [input DIR] -x [XSD_FILE] -t [TEMPORAL_NAME] -l [XML_NAME]\n");
        strError.append("\t\t\t\t\t\t\t\t\tDESCRIPTION:\n");
        strError.append(space + "-x, -xsd\n");
        strError.append(space + "\t filename to schema XSD");
        strError.append("\n");
        strError.append(space + "-d, -dir\n");
        strError.append(space + "\t path to input directory");
        strError.append("\n");
        strError.append(space + "-t, -temp\n");
        strError.append(space + "\t name to search the input directory");
        strError.append("\n");
        strError.append(space + "-l, -xml\n");
        strError.append(space + "\t name to search the XML's");

        return strError.toString();
    }
}
