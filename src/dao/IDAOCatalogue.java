package dao;

import java.sql.SQLException;
import java.util.List;

import metier.Categorie;
import metier.Produit;

public interface IDAOCatalogue {

	List<Produit> findProduitsByCategorie(Integer codeCatalogue) throws SQLException;

	List<Categorie> findAllCategories() throws SQLException;
	
	List<Produit> findAllProduits() throws SQLException;

	void ajouterCategorie(Categorie cat) throws SQLException;
	
	void ajouterProduit(Produit produit) throws SQLException;
	
	Categorie updateCategorie(Categorie categorie) throws SQLException;

	
	void deleteCategorieById(Integer categorie_id) throws SQLException;

	
}
