package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
@Entity
public class setting extends Model{
	public String Nama_setting ;
	public String email ;
	public String printKasir;
	public String printadmin;
}
