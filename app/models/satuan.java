package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
@Entity
public class satuan extends Model {
	public String Satuan;
	public String toString(){
		return this.Satuan;
	}
}
