package models;

import javax.persistence.Entity;

import play.db.jpa.Model;
@Entity
public class user extends Model{
	public String user;
	public String pass;
}
