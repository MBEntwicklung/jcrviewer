package de.mbentwicklung.jcrviewer.core.repositories;

import javax.jcr.Credentials;
import javax.jcr.Repository;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.core.TransientRepository;

import de.mbentwicklung.jcrviewer.core.repositories.setups.JackrabbitSetup;
import de.mbentwicklung.jcrviewer.core.repositories.setups.Setup;

/**
 * Diese Utility Class bieten Hilfsfunktionen zum Erstellen eines {@link Repository}.
 * 
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public final class RepositoryFactory {

	/**
	 * Kein Konstruktor
	 */
	private RepositoryFactory() {
	}

	/**
	 * Standard Anmeldedaten. Username: "userId" Password: "password"
	 */
	public static final Credentials DEFAULT_CREDENTIALS = new SimpleCredentials("userId",
			"password".toCharArray());

	/**
	 * Diese Funktion übernimmt die Aufgabe des Erstellens von {@link Repository}. Derzeit werden
	 * folgende Repositories unterstüzt.
	 * <ul>
	 * <li>Jackrabbit</li>
	 * </ul>
	 * Für Informationen zum Erstellen wird ein {@link Setup} Objekt benötigt. In diesem wird auch
	 * übergeben was für ein Repository erstellt werden soll.
	 * 
	 * @param setup
	 *            Setuo mit allen Informationen zum Erstellen
	 * @return Ein Erstelltes Repository
	 */
	public static Repository createRepository(final Setup setup) {

		Repository repository = null;

		switch (setup.getRepositoryType()) {

		case JACKRABBIT:
			if (!(setup instanceof JackrabbitSetup)) {
				throw new IllegalArgumentException("RepositoryType is " + setup.getRepositoryType()
						+ ", but Setup is " + setup.getClass());
			}
			JackrabbitSetup js = (JackrabbitSetup) setup;
			repository = new TransientRepository(js.getRepositoryXmlFile(),
					js.getRepositoryDirFile());
			break;
		default:
			throw new UnsupportedOperationException("Repository " + setup.getRepositoryType()
					+ " not implement!");
		}

		return repository;
	}
}
