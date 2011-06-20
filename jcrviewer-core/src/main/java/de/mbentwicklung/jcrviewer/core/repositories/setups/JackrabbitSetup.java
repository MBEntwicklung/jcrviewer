/**
 * 
 */
package de.mbentwicklung.jcrviewer.core.repositories.setups;

import java.io.File;

import de.mbentwicklung.jcrviewer.core.repositories.SupportedRepositorys;

/**
 * @author Marc Bellmann
 * 
 */
public class JackrabbitSetup extends Setup {

	/** Path to repository.xml */
	private File repositoryXmlPath;

	/** Path to repository dir */
	private File repositoryTmpPath;

	/**
	 * 
	 */
	public JackrabbitSetup() {
		super();
		setRepositoryType(SupportedRepositorys.JACKRABBIT);
	}

	/**
	 * @param repositoryType
	 * @param repositoryXmlPath
	 * @param repositoryTmpPath
	 * @param username
	 * @param password
	 */
	public JackrabbitSetup(File repositoryXmlPath, File repositoryTmpPath,
			String username, String password) {
		super(SupportedRepositorys.JACKRABBIT, username, password);

		this.repositoryXmlPath = repositoryXmlPath;
		this.repositoryTmpPath = repositoryTmpPath;
	}

	/**
	 * @return the repositoryXmlPath
	 */
	public File getRepositoryXmlPath() {
		return repositoryXmlPath;
	}

	/**
	 * @param repositoryXmlPath
	 *            the repositoryXmlPath to set
	 */
	public void setRepositoryXmlPath(File repositoryXmlPath) {
		this.repositoryXmlPath = repositoryXmlPath;
	}

	/**
	 * @return the repositoryTmpPath
	 */
	public File getRepositoryTmpPath() {
		return repositoryTmpPath;
	}

	/**
	 * @param repositoryTmpPath
	 *            the repositoryTmpPath to set
	 */
	public void setRepositoryTmpPath(File repositoryTmpPath) {
		this.repositoryTmpPath = repositoryTmpPath;
	}

}
