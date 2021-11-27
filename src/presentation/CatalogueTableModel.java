package presentation;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import metier.Catalogue;

public class CatalogueTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<Catalogue> lesCatalogues = new ArrayList<>();
	String titre[] = { "Code", "Nom" };

	@Override
	public int getRowCount() {
		return lesCatalogues.size();
	}

	@Override
	public int getColumnCount() {
		return titre.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return lesCatalogues.get(rowIndex).getCode();
		case 1:
			return lesCatalogues.get(rowIndex).getNom();
		}
		return null;
	}

	public String getColumnName(int column) {
		return titre[column];
	}

	public void chargerTable(List<Catalogue> catalogues) {
		this.lesCatalogues = catalogues;
		fireTableDataChanged();
	}

}
