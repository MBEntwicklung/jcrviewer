/**
 * 
 */
package de.mbentwicklung.jcrviewer.swing.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import de.mbentwicklung.jcrviewer.core.domains.Attribute;

/**
 * @author Marc Bellmann
 * 
 */
@SuppressWarnings("serial")
public class AttributeTableModel extends AbstractTableModel {
	private final List<Attribute> attributes;

	/**
	 * 
	 * @param attributes
	 */
	public AttributeTableModel(final Collection<Attribute> attributes) {
		this.attributes = new ArrayList<Attribute>(attributes);
	}

	public int getRowCount() {
		return attributes.size();
	}

	public int getColumnCount() {
		return 2;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Attribute attribute = attributes.get(rowIndex);

		switch (columnIndex) {
		case 0:
			return attribute.getName();
		case 1:
			return attribute.getValue();

		default:
			throw new IllegalArgumentException("columnIndex [" + columnIndex
					+ "] isn't correct!");
		}
	}

	@Override
	public String getColumnName(int column) {
		switch (column) {
		case 0:
			return "Attribute - Name";
		case 1:
			return "Value";

		default:
			throw new IllegalArgumentException("columnIndex [" + column
					+ "] isn't correct!");
		}
	}
	
}
