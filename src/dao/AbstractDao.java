package dao;

import java.sql.SQLException;
import java.util.List;

public interface AbstractDao<T, PK> {

	public abstract int add(T t) throws SQLException;

	public abstract void delete(PK id) throws SQLException;
	
	public abstract T findById(PK id) throws SQLException;

	public abstract List<T> findAll() throws SQLException;

	public abstract T update(T t) throws SQLException;

}
