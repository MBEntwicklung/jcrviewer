/**
 * 
 */
package de.mbentwicklung.jcrviewer.core.converter;

import org.junit.Before;
import org.junit.Test;

import de.mbentwicklung.jcrviewer.testutils.ExampleRepository;

/**
 * Test {@link RepositoryConverterTest}
 * 
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 * 
 */
public class RepositoryConverterTest {

	/** Test Objekt */
	private RepositoryConverter converter;

	/**
	 * init Test Repository
	 * 
	 * @throws Exception
	 *             Fehler?
	 */
	public RepositoryConverterTest() throws Exception {
		ExampleRepository.main(new String[0]);
	}

	/**
	 * init
	 * 
	 * @throws java.lang.Exception
	 *             Fehler?
	 */
	@Before
	public void setUp() throws Exception {
		converter = new RepositoryConverter(ExampleRepository.JACKRABBIT_SETUP);
	}

	/**
	 * Startet Umwandlung eines Test Repositories
	 * 
	 * @throws Exception
	 *             Fehler?
	 */
	@Test
	public void testTestBuildRootNode() throws Exception {
		converter.buildRootNode();
	}
}
