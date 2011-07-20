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
	private final Object value;

	/**
	 * Konstruktor zum Setzen des Attributes.
	 * 
	 * @param name
	 *            Name / Key
	 * @param value
	 *            Wert / Value
	 */
	public Attribute(final String name, final Object value) {
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
	public Object getValue() {
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Attribute other = (Attribute) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
