package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import play.db.jpa.Model;

@Entity
public class bahanpakai extends Model {
	@ManyToOne
	public bahan Nama_Bahan;
	public double Stock;
	public Date Tanggal_Pakai;
	public String oleh;
}
