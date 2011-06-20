package de.mbentwicklung.jcrviewer.core.tree;

import javax.jcr.LoginException;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import de.mbentwicklung.jcrviewer.core.converter.PropertyToStringConverter;
import de.mbentwicklung.jcrviewer.core.repositories.RepositoryFactory;
import de.mbentwicklung.jcrviewer.core.repositories.setups.Setup;

/**
 * 
 */

/**
 * @author Marc Bellmann
 * 
 */
public class NodeConverter {

	private final Repository repository;
	private final Setup setup;

	public NodeConverter(final Setup setup) {
		this.setup = setup;
		this.repository = RepositoryFactory.createRepository(setup);
	}

	public Node toRootNode() {
		Session session = null;
		Node rootNode = null;
		try {
			session = repository.login(new SimpleCredentials(setup
					.getUsername(), setup.getPassword().toCharArray()));

			rootNode = buildNode(session.getRootNode());

		} catch (LoginException e) {
			e.printStackTrace();
		} catch (RepositoryException e) {
			e.printStackTrace();
		} finally {
			if (session != null)
				session.logout();
		}

		return rootNode;
	}

	private Node buildNode(javax.jcr.Node rootNode) throws RepositoryException {
		Node node = new Node(rootNode.getPath());

		NodeIterator iterator = rootNode.getNodes();
		while (iterator.hasNext()) {
			node.addChildNode(buildNode(iterator.nextNode()));
		}

		PropertyIterator propertyIterator = rootNode.getProperties();
		while (propertyIterator.hasNext()) {
			Property property = propertyIterator.nextProperty();
			node.addAttribute(property.getName(),
					new PropertyToStringConverter(property).toString());
		}
		return node;
	}
}
