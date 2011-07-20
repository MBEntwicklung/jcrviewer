/**
 * 
 */
package de.mbentwicklung.jcrviewer.swing.components;

import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.swing.JTable;

import de.mbentwicklung.jcrviewer.core.domains.Attribute;
import de.mbentwicklung.jcrviewer.core.domains.Version;

/**
 * @author Marc Bellmann
 * 
 */
@SuppressWarnings("serial")
public class AttributeTable extends JTable {

	private List<Attribute> attributes;

	public AttributeTable(final Version version) {
		super();

		updateTable(version);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Object value = attributes.get(getSelectedRow()).getValue();
				if (value instanceof InputStream) {
					downloadFile((InputStream) value);

				}
			}
		});
	}

	public void updateTable(final Version version) {
		attributes = version.getAttributes();
		this.setModel(new AttributeTableModel(attributes));
	}

	private void downloadFile(final InputStream inputStream) {
		File temp;
		try {
			temp = File.createTempFile("jcrViewer", ".tmp");
			BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(temp));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
			outputStream.flush();
			outputStream.close();

			Desktop.getDesktop().open(temp);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}
