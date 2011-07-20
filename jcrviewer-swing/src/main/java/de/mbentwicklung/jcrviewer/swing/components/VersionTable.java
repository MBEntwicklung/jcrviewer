/**
 * 
 */
package de.mbentwicklung.jcrviewer.swing.components;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTable;

import de.mbentwicklung.jcrviewer.core.domains.Node;
import de.mbentwicklung.jcrviewer.core.domains.Version;

/**
 * @author Marc Bellmann
 * 
 */
@SuppressWarnings("serial")
public class VersionTable extends JTable {

	private final AttributeTable attributeTable;

	private List<Version> versions;

	/**
	 * @param nodeTree
	 * 
	 */
	public VersionTable(final Node node, final AttributeTable attributeTable) {
		super();
		this.attributeTable = attributeTable;

		updateTable(node);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (getSelectedRow() > 0 && getSelectedRow() < versions.size())
					getAttributeTable().updateTable(versions.get(getSelectedRow()));
			}
		});
	}

	public void updateTable(final Node node) {
		versions = node.getVersions();
		this.setModel(new VersionTableModel(versions));
	}
	
	public AttributeTable getAttributeTable() {
		return attributeTable;
	}
}
