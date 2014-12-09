package controllers;

import java.util.Date;
import java.util.List;

import models.Orang;
import models.bahanpakai;
import models.meja;
import models.menu;
import models.pesanan;
import models.realpesanan;
import models.realresep;
import models.resep;
import models.status;

import play.db.jpa.GenericModel.JPAQuery;
import play.mvc.Controller;

public class pelayan extends Controller {
	public static void index(){
		List<realpesanan> m = realpesanan.findAll();
		//List<pesanan> n = pesanan.
		//pesanan
		for(realpesanan a:m){
			//a.tagihan = m.
			a.save();
		}
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
		List<menu> r = menu.findAll();
        for(menu n : r){
  	  		n.Harga = n.Nama_Resep.Harga_menu ;
  	  		n.save();
  	    }
		List m = menu.findAll();
		render(m,a);
	}
	public static void lihat(long a){
		// Looping list pesanan terus ditambah harga menu 
		long harga = 0;
		
		realpesanan x = realpesanan.find("id=?",a).first();
		List<pesanan> m = pesanan.find("Nama_pesanannya=?", x).fetch();
		for(pesanan p : m){
			p.Harga = p.Jumlah_Pesan * p.menu_pesan.HargaUntung;
			Date current = new Date();
			if(p.cek == 0){
			  for(resep n: p.menu_pesan.Nama_Resep.idresep){
				bahanpakai bpki = new bahanpakai() ;
        		n.Bahan.Stock -= n.Jumlah * p.Jumlah_Pesan ; 
        	 	n.Bahan.save();
        	 	bpki.Nama_Bahan = n.Bahan;
        	 	bpki.Stock = n.Jumlah * p.Jumlah_Pesan;
        	 	bpki.Tanggal_Pakai = new Date();
        	 	bpki.save();
        	  }
			  p.cek = 1;
			}
			//resep xx = resep.find("id=?", p.menu_pesan.Nama_Resep.id).first();
			//bpki.Stock = xx.Jumlah;
			//bpki.Nama_Bahan.id = xx.Bahan.id;
			///bpki.Tanggal_Pakai = new Date();
			//bpki.save();
			//for(resep y:xx){
			//	bpki.Stock = 0;
			//	bpki.Nama_Bahan.id = y.Bahan.id;
			//	bpki.Tanggal_Pakai = new Date();
			//	bpki.save();
			//}
			p.save();
			harga = harga+ p.Harga;
		}
		x.tagihan = harga ;
		x.save();
		render(m,a);
	}
	public static void hapuspesanan(long a,long la){
		pesanan.delete("id=?",a);
		lihat(la);
	}
	public static void hapusrealpesanan(long id){
		pesanan.delete("Nama_pesanannya.id=?", id);
		realpesanan.delete("id=?",id);
		index();
	}
	public static void dipesan(pesanan a,long id){
		a.Tanggal_Pesan=new Date();
		a.cek = 0;
		a.save();
		lihat(id);
	}
	public static void test(){
		
	}
}
