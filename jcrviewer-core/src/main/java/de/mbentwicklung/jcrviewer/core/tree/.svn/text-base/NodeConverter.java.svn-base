package de.mbentwicklung.jcrviewer.core.tree;

import javax.jcr.LoginException;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.PropertyType;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.ValueFormatException;

import de.mbentwicklung.jcrviewer.core.repositories.RepositoryFactory;

/**
 * 
 */

/**
 * @author Marc Bellmann
 * 
 */
public class NodeConverter {

	private final Repository repository;

	public NodeConverter(final Repository repository) {
		this.repository = repository;
	}

	public Node toRootNode() {
		Session session = null;
		Node rootNode = null;
		try {
			session = repository.login(RepositoryFactory.DEFAULT_CREDENTIALS);

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
			node.addAttribute(property.getName(), toString(property));
		}
		return node;
	}

	private String toString(final Property property)
			throws ValueFormatException, RepositoryException {

		if (property.isMultiple()) {
			return property.getValues().toString();
		}
		
		switch (property.getType()) {

		case PropertyType.BINARY:
			return property.getBinary().getStream().toString();

		case PropertyType.BOOLEAN:
			return property.getBoolean() + "";

		case PropertyType.DATE:
			return property.getDate().toString();

		case PropertyType.DECIMAL:
			return property.getDecimal().toString();

		case PropertyType.DOUBLE:
			return property.getDouble() + "";

		case PropertyType.LONG:
			return property.getLong() + "";

		case PropertyType.NAME:
			return property.getName();

		case PropertyType.PATH:
			return property.getPath();

		case PropertyType.STRING:
			return property.getString();

		default:
			return "unknown Type";
		}
	}
}
