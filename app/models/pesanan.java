package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;
@Entity
public class pesanan extends Model{
	@ManyToOne
	public menu menu_pesan;
	@ManyToOne
	public realpesanan Nama_pesanannya ;
	public Date Tanggal_Pesan ;
	public long Jumlah_Pesan ;
	public long Harga;
	
}
