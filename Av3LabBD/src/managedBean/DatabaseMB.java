package managedBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;

import model.Database;
import persistence.DataBaseDaoImpl;
import persistence.DatabaseDao;

@ManagedBean
@SessionScoped
public class DatabaseMB {
	
	private List<Database> lista;
	private DatabaseDao dbDao;

	public DatabaseMB() {
		setLista(new ArrayList<Database>());
		dbDao = new DataBaseDaoImpl();
	}
	
	public void listaBD() {
		String msg="Nenhum BD encontrado!";
		
		try {
			lista = dbDao.listaDatabase();
		} catch (SQLException e) {
			FacesContext fc = FacesContext.getCurrentInstance();
			fc.addMessage(null, new FacesMessage(msg));
			e.printStackTrace();
		}
	}
	
    public void handleFileUpload(FileUploadEvent event) {
        FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


	public void fullBackup(){
		System.out.println("fullbackup");
	}
	
	public List<Database> getLista() {
		return lista;
	}

	public void setLista(List<Database> lista) {
		this.lista = lista;
	}
	
	public DatabaseDao getDbDao() {
		return dbDao;
	}

	public void setDbDao(DatabaseDao dbDao) {
		this.dbDao = dbDao;
	}

}
