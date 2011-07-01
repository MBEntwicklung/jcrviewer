package de.mbentwicklung.jcrviewer.core.converter;

/**
 * Eine {@link ConvertException} tritt auf, wenn ein Fehler beim Umwandeln des Repositories in einen
 * Node auftritt. Sie kapselt JCR Exceptions damit diese Abhängigkeit nicht werden gegeben werden
 * muss.
 * 
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class ConvertException extends Exception {

	/** serialVersionUID */
	private static final long serialVersionUID = -8236951070813531757L;

	/**
	 * Konstruktor für Übergabe der Fehlernachricht und der auslösenden {@link Exception}
	 * 
	 * @param msg
	 *            Fehlernachricht
	 * @param throwable
	 *            Auslösende Exception
	 */
	public ConvertException(final String msg, final Throwable throwable) {
		super(msg, throwable);
	}
}
