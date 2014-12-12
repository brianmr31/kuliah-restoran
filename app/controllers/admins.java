package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import models.HakAkses;
import models.Orang;
import models.bahan;
import models.bahanbeli;
import models.bahanpakai;
import models.jenisKelamin;
import models.menu;
import models.pembelian;
import models.pesanan;
import models.realpesanan;
import models.realresep;
import models.resep;
import models.satuan;
import models.setting;
import models.staff;
import play.libs.Mail;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
@Check("admin")
public class admins extends Controller {
	public static final Map<String, String> bhn =new HashMap<String, String>();
	public static void lihatBahan(String mesg){
		String pesan = "kelola bahan \n ";
		String subject="Daftar kelola bahan" ;
		List<bahan> m = bahan.findAll();
		for(bahan n:m){
			pesan += n.Nama_Bahan +" stok "+String.valueOf(n.Stock)+" ; \n ";
		}
		render(m,mesg,pesan,subject);
	}
	public static void awal(){
		render();
	}
	public static void tambahResep(){
		render();
	}
	public static void tambahMenu(){
		render();
	}
	public static void bahanBeli(){
		String pesan = "beli bahan \n ";
		String subject="Daftar pembelian bahan" ;
		List<bahan> b = bahan.findAll();
		List s = satuan.findAll();
		List m = bahanbeli.findAll();
		for(bahan c : b){
			if(c.Harga_Persatuan == 0){
				
			}else{
				pesan += c.Nama_Bahan+" harga/satuan \n "+c.Harga_Persatuan+"/"+c.Satuan.Satuan+" jumlah "+c.Stock+" ; \n" ;
			}
		}
		//pesan += b.toString()+"\n";
		render(m,b,s,pesan,subject);
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
		String pesan = " Daftar Resep \n ";
		String subject="Daftar Resep" ;
		long totalharga = 0 ;
		List<realresep> rr = realresep.findAll();
        for(realresep nn: rr){
        	pesan += nn.Nama_Resep.toString()+" : " ;
			for(resep n: nn.idresep){
            	totalharga += n.Harga;
        	}
        	nn.Harga_menu = totalharga ;
        	pesan += Long.toString(totalharga)+" ;\n " ;
        	nn.save();
        	totalharga=0;
        }
		List m = realresep.findAll();
		render(m,pesan,subject);
	}
	public static void lihatBahanR(long id){
		realresep a;
		List<resep> r = resep.findAll();
        for(resep n : r){
  	  		n.Harga = n.Jumlah * n.Bahan.Harga_Persatuan ;
  	  		n.save();
  	    }
		a = realresep.find("id=?", id).first();
		List<resep> m = resep.find("Nama_RealResep=?", a).fetch();
		render(m,id);
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
			List s = satuan.findAll();
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
			render(m,b,a,s);
		}else{
			render();
		}
	}
	public static void saveBahanR(resep m,long id){
		resep x = resep.find("Bahan=? and Nama_RealResep.id=?", m.Bahan,id).first();
		if(x == null ){
			m.save();
		}else{
			x.Jumlah += m.Jumlah ;
			x.save();
		}
		lihatBahanR(id);
	}
	public static void saveBahan(resep m,long id){
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
	public static void getPicture(Long id) {
	    menu Menu = menu.findById(id);
	    notFoundIfNull(Menu);
	    response.setContentTypeIfNotSet(Menu.gambar.type());
	    renderBinary(Menu.gambar.get());
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
		String pesan = "Daftar Pemakaian bahan \n " ;
		String subject= "Mail Pemakaian bahan ";
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
			    pesan += x.Nama_Bahan.Nama_Bahan + " : "+ total+";\n "  ;
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
					pesan += x.Nama_Bahan.Nama_Bahan + " : "+ total+";\n "  ;
					bhn.put(x.Nama_Bahan.Nama_Bahan,total);
				}
			}
			totalPemakaian += x.Stock;
			i++;
			bhn.put("jumlah", Long.toString(totalPemakaian));
			//pesan = bhn.toString();
		}
		//String a = nm_bhn.toString();
		render(m,bhn,pesan,subject);
		
	}
	public static void sendmail(String a,String b){
		SimpleEmail email = new SimpleEmail();
		setting Nemail = setting.findById((long)13);
		String Nama = Nemail.email ;
    	try {
			email.setFrom("brian@localhost");
			email.addTo(Nama);
	    	email.setSubject(b);
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
	    if(b.equals("Daftar Resep")){
	    	lihatResep();
	    }else if(b.equals("Mail Pemakaian bahan ")){
	    	lihatBahanPakai();
	    }else if(b.equals("Daftar pembelian bahan")){
	    	bahanBeli();
	    }else if(b.equals("Daftar kelola bahan")){
	    	lihatBahan(null);
	    }else if(b.equals("Mail Delivery Order")){
	    	index();
	    }else if(b.equals("Mail Data Pelanggan")){
	    	tambahorang();
	    }else if(b.equals("Mail Transaksi Penjualan")){
	    	lihatpembelian();
	    }
	    
	}
	public static void  lihatsetting(){
		//Ganti 13 dengan id setting yang dimasukan
		setting sa = setting.findById((long)1);
		render(sa);
	}
	public static void savesetting(setting m){
		m.save();
		lihatsetting();
	}
	public static void index(){
		String pesan = "Daftar Delivery Order \n ============================================\n " ;
		String subject= "Mail Delivery Order";
		List<realpesanan> m = realpesanan.findAll();
		List o = Orang.findAll();
		for(realpesanan a:m){
			//a.tagihan = m.
			pesan += a.Nama_Pesanan.toString()+" \n Nama_Orang : "+a.tagihan+" : "+a.No_Meja+" Tanggal : "+a.tanggal+";\n";
			a.save();
		}
		render(m,o,pesan,subject);
	}
	public static void tambahorang(){
		String pesan = "Daftar Data Pelanggan \n ============================================\n " ;
		String subject= "Mail Data Pelanggan";
		List<Orang> o= Orang.findAll();
		List j= jenisKelamin.findAll();
		List h= HakAkses.findAll();
		for(Orang a: o){
			pesan +="====================================== \n Nama : "+a.Nama+" \n kontak : "+a.Kontak+" \n Alamat : "+a.Alamat+" \n " ;
		}
		render(o,j,h,pesan,subject);
	}
	public static void lihatpembelian(){
		String pesan = "Daftar Transaksi Penjualan \n ============================================\n " ;
		String subject= "Mail Transaksi Penjualan";
		List<pembelian> p = pembelian.findAll();
		for(pembelian a: p){
			pesan += "====================================== \n kode_pembelian : "+a.kode_pembelian+" : \n pemesan "+a.Pemesan+" : \n Harga : "+a.Harga+" \n ";
		}
		render(p,pesan,subject);
	}
}
