/**
 * 
 */
package de.mbentwicklung.jcrviewer.swing.components;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import de.mbentwicklung.jcrviewer.core.domains.Node;

/**
 * @author Marc Bellmann
 * 
 */
@SuppressWarnings("serial")
public class VersionTable extends JTable {

	private final AttributeTable attributeTable;

	private Node selectedNode;
	
	/**
	 * @param nodeTree
	 * 
	 */
	public VersionTable(final Node node, final AttributeTable attributeTable) {
		super();
		this.attributeTable = attributeTable;
		
		updateTable(node);
	}

	public void updateTable(final Node node) {
		this.setModel(new VersionTableModel(node.getVersions()));
		selectedNode = node;
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				attributeTable.updateTable(getSelectedNode().getVersions().get(getSelectedRow()));
			}
		});
	}
	
	private Node getSelectedNode() {
		return selectedNode;
	}
}
