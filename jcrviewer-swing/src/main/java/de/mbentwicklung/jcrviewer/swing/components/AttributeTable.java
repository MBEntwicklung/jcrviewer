/**
 * 
 */
package de.mbentwicklung.jcrviewer.swing.components;

import javax.swing.JTable;

import de.mbentwicklung.jcrviewer.core.domains.Version;

/**
 * @author Marc Bellmann
 * 
 */
@SuppressWarnings("serial")
public class AttributeTable extends JTable {

	public AttributeTable(final Version version) {
		super();

		updateTable(version);
	}

	public void updateTable(final Version version) {
		this.setModel(new AttributeTableModel(version.getAttributes()));
	}

}
