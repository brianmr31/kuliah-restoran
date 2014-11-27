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
	public String namaMenu ; 
	public String keterangan   ;
	public long harga;
	@ManyToOne
	public Orang org;
	@OneToMany(cascade=CascadeType.ALL, mappedBy= "mnu")
	public List<pesanan> idmenu;
	@ManyToOne
	public resep dataresep;
}
