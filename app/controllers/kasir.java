package controllers;

import java.util.Date;
import java.util.List;

import models.menu;
import models.pembelian;
import models.pesanan;
import models.realpesanan;
import models.resep;
import play.mvc.Controller;

public class kasir extends Controller {
	public static void index(){
		List m = realpesanan.findAll();
		render(m);
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
				pesan += y.Bahan.Nama_Bahan + " : ";
				pesan += y.Jumlah+"\n";
				Totaljumlah += y.Harga;
			}
		}
		//x.delete();
		render(rsp,pesan);
	}
}
