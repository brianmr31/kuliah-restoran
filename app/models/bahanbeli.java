package models;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class bahanbeli extends Model{
	public String Nama_bahan ;
	public double Stock;
	public double Harga_Persatuan;
	public Date Tanggal_Beli;
	@ManyToOne
	public satuan satuan;
}
