package dao;

import java.sql.SQLException;
import java.util.List;

import metier.Categorie;
import metier.Produit;

public class IDAOCatalogueImpl implements IDAOCatalogue {

	private IDAOCategorie categorieDao = new CategorieDao();
	private IDAOProduit produitDao = new ProduitDao();

	@Override
	public List<Produit> findProduitsByCategorie(Integer codeCatalogue) throws SQLException {
		return produitDao.findProduitsByCategorie(codeCatalogue);
	}

	@Override
	public List<Categorie> findAllCategories() throws SQLException {
		return categorieDao.findAll();
	}

	@Override
	public List<Produit> findAllProduits() throws SQLException {
		return produitDao.findAll();
	}

	@Override
	public void ajouterCategorie(Categorie cat) throws SQLException {
		categorieDao.add(cat);
	}

	@Override
	public void ajouterProduit(Produit produit) throws SQLException {
		produitDao.add(produit);
	}

	@Override
	public void deleteCategorieById(Integer categorieId) throws SQLException {
		categorieDao.delete(categorieId);
	}

	@Override
	public Categorie updateCategorie(Categorie categorie) throws SQLException {
		return categorieDao.update(categorie);
	}

}
