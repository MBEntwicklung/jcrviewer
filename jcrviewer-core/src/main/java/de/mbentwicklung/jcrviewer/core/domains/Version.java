/**
 * 
 */
package de.mbentwicklung.jcrviewer.core.domains;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Eine Version besteht aus mehreren {@link Attribute}s, einem Name und einem Erstelldatum. Zu einem
 * {@link Node} können mehrere {@link Version}s existieren. Eine Version entspricht der JCR
 * {@link javax.jcr.version.Version Version}.
 * 
 * @see Node
 * @see Attribute
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public class Version {

	/** Liste mit {@link Attribute}s */
	private final List<Attribute> attributes;

	/** Name der Version */
	private final String name;

	/** Erstelldatum */
	private final String created;

	/**
	 * Konstruktor zum Zuweisen des Namens und des Erstelldatums. Außerdem wird die Liste für die
	 * {@link Attribute}s initialisiert. Attribute können mit der
	 * {@link #addAttribute(String, String)} Funktion hinzugefügt werden.
	 * 
	 * @param name
	 *            Name
	 * @param created
	 *            Erstelldatum
	 */
	public Version(final String name, final String created) {
		this.attributes = new ArrayList<Attribute>();
		this.name = name;
		this.created = created;
	}

	/**
	 * Getter für den Name der Version
	 * 
	 * @return Name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter für das Erstelldatum der Version
	 * 
	 * @return Erstelldatum
	 */
	public String getCreated() {
		return created;
	}

	/**
	 * Zum Hinzufügen von neuen {@link Attribute}s zur Version.
	 * 
	 * @param attribute
	 *            Das neue Attribute
	 */
	public void addAttribute(final Attribute attribute) {
		attributes.add(attribute);
	}

	/**
	 * Getter für die Liste der Attribute die zu der Version gehören. Die Liste wird sortiert zurück
	 * übergeben.
	 * 
	 * @return Sortierte Liste mit {@link Attribute}s
	 */
	public List<Attribute> getAttributes() {
		Collections.sort(attributes);
		return attributes;
	}

	@Override
	public String toString() {
		return "Version [name=" + name + ", attributes=" + attributes + "]";
	}
}
