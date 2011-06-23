/**
 * 
 */
package de.mbentwicklung.jcrviewer.swing.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import de.mbentwicklung.jcrviewer.core.repositories.SupportedRepositorys;
import de.mbentwicklung.jcrviewer.core.repositories.setups.JackrabbitSetup;
import de.mbentwicklung.jcrviewer.core.repositories.setups.Setup;

/**
 * @author Marc Bellmann
 * 
 */
@SuppressWarnings("serial")
public class MenuPanel extends JToolBar {

	private final JComboBox repositoryTypeComboBox;
	private final JTextField usernameField;
	private final JPasswordField passwordField;
	private final JTextField xmlFilePathField;
	private final JButton xmlFileButton;
	private final JFileChooser xmlFileChooser;
	private final JTextField dirFilePathField;
	private final JButton dirFileButton;
	private final JFileChooser dirFileChooser;
	private final JButton connectButton;

	private final RepositoryConnectEvent connectEvent;
	
	private final JackrabbitSetup setup;

	/**
	 * 
	 */
	public MenuPanel(final RepositoryConnectEvent connectEvent) {
		super();

		this.connectEvent = connectEvent;

		repositoryTypeComboBox = new JComboBox();
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		xmlFilePathField = new JTextField();
		xmlFileButton = new JButton();
		xmlFileChooser = new JFileChooser();
		dirFilePathField = new JTextField();
		dirFileButton = new JButton();
		dirFileChooser = new JFileChooser();
		connectButton = new JButton();
		
		setup = new JackrabbitSetup();

		initRepositoryTypeComboBox();
		initUsernameField();
		initPasswordField();
		initXmlFileChooser();
		initDirFileCooser();
		initConnectButton();
	}

	private void initRepositoryTypeComboBox() {
		repositoryTypeComboBox.addItem(SupportedRepositorys.JACKRABBIT);

		add(repositoryTypeComboBox);
	}

	private void initUsernameField() {
		usernameField.setText("default");
		
		add(new JLabel("Username: "));
		add(usernameField);
	}

	private void initPasswordField() {
		passwordField.setText("default");
		
		add(new JLabel("Password: "));
		add(passwordField);
	}

	private void initXmlFileChooser() {
		xmlFilePathField.setEditable(false);

		xmlFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		xmlFileButton.setText("Repository XML: ");
		xmlFileButton.addActionListener(buildActionListenerForFileChooser(
				"XML File", xmlFileChooser, xmlFilePathField));

		add(xmlFileButton);
		add(xmlFilePathField);
	}

	private void initDirFileCooser() {
		dirFilePathField.setEditable(false);

		dirFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		dirFileButton.setText("Repository Dir: ");
		dirFileButton.addActionListener(buildActionListenerForFileChooser(
				"Repository Dir", dirFileChooser, dirFilePathField));

		add(dirFileButton);
		add(dirFilePathField);
	}

	private ActionListener buildActionListenerForFileChooser(
			final String windowTitle, final JFileChooser fileChooser,
			final JTextField targetTextField) {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				final JFrame popUp = new JFrame(windowTitle);
				popUp.add(fileChooser);
				popUp.setSize(600, 500);

				popUp.setVisible(true);

				fileChooser.addActionListener(new AbstractAction() {

					@Override
					public void actionPerformed(ActionEvent e) {
						if (JFileChooser.APPROVE_SELECTION.equals(e
								.getActionCommand())) {
							targetTextField.setText(fileChooser
									.getSelectedFile().getAbsolutePath());

							if (e.getSource().equals(xmlFileChooser)) {
								setup.setRepositoryXmlFile(fileChooser.getSelectedFile());
							} else if (e.getSource().equals(dirFileChooser)) {
								setup.setRepositoryDirFile(fileChooser.getSelectedFile());
							}
							
							popUp.setVisible(false);
						} else if (JFileChooser.CANCEL_SELECTION.equals(e
								.getActionCommand())) {
							popUp.setVisible(false);
						}
					}
				});
			}
		};
	}

	private void initConnectButton() {
		connectButton.setText("Connect / Refresh");

		connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setup.setUsername(usernameField.getText());
				setup.setPassword(passwordField.getPassword().toString());
				
				System.out.println(setup.toString());
				connectEvent.connectWith(setup);
			}
		});

		add(connectButton);
	}

	public interface RepositoryConnectEvent {
		void connectWith(final Setup setup);
	}
}
