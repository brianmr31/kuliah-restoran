package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;
@Entity
public class pembelian extends Model{
	public String kode_pembelian ;
	public String Pemesan ;
	public double Harga;
	public double Untung;
	public Date tgl_bayar;
	public String pesanannya;
	public String toString(){
		return kode_pembelian;
	}
}
