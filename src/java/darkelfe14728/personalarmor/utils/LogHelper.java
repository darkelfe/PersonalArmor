package darkelfe14728.personalarmor.utils;

import java.util.ArrayList;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @author Julien Rosset
 * 
 *         Logging system.
 */
public abstract class LogHelper
{
    /**
     * Instance of internal logging system.
     */
    private static Logger            logger = LogManager.getLogger("PersonalArmor");

    /**
     * List of opened blocks.
     */
    private static ArrayList<String> blocks = new ArrayList<String>();

    /**
     * Start a new block
     * 
     * @param name
     *            Block name
     */
    public static void startBlock(String name)
    {
        LogHelper.blocks.add(name);
    }
    /**
     * Close last opened block
     */
    public static void stopBlock()
    {
        LogHelper.blocks.remove(LogHelper.blocks.size() - 1);
    }

    /**
     * Log a message.
     * 
     * @param level
     *            Log level.
     * @param message
     *            Message to log.
     */
    public static void log(Level level, String message)
    {
        for(String block : LogHelper.blocks)
            message = block + " : " + message;

        LogHelper.logger.log(level, message);
    }

    /**
     * Log a TRACE message.
     * 
     * @param message
     *            Message to log.
     */
    public static void trace(String message)
    {
        LogHelper.log(Level.TRACE, message);
    }
    /**
     * Log a DEBUG message.
     * 
     * @param message
     *            Message to log.
     */
    public static void debug(String message)
    {
        LogHelper.log(Level.DEBUG, message);
    }
    /**
     * Log a INFO message.
     * 
     * @param message
     *            Message to log.
     */
    public static void info(String message)
    {
        LogHelper.log(Level.INFO, message);
    }
    /**
     * Log a WARN message.
     * 
     * @param message
     *            Message to log.
     */
    public static void warn(String message)
    {
        LogHelper.log(Level.WARN, message);
    }
    /**
     * Log a ERROR message.
     * 
     * @param message
     *            Message to log.
     */
    public static void error(String message)
    {
        LogHelper.log(Level.ERROR, message);
    }
    /**
     * Log a FATAL message.
     * 
     * @param message
     *            Message to log.
     */
    public static void fatal(String message)
    {
        LogHelper.log(Level.FATAL, message);
    }
}
