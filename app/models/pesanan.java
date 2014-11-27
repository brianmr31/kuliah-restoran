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
	public status Status_Pesan;
	@ManyToOne
	public menu Nama_Menu;
	@ManyToOne
	public Orang Nama_Orang;
	@ManyToOne
	public meja No_Meja;
	
	public long Harga;
}
