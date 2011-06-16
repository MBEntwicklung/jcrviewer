package de.mbentwicklung.jcrviewer.core.tree;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
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
	private final List<Node> children;
	private final List<Attribute> attributes;

	/**
	 * @param node
	 * @param children
	 */
	public Node(Object node) {
		super();
		this.node = node;
		this.children = new ArrayList<Node>();
		this.attributes = new ArrayList<Attribute>();
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
	
	public void addAttribute(final String name, final String value) {
		attributes.add(new Attribute(name, value));
	}
	
	public List<Attribute> getAttributes() {
		return attributes;
	}
	
	@Override
	public String toString() {
		return node.toString();
	}

	public int compareTo(Node o) {
		return node.toString().compareToIgnoreCase(o.toString());
	}
}
