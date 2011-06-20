/**
 * 
 */
package de.mbentwicklung.jcrviewer.swing.components;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
 * @author marc
 * 
 */
@SuppressWarnings("serial")
public class MenuPanel extends JToolBar {

	private final JComboBox repositoryTypeComboBox;
	private final JTextField usernameField;
	private final JPasswordField passwordField;
	private final JButton xmlFileButton;
	private final JFileChooser xmlFileChooser;
	private final JButton dirFileButton;
	private final JFileChooser dirFileChooser;
	private final JButton connectButton;

	private final RepositoryConnectEvent connectEvent;

	/**
	 * 
	 */
	public MenuPanel(final RepositoryConnectEvent connectEvent) {
		super();

		this.connectEvent = connectEvent;

		repositoryTypeComboBox = new JComboBox();
		usernameField = new JTextField();
		passwordField = new JPasswordField();
		xmlFileButton = new JButton();
		xmlFileChooser = new JFileChooser();
		dirFileButton = new JButton();
		dirFileChooser = new JFileChooser();
		connectButton = new JButton();

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

		add(new JLabel("Username: "));
		add(usernameField);
	}

	private void initPasswordField() {
		passwordField.
		
		add(new JLabel("Password: "));
		add(passwordField);
	}

	private void initXmlFileChooser() {
		xmlFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

		xmlFileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame popUp = new JFrame("Repository XML");
				popUp.add(xmlFileChooser);
				popUp.setSize(600, 500);
				
				popUp.setVisible(true);
			}
		});
		
		xmlFileButton.setText("Repository XML");
		add(xmlFileButton);
	}

	private void initDirFileCooser() {
		dirFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		dirFileButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFrame popUp = new JFrame("Repository Dir");
				popUp.add(dirFileChooser);
				popUp.setSize(600, 500);
				
				popUp.setVisible(true);
			}
		});
		
		dirFileButton.setText("Repository Dir");
		add(dirFileButton);
	}

	private void initConnectButton() {
		connectButton.setText("Connect / Refresh");

		connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = usernameField.getText();
				String password = passwordField.getText();
				
				connectEvent.connectWith(new JackrabbitSetup(new File(
						"/tmp/repository.xml"), new File("/tmp/repository/"),
						username, password));
			}
		});

		add(connectButton);
	}

	public interface RepositoryConnectEvent {
		void connectWith(final Setup setup);
	}
}
