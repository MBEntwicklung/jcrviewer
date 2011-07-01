/**
 * In diesem Package sind alle Domain Objekte des JCR Viewers. 
 * <p />
 * Die Datenstruktur eines JCR Repositories wird mit folgenden Objekten nachgebaut.
 * <ul>
 * <li><b>Nodes</b> - Für ein Repository existiert ein Rootnode. Dieser Rootnode kann aus 
 *	 	mehreren untergeordneten Nodes bestehen, für welche wiederum mehrere Unternodes existieren 
 * 		können. Dadurch entsteht eine Treeähnliche Datenstruktur. Das Konzept ist Identisch mit den 
 * 		JCR {@link javax.jcr.Node Node}s.</li>
 * <li><b>Versions</b> - Ein Node kann mehrere unterschiedliche Versionen besitzen. Eine Version 
 * 		ist jedoch die Baseversion. Dies ist die aktuellste Version des Nodes. Auch dieses Konzept ist
 * 		den JCR {@link javax.jcr.version.Version Version}s nachempfunden.</li>
 * <li><b>Attributes</b> - Letztendlich besitzt jede Version verschiedene Attribute. Attribute sind den
 * 		{@link javax.jcr.Property Properties} in JCR ähnlich.</li>
 * </ul>
 * 
 * @see de.mbentwicklung.jcrviewer.core.domains.Node
 * @see de.mbentwicklung.jcrviewer.core.domains.Version
 * @see de.mbentwicklung.jcrviewer.core.domains.Attribute
 * @author Marc Bellmann <marc.bellmann@mb-entwicklung.de>
 */
package de.mbentwicklung.jcrviewer.core.domains;

