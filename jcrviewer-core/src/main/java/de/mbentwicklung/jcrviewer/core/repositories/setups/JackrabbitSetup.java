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
	private File repositoryXmlFile;

	/** Path to repository dir */
	private File repositoryDirFile;

	/**
	 * 
	 */
	public JackrabbitSetup() {
		super();
		setRepositoryType(SupportedRepositorys.JACKRABBIT);
	}

	/**
	 * @param repositoryType
	 * @param repositoryXmlFile
	 * @param repositoryDirFile
	 * @param username
	 * @param password
	 */
	public JackrabbitSetup(File repositoryXmlFile, File repositoryDirFile,
			String username, String password) {
		super(SupportedRepositorys.JACKRABBIT, username, password);

		this.repositoryXmlFile = repositoryXmlFile;
		this.repositoryDirFile = repositoryDirFile;
	}

	/**
	 * @return the repositoryXmlFile
	 */
	public File getRepositoryXmlFile() {
		return repositoryXmlFile;
	}

	/**
	 * @param repositoryXmlFile
	 *            the repositoryXmlFile to set
	 */
	public void setRepositoryXmlFile(File repositoryXmlFile) {
		this.repositoryXmlFile = repositoryXmlFile;
	}

	/**
	 * @return the repositoryDirFile
	 */
	public File getRepositoryDirFile() {
		return repositoryDirFile;
	}

	/**
	 * @param repositoryDirFile
	 *            the repositoryDirFile to set
	 */
	public void setRepositoryDirFile(File repositoryTmpFile) {
		this.repositoryDirFile = repositoryTmpFile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JackrabbitSetup [repositoryXmlFile=" + repositoryXmlFile
				+ ", repositoryDirFile=" + repositoryDirFile
				+ ", repositoryType=" + repositoryType + ", username="
				+ username + ", password=" + password + "]";
	}

}
