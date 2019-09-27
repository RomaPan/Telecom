package by.roma.telecom.test.jdbc;

//import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Log4j {

	private static final Logger LOGGER = Logger.getLogger(Log4j.class);

	public static Logger getLogger() {
		return LOGGER;
	}
	
	public static void main(String[] args) {
//		BasicConfigurator.configure();
		LOGGER.debug("Test log message");
		LOGGER.fatal("FATAL MESSAGE!!!");
		LOGGER.warn("Warn message!");
		LOGGER.trace("this WILL BE ignored, due to lowest level logger I set up is DEBUG");
		LOGGER.error("Error message");
	}
}
