package models;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class bahanbeli extends Model{
	@ManyToOne
	public bahan Nama_bahan ;
	public long Stock;
	public long Harga_Persatuan;
	public Date Tanggal_Beli;
	@ManyToOne
	public satuan satuan;
}
