package models;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class bahanbeli extends Model{
	public String Nama_Bahan;
	public Date Tanggal_Beli;
	public long Stock;
	public long Harga_Persatuan;
	
	@ManyToOne
	public satuan Satuan;
	public String toString(){
		return this.Nama_Bahan;
	}
}
