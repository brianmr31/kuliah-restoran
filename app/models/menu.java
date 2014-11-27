package models;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;
@Entity
public class menu extends Model {
	public String Nama_Menu ; 
	public String Keterangan   ;
	public long Harga;
	@OneToMany(cascade=CascadeType.ALL, mappedBy= "Nama_Menu")
	public List<pesanan> idmenu;
	@ManyToOne
	public realresep Nama_Resep;
	public String toString(){
		return Nama_Menu ;
	}
}
