package de.mbentwicklung.jcrviewer.core.repositories;

import java.io.File;

import javax.jcr.Repository;

import org.apache.jackrabbit.core.TransientRepository;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import de.mbentwicklung.jcrviewer.core.repositories.setups.JackrabbitSetup;
import de.mbentwicklung.jcrviewer.core.repositories.setups.Setup;

/**
 * Testet {@link RepositoryFactory}
 * 
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class RepositoryFactoryTest {

	/** Erwarte Exception */
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/** Rep XML File */
	private static final File JACK_XML_FILE = new File("test.xml");

	/** Rep dir */
	private static final File JACK_DIR_FILE = new File("test");

	/** JCR User */
	private static final String JCR_LOGIN_USER = "username";

	/** JCR Password */
	private static final String JCR_LOGIN_PASS = "password";

	/* JACKRABBIT */

	/**
	 * Testet Konstruktor
	 * 
	 * @throws Exception
	 *             Fehler
	 */
	@Test
	public void testJackrabbitStandardKonstruktor() throws Exception {
		Setup setup = new JackrabbitSetup(JACK_XML_FILE, JACK_DIR_FILE, JCR_LOGIN_USER,
				JCR_LOGIN_PASS);

		Repository repository = RepositoryFactory.createRepository(setup);

		Assert.assertEquals(TransientRepository.class, repository.getClass());
	}

	/**
	 * Testet Setter
	 * 
	 * @throws Exception
	 *             Fehler
	 */
	@Test
	public void testJackrabbitStandardSetter() throws Exception {
		JackrabbitSetup setup = new JackrabbitSetup();
		setup.setRepositoryDirFile(JACK_DIR_FILE);
		setup.setRepositoryXmlFile(JACK_XML_FILE);
		setup.setPassword(JCR_LOGIN_PASS);
		setup.setUsername(JCR_LOGIN_USER);

		Repository repository = RepositoryFactory.createRepository(setup);

		Assert.assertEquals(TransientRepository.class, repository.getClass());
	}

	/**
	 * Testet Fehlerhaftes Setup
	 * 
	 * @throws Exception
	 *             Fehler
	 */
	@Test
	public void testIncorrectSetupWithJackrabbitType() throws Exception {
		thrown.expect(IllegalArgumentException.class);

		Setup setup = new IncorrectSetup();
		setup.setRepositoryType(SupportedRepositories.JACKRABBIT);

		RepositoryFactory.createRepository(setup);
	}

	class IncorrectSetup extends Setup {
	}
}
