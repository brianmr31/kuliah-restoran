package models;

<<<<<<< HEAD
import java.util.Date;
=======
>>>>>>> 059c05fd0ba057b16bdb5a09e45b10898c8e996e
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
<<<<<<< HEAD
import javax.persistence.ManyToOne;
=======
>>>>>>> 059c05fd0ba057b16bdb5a09e45b10898c8e996e
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class realpesanan extends Model {
	public String Nama_Pesanan ;
<<<<<<< HEAD

	@ManyToOne
	public status Status_Pesan;
	@ManyToOne
	public Orang Nama_Orang;
	@ManyToOne
	public meja No_Meja;
	public Date tanggal;
=======
>>>>>>> 059c05fd0ba057b16bdb5a09e45b10898c8e996e
	@OneToMany(cascade=CascadeType.ALL, mappedBy="Nama_pesanannya")
	public List<pesanan> id_pesanan;
	//@OneToMany(cascade=CascadeType.ALL, mappedBy="Pesanan")
	//public List<pembelian> id_pembelian;
	public String toString(){
		return Nama_Pesanan ;
	}
}
