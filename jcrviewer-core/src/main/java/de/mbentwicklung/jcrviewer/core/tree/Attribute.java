/**
 * 
 */
package de.mbentwicklung.jcrviewer.core.tree;

/**
 * @author Marc Bellmann
 * 
 */
public class Attribute implements Comparable<Attribute> {

	private final String name;
	private final String value;

	/**
	 * @param name
	 * @param value
	 */
	public Attribute(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	@Override
	public int compareTo(Attribute attribute) {
		return name.compareToIgnoreCase(attribute.name);
	}
}
