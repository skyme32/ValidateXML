import com.github.jankroken.commandline.CommandLineParser;
import com.github.jankroken.commandline.OptionStyle;
import com.github.jankroken.commandline.domain.InvalidCommandLineException;
import factory.Arguments;
import factory.Controller;
import factory.Usage;
import log.Logging;
import log.Priority;

import java.io.File;
import java.lang.reflect.InvocationTargetException;

public class Main {

    private static Controller controller;
    private static String inputDir = "temp";
    private static String EXT_XSD = "xsd";
    private static String SEPARATED = "/";
    private static Integer findinput = 0;

    /**
     * Recursive method
     *
     * @param path
     */
    public static void recFilesInDir(String path) {
        File directory = new File(path);

        if (directory.exists()) {
            for (File fileXML : directory.listFiles()) {
                if (fileXML.getName().equals(inputDir)) {
                    findinput++;
                    controller.setFileInputDir(fileXML);
                } else if (fileXML.isDirectory()) {
                    recFilesInDir(fileXML.getAbsolutePath());
                }
            }
        }
    }

    /**
     * Recursive method
     *
     * @param path
     */
    public static void recFilesInDir(String path, String extension) {
        File directory = new File(path);

        if (directory.exists()) {
            for (File fileXML : directory.listFiles()) {
                String ext = fileXML.getName().substring(fileXML.getName().lastIndexOf(".") + 1).toLowerCase();
                if (ext.equals(extension)) {
                    findinput++;
                    controller.setFileInputXsd(fileXML);
                } else if (fileXML.isDirectory()) {
                    recFilesInDir(fileXML.getAbsolutePath(), extension);
                }
            }
        }
    }

    /**
     * @param args
     */
    private static void arg1(String[] args) {
        File file = new File(args[0]);

        if (file.isDirectory()) {
            recFilesInDir(file.getAbsolutePath());
            recFilesInDir(file.getAbsolutePath(), EXT_XSD);
        } else {
            Logging.logMessage(Logging.SEVERELEVEL, "The directory " + args[0] + " is NOT a directory or NOT exist.");
            System.exit(1);
        }

    }// Method ARG1

    /**
     * @param args
     */
    private static void arg2(String[] args) {

        for (String strFile : args) {
            File file = new File(strFile);

            if (file.isDirectory()) {
                recFilesInDir(file.getAbsolutePath());
            }

            if (file.isFile()) {
                String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase();
                if (ext.equals(EXT_XSD) && file.exists()) {
                    findinput++;
                    controller.setFileInputXsd(file);
                }
            }
        }
    } // Method ARG2


    /**
     * @param args
     */
    private static void arg3(String[] args) {
        Arguments arguments = null;

        try {
            arguments = CommandLineParser.parse(Arguments.class, args, OptionStyle.SIMPLE);
        } catch (IllegalAccessException e) {
            System.out.println(e.getCause());
        } catch (InstantiationException e) {
            System.out.println(e.getCause());
        } catch (InvocationTargetException e) {
            System.out.println(e.getCause());
        } catch (InvalidCommandLineException e) {
            System.out.println(e.getMessage());
        }


        if (arguments.getTempporal() != null) {
            inputDir = arguments.getTempporal();
        } else {
            inputDir = "temp";
        }

        if (arguments.getXml() != null) {

        }

        if (arguments.getDirectory() != null) {
            recFilesInDir(arguments.getDirectory());
        }

        if (arguments.getFilename() != null) {
            File file = new File(arguments.getFilename());
            String ext = file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase();
            if (ext.equals(EXT_XSD) && file.exists()) {
                findinput++;
                controller.setFileInputXsd(file);
            }
        }
    } // Method ARG3

    /**
     * MAIN THE APP
     *
     * @param args
     */
    public static void main(String[] args) {
        //INITIALIZE THE LOGGING
        Logging.initiateLogging(Priority.CONFIG);

        Logging.logMessage(Logging.INFOLEVEL, "BEGIN VALIDATE XSD to XML");
        Logging.logMessage(Logging.INFOLEVEL, "");
        Logging.logMessage(Logging.INFOLEVEL, "----------------------------------------------------------------------------");

        // INITIALIZE
        String[] argsVal = new String[5];
        controller = new Controller();


        if (args.length == 1) {
            arg1(args);
        } else if (args.length == 2) {
            arg2(args);
        } else if (args.length > 3) {
            arg3(args);
        } else {
            Logging.logMessage(Logging.SEVERELEVEL, "ERROR illegal arguments.\n");
            Logging.logMessage(Logging.SEVERELEVEL, Usage.usageString());
            System.exit(1);
        }


        if (controller.getFileInputDir() == null) {
            Logging.logMessage(Logging.SEVERELEVEL, "NOT exist any directory.\n");
            Logging.logMessage(Logging.SEVERELEVEL, Usage.usageString());
            System.exit(1);
        } else if (controller.getFileInputXsd() == null) {
            Logging.logMessage(Logging.SEVERELEVEL, "NOT exist any schemna file (XSD).\n");
            Logging.logMessage(Logging.SEVERELEVEL, Usage.usageString());
            System.exit(1);
        }

        controller.begin();

    } // Method MAIN
}

