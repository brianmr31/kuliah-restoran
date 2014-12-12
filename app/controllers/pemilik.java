package controllers;

import java.util.Date;
import java.util.List;

import models.bahan;
import models.bahanbeli;
import models.bahanpakai;
import models.menu;
import models.pesanan;
import models.realpesanan;
import models.realresep;
import models.resep;
import models.satuan;
import models.setting;
import play.mvc.Controller;

public class pemilik extends Controller{
	public static void lihatBahan(String mesg){
		List m = bahan.findAll();
		render(m,mesg);
	}
	public static void bahanBeli(){
		List b = bahan.findAll();
		List s = satuan.findAll();
		List m = bahanbeli.findAll();
		render(m,b,s);
	}
	public static void lihatResep(){
		long totalharga = 0 ;
		List<realresep> rr = realresep.findAll();
        for(realresep nn: rr){
			for(resep n: nn.idresep){
            	totalharga += n.Harga;
        	}
        	nn.Harga_menu = totalharga ;
        	nn.save();
        	totalharga=0;
        }
		List m = realresep.findAll();
		render(m);
	}
	public static void lihatBahanR(long id){
		realresep a;
		List<resep> r = resep.findAll();
        for(resep n : r){
  	  		n.Harga = n.Jumlah * n.Bahan.Harga_Persatuan ;
  	  		n.save();
  	    }
		a = realresep.find("id=?", id).first();
		List m = resep.find("Nama_RealResep=?", a).fetch();
		render(m);
	}
	public static void lihat(long a){
		// Looping list pesanan terus ditambah harga menu 
		double harga = 0;
		realpesanan x = realpesanan.find("id=?",a).first();
		List<pesanan> m = pesanan.find("Nama_pesanannya=?", x).fetch();
		for(pesanan p : m){
			p.Harga = p.Jumlah_Pesan * p.menu_pesan.HargaUntung;
			if(p.cek == 0){
			  for(resep n: p.menu_pesan.Nama_Resep.idresep){
				bahanpakai bpki = new bahanpakai() ;
        		n.Bahan.Stock -= n.Jumlah * p.Jumlah_Pesan ; 
        	 	n.Bahan.save();
        	 	bpki.Nama_Bahan = n.Bahan;
        	 	bpki.Stock = n.Jumlah * p.Jumlah_Pesan;
        	 	bpki.Tanggal_Pakai = new Date();
        	 	bpki.oleh = x.Nama_Pesanan;
        	 	bpki.save();
        	  }
			  p.cek = 1;
			}
			p.save();
			harga = harga+ p.Harga;
		}
		x.tagihan = harga ;
		x.save();
		render(m,a);
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
	public static void  lihatsetting(){
		//Ganti 13 dengan id setting yang dimasukan
		setting sa = setting.findById((long)13);
		render(sa);
	}
	public static void lihatMenu(){
		List<realresep> aa = realresep.findAll();
		List m = menu.findAll();
		List<menu> menua = menu.findAll();
		for(menu a: menua){
			 a.Harga =a.Nama_Resep.Harga_menu ;
			 a.save();
		}
		render(aa,m);
	}
}
