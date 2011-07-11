/**
 * 
 */
package de.mbentwicklung.jcrviewer.core;

/**
 * Builder for error Messages
 * 
 * @author Marc Bellmann<marc.bellmann@mb-entwicklung.de>
 */
public class ErrorMessageBuilder {

	/** Error title */
	private final String title;

	/** Error Message */
	private final String message;

	/**
	 * Constructor to build the error message
	 * 
	 * @param title
	 *            the error title
	 * @param message
	 *            the error message
	 * @param args
	 *            arguments for the message
	 */
	public ErrorMessageBuilder(final String title, final String message, final String... args) {
		super();
		this.title = title;
		this.message = message;
	}

	/**
	 * get the error title
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * get the error message
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}
