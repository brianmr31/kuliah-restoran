package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;
@Entity
public class resep extends Model {
	@ManyToOne
	public bahan Bahan;
	public double Jumlah;
	@ManyToOne
	public satuan Satuan;
	public double Harga ;
	@ManyToOne
	public realresep Nama_RealResep;
}
