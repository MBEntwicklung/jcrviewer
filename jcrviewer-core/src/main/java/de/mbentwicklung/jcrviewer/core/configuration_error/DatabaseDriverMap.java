/**
 * 
 */
package de.mbentwicklung.jcrviewer.core.configuration_error;

import java.util.HashMap;
import java.util.Map;

/**
 * Map with well-known Database Driver.
 * 
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class DatabaseDriverMap {

	public static final String UNKNOWN_DRIVER_NAME = "unknown";
	private static final String UNKNOWN_DRIVER_DOWNLOAD = "jcrviewer.mb-entwicklung.de";

	/** Map */
	private final Map<String, DatabaseDriverInformation> databaseDriverMap;

	/**
	 * Constructor to init the map
	 */
	public DatabaseDriverMap() {
		databaseDriverMap = new HashMap<String, DatabaseDriverInformation>();

		initMap();
	}

	/**
	 * init map with values
	 */
	private void initMap() {
		add(UNKNOWN_DRIVER_NAME, UNKNOWN_DRIVER_DOWNLOAD);
		
		add("net.sourceforge.jtds.jdbc.Driver", "");

	}

	/**
	 * help function to a drivers
	 * 
	 * @param driverName
	 *            driver name
	 * @param downloadDriver
	 *            download driver address
	 */
	private void add(final String driverName, final String downloadDriver) {
		final DatabaseDriverInformation information = new DatabaseDriverInformation(driverName,
				downloadDriver);
		databaseDriverMap.put(information.getDriverName(), information);
	}

	public String getDriverName(final String driverName) {
		if (!databaseDriverMap.containsKey(driverName)) {
			return databaseDriverMap.get(UNKNOWN_DRIVER_NAME).getDriverName();
		}
		return databaseDriverMap.get(driverName).getDriverName();
	}

	public String getDownloadDriver(final String driverName) {
		if (!databaseDriverMap.containsKey(driverName)) {
			return databaseDriverMap.get(UNKNOWN_DRIVER_NAME).getDownloadDriver();
		}
		return databaseDriverMap.get(driverName).getDownloadDriver();
	}

}

/**
 * Map with well-known Database Driver.
 * 
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
class DatabaseDriverInformation {

	/** the driver name */
	private final String driverName;

	/** the download driver address */
	private final String downloadDriver;

	/**
	 * constructor to set the final values
	 * 
	 * @param driverName
	 *            driver name
	 * @param downloadDriver
	 *            download driver adress
	 */
	public DatabaseDriverInformation(final String driverName, final String downloadDriver) {
		super();
		this.driverName = driverName;
		this.downloadDriver = downloadDriver;
	}

	/**
	 * get the driver name
	 * 
	 * @return the driverName
	 */
	public String getDriverName() {
		return driverName;
	}

	/**
	 * get the download driver address
	 * 
	 * @return the downloadDriver
	 */
	public String getDownloadDriver() {
		return downloadDriver;
	}
}
