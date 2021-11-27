package presentation;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import metier.Produit;

public class ProduitTableModel extends AbstractTableModel {
	List<Produit> lesProduits = new ArrayList();
	String titre[] = { "Reference", "Nom", "Prix", "Quantité", "Catalogue" };

	@Override
	public int getRowCount() {
		return lesProduits.size();
	}

	@Override
	public int getColumnCount() {
		return titre.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return lesProduits.get(rowIndex).getReference();
		case 1:
			return lesProduits.get(rowIndex).getNom();
		case 2:
			return lesProduits.get(rowIndex).getPrix();
		case 3:
			return lesProduits.get(rowIndex).getQuantite();
		case 4:
			return lesProduits.get(rowIndex).getCatalogue().getNom();
		}
		return null;
	}

	@Override
    public String getColumnName(int column) {
        return titre[column];
    }

	public void chargerTable(List<Produit> lesProduits) {
		this.lesProduits = lesProduits;
		fireTableDataChanged();
	}

}
