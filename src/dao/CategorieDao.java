package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.Categorie;
import util.SingletonConnection;

public class CategorieDao implements IDAOCategorie {

	private static final String UPDATE_CATALOGUES = "UPDATE categories set nom = ? where code=?";
	private static final String SELECT_ALL_CATALOGUES = "SELECT * FROM categories";
	private static final String DELETE_CATALOGUE_BY_ID = "DELETE FROM categories where code = ?";
	private static final String INSERT_CATALOGUE = "INSERT INTO categories(nom) values(?)";
	private static final String SELECT_CATALOGUE_BY_ID = "SELECT * FROM categories where code = ?";

	@Override
	public int add(Categorie t) throws SQLException {
		Connection cx = SingletonConnection.getConnection();
		PreparedStatement ps = cx.prepareStatement(INSERT_CATALOGUE);
		ps.setString(1, t.getNom());
		return ps.executeUpdate();
	}

	@Override
	public void delete(Integer id) throws SQLException {
		Connection cx = SingletonConnection.getConnection();
		PreparedStatement ps = cx.prepareStatement(DELETE_CATALOGUE_BY_ID);
		ps.setInt(1, id);
		ps.executeUpdate();
	}

	@Override
	public List<Categorie> findAll() throws SQLException {
		Connection cx = SingletonConnection.getConnection();
		List<Categorie> liste = new ArrayList<>();
		PreparedStatement ps = cx.prepareStatement(SELECT_ALL_CATALOGUES);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			liste.add(buildCatalogue(rs));
		}
		return liste;
	}

	private Categorie buildCatalogue(ResultSet rs) throws SQLException {
		Categorie catalogue = new Categorie();
		catalogue.setCode(rs.getInt("code"));
		catalogue.setNom(rs.getString("nom"));
		return catalogue;
	}

	@Override
	public Categorie update(Categorie t) throws SQLException {
		Connection cx = SingletonConnection.getConnection();
		PreparedStatement ps = cx.prepareStatement(UPDATE_CATALOGUES);
		ps.setString(1, t.getNom());
		ps.setInt(2, t.getCode());
		ps.executeUpdate();
		return t;
	}

	@Override
	public Categorie findById(Integer id) throws SQLException {
		Connection cx = SingletonConnection.getConnection();
		PreparedStatement ps = cx.prepareStatement(SELECT_CATALOGUE_BY_ID);
		ps.setInt(1, id);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			return buildCatalogue(rs);
		}
		return null;
	}

}
