package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
@Entity
public class jenisKelamin extends Model{
	public String Nama_Jeniskelamin;
	public String toString(){
		return this.Nama_Jeniskelamin;
	}
}
