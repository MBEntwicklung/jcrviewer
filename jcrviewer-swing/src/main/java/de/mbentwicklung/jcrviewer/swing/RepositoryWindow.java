/**
 * 
 */
package de.mbentwicklung.jcrviewer.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import de.mbentwicklung.jcrviewer.core.repositories.setups.Setup;
import de.mbentwicklung.jcrviewer.core.tree.Node;
import de.mbentwicklung.jcrviewer.core.tree.NodeConverter;
import de.mbentwicklung.jcrviewer.core.tree.Version;
import de.mbentwicklung.jcrviewer.swing.components.AttributeTable;
import de.mbentwicklung.jcrviewer.swing.components.MenuPanel;
import de.mbentwicklung.jcrviewer.swing.components.NodeTree;
import de.mbentwicklung.jcrviewer.swing.components.VersionTable;

/**
 * @author marc
 * 
 */
public class RepositoryWindow extends JFrame {

	/** serialVersionUID */
	private static final long serialVersionUID = -6578764732683432196L;
	private NodeTree nodeTree;
	private AttributeTable attributeTable;
	private VersionTable versionTable;
	private final JSplitPane pane;
	private JPanel informationPanel;


	private Setup setup;

	/**
	 * 
	 */
	public RepositoryWindow() {
		super("JcrViewer");

		setExtendedState(Frame.MAXIMIZED_BOTH);
		setPreferredSize(new Dimension(1000, 800));

		pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		updateRepositoryPanel();

		add(buildMenuPanel(), BorderLayout.PAGE_START);
		add(pane);

		addWindowListener(new WindowListener());

		pack();
		setVisible(true);
	}

	private Component buildMenuPanel() {
		return new MenuPanel(new MenuPanel.RepositoryConnectEvent() {
			@Override
			public void connectWith(Setup setup) {
				setSetup(setup);

			}
		});
	}

	/**
	 * 
	 */
	private void updateRepositoryPanel() {

		if (setup != null) {
			NodeConverter nodeConverter = new NodeConverter(setup);
			Node rootNode = nodeConverter.toRootNode();
			Version rootVersion = rootNode.getBaseVersion();
			
			attributeTable = new AttributeTable(rootVersion);
			versionTable = new VersionTable(rootNode, attributeTable);
			nodeTree = new NodeTree(rootNode, versionTable, attributeTable);
			informationPanel  = new JPanel(new GridLayout(2, 1));
			
			informationPanel.add(versionTable);
			informationPanel.add(attributeTable);
		}
		
		pane.setLeftComponent(new JScrollPane(nodeTree));
		pane.setRightComponent(new JScrollPane(informationPanel));
		pane.setResizeWeight(0.4);
	}

	public void setSetup(Setup setup) {
		this.setup = setup;
		updateRepositoryPanel();
	}
}
