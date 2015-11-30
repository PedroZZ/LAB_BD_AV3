package managedBean;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

import model.Database;
import persistence.DataBaseDaoImpl;
import persistence.DatabaseDao;

@ManagedBean
@SessionScoped
public class DatabaseMB implements Serializable{
	private static final long serialVersionUID = 1040446122915171399L;
	
	private List<Database> lista;
	private DatabaseDao dbDao;
	private final String caminho = "C:\\Users\\Pedro\\git\\LAB_BD_AV3\\Av3LabBD\\WebContent\\backupBD\\";

	public DatabaseMB() {
		lista = new ArrayList<Database>();
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
		try {
			dbDao.backupBD(caminho);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void backupSelecionados(Database db) {
		try {
			dbDao.backupSelecionado(db, caminho);
		} catch (SQLException e) {
			e.printStackTrace();
		}
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
