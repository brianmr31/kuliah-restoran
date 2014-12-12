package models;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;
@Entity
public class realpesanan extends Model {
	public String Nama_Pesanan ;
	@ManyToOne
	public status Status_Pesan;
	@ManyToOne
	public Orang Nama_Orang;
	@ManyToOne
	public meja No_Meja;
	public Date tanggal;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="Nama_pesanannya")
	public List<pesanan> id_pesanan;
	public double tagihan ;
	//@OneToMany(cascade=CascadeType.ALL, mappedBy="Pesanan")
	//public List<pembelian> id_pembelian;
	public String toString(){
		return Nama_Pesanan ;
	}
}
