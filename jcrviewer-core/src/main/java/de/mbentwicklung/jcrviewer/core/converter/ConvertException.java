package de.mbentwicklung.jcrviewer.core.converter;

import de.mbentwicklung.jcrviewer.core.ErrorMessageBuilder;
import de.mbentwicklung.jcrviewer.core.JcrViewerException;

/**
 * Eine {@link ConvertException} tritt auf, wenn ein Fehler beim Umwandeln des Repositories in einen
 * Node auftritt. Sie kapselt JCR Exceptions damit diese Abhängigkeit nicht werden gegeben werden
 * muss.
 * 
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class ConvertException extends JcrViewerException {

	/** serialVersionUID */
	private static final long serialVersionUID = -8236951070813531757L;

	/** Error Title */
	private static final String ERROR_TITLE = "Convert Error";
	
	/**
	 * Konstruktor für Übergabe der Fehlernachricht und der auslösenden {@link Exception}
	 * 
	 * @param msg
	 *            Fehlernachricht
	 * @param throwable
	 *            Auslösende Exception
	 */
	public ConvertException(final String msg, final Throwable throwable) {
		super(new ErrorMessageBuilder(ERROR_TITLE, msg), throwable);
	}
}
