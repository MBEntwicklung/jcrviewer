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
	public static void main(String... args) {
		RepositoryWindow repositoryWindow = new RepositoryWindow();

		if (args.length == 2) {
			JackrabbitSetup jackrabbitSetup = new JackrabbitSetup(new File(args[0]),
					new File(args[1]), "default", "default");
			repositoryWindow.setSetup(jackrabbitSetup);
		}
	}

}
