/**
 * 
 */
package de.mbentwicklung.jcrviewer.core.converter;

import java.util.Formatter;

import javax.jcr.Property;
import javax.jcr.PropertyType;
import javax.jcr.RepositoryException;

/**
 * Wandelt eine JCR {@link Property} in einen String um. Dieser String wird in der Anwendung
 * ausgegeben.
 * 
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class PropertyToStringConverter {

	/** Umgewandelte Property */
	private final Object value;

	/**
	 * Konstruktor zum Übergeben der Property und Umwandeln in den String
	 * 
	 * @param property
	 *            Property
	 * @throws RepositoryException
	 *             JCR Exception
	 */
	public PropertyToStringConverter(final Property property) throws RepositoryException {
		super();

		value = propertyToString(property);
	}

	/**
	 * Wandelt die Property in einen String um.
	 * 
	 * @param property
	 *            {@link Property}
	 * @return {@link String}
	 * @throws RepositoryException
	 *             JCR Exception
	 */
	private static Object propertyToString(final Property property) throws RepositoryException {

		if (property == null) {
			throw new IllegalArgumentException("property is null");
		}

		if (property.isMultiple()) {
			return property.getValues().toString();
		}

		switch (property.getType()) {

		case PropertyType.BINARY:
			return property.getBinary().getStream();

		case PropertyType.BOOLEAN:
			return property.getBoolean() + "";

		case PropertyType.DATE:
			Formatter formatter = new Formatter();
			formatter.format("%tF", property.getDate());
			return formatter.out().toString();

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

	/**
	 * Gibt den umgewandelten String zurück
	 * 
	 * @return Property as String
	 */
	public Object getValue() {
		return value;
	}
}
