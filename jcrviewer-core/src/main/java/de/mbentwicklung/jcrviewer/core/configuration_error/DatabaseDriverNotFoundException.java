package de.mbentwicklung.jcrviewer.core.configuration_error;

import de.mbentwicklung.jcrviewer.core.ErrorMessageBuilder;
import de.mbentwicklung.jcrviewer.core.JcrViewerException;

/**
 * Database driver for connection with repository failed.
 * 
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
@SuppressWarnings("serial")
public class DatabaseDriverNotFoundException extends JcrViewerException {

	/** Error Title */
	private static final String ERROR_TITLE = "Database driver not found!";

	/** Name des Drivers der fehlt */
	private final String driverName;

	/**
	 * Konstruktor zum Erstellen der Exception.
	 * 
	 * @param driverName
	 *            Name der fehlenden Klasse
	 * @param throwable
	 *            auslösende Exception
	 */
	public DatabaseDriverNotFoundException(final String driverName, final Throwable throwable) {
		super(new ErrorMessageBuilder(ERROR_TITLE, buildErrorMessage(driverName)), throwable);
		this.driverName = driverName;
	}

	/**
	 * get the wanted driver name
	 * 
	 * @return driver name
	 */
	public String getDriverName() {
		return driverName;
	}

	/**
	 * Build the Error Message
	 * 
	 * @param driverName
	 *            Driver name to load Information from {@link DatabaseDriverMap}.
	 * @return Error Message
	 */
	private static String buildErrorMessage(final String driverName) {
		DatabaseDriverMap driverMap = new DatabaseDriverMap();
		StringBuilder builder = new StringBuilder();
		builder.append("The download driver ");
		builder.append(driverMap.getDriverName(driverName));
		builder.append(" not found in the classpath! \nPlease download ");
		builder.append(driverMap.getDownloadDriver(driverName));
		builder.append(" and start the JCRViewer \nwith java -cp \"database.driver.jar\" JCRViewer.jar");

		return builder.toString();
	}
}
