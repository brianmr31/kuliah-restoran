package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;
@Entity
public class pesanan extends Model{
	@ManyToOne
	public status sts;
	@ManyToOne
	public menu mnu;
	@ManyToOne
	public Orang org;
	@ManyToOne
	public meja mja;
	public long harga;
}
