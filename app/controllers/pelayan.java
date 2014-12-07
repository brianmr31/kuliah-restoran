package controllers;

import java.util.List;

import models.Orang;
import models.meja;
import models.menu;
import models.pesanan;
import models.realpesanan;
import models.status;

import play.mvc.Controller;

public class pelayan extends Controller {
	public static void index(){
		List m = realpesanan.findAll();
		render(m);
	}
	public static void tambahPesanan(){
		List sp = status.findAll();
		List mp = menu.findAll();
		List o  = Orang.findAll();
		List nm = meja.findAll();
		
		render(sp,mp,o,nm);
	}
	public static void savePesanan(realpesanan a){
		a.save();
		menu(a.id);
	}
	public static void menu(long a ){
		List m = menu.findAll();
		render(m,a);
	}
	public static void lihat(long a){
		realpesanan x = realpesanan.find("id=?",a).first();
		List m = pesanan.find("Nama_pesanannya=?", x).fetch();
		render(m);
	}
	public static void hapuspesanan(long a){
		pesanan.delete("id=?",a);
		
	}
	public static void dipesan(pesanan a){
		a.save();
		index();
	}
	public static void test(){
		
	}
}
