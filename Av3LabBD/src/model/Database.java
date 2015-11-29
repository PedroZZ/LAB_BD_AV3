package model;

import java.util.Date;

/**
 * 
 * @author hury_
 *
 */
public class Database {

	private String nome;
	private Date data;
	private long id;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
