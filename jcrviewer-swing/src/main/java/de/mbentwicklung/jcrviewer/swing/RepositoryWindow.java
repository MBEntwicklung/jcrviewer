/**
 * 
 */
package de.mbentwicklung.jcrviewer.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import de.mbentwicklung.jcrviewer.core.repositories.setups.Setup;
import de.mbentwicklung.jcrviewer.core.tree.Node;
import de.mbentwicklung.jcrviewer.core.tree.NodeConverter;
import de.mbentwicklung.jcrviewer.swing.components.AttributeTable;
import de.mbentwicklung.jcrviewer.swing.components.MenuPanel;
import de.mbentwicklung.jcrviewer.swing.components.NodeTree;

/**
 * @author marc
 * 
 */
public class RepositoryWindow extends JFrame {

	/** serialVersionUID */
	private static final long serialVersionUID = -6578764732683432196L;
	private NodeTree nodeTree;
	private AttributeTable attributeTable;
	private final JSplitPane pane;

	private Setup setup;

	/**
	 * 
	 */
	public RepositoryWindow() {
		super("JcrViewer");

		setExtendedState(Frame.MAXIMIZED_BOTH);

		pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		updateRepositoryPanel();

		add(buildMenuPanel(), BorderLayout.PAGE_START);
		add(pane);

		addWindowListener();

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

	public void setSetup(Setup setup) {
		this.setup = setup;
		updateRepositoryPanel();
	}

	/**
	 * 
	 */
	private void updateRepositoryPanel() {
		if (setup != null) {
			NodeConverter nodeConverter = new NodeConverter(setup);
			Node rootNode = nodeConverter.toRootNode();
			attributeTable = new AttributeTable(rootNode);
			nodeTree = new NodeTree(rootNode, attributeTable);
		}

		pane.setLeftComponent(new JScrollPane(nodeTree));
		pane.setRightComponent(new JScrollPane(attributeTable));

		pane.setResizeWeight(0.4);
	}

	private void addWindowListener() {
		addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
			}

			@Override
			public void windowIconified(WindowEvent e) {
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
			}

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			@Override
			public void windowClosed(WindowEvent e) {
			}

			@Override
			public void windowActivated(WindowEvent e) {
			}
		});
	}

}
