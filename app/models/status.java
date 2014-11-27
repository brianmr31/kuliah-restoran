package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;
@Entity
public class status extends Model{
	public String namaStatus;
	
	public String toString(){
		return this.namaStatus;
	}
		
	@OneToMany(cascade=CascadeType.ALL, mappedBy="sts")
	public List<pesanan> idstatus;
	
	
}
