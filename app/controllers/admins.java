package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import models.HakAkses;
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
import models.staff;
import play.libs.Mail;
import play.mvc.Controller;

public class admins extends Controller {
	public static final Map<String, String> bhn =new HashMap<String, String>();
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
	public static void editMenu(long id){
		List aa = realresep.findAll(); 
		menu m = menu.find("id=?", id).first();
		render(m,aa);
	}
	public static void hapusMenu(long id){
		menu.delete("id=?", id);
		lihatMenu();
	}
	public static void saveMenu(menu c){
		c.save();
		lihatMenu();
	}
	public static void lihatstaff(){
		List<HakAkses> aa = HakAkses.findAll();
		List s = staff.findAll();
		render(aa,s);
	}
	public static void saveStaff(staff m){
		m.save();
		lihatstaff();
	}
	public static void editstaff(long id){
		staff z= staff.find("id=?",id).first();
		List s= HakAkses.findAll();
		render(z,s);
	}
	public static void hapusStaff(long id){
		staff.delete("id=?", id);
		lihatstaff();
	}
	public static void lihatBahanPakai(){
		long totalPemakaian = 0;
		long totalper = 0 ;
		String total = null ;
		String pesan = null ;
		int i =0 ;
		List<bahanpakai> m = bahanpakai.findAll();
		String nm = null;
		//List<String> bhn = new ArrayList<String>();
		Map<String, String> bhn =new HashMap<String, String>();
		for(bahanpakai x: m){
			if(i==0){
				//bhn.add(x.Nama_Bahan.Nama_Bahan);
			    List<bahanpakai> jml = bahanpakai.find("Nama_Bahan=?", x.Nama_Bahan).fetch();
			    for(bahanpakai xx : jml){
			    	totalper += xx.Stock;
			    }
			    total = Long.toString(totalper);
			    bhn.put(x.Nama_Bahan.Nama_Bahan,total);
				nm=x.Nama_Bahan.Nama_Bahan;
			}else{
				if(nm != x.Nama_Bahan.Nama_Bahan ){
					nm = x.Nama_Bahan.Nama_Bahan;
					//bhn.add(x.Nama_Bahan.Nama_Bahan);
					List<bahanpakai> jml = bahanpakai.find("Nama_Bahan=?", x.Nama_Bahan).fetch();
					    for(bahanpakai xx : jml){
					    	totalper += xx.Stock;
					    }
					total = Long.toString(totalper);
					//bhn.add(total);
					bhn.put(x.Nama_Bahan.Nama_Bahan,total);
				}
			}
			totalPemakaian += x.Stock;
			i++;
			bhn.put("jumlah", Long.toString(totalPemakaian));
			pesan = bhn.toString();
		}
		//String a = nm_bhn.toString();
		render(m,bhn,pesan);
		
	}
	public static void sendmail(String a){
		SimpleEmail email = new SimpleEmail();
		setting Nemail = setting.findById((long)13);
		String Nama = Nemail.email ;
    	try {
			email.setFrom("brian@localhost");
			email.addTo(Nama);
	    	email.setSubject("Mail Pemakaian bahan ");
	    	email.setMsg(a);
	    	Mail.send(email);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	    try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    email = null ;
	    lihatBahanPakai();
	}
	public static void  lihatsetting(){
		//Ganti 13 dengan id setting yang dimasukan
		setting sa = setting.findById((long)13);
		render(sa);
	}
	public static void savesetting(setting m){
		m.save();
		lihatsetting();
	}
}
