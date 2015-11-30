package persistence;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionImpl;
import connection.GenericConnection;
import model.Database;


public class DataBaseDaoImpl implements DatabaseDao {
	
	private Connection c;
	
	public DataBaseDaoImpl() {
		GenericConnection gc = new ConnectionImpl();
		c = gc.getConnection();
	}

	@Override
	public List<Database> listaDatabase() throws SQLException {
		List<Database> lista = new ArrayList<Database>();
		
		String sql = "SELECT dbid,name,convert(varchar(10),crdate, 103) as data, "
					+ "substring(convert(varchar(10),crdate, 108),1,5) as hora "
					+ "FROM sys.sysdatabases where name not like 'tempdb'";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		while ( rs.next() ) {
			try {
				Database db = new Database();
				db.setId(rs.getLong("dbid"));
				db.setNome(rs.getString("name"));
				Date data = new Date(sdf.parse(rs.getString("data")).getTime());
				db.setData(data);
				db.setHora(rs.getString("hora"));
				lista.add(db);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		return lista;
	}

	@Override
	public void backupBD(String pasta) throws SQLException {
		String sql = "{call sp_backupBD(?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, pasta);
		cs.execute();
		cs.close();
	}

	@Override
	public void backupSelecionado(Database db, String caminho) throws SQLException {
		String sql = "{call sp_backupSelecionado(?,?)}";
		CallableStatement cs = c.prepareCall(sql);
		cs.setString(1, caminho);
		cs.setString(2, db.getNome());
		cs.execute();
		cs.close();
	}



}
