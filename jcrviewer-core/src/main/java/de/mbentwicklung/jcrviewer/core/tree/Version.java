/**
 * 
 */
package de.mbentwicklung.jcrviewer.core.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Marc Bellmann
 *
 */
public class Version {

	private final List<Attribute> attributes;
	private final String name;
	private final String created;
	
	/**
	 * @param node
	 */
	public Version(final String name, final String created) {
		this.attributes = new ArrayList<Attribute>();
		this.name = name;
		this.created = created;
	}

	public String getName() {
		return name;
	}
	
	public String getCreated() {
		return created;
	}
	
	public void addAttribute(final String name, final String value) {
		attributes.add(new Attribute(name, value));
	}
	
	public List<Attribute> getAttributes() {
		Collections.sort(attributes);
		return attributes;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Version [name=" + name + ", attributes=" + attributes + "]";
	}
}
