/**
 * 
 */
package de.mbentwicklung.jcrviewer.swing.components;

import javax.swing.JTable;

import de.mbentwicklung.jcrviewer.core.tree.Node;

/**
 * @author Marc Bellmann
 * 
 */
@SuppressWarnings("serial")
public class AttributeTable extends JTable {

	/**
	 * @param nodeTree
	 * 
	 */
	public AttributeTable(final Node node) {
		super();

		updateTable(node);
	}

	public void updateTable(final Node node) {
		this.setModel(new AttributeTableModel(node.getAttributes()));
	}
}
