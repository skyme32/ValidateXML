package log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.logging.*;

public class Logging {

    // COSTANTS
    public static final int SEVERELEVEL = 1000;
    public static final int WARNINGLEVEL = 900;
    public static final int INFOLEVEL = 800;
    public static final int CONFIGLEVEL = 700;
    public static final int FINELEVEL = 500;
    public static final int FINERLEVEL = 400;
    public static final int FINESTLEVEL = 300;

    // Logging
    private static final Logger logger = Logger.getLogger(Thread.currentThread().getStackTrace()[0].getClassName());

    public void setLevel(String logLevel) {

        Level level = null;
        switch (logLevel.toUpperCase()) {
            case "OFF":
                level = Level.OFF;
                break;
            case "SEVERE":
                level = Level.SEVERE;
                break;
            case "WARNING":
                level = Level.WARNING;
                break;
            case "INFO":
                level = Level.INFO;
                break;
            case "CONFIG":
                level = Level.CONFIG;
                break;
            case "FINE":
                level = Level.FINE;
                break;
            case "FINER":
                level = Level.FINER;
                break;
            case "FINEST":
                level = Level.FINEST;
                break;
            case "ALL":
                level = Level.ALL;
                break;
        }

        logger.setLevel(level);

    }

    /**
     * @param level
     * @param message
     */
    public static void logMessage(int level, String message) {
        switch (level) {
            case SEVERELEVEL:
                logger.severe(message);
                break;
            case WARNINGLEVEL:
                logger.warning(message);
                break;
            case INFOLEVEL:
                logger.info(message);
                break;
            case CONFIGLEVEL:
                logger.config(message);
                break;
            case FINELEVEL:
                logger.fine(message);
                break;
            case FINERLEVEL:
                logger.finer(message);
                break;
            case FINESTLEVEL:
                logger.finest(message);
                break;
        }
    }

    /**
     * @param logLevel
     */
    public static void initiateLogging(String logLevel) {
        try {
            logger.setUseParentHandlers(false);
            ConsoleHandler logConsoleHandler = new ConsoleHandler();
            logger.addHandler(logConsoleHandler);

            SimpleFormatter logFormatter = new SimpleFormatter() {
                private static final String format = "[%1$tF %1$tT.%1$tL] [%2$-7s] %3$s %n";

                public synchronized String format(LogRecord lr) {
                    return String.format(format, new Date(lr.getMillis()), lr.getLevel().getName(), lr.getMessage());
                }

                ;
            };
            logConsoleHandler.setFormatter(logFormatter);

            Level level = null;
            switch (logLevel.toUpperCase()) {
                case "OFF":
                    level = Level.OFF;
                    break;
                case "SEVERE":
                    level = Level.SEVERE;
                    break;
                case "WARNING":
                    level = Level.WARNING;
                    break;
                case "INFO":
                    level = Level.INFO;
                    break;
                case "CONFIG":
                    level = Level.CONFIG;
                    break;
                case "FINE":
                    level = Level.FINE;
                    break;
                case "FINER":
                    level = Level.FINER;
                    break;
                case "FINEST":
                    level = Level.FINEST;
                    break;
                case "ALL":
                    level = Level.ALL;
                    break;
            }

            logger.setLevel(level);

            for (Handler handler : logger.getHandlers()) {
                handler.setLevel(level);
            }

        } catch (Exception e) {
            logMessage(SEVERELEVEL, e.toString());
            e.printStackTrace();
        }
    }

    /**
     * @param logLevel
     * @param pathFileName
     */
    public static void initiateLogging(String logLevel, File pathFileName) {
        String logFileName = "LOG.log";

        if (pathFileName.isDirectory()) {
            logFileName = pathFileName.getAbsolutePath() + "/" + logFileName;
        } else {
            if (pathFileName.getName().lastIndexOf(".") > 1) {
                logFileName = pathFileName.getAbsolutePath().substring(0, pathFileName.getAbsolutePath().lastIndexOf(".")) + ".log";
            } else {
                logFileName = pathFileName.getAbsolutePath() + ".log";
            }
        }

        try {
            File pathLogFile = new File(logFileName);
            Path pathLog = Paths.get(pathLogFile.getParent());
            Files.createDirectories(pathLog);


            logger.setUseParentHandlers(false);
            ConsoleHandler logConsoleHandler = new ConsoleHandler();
            logger.addHandler(logConsoleHandler);
            FileHandler logFileHandler = new FileHandler(pathLogFile.getAbsolutePath());
            logger.addHandler(logFileHandler);

            // Create a io to the LOG out
            SimpleFormatter logFormatter = new SimpleFormatter() {
                private static final String format = "[%1$tF %1$tT.%1$tL] [%2$-7s] %3$s %n";

                public synchronized String format(LogRecord lr) {
                    return String.format(format, new Date(lr.getMillis()), lr.getLevel().getName(), lr.getMessage());
                }

                ;
            };
            logConsoleHandler.setFormatter(logFormatter);
            logFileHandler.setFormatter(logFormatter);

            Level level = null;
            switch (logLevel.toUpperCase()) {
                case "OFF":
                    level = Level.OFF;
                    break;
                case "SEVERE":
                    level = Level.SEVERE;
                    break;
                case "WARNING":
                    level = Level.WARNING;
                    break;
                case "INFO":
                    level = Level.INFO;
                    break;
                case "CONFIG":
                    level = Level.CONFIG;
                    break;
                case "FINE":
                    level = Level.FINE;
                    break;
                case "FINER":
                    level = Level.FINER;
                    break;
                case "FINEST":
                    level = Level.FINEST;
                    break;
                case "ALL":
                    level = Level.ALL;
                    break;
            }
            logger.setLevel(level);

            for (Handler handler : logger.getHandlers()) {
                handler.setLevel(level);
            }

        } catch (FileNotFoundException e) {
            logMessage(SEVERELEVEL, e.toString());
            e.printStackTrace();
        } catch (IOException e) {
            logMessage(SEVERELEVEL, e.toString());
            e.printStackTrace();
        } catch (Exception e) {
            logMessage(SEVERELEVEL, e.toString());
            e.printStackTrace();
        }
    }
}
