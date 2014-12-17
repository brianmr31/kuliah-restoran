package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Orang;
import models.bahan;
import models.bahanpakai;
import models.meja;
import models.menu;
import models.pembelian;
import models.pesanan;
import models.realpesanan;
import models.realresep;
import models.resep;
import models.status;
import play.db.jpa.GenericModel.JPAQuery;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
@Check("pelayan")
public class pelayan extends Controller {
	public static void awal(){
		render();
	}
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
		a.tanggal = new Date();
		a.Nama_Orang = Orang.findById((long)4);
		a.Status_Pesan = status.findById((long)2);
		a.save();
		menu(a.id);
	}
	public static void menu(long a ){
		List<menu> r = menu.findAll();
		List nm = meja.findAll();
        for(menu n : r){
  	  		n.Harga = n.Nama_Resep.Harga_menu ;
  	  		n.save();
  	    }
		List m = menu.findAll();
		render(m,a,nm);
	}
	public static void lihat(long a){
		// Looping list pesanan terus ditambah harga menu 
		double harga = 0;
		double untung= 0;
		realpesanan x = realpesanan.find("id=?",a).first();
		List<pesanan> m = pesanan.find("Nama_pesanannya=?", x).fetch();
		for(pesanan p : m){
			p.Harga = p.Jumlah_Pesan * p.menu_pesan.HargaUntung;
			p.Untung= p.Jumlah_Pesan * p.menu_pesan.Untung;
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
			untung += p.Untung;
			harga += p.Harga;
		}
		x.tagihan = harga ;
		x.Untung = untung;
		x.save();
		render(m,a);
	}
	public static void hapuspesanan(long a,long la){
		double harga = 0;
		realpesanan x = realpesanan.find("id=?",la).first();
		List<pesanan> m = pesanan.find("Nama_pesanannya=?", x).fetch();
		for(pesanan p : m){
			if(p.cek == 1){
			  for(resep n: p.menu_pesan.Nama_Resep.idresep){
				bahanpakai.delete("oleh=?", x.Nama_Pesanan);
        		n.Bahan.Stock += n.Jumlah * p.Jumlah_Pesan ; 
        	 	n.Bahan.save();
        	  }
			}
			p.save();
			harga = harga+ p.Harga;
		}
		pesanan.delete("id=?",a);
		lihat(la);
	}
	public static void hapusrealpesanan(long id){
		double harga = 0;
		realpesanan x = realpesanan.find("id=?",id).first();
		List<pesanan> m = pesanan.find("Nama_pesanannya=?", x).fetch();
		for(pesanan p : m){
			if(p.cek == 1){
			  for(resep n: p.menu_pesan.Nama_Resep.idresep){
				bahanpakai.delete("oleh=?", x.Nama_Pesanan);
        		n.Bahan.Stock += n.Jumlah * p.Jumlah_Pesan ; 
        	 	n.Bahan.save();
        	  }
			}
			p.save();
			harga = harga+ p.Harga;
		}
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
	public static void getPicture(Long id) {
	    menu Menu = menu.findById(id);
	    notFoundIfNull(Menu);
	    response.setContentTypeIfNotSet(Menu.gambar.type());
	    renderBinary(Menu.gambar.get());
	}
	public static void menufavorit(){ 
		//Map<String, String> mf =new HashMap<String, String>();
		List<pembelian> pbl = pembelian.findAll();
		List<realresep> rr	= realresep.findAll();
		String[] daftar = new String[rr.size()];
		String[][] menu = new String[rr.size()][2];
		int[] jmlDaftar = new int[rr.size()];
		int i = 0,j= 0 ;
		int pri = 0 ;
		List<String> a = new ArrayList<String>();
		List<String> b = new ArrayList<String>();
		for(realresep r : rr){
			daftar[i] = r.Nama_Resep;
			a.add(daftar[i]);
			i++;
		}
		String[] aa = null ; 
		for(pembelian p : pbl){
			aa = p.pesanannya.split(" ,");
			for(String a1 : aa){
				a.add(a1);
			}
		}
		for(i = 0 ; i < rr.size(); i++){
			for(j = 0 ; j < a.size() ; j++ ){
				if(daftar[i].equals(a.get(j))){
					jmlDaftar[i] += 1 ;
				}
			}
		}
		for(i = 0 ;i < jmlDaftar.length;i++){
			for(j = 0 ;j < jmlDaftar.length;j++){
				if(jmlDaftar[i] >= jmlDaftar[j]){
					pri += 1;
				}
			}
			menu[jmlDaftar.length-pri][0] = daftar[i];
			menu[jmlDaftar.length-pri][1] = String.valueOf(jmlDaftar[i]);
			pri=0;
		}
		a.clear();
		for(i = 0 ; i < menu.length;i++){
			b.add(menu[i][0]);
			//b.add(menu[i][1]);
			//mf.put(menu[i][0], String.valueOf(menu[i][1]));
		}
		render(b);
	}
}
