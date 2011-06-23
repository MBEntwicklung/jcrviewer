/**
 * 
 */
package de.mbentwicklung.jcrviewer.swing.components;

import java.util.Collections;
import java.util.List;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

import de.mbentwicklung.jcrviewer.core.tree.Node;

/**
 * @author Marc Bellmann
 * 
 */
@SuppressWarnings("serial")
public class NodeTree extends JTree {

	private final VersionTable versionTable;
	private final AttributeTable attributeTable;

	public NodeTree(final Node rootNode, final VersionTable versionTable,
			final AttributeTable attributeTable) {
		super(toMutableTreeNode(rootNode));

		this.versionTable = versionTable;
		this.attributeTable = attributeTable;

		getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		addTreeSelectionListener();
	}

	public void addTreeSelectionListener() {
		super.addTreeSelectionListener(new TreeSelectionListener() {

			public void valueChanged(TreeSelectionEvent e) {
				if (getLastSelectedPathComponent() instanceof DefaultMutableTreeNode) {
					DefaultMutableTreeNode mutableTreeNode = (DefaultMutableTreeNode) getLastSelectedPathComponent();
					if (mutableTreeNode.getUserObject() instanceof Node) {
						Node node = (Node) mutableTreeNode.getUserObject();
						versionTable.updateTable(node);
					}
				}
			}
		});
	}

	private static MutableTreeNode toMutableTreeNode(final Node rootNode) {
		DefaultMutableTreeNode tree = new DefaultMutableTreeNode(rootNode, !rootNode.getChildren()
				.isEmpty());

		List<Node> childNodes = rootNode.getChildren();
		Collections.sort(childNodes);
		for (Node childNode : childNodes) {
			tree.add(toMutableTreeNode(childNode));
		}

		return tree;
	}
}
