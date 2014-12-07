package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;
@Entity
public class pembelian extends Model{
	public String kode_pembelian ;
	@ManyToOne
	public realpesanan Pesanan;
	public long Harga;
	public String toString(){
		return kode_pembelian;
	}
}
