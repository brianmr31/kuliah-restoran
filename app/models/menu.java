package models;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Blob;
import play.db.jpa.Model;
@Entity
public class menu extends Model {
	public String Nama_Menu ; 
	public String Keterangan   ;
	public long Harga;
	public long HargaUntung;
	@ManyToOne
	public realresep Nama_Resep;
	public Blob gambar;
	public String toString(){
		return Nama_Menu ;
	}
}
