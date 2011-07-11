/**
 * 
 */
package de.mbentwicklung.jcrviewer.core;


/**
 * Standard abstract JCR Viewer Exception
 * 
 * @author Marc Bellmann<marc.bellmann@mb-entwicklung.de>
 * 
 */
@SuppressWarnings("serial")
public abstract class JcrViewerException extends Exception {

	/** Error Message Builder to build a message */
	private final ErrorMessageBuilder errorMessageBuilder;

	/**
	 * Constructor to set the {@link #errorMessageBuilder}
	 * 
	 * @param errorMessageBuilder
	 *            the Error Message Builder with message
	 */
	protected JcrViewerException(final ErrorMessageBuilder errorMessageBuilder, final Throwable throwable) {
		super(throwable);
		this.errorMessageBuilder = errorMessageBuilder;
	}

	/**
	 * Get the Error Title
	 * 
	 * @return {@link #errorMessageBuilder.getTitle()}
	 */
	public String getErrorTitle() {
		return errorMessageBuilder.getTitle();
	}

	/**
	 * Get the Error Message
	 * 
	 * @return {@link #errorMessageBuilder.getMessage()}
	 */
	public String getErrorMessage() {
		return errorMessageBuilder.getMessage();
	}
}
