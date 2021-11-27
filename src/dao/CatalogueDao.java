package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.Catalogue;
import util.SingletonConnection;

public class CatalogueDao implements IDAOCatalogue {

	private static final String UPDATE_CATALOGUES = "UPDATE catalogues set nom = ? where code=?";
	private static final String SELECT_ALL_CATALOGUES = "SELECT * FROM catalogues";
	private static final String DELETE_CATALOGUE_BY_ID = "DELETE FROM catalogues where code = ?";
	private static final String INSERT_CATALOGUE = "INSERT INTO catalogues(nom) values(?)";
	private static final String SELECT_CATALOGUE_BY_ID = "SELECT * FROM catalogues where code = ?";

	@Override
	public int add(Catalogue t) throws SQLException {
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
	public List<Catalogue> findAll() throws SQLException {
		Connection cx = SingletonConnection.getConnection();
		List<Catalogue> liste = new ArrayList<>();
		PreparedStatement ps = cx.prepareStatement(SELECT_ALL_CATALOGUES);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			liste.add(buildCatalogue(rs));
		}
		return liste;
	}

	private Catalogue buildCatalogue(ResultSet rs) throws SQLException {
		Catalogue catalogue = new Catalogue();
		catalogue.setCode(rs.getInt("code"));
		catalogue.setNom(rs.getString("nom"));
		return catalogue;
	}

	@Override
	public Catalogue update(Catalogue t) throws SQLException {
		Connection cx = SingletonConnection.getConnection();
		PreparedStatement ps = cx.prepareStatement(UPDATE_CATALOGUES);
		ps.setString(1, t.getNom());
		ps.setInt(2, t.getCode());
		ps.executeUpdate();
		return t;
	}

	@Override
	public Catalogue findById(Integer id) throws SQLException {
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
