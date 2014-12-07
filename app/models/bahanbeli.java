package models;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class bahanbeli extends Model{
	@ManyToOne
	public bahan Nama_bahan ;
	public Date Tanggal_Beli;
	public long Stock;
	public long Harga_Persatuan;
}
