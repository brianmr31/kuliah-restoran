package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
@Entity
public class HakAkses extends Model{
	public String Nama_Akses;
	public String toString(){
		return this.Nama_Akses;
	}
}
