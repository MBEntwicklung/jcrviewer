/**
 * 
 */
package de.mbentwicklung.jcrviewer.testutils;

import java.io.File;

import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.Session;

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

	public static void main(String[] args) throws Exception {
		Session session = null;
		try {
			final Logger logger = LoggerFactory
					.getLogger(ExampleRepository.class);
			logger.info("build Repository");
			JackrabbitSetup jackrabbitSetup = new JackrabbitSetup(new File(
					"/tmp/repository.xml"), new File("/tmp/repository/"),
					"default", "default");

			Repository repository = RepositoryFactory
					.createRepository(jackrabbitSetup);
			session = repository.login(RepositoryFactory.DEFAULT_CREDENTIALS);

			addParentNodes(session, NODE_PATH);

			for (int i = 0; i < 100; i++) {
				Node node = session.getRootNode().addNode(
						NODE_PATH + "/node" + i);

				node.setProperty("Irgendein key", "irgendein Value");
				node.setProperty("String", "String zum Testen");
				node.setProperty("id", i);
			}
			session.save();
		} finally {
			if (session != null)
				session.logout();
		}

	}

	private static void addParentNodes(Session session, String nodePath)
			throws Exception {

		String[] nodes = nodePath.split("/");
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < nodes.length; i++) {
			builder.append(nodes[i]).append("/");
			if (!session.nodeExists("/" + builder.toString()))
				session.getRootNode().addNode(builder.toString());
		}
	}
}