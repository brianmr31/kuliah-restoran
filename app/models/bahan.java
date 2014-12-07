package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;
@Entity
public class bahan extends Model {
	public String Nama_Bahan;
	public Date Tanggal_Beli;
	public long Stock;
	public long Harga_Persatuan;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="bahannya")
	public List<bahanbeli> id_bahanbeli ;
	@ManyToOne
	public satuan Satuan;
	public String toString(){
		return this.Nama_Bahan;
	}
}
