package controllers;

import java.util.List;

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
		pembelian pbl = new pembelian();
		realpesanan x = realpesanan.findById(id);
		pbl.Harga = x.tagihan ;
		pbl.tgl_bayar= x.tanggal;
		pbl.Pemesan = x.Nama_Pesanan;
		pbl.kode_pembelian = "KodePem"+String.valueOf(x.id)+"_"+pbl.Pemesan;
		pbl.save();
		List<resep> rsp = resep.find("Nama_RealResep=?", x).fetch();
		x.delete();
		render(rsp);
	}
}
