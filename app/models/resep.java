package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;
@Entity
public class resep extends Model {
	public String Nama_Resep;
	public long Harga ;

	@ManyToOne
	public bahan bahan;
	public String toString(){
		return this.Nama_Resep;
	}
}
