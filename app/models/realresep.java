package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class realresep extends Model{
	public String Nama_Resep ;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="Nama_Resep")
	public List<menu> idresep;
	public String toString(){
		return Nama_Resep;
	}
}
