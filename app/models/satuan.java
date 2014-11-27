package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
@Entity
public class satuan extends Model {
	public String satuan;
	public String toString(){
		return this.satuan;
	}
}
