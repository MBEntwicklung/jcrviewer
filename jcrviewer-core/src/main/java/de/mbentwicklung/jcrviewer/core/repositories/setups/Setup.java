/**
 * 
 */
package de.mbentwicklung.jcrviewer.core.repositories.setups;

import de.mbentwicklung.jcrviewer.core.repositories.SupportedRepositorys;

/**
 * @author Marc Bellmann
 * 
 */
public abstract class Setup {

	/** Implementation of JCR */
	protected SupportedRepositorys repositoryType;

	/** Username for repository */
	protected String username;

	/** Password for repostory */
	protected String password;

	/**
	 * Default Konstruktor
	 */
	public Setup() {
	}

	/**
	 * Konstruktor with sets
	 * 
	 * @param repositoryType
	 */
	public Setup(SupportedRepositorys repositoryType, String username,
			String password) {
		super();
		this.repositoryType = repositoryType;
		this.username = username;
		this.password = password;
	}

	/**
	 * @return the repositoryType
	 */
	public SupportedRepositorys getRepositoryType() {
		return repositoryType;
	}

	/**
	 * @param repositoryType
	 *            the repositoryType to set
	 */
	public void setRepositoryType(SupportedRepositorys repositoryType) {
		this.repositoryType = repositoryType;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Setup [repositoryType=" + repositoryType + ", username="
				+ username + ", password=" + password + "]";
	}
}
