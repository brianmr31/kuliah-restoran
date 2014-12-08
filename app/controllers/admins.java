package controllers;

import java.util.Date;
import java.util.List;

import models.bahan;
import models.bahanbeli;
import models.realresep;
import models.resep;
import models.satuan;
import play.mvc.Controller;

public class admins extends Controller {
	public static void lihatBahan(String mesg){
		List m = bahan.findAll();
		render(m,mesg);
	}
	public static void tambahResep(){
		render();
	}
	public static void bahanBeli(){
		List b = bahan.findAll();
		List s = satuan.findAll();
		List m = bahanbeli.findAll();
		render(m,b,s);
	}
	public static void saveBeliBahan(bahanbeli bb){
		bahan a = bahan.find("Nama_bahan=?", bb.Nama_bahan).first() ;
		bb.Tanggal_Beli =  new Date();
		bb.save();
		a.Harga_Persatuan = bb.Harga_Persatuan;
		a.Tanggal_Beli =  new Date() ;
		a.Stock = a.Stock + bb.Stock;
		a.Satuan = bb.satuan;
		a.save();
		bahanBeli();
	}
	public static void hapusBeliBahanLog(long id){
		bahanbeli.delete("id=?", id);
		bahanBeli();
	}
	public static void hapusBahan(long id){
		try{
			bahan.delete("id=?", id);
			lihatBahan(" Hello World ");
		}catch(Exception e){
			lihatBahan("Bahan dipakai oleh resep");
		}
		
	}
	
	public static void saveBarang(bahan m){
		m.save();
		lihatBahan(" Hello World ");
	}
	public static void saveRealResep(realresep a){
		a.save();
		lihatResep();
	}
	public static void hapusResep(long id){
		//resep.delete("realresep.id=?", id);
		resep.delete("Nama_RealResep.id=?", id);
		realresep.delete("id=?",id);
		lihatResep();
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
	public static void hapusBahanR(long id){
		resep.delete("id=?", id);
		lihatResep();
	}
	public static void tambahBahanR(long a){
		// cari langsung dari resep.realresepnya
		List m = resep.find("Nama_RealResep.id=?", a).fetch();
		if(m != null ){
			List b = bahan.findAll() ;
			//List b = bahan.find("Bahan=?", m.Bahan).fetch();
			//List<bahan> bhsA = null;
			//long[] arr =null ;
			//long id= 0 ;
			//for(resep c : b){
			//	int i = 0;
			//	id = c.Bahan.id;
				//arr[i]=id;
				//bhsA.add(bhs);
			//	i++;
			//}
			render(m,b,a);
		}else{
			render();
		}
		
	}
	public static void saveBahanR(resep m,long id){
		m.save();
		lihatBahanR(id);
	}
}
