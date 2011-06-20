/**
 * 
 */
package de.mbentwicklung.jcrviewer.core.converter;

import javax.jcr.Property;
import javax.jcr.PropertyType;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;

/**
 * @author marc
 *
 */
public class PropertyToStringConverter extends ToStringConverter {

	/**
	 * @param string
	 * @throws RepositoryException 
	 * @throws ValueFormatException 
	 */
	public PropertyToStringConverter(final Property string) throws ValueFormatException, RepositoryException {
		super(propertyToString(string));
	}
	

	private static String propertyToString(final Property property)
			throws ValueFormatException, RepositoryException {

		if (property.isMultiple()) {
			return property.getValues().toString();
		}

		switch (property.getType()) {

		case PropertyType.BINARY:
			return property.getBinary().getStream().toString();

		case PropertyType.BOOLEAN:
			return property.getBoolean() + "";

		case PropertyType.DATE:
			return property.getDate().toString();

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

}
