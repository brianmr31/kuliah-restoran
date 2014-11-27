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
	public String Nama;
	public String Alamat;
	public String Kontak;
	@ManyToOne
	public jenisKelamin Jenis_Kelamin;
	@ManyToOne
	public HakAkses Nama_Akses;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="Nama_Orang")
	public List<pesanan> idOrang;
	public String toString(){
		return this.Nama;
	}
}

	
	

