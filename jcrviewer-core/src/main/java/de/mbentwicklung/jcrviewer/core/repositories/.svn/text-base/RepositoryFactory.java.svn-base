/**
 * 
 */
package de.mbentwicklung.jcrviewer.core.repositories;

import javax.jcr.Credentials;
import javax.jcr.Repository;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.core.TransientRepository;

/**
 * @author Marc Bellmann
 * 
 */
public class RepositoryFactory {

	public static final Credentials DEFAULT_CREDENTIALS = new SimpleCredentials(
			"userId", "password".toCharArray());

	public static Repository createRepository() {
		return new TransientRepository();
	}

}
