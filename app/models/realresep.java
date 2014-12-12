package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class realresep extends Model{
	public String Nama_Resep ;
	public double Harga_menu ;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="Nama_Resep")
	public List<menu> idmenu;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="Nama_RealResep")
	public List<resep> idresep;
	public String toString(){
		return Nama_Resep;
	}
}
