package controllers;

import java.util.List;

import models.bahan;
import models.realresep;
import models.resep;
import play.mvc.Controller;

public class admins extends Controller {
	public static void lihatBahan(){
		List m = bahan.findAll();
		render(m);
	}
	public static void tambahResep(){
		render();
	}
	public static void saveRealResep(realresep a){
		a.save();
		lihatResep();
	}
	public static void hapusResep(long id){
		//resep.delete("realresep.id=?", id);
		realresep.delete("id=?",id);
	}
	public static void lihatResep(){
		List m = realresep.findAll();
		render(m);
	}
	public static void lihatBahanR(long id){
		realresep a;
		a = realresep.find("id=?", id).first();
		List m = resep.find("Nama_RealResep=?", a).fetch();
		render(m);
	}
	public static void hapusBahanR(long id){
		resep.delete("id=?", id);
	}
	public static void tambahBahanR(long a){
		List m = resep.findAll() ;
		List b = bahan.findAll();
		render(m,b,a);
	}
	public static void saveBahanR(resep m){
		m.save();
		
	}
}
