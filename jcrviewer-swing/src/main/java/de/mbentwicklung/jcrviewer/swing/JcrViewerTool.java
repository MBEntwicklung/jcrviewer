/**
 * 
 */
package de.mbentwicklung.jcrviewer.swing;

import java.io.File;

import de.mbentwicklung.jcrviewer.core.repositories.setups.JackrabbitSetup;


/**
 * @author Marc Bellmann
 *
 */
public class JcrViewerTool {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RepositoryWindow repositoryWindow = new RepositoryWindow();
		
		JackrabbitSetup jackrabbitSetup = new JackrabbitSetup(new File("/tmp/repository.xml"),
				new File("/tmp/repository/"), "default", "default");
		repositoryWindow.setSetup(jackrabbitSetup);
	}

}
