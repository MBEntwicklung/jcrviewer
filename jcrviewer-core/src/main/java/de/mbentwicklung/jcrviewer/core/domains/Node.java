/**
 * 
 */
package de.mbentwicklung.jcrviewer.core.domains;

import java.util.ArrayList;
import java.util.List;

/**
 * Ein Node besteht aus einem Namen, einer Liste von {@link Version}s, einer Liste von
 * untergeordneten Nodes und eine Baseversion. Die Baseversion ist die aktuelle Version des Nodes.
 * Mithilfe der Liste von untergeordneten Nodes kann ein ausgelesenes Repository als Tree
 * dargestellt werden. Beim Erstellen der Nodes muss nur der Root Node gespeichert werden. Mit Hilfe
 * diesem kann auf alle untergeordneten Nodes zugegriffen werden. Es können jedoch NICHT mehrere
 * Root Nodes existieren.
 * 
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 * 
 */
public class Node implements Comparable<Node> {

	/** Name des Nodes */
	private final String name;

	/** Aktuelleste Version des Nodes */
	private Version baseVersion;

	/** Liste mit allen untergeordneten Nodes */
	private final List<Node> children;

	/** Liste mit allen existierenden {@link Version}s (inkl. {@link #baseVersion}) */
	private final List<Version> versions;

	/**
	 * Konstruktor für Übergabe des Node Names. Außerdem werden alle Listen initialisiert. Objekte
	 * werden in den dazugehörigen Funktionen {@link #addChildNode(Node)} und
	 * {@link #addVersion(Version)} hinzugefügt. Mit der Funktion {@link #setBaseVersion(Version)}
	 * kann die BaseVersion gesetzt werden.
	 * 
	 * @param name
	 *            Name des Nodes
	 */
	public Node(final String name) {
		super();
		this.name = name;
		this.children = new ArrayList<Node>();
		this.versions = new ArrayList<Version>();
	}

	/**
	 * Getter für den Name des Nodes
	 * 
	 * @return Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Zum Hinzufügen eines Untergeordneten Nodes
	 * 
	 * @param node
	 *            Childnode
	 */
	public void addChildNode(final Node node) {
		children.add(node);
	}

	/**
	 * Getter für die Liste der Untergeordneten Nodes
	 * 
	 * @return childnodes
	 */
	public List<Node> getChildren() {
		return children;
	}

	/**
	 * Zum Hinzufügen der existierenden {@link Version}s
	 * 
	 * @param version
	 *            Version des Nodes
	 */
	public void addVersion(final Version version) {
		versions.add(version);
	}

	/**
	 * Getter für die Liste der existierenden {@link Version}s
	 * 
	 * @return existierende {@link Version}s
	 */
	public List<Version> getVersions() {
		return versions;
	}

	/**
	 * Getter für die aktuellste Version
	 * 
	 * @return the baseVersion
	 */
	public Version getBaseVersion() {
		return baseVersion;
	}

	/**
	 * Setter für die aktuelleste Version
	 * 
	 * @param baseVersion
	 *            the baseVersion to set
	 */
	public void setBaseVersion(final Version baseVersion) {
		this.baseVersion = baseVersion;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int compareTo(final Node o) {
		return name.compareToIgnoreCase(o.getName());
	}
}
