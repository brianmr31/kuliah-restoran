package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import play.db.jpa.Model;
@Entity
public class meja extends Model {
	public long no_meja;
	@OneToMany(cascade=CascadeType.ALL, mappedBy="mja")
	public List<pesanan> idmeja;
	public String toString(){
		return String.valueOf(no_meja);
	}
}
