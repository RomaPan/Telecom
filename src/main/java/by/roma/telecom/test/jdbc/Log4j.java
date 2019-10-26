package by.roma.telecom.test.jdbc;

import org.apache.log4j.Logger;

public class Log4j {

	private static final Logger LOGGER = Logger.getLogger(Log4j.class);

	public static Logger getLogger() {
		return LOGGER;
	}
	
	public static void main(String[] args) {
		LOGGER.debug("DEBUG MESSAGE!");
		LOGGER.fatal("FATAL MESSAGE!");
		LOGGER.warn("WARN MESSAGE!");
		LOGGER.trace("TRACE MESSAGE!");
		LOGGER.error("ERROR MESSAGE!");
	}
}
