package controllers;

import java.util.Date;
import java.util.List;

import models.HakAkses;
import models.Orang;
import models.bahanpakai;
import models.jenisKelamin;
import models.meja;
import models.menu;
import models.pembelian;
import models.pesanan;
import models.realpesanan;
import models.resep;
import models.status;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
@Check("kasir")
public class kasir extends Controller {
	public static void index(){
		List<realpesanan> m = realpesanan.findAll();
		List o = Orang.findAll();
		for(realpesanan a:m){
			//a.tagihan = m.
			a.save();
		}
		render(m,o);
	}
	public static void bayar(long id){
		long Totaljumlah = 0 ;
		String pesan = "" ;
		pembelian pbl = new pembelian();
		realpesanan x = realpesanan.findById(id);
		pbl.Harga = x.tagihan ;
		pbl.tgl_bayar= x.tanggal;
		pbl.Pemesan = x.Nama_Pesanan;
		pbl.Untung = x.Untung;
		pbl.pesanannya = "";
		pbl.kode_pembelian = "KodePem"+String.valueOf(x.id)+"_"+pbl.Pemesan;
		menu mn;
		List<resep> rsp = null;
		List<pesanan> psn = pesanan.find("Nama_pesanannya=?", x).fetch();
		for(pesanan p: psn){
		mn = menu.find("Nama_Menu=?", p.menu_pesan.Nama_Menu).first();
			pbl.pesanannya +=p.menu_pesan.Nama_Menu+",";
		rsp = resep.find("Nama_RealResep=?", mn.Nama_Resep).fetch();
			for(resep y : rsp){
				pesan += " " ;
				pesan += y.Bahan.Nama_Bahan + " ; Jumlah : ";
				pesan += y.Jumlah+" ; Harga :  ";
				pesan += y.Harga+"  ";
				Totaljumlah += y.Harga;
				pesan += "; Harga Total : "+Totaljumlah+"";
			}
		}
		pbl.save();
		x.delete();
		index();
		//render(rsp,pesan);
	}
	public static void antarsave(realpesanan c){
		c.Status_Pesan = status.findById((long)1);;
		c.No_Meja  = meja.findById((long)1);
		c.tanggal = new Date();
		c.save();
		index();
	}
	public static void tambahorang(Orang x,long id){
		List<Orang> o= Orang.findAll();
		List j= jenisKelamin.findAll();
		List h= HakAkses.findAll();
		render(o,j,h,x,id);
	}
	public static void simpanorang(Orang o,long id){
		o.save();
		tambahorang(null,0);
	}
	public static void hapusorang(long id){
		Orang.delete("id=?", id);
		tambahorang(null,0);
	}
	public static void editorang(long id){
		Orang o = Orang.findById(id);
		tambahorang(o,id);
	}
	public static void lihatpembelian(){
		List<pembelian> p = pembelian.findAll();
		render(p);
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
		double harga = 0;
		double untung= 0;
		realpesanan x = realpesanan.find("id=?",a).first();
		List<pesanan> m = pesanan.find("Nama_pesanannya=?", x).fetch();
		for(pesanan p : m){
			p.Harga = p.Jumlah_Pesan * p.menu_pesan.HargaUntung ;
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
	public static void getPicture(Long id) {
	    menu Menu = menu.findById(id);
	    notFoundIfNull(Menu);
	    response.setContentTypeIfNotSet(Menu.gambar.type());
	    renderBinary(Menu.gambar.get());
	}
}
