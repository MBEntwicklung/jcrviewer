/**
 * 
 */
package de.mbentwicklung.jcrviewer.testutils;

import java.io.File;

import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.version.Version;
import javax.jcr.version.VersionHistory;
import javax.jcr.version.VersionIterator;
import javax.jcr.version.VersionManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.mbentwicklung.jcrviewer.core.repositories.RepositoryFactory;
import de.mbentwicklung.jcrviewer.core.repositories.setups.JackrabbitSetup;

/**
 * @author Marc Bellmann
 * 
 */
public class ExampleRepository {

	private static final String NODE_PATH = "de/mbentwicklung/jcrviewer/testnodes";

	public static final JackrabbitSetup JACKRABBIT_SETUP = new JackrabbitSetup(new File("/tmp/repository.xml"), new File("/tmp/repository/"), "default", "default");
	
	public static void main(String[] args) throws Exception {
		Session session = null;
		try {
			Repository repository = RepositoryFactory.createRepository(JACKRABBIT_SETUP);
			session = repository.login(RepositoryFactory.DEFAULT_CREDENTIALS);
			// versioningBasics(session.getRootNode(), session);
			final Logger logger = LoggerFactory.getLogger(ExampleRepository.class);
			logger.info("build Repository");

			VersionManager versionManager = session.getWorkspace().getVersionManager();

			addParentNodes(session, NODE_PATH);

			for (int i = 0; i < 100; i++) {
				Node node = session.getRootNode().addNode(NODE_PATH + "/node" + i,
						"nt:unstructured");

				node.addMixin("mix:versionable");
				node.setProperty("Irgendein key", "irgendein Value");
				node.setProperty("String", "String zum Testen");
				node.setProperty("id", i);
			}

			session.save();

			for (int i = 0; i < 100; i++) {
				Node node = session.getRootNode().getNode(NODE_PATH + "/node" + i);
				for (int j = 0; j < 10; j++) {
					versionManager.checkout(node.getPath());
					node.setProperty("Version", j);
					session.save();
					versionManager.checkin(node.getPath());
				}
				session.save();
			}
		} finally {
			if (session != null)
				session.logout();
		}

	}

	public static void versioningBasics(Node parentNode, Session session)
			throws RepositoryException {
		// create versionable node
		Node n = parentNode.addNode("childNode", "nt:unstructured");
		n.addMixin("mix:versionable");
		n.setProperty("anyProperty", "Blah");
		session.save();
		VersionManager versionManager = session.getWorkspace().getVersionManager();

		// add new version
		Node child = parentNode.getNode("childNode");
		for (int i = 0; i < 10; i++) {
			versionManager.checkout(child.getPath());
			child.setProperty("anyProperty", "Blah2");
			session.save();
			versionManager.checkin(child.getPath());
		}

		// print version history
		VersionHistory history = versionManager.getVersionHistory(child.getPath());
		for (VersionIterator it = history.getAllVersions(); it.hasNext();) {
			Version version = (Version) it.next();
			System.out.println(version.getCreated().getTime());
		}
	}

	private static void addParentNodes(Session session, String nodePath) throws Exception {

		String[] nodes = nodePath.split("/");
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < nodes.length; i++) {
			builder.append(nodes[i]).append("/");
			if (!session.nodeExists("/" + builder.toString()))
				session.getRootNode().addNode(builder.toString());
		}
	}
}
