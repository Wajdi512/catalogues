package dao;

import java.sql.SQLException;
import java.util.List;

import metier.Produit;

public interface IDAOProduit extends AbstractDao<Produit, Integer>{

	List<Produit> findProduitsByCategorie(Integer codeCatalogue) throws SQLException;
}
