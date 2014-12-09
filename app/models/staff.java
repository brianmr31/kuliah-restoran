package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;
@Entity
public class staff extends Model{
	public String nama;
	public String email;
	@ManyToOne
	public HakAkses sebagai;
	public String toString(){
		return this.nama;
	}
}
