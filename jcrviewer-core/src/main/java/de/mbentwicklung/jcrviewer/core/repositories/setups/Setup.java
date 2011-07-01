package de.mbentwicklung.jcrviewer.core.repositories.setups;

import de.mbentwicklung.jcrviewer.core.repositories.SupportedRepositories;

/**
 * Abstrakte Klasse für eine Repository Konfiguration. In dieser Klasse sind alle Notwendigen
 * Informationen für ein JCR {@link javax.jcr.Repository Repository} hinterlegt. Für eine
 * individuelle Implementierung muss diese Klasse erweitert werden.
 * 
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
public abstract class Setup {

	/** Implementation of JCR */
	protected SupportedRepositories repositoryType;

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
	 * Konstruktor zum Setzen der Standard Informationen
	 * 
	 * @param repositoryType
	 *            {@link #repositoryType}
	 * @param username
	 *            {@link #username}
	 * @param password
	 *            {@link #password}
	 */
	public Setup(final SupportedRepositories repositoryType, final String username,
			final String password) {
		super();
		this.repositoryType = repositoryType;
		this.username = username;
		this.password = password;
	}

	/**
	 * Getter für {@link #repositoryType}
	 * 
	 * @return the repositoryType
	 */
	public SupportedRepositories getRepositoryType() {
		return repositoryType;
	}

	/**
	 * Setter für {@link #repositoryType}
	 * 
	 * @param repositoryType
	 *            the repositoryType to set
	 */
	public void setRepositoryType(final SupportedRepositories repositoryType) {
		this.repositoryType = repositoryType;
	}

	/**
	 * Getter für {@link #username}
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Setter für {@link #username}
	 * 
	 * @param username
	 *            the username to set
	 */
	public void setUsername(final String username) {
		this.username = username;
	}

	/**
	 * Getter für {@link #password}
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter für {@link #password}
	 * 
	 * @param password
	 *            the password to set
	 */
	public void setPassword(final String password) {
		this.password = password;
	}
}
