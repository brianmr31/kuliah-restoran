package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Blob;
import play.db.jpa.Model;
@Entity
public class Orang extends Model {
	public String nama;
	public String alamat;
	public String kontak;
	@ManyToOne
	public jenisKelamin jnsKelaminx;
	
	@ManyToOne
	public HakAkses NamaAksesx;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="org")
	public List<menu> idMenu;
	
	
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="org")
	public List<pesanan> idOrang;
	
	public String toString(){
		return this.nama;
	}
}

	
	

