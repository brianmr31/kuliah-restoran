package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;
@Entity
public class resep extends Model {
	public String Nama_Resep;
	@ManyToOne
	public bahan Bahan;
	public long Jumlah;
	@ManyToOne
	public satuan Satuan;
	public long Harga ;
	public String toString(){
		return this.Nama_Resep;
	}
	@ManyToOne
	public realresep Nama_RealResep;
}
