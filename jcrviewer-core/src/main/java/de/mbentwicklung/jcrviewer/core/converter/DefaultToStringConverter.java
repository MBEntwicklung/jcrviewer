/**
 * 
 */
package de.mbentwicklung.jcrviewer.core.converter;

/**
 * @author Marc Bellmann
 * 
 */
public class DefaultToStringConverter extends ToStringConverter {

	public DefaultToStringConverter(final Object object) {
		super(object.toString());
	}

}
