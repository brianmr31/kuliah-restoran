package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;
@Entity
public class user extends Model{
	public String user;
	public String pass;
	@ManyToOne
	public HakAkses level;
	
	public String toString(){
		return this.user;
	}
}
