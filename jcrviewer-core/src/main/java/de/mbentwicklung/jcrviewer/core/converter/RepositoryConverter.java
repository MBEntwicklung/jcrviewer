package de.mbentwicklung.jcrviewer.core.converter;

import javax.jcr.LoginException;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.version.VersionHistory;
import javax.jcr.version.VersionIterator;
import javax.jcr.version.VersionManager;

import de.mbentwicklung.jcrviewer.core.domains.Attribute;
import de.mbentwicklung.jcrviewer.core.domains.Node;
import de.mbentwicklung.jcrviewer.core.domains.Version;
import de.mbentwicklung.jcrviewer.core.repositories.RepositoryFactory;
import de.mbentwicklung.jcrviewer.core.repositories.setups.Setup;

/**
 * Der {@link RepositoryConverter} wandelt ein JCR {@link Repository} in einen Root Node um.
 * 
 * @see Node
 * @see Version
 * @see Attribute
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class RepositoryConverter {

	/** Erkennungsmerkmal eines Versionierten Nodes */
	private static final String MIX_VERSION = "jcr:versionHistory";

	/** JCR Repository */
	private final Repository repository;

	/** Login Session */
	private Session session;

	/** Repository Setup */
	private final Setup setup;

	/**
	 * Konstruktor zum Initialisieren des Repositories mit einem bestimmten Setup. Das Setup wird an
	 * die RepositoryFactory übergeben.
	 * 
	 * @param setup
	 *            Setup zum Initialisieren des {@link Repository}
	 */
	public RepositoryConverter(final Setup setup) {
		this.setup = setup;
		this.repository = RepositoryFactory.createRepository(setup);
	}

	/**
	 * Erstellt den Root Node des Repositories. Über den Root Node kann auf alle ausgelesen
	 * Informationen zugegriffen werden. Zum Erstellen des Root Nodes wird eine Verbindung mit dem
	 * Repository aufgebaut. Die Login Informationen werden aus dem {@link #setup} gelesen.
	 * 
	 * @return Der Root Node
	 * @throws ConvertException
	 *             Fehler bei Umwandlung von Repository to Node
	 */
	public Node buildRootNode() throws ConvertException {
		Node rootNode = null;
		try {
			session = repository.login(new SimpleCredentials(setup.getUsername(), setup
					.getPassword().toCharArray()));

			rootNode = buildNode(session.getRootNode());

		} catch (LoginException e) {
			throw new ConvertException("JCR Repository Login Error", e);
		} catch (RepositoryException e) {
			throw new ConvertException("JCR Repository Error", e);
		} finally {
			if (session != null)
				session.logout();
		}

		return rootNode;
	}

	/**
	 * Erstellt einen Node mit allen Versionen und ruft diese Funktion für alle untergeordneten
	 * Nodes auf. Diese Untergeordneten Nodes werden diesem Node, sobald sie erstellt wurden,
	 * hinzugefügt.
	 * <p />
	 * <b>Wichtig ist der Unterschied zwischen einem jcrNode und einem Anwendungs internen Node!</b>
	 * 
	 * @param jcrNode
	 *            Der JCR Node
	 * @return Ein Anwendungs interner Node
	 * @throws RepositoryException Fehler beim Umwandeln
	 */
	private Node buildNode(final javax.jcr.Node jcrNode) throws RepositoryException {
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
		} else {
			Version version = buildVersion(jcrNode);
			node.setBaseVersion(version);
			node.addVersion(version);
		}

		NodeIterator iterator = jcrNode.getNodes();
		while (iterator.hasNext()) {
			node.addChildNode(buildNode(iterator.nextNode()));
		}

		return node;
	}

	/**
	 * Erstellt eine Version aus einem jcrNode und füllt diesen mit {@link Attribute}s.
	 * <p />
	 * <b>Auch hier ist es wichtig, den Unterschied eines jcrNodes und einer jcrVersion mit einem
	 * Anwendungs internen Node und Version zu beachten!</b>
	 * 
	 * @param jcrNode
	 *            Der JCR Node
	 * @return Die Version
	 * @throws RepositoryException
	 *             Fehler beim Umwandeln
	 */
	private Version buildVersion(final javax.jcr.Node jcrNode) throws RepositoryException {
		String created = "";
		if (jcrNode instanceof javax.jcr.version.Version) {
			created = ((javax.jcr.version.Version) jcrNode).getCreated().getTime().toString();
		}
		Version version = new Version(jcrNode.getName(), created);

		PropertyIterator propertyIterator = jcrNode.getProperties();
		while (propertyIterator.hasNext()) {
			Property property = propertyIterator.nextProperty();
			Attribute attribute = new Attribute(property.getName(), new PropertyToStringConverter(
					property).getString());
			version.addAttribute(attribute);
		}
		return version;
	}
}
