/**
 * 
 */
package de.mbentwicklung.jcrviewer.core.converter;

/**
 * @author marc
 * 
 */
public abstract class ToStringConverter {

	private final String string;

	/**
	 * 
	 */
	public ToStringConverter(final String string) {
		this.string = string;
	}

	@Override
	public String toString() {
		return string;
	}

}
