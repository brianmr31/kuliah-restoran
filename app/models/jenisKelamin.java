package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
@Entity
public class jenisKelamin extends Model{
	public String jenis;
	public String toString(){
		return this.jenis;
	}
}
