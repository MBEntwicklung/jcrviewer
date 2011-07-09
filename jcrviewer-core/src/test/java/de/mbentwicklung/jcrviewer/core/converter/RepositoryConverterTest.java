/**
 * 
 */
package de.mbentwicklung.jcrviewer.core.converter;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Credentials;
import javax.jcr.NodeIterator;
import javax.jcr.Repository;
import javax.jcr.Session;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Assert;
import org.junit.Test;

import de.mbentwicklung.jcrviewer.core.domains.Node;
import de.mbentwicklung.jcrviewer.core.domains.Version;
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

	/** jMock */
	private Mockery mockContext = new JUnit4Mockery();

	/**
	 * Testet das auslesen eines Repositories
	 * 
	 * @throws Exception
	 *             Fehler?
	 */
	@Test
	public void testReadInformationFromOneVersionRootNode() throws Exception {
		final Repository repository = mockContext.mock(Repository.class);
		final Session session = mockContext.mock(Session.class);
		final javax.jcr.Node jcrNode = mockContext.mock(javax.jcr.Node.class);
		final NodeIterator nodeIterator = mockContext.mock(NodeIterator.class);

		final String nodeName = "Node Name";
		final String versName = "Version Node";
		final Version baseVersion = new Version(versName, "Created");
		final List<Version> versions = new ArrayList<Version>();
		final List<Node> children = new ArrayList<Node>();

		converter = new RepositoryConverter(ExampleRepository.JACKRABBIT_SETUP, repository);

		mockContext.checking(new Expectations() {
			{
				oneOf(repository).login(with(any(Credentials.class)));
				will(returnValue(session));
				
				oneOf(session).getRootNode();
				will(returnValue(jcrNode));
				
				oneOf(jcrNode).getPath();
				will(returnValue(nodeName));
				
				oneOf(jcrNode).hasProperty(with(any(String.class)));
				will(returnValue(false));
				
				oneOf(jcrNode).getName();
				will(returnValue(versName));
				
				oneOf(jcrNode).getProperties();
				
				oneOf(jcrNode).getNodes();
				will(returnValue(nodeIterator));

				one(nodeIterator).hasNext();
//				will(returnValue(false));
//
//				one(nodeIterator).hasNext();
//				will(returnValue(true));
				
				oneOf(session).logout();
			}
		});

		Node rootNode = converter.buildRootNode();

		Assert.assertEquals(nodeName, rootNode.getName());
//		Assert.assertEquals(baseVersion, rootNode.getBaseVersion());
//		assertChildren(children, rootNode.getChildren());
//		assertVersions(versions, rootNode.getVersions());

		mockContext.assertIsSatisfied();
	}

	/**
	 * Zwei Listen mit Versions gleich?
	 * 
	 * @param expectedVersions
	 *            Erwartet
	 * @param actualVersions
	 *            Resultat
	 */
	private void assertVersions(final List<Version> expectedVersions,
			final List<Version> actualVersions) {
		for (Version version : expectedVersions) {
			Assert.assertTrue("Version " + version + " not in  the actual List",
					actualVersions.contains(version));
		}
		for (Version version : actualVersions) {
			Assert.assertTrue("Version " + version + " not in  the expected List",
					expectedVersions.contains(version));
		}
	}

	/**
	 * Zwei Listen mit Nodes gleich?
	 * 
	 * @param expectedNodes
	 *            Erwartet
	 * @param actualNodes
	 *            Resultat
	 */
	private void assertChildren(final List<Node> expectedNodes, final List<Node> actualNodes) {
		for (Node node : expectedNodes) {
			Assert.assertTrue("Node " + node + " not in  the actual List",
					actualNodes.contains(node));
		}

		for (Node node : actualNodes) {
			Assert.assertTrue("Node " + node + " not in  the expected List",
					expectedNodes.contains(node));
		}
	}
}
