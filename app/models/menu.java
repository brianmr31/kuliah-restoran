package models;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;
@Entity
public class menu extends Model {
	public String Nama_Menu ; 
	public String Keterangan   ;
	public long Harga;
	
	@ManyToOne
	public resep resep ;
	@ManyToOne
	public pesanan pesan ;
	
	public String toString(){
		return Nama_Menu ;
	}
}
