package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.Produit;
import util.SingletonConnection;

public class ProduitDao implements IDAOProduit {
	
	private static final String INSERT_PRODUIT = "INSERT INTO produits(reference,nom,prix,quantite,catalogue_id) values(?,?,?,?,?)";
	private static final String DELETE_PRODUIT_BY_ID = "DELETE FROM produit where reference = ?";
	private static final String SELECT_ALL_PRODUITS = "SELECT * FROM produits";
	private static final String SELECT_PRODUIT_BY_ID = "SELECT * FROM produits where reference = ?";
	private static final String SELECT_PRODUITS_BY_CATALOGUE_ID = "SELECT * FROM produits where catalogue_id = ?";
	private static final String UPDATE_PRODUIT_QTE = "UPDATE produit set quantite = ? where reference=?";


	private IDAOCatalogue catalogueDao  = new CatalogueDao();
	@Override
	public int add(Produit t) throws SQLException {
		Connection cx = SingletonConnection.getConnection();
		PreparedStatement ps = cx.prepareStatement(INSERT_PRODUIT);
		ps.setInt(1, t.getReference());
		ps.setString(2, t.getNom());
		ps.setDouble(3, t.getPrix());
		ps.setInt(4, t.getQuantite());
		ps.setInt(5, t.getCatalogue().getCode());
		return ps.executeUpdate();
	}

	@Override
	public void delete(Integer id) throws SQLException {
		Connection cx = SingletonConnection.getConnection();
		PreparedStatement ps = cx.prepareStatement(DELETE_PRODUIT_BY_ID);
		ps.setInt(1, id);
		ps.executeUpdate();
	}

	@Override
	public List<Produit> findAll() throws SQLException {
		Connection cx = SingletonConnection.getConnection();
		List<Produit> liste = new ArrayList<>();
		PreparedStatement ps = cx.prepareStatement(SELECT_ALL_PRODUITS);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			liste.add(buildProduit(rs));
		}
		return liste;
	}

	private Produit buildProduit(ResultSet rs) throws SQLException {
		Produit produit = new Produit();
		produit.setReference(rs.getInt("reference"));
		produit.setNom(rs.getString("nom"));
		produit.setPrix(rs.getDouble("prix"));
		produit.setQuantite(rs.getInt("quantite"));
		produit.setCatalogue(catalogueDao.findById(rs.getInt("catalogue_id")));
		return produit;
	}

	@Override
	public Produit update(Produit t) throws SQLException {
		Connection cx = SingletonConnection.getConnection();
		PreparedStatement ps = cx.prepareStatement(UPDATE_PRODUIT_QTE);
		ps.setInt(1, t.getQuantite());
		ps.setInt(2, t.getReference());
		ps.executeUpdate();
		return t;
	}

	@Override
	public Produit findById(Integer id) throws SQLException {
		Connection cx = SingletonConnection.getConnection();
		PreparedStatement ps = cx.prepareStatement(SELECT_PRODUIT_BY_ID);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			return buildProduit(rs);
		}
		return null;
	}

	@Override
	public List<Produit> findProduitsByCategorie(Integer codeCatalogue) throws SQLException {
		Connection cx = SingletonConnection.getConnection();
		List<Produit> liste = new ArrayList<>();
		PreparedStatement ps = cx.prepareStatement(SELECT_PRODUITS_BY_CATALOGUE_ID);
		ps.setInt(1, codeCatalogue);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			liste.add(buildProduit(rs));
		}
		return liste;
	}
	
}
