package de.mbentwicklung.jcrviewer.core.tree;

import javax.jcr.LoginException;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.ValueFormatException;
import javax.jcr.version.VersionHistory;
import javax.jcr.version.VersionIterator;
import javax.jcr.version.VersionManager;

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

	private static final String MIX_VERSION = "jcr:versionHistory";
	private final Repository repository;
	private Session session;
	private final Setup setup;

	public NodeConverter(final Setup setup) {
		this.setup = setup;
		this.repository = RepositoryFactory.createRepository(setup);
	}

	public Node toRootNode() {
		Node rootNode = null;
		try {
			session = repository.login(new SimpleCredentials(setup.getUsername(), setup
					.getPassword().toCharArray()));

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

	private Node buildNode(javax.jcr.Node jcrNode) throws RepositoryException {
		Node node = new Node(jcrNode.getPath());

		if (jcrNode.hasProperty(MIX_VERSION)) {
			VersionManager versionManager = session.getWorkspace().getVersionManager();
			VersionHistory versionHistory = versionManager.getVersionHistory(jcrNode.getPath());
			VersionIterator versionIterator = versionHistory.getAllVersions();
			while (versionIterator.hasNext()) {
				javax.jcr.version.Version jcrVersion = versionIterator.nextVersion();

				node.addVersion(buildVersion(jcrVersion));
			}
			node.setBaseVersion(buildVersion(versionManager.getBaseVersion(jcrNode.getPath())));
		}
		
		Version version = buildVersion(jcrNode);
		node.setBaseVersion(version);
		node.addVersion(version);

		NodeIterator iterator = jcrNode.getNodes();
		while (iterator.hasNext()) {
			node.addChildNode(buildNode(iterator.nextNode()));
		}

		return node;
	}

	private Version buildVersion(javax.jcr.Node jcrNode) throws RepositoryException {
		String created = "";
		if (jcrNode instanceof javax.jcr.version.Version) {
			created = ((javax.jcr.version.Version) jcrNode).getCreated().getTime().toString();
		}
		Version version = new Version(jcrNode.getName(), created);

		PropertyIterator propertyIterator = jcrNode.getProperties();
		while (propertyIterator.hasNext()) {
			Property property = propertyIterator.nextProperty();
			version.addAttribute(property.getName(),
					new PropertyToStringConverter(property).toString());
		}
		return version;
	}
}
