/**
 * 
 */
package de.mbentwicklung.jcrviewer.swing.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.mbentwicklung.jcrviewer.core.tree.Version;

/**
 * @author Marc Bellmann
 * 
 */
public class VersionTableModel extends AbstractTableModel {

	private final List<Version> versions;

	public VersionTableModel(final Collection<Version> versions) {
		this.versions = new ArrayList<Version>(versions);
	}

	@Override
	public int getRowCount() {
		return versions.size();
	}

	public Version getVersion(final int i) {
		return versions.get(i);
	}

	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Version version = versions.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return version.getName();
		case 1: return version.getCreated();
		default:
			throw new IllegalArgumentException("columnIndex [" + columnIndex + "] isn't correct!");
		}
	}

	@Override
	public String getColumnName(int column) {

		switch (column) {
		case 0:
			return "Version - Name";
		case 1: return "Version - Erstellt";
		default:
			throw new IllegalArgumentException("columnIndex [" + column + "] isn't correct!");
		}
	}
}
