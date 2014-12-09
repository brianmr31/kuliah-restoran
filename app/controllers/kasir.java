package controllers;

import java.util.List;

import models.pembelian;
import models.pesanan;
import models.realpesanan;
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
		x = null ;
		//x.delete();
		//x._delete();
		//delete(id);
	}
	public static void delete(long id){
		//pesanan.delete("Nama_pesanannya.id=?", id);
		///realpesanan.delete("id=?",id);
		//index();
	}
}
