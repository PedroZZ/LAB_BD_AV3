package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Database;

public interface DatabaseDao {

	public List<Database> listaDatabase() throws SQLException;
	
}
