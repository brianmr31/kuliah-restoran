package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class realpesanan extends Model {
	public String Nama_Pesanan ;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="Nama_pesanannya")
	public List<pesanan> id_pesanan;
	//@OneToMany(cascade=CascadeType.ALL, mappedBy="Pesanan")
	//public List<pembelian> id_pembelian;
	public String toString(){
		return Nama_Pesanan ;
	}
}
