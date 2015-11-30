package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Database;

public interface DatabaseDao {

	public List<Database> listaDatabase() throws SQLException;
	
	void backupBD(String pasta) throws SQLException;
	
	void backupSelecionados() throws SQLException;
	
}
