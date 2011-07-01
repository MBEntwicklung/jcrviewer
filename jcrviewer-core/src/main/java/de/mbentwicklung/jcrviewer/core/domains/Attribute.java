/**
 * 
 */
package de.mbentwicklung.jcrviewer.core.domains;

/**
 * Ein {@link Attribute} besteht aus genau einem Namen (Key) und einem Wert (Value). Dabei handelt es sich
 * um eine {@link javax.jcr.Property Property} bei JCR.
 * <p />
 * Mehrere Attribute sind Teil einer {@link Version}. Eine Liste mit Attributes können sortiert werden.
 * 
 * @see Version
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 * 
 */
public class Attribute implements Comparable<Attribute> {

	/** Der Name / Key des Attributes */
	private final String name;

	/** Der Wert / Value des Attributes */
	private final String value;

	/**
	 * Konstruktor zum Setzen des Attributes.
	 * 
	 * @param name
	 *            Name / Key
	 * @param value
	 *            Wert / Value
	 */
	public Attribute(final String name, final String value) {
		super();
		this.name = name;
		this.value = value;
	}

	/**
	 * Getter des Names / Keys
	 * 
	 * @return Name / Key
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter des Wert / Values
	 * 
	 * @return Wert / Value
	 */
	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return name + ": " + value;
	}

	@Override
	public int compareTo(final Attribute attribute) {
		return name.compareToIgnoreCase(attribute.name);
	}
}
