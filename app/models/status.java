package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;
@Entity
public class status extends Model{
	public String Nama_Status;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="Status_Pesan")
	public List<pesanan> idstatus;
	public String toString(){
		return this.Nama_Status;
	}
}
