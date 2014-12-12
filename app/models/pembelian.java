package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;
@Entity
public class pembelian extends Model{
	public String kode_pembelian ;
	public String Pemesan ;
	public double Harga;
	public Date tgl_bayar;
	public String toString(){
		return kode_pembelian;
	}
}
