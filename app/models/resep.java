package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;
@Entity
public class resep extends Model {
	public String namaResep;
	@ManyToOne
	public bahan bahannya;
	
	public String jumlah;
	
	
	public String toString(){
		return this.namaResep;
		
	}
	@ManyToOne
	public satuan Satuannya;
	

	
	

}
