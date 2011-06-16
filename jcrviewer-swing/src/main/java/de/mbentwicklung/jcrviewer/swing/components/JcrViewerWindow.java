/**
 * 
 */
package de.mbentwicklung.jcrviewer.swing.components;

import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import de.mbentwicklung.jcrviewer.core.repositories.RepositoryFactory;
import de.mbentwicklung.jcrviewer.core.tree.Node;
import de.mbentwicklung.jcrviewer.core.tree.NodeConverter;

/**
 * @author Marc Bellmann
 * 
 */
@SuppressWarnings("serial")
public class JcrViewerWindow extends JFrame {

	private final JSplitPane pane;
	private NodeTree nodeTree;
	private AttributeTable attributeTable;

	public JcrViewerWindow() {
		super("JcrViewer");

		pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);

		setExtendedState(Frame.MAXIMIZED_BOTH);
		
		initializePane();

		add(pane);
		
		pack();
		setVisible(true);
	}

	/**
	 * 
	 */
	private void initializePane() {

		NodeConverter nodeConverter = new NodeConverter(
				RepositoryFactory.createRepository());

		Node rootNode = nodeConverter.toRootNode();

		attributeTable = new AttributeTable(rootNode);
		nodeTree = new NodeTree(rootNode, attributeTable);

		pane.setLeftComponent(new JScrollPane(nodeTree));
		pane.setRightComponent(new JScrollPane(attributeTable));

		pane.setPreferredSize(new Dimension(500, getHeight()));
		
	}
}
