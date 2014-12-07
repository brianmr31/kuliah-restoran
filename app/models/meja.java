package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;
@Entity
public class meja extends Model {
	public long No_Meja;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="No_Meja")
	public List<realpesanan> Id_Meja;
	public String toString(){
		return String.valueOf(No_Meja);
	}
}
