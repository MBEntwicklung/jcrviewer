package de.mbentwicklung.jcrviewer.core.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author Marc Bellmann
 * 
 */
public class Node implements Comparable<Node> {
	private final Object node;
	private Version baseVersion;
	private final List<Node> children;
	private final List<Version> versions;

	/**
	 * @param node
	 * @param children
	 */
	public Node(Object node) {
		super();
		this.node = node;
		this.children = new ArrayList<Node>();
		this.versions = new ArrayList<Version>();
	}

	public Object getNode() {
		return node;
	}

	public void addChildNode(final Node node) {
		children.add(node);
	}

	public List<Node> getChildren() {
		return children;
	}
	
	public void addVersion(final Version version) {
		versions.add(version);
	}

	public List<Version> getVersions() {
		return versions;
	}

	/**
	 * @return the baseVersion
	 */
	public Version getBaseVersion() {
		return baseVersion;
	}

	/**
	 * @param baseVersion the baseVersion to set
	 */
	public void setBaseVersion(Version baseVersion) {
		this.baseVersion = baseVersion;
	}

	@Override
	public String toString() {
		return node.toString();
	}

	public int compareTo(Node o) {
		return node.toString().compareToIgnoreCase(o.toString());
	}
}
