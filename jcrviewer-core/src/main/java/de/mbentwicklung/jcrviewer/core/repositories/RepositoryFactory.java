/**
 * 
 */
package de.mbentwicklung.jcrviewer.core.repositories;

import javax.jcr.Credentials;
import javax.jcr.Repository;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.core.TransientRepository;
import org.apache.poi.ss.formula.eval.NotImplementedException;

import de.mbentwicklung.jcrviewer.core.repositories.setups.JackrabbitSetup;
import de.mbentwicklung.jcrviewer.core.repositories.setups.Setup;

/**
 * The {@link RepositoryFactory} build a {@link Repository}.
 * 
 * @author Marc Bellmann
 */
public class RepositoryFactory {

	public static final Credentials DEFAULT_CREDENTIALS = new SimpleCredentials(
			"userId", "password".toCharArray());

	public static Repository createRepository(final Setup setup) {

		Repository repository = null;

		switch (setup.getRepositoryType()) {
		case JACKRABBIT:
			if (!(setup instanceof JackrabbitSetup)) {
				throw new IllegalArgumentException("RepositoryType is "
						+ setup.getRepositoryType().getClass()
						+ ", but Setup is " + setup.getClass());
			}
			JackrabbitSetup js = (JackrabbitSetup) setup;
			repository = new TransientRepository(js.getRepositoryXmlPath(),
					js.getRepositoryTmpPath());
			break;
		default:
			throw new NotImplementedException("Repository "
					+ setup.getRepositoryType() + " not implement!");
		}

		return repository;
	}
}
