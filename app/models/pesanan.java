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
	public String namaPesanan;
	@ManyToOne
	public status Status_Pesan;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="menu1")
	public List<menu> Nama_Menu;
	@ManyToOne
	public Orang Nama_Orang;
	@ManyToOne
	public meja No_Meja;
	public Date Tanggal_Pesan ;
	public long Jumlah_Pesan ;
	public long Harga;
	
	public String toString(){
		return this.namaPesanan;
	}

}
