package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
@Entity
public class HakAkses extends Model{
	public String NamaAkses;
	
	public String toString(){
		return this.NamaAkses;
	}
}
