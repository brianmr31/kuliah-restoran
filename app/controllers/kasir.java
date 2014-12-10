package controllers;

import java.util.Date;
import java.util.List;

import models.Orang;
import models.meja;
import models.menu;
import models.pembelian;
import models.pesanan;
import models.realpesanan;
import models.resep;
import models.status;
import play.mvc.Controller;

public class kasir extends Controller {
	public static void index(){
		List m = realpesanan.findAll();
		List o = Orang.findAll();
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
		pbl.kode_pembelian = "KodePem"+String.valueOf(x.id)+"_"+pbl.Pemesan;
		//pbl.save();
		menu mn;
		List<resep> rsp = null;
		List<pesanan> psn = pesanan.find("Nama_pesanannya=?", x).fetch();
		for(pesanan p: psn){
		mn = menu.find("Nama_Menu=?", p.menu_pesan.Nama_Menu).first();
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
		x.delete();
		index();
		//render(rsp,pesan);
	}
	public static void antarsave(realpesanan c){
		c.Status_Pesan = status.findById((long)1);;
		c.No_Meja  = meja.findById((long)4);
		c.tanggal = new Date();
		c.save();
	}
}
