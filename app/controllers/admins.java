package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
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
		String pesan = "<h1> beli bahan </h1> <hr> ";
		String subject="Daftar pembelian bahan" ;
		List<bahan> b = bahan.findAll();
		List s = satuan.findAll();
		List m = bahanbeli.findAll();
		for(bahan c : b){
			if(c.Harga_Persatuan == 0){
				
			}else{
				pesan += c.Nama_Bahan+" harga/satuan <br> "+c.Harga_Persatuan+"/"+c.Satuan.Satuan+" jumlah "+c.Stock+" <br>" ;
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
		String pesan = "<h1> Daftar Resep</h1> <hr> ";
		String subject="Daftar Resep" ;
		long totalharga = 0 ;
		List<realresep> rr = realresep.findAll();
        for(realresep nn: rr){
        	pesan += nn.Nama_Resep.toString()+" : " ;
			for(resep n: nn.idresep){
            	totalharga += n.Harga;
        	}
        	nn.Harga_menu = totalharga ;
        	pesan += "<h6> Rp "+Long.toString(totalharga)+",-- </h6> <br> " ;
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
		String pesan = "<h1> Daftar Daftar Menu </h1> <hr> " ;
		String subject= "Mail Daftar Menu";
		List<realresep> aa = realresep.findAll();
		List m = menu.findAll();
		List<menu> menua = menu.findAll();
		for(menu a: menua){
			 pesan += "<h3> Nama Menu : "+a.Nama_Menu+" </br> Keterangan : <br> "+a.Keterangan+" <br> Harga : Rp "+a.Harga+",-- <br> </h3>" ;
			 a.Harga =a.Nama_Resep.Harga_menu ;
			 a.save();
		}
		render(aa,m,pesan,subject);
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
		c.Untung = c.HargaUntung - c.Nama_Resep.Harga_menu ;
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
		String pesan = "<h1> Daftar Pemakaian bahan </h1> <hr> " ;
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
			    pesan += x.Nama_Bahan.Nama_Bahan + " : "+ total+" <br> "  ;
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
					pesan += x.Nama_Bahan.Nama_Bahan + " : "+ total+" <br> "  ;
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
    	HtmlEmail email = new HtmlEmail();
		setting Nemail = setting.findById((long)1);
		String Nama = Nemail.email ;
    	try {
    		email.addTo("brianmr31@gmail.com");
    		email.setFrom(Nama);
	    	email.setSubject(b);
	    	email.setMsg(a);
	    	Mail.send(email);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	    try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
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
	    }else if(b.equals("Mail Daftar Menu")){
	    	lihatMenu();
	    }else if(b.equals("keuntungan")){
	    	TotalSekarang(null,0);
	    }else if(b.equals("Pendapatan")){
	    	pendapatanSekarang(null,0);
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
		String pesan = "<h1> Daftar Delivery Order </h1> <hr> " ;
		String subject= "Mail Delivery Order";
		List<realpesanan> m = realpesanan.findAll();
		List o = Orang.findAll();
		for(realpesanan a:m){
			//a.tagihan = m.
			pesan += a.Nama_Pesanan.toString()+" <br> Nama_Orang : "+a.tagihan+" : "+a.No_Meja+" Tanggal : "+a.tanggal+" ";
			a.save();
		}
		render(m,o,pesan,subject);
	}
	public static void tambahorang(){
		String pesan = "<h1> Daftar Data Pelanggan </h1> <hr> " ;
		String subject= "Mail Data Pelanggan";
		List<Orang> o= Orang.findAll();
		List j= jenisKelamin.findAll();
		List h= HakAkses.findAll();
		for(Orang a: o){
			pesan +="<br> Nama : "+a.Nama+"<br> kontak : "+a.Kontak+"<br> Alamat : "+a.Alamat+"  " ;
		}
		render(o,j,h,pesan,subject);
	}
	public static void lihatpembelian(){
		String pesan = "<h1> Daftar Transaksi Penjualan </h1> <hr> " ;
		String subject= "Mail Transaksi Penjualan";
		List<pembelian> p = pembelian.findAll();
		for(pembelian a: p){
			pesan += "<br> kode_pembelian : "+a.kode_pembelian+" : <br> pemesan "+a.Pemesan+" : <br> Harga : "+a.Harga+" <br> ";
		}
		render(p,pesan,subject);
	}
	public static void TotalSekarang(Date tgl,int t){
		String pesan = "<h1> KeUntungan </h1> <hr> ";
		String subject="keuntungan";
		List<pembelian> p = pembelian.findAll();
		SimpleDateFormat aa = new SimpleDateFormat();
		SimpleDateFormat bb = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
		SimpleDateFormat cc = new SimpleDateFormat("yyyy-MMM-dd ");
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MMM");
		SimpleDateFormat ff = new SimpleDateFormat("yyyy");
		PrintStream a,b,d,f;
		long bulan = 0,tahun=0, hari = 0;
		try {
			a = new PrintStream("./public/data/dataUntungJam.tsv");
			b = new PrintStream("./public/data/dataUntungHari.tsv");
			d = new PrintStream("./public/data/dataUntungBulan.tsv");
			f = new PrintStream("./public/data/dataUntungTahun.tsv");
			a.print("date\t");
			a.print("close\n");
			b.print("date\t");
			b.print("close\n");
			d.print("date\t");
			d.print("close\n");
			f.print("date\t");
			f.print("close\n");
			Date awal = new Date();
			double UTS = 0 ,cUTS = 0;
			double UBS = 0 ,cUBS = 0;
			double UDS = 0 ,cUDS = 0;
			String Semua = null ;
			String thn= null,bln = null,day =null ;
			if(t == 1){
				awal = tgl;
				for(pembelian c : p){
					aa =  new SimpleDateFormat("yyyy");
					thn = aa.format(awal);
					if(tahun != awal.getYear()){
						f.print(ff.format(c.tgl_bayar));
						f.print("\t");
						tahun = c.tgl_bayar.getYear();
						for(pembelian cc2:p){
							if(c.tgl_bayar.getYear() == awal.getYear()){
								cUTS += cc2.Untung ;
							}
						}
						f.print(cUTS+"\n");
						cUTS = 0;
					}
					if(c.tgl_bayar.getYear() == awal.getYear() ){
						aa =  new SimpleDateFormat("MMM");
						bln = aa.format(awal);
						UTS += c.Untung ;
						if(bulan != c.tgl_bayar.getMonth()){
							d.print(dd.format(c.tgl_bayar));
							d.print("\t");
							bulan= c.tgl_bayar.getMonth();
							for(pembelian cc1 : p){
								if(c.tgl_bayar.getYear() == awal.getYear()){
									if(c.tgl_bayar.getMonth() == awal.getMonth()){
										cUBS += cc1.Untung ;
									}
								}
							}
							d.print(cUBS+"\n");
							cUBS = 0;
						}
						if(c.tgl_bayar.getMonth() == awal.getMonth()){
							aa =  new SimpleDateFormat("d");
							day = aa.format(awal);
							UBS += c.Untung ;
							if(hari != c.tgl_bayar.getDay() ){
								b.print(cc.format(c.tgl_bayar));
								b.print("\t");
								hari = c.tgl_bayar.getDay();
								for(pembelian ccc: p){
									if(c.tgl_bayar.getYear() == awal.getYear()){
										if(c.tgl_bayar.getMonth() == awal.getMonth()){
											if(ccc.tgl_bayar.getDay() == hari){
												cUDS += ccc.Untung;
											}
										}
									}
								}
								b.print(cUDS+"\n");
								cUDS = 0 ;
							}
							if(c.tgl_bayar.getDay() == awal.getDay()){
								UDS += c.Untung ;
								a.print(bb.format(c.tgl_bayar));
								a.print("\t");
								a.print(c.Untung+"\n");
							}
						}
					}
				}
				a.close();
				b.close();
				d.close();
				f.close();
				pesan += "<hr> Tahun "+thn+" = "+UTS+",-- <br> Bulan "+bln+" = Rp "+UBS+",-- <br>Hari"+UDS+",-- <br>";
				render(awal,thn,bln,day,UTS,UBS,UDS,pesan,subject);
			}else{
				for(pembelian c: p){
					Semua = "Semua";
					UTS += c.Untung ;
				}
				pesan += "<hr> Total Semua Keuntungan dari pembayaran adalah Rp"+UTS+",-- <hr>";
				render(UTS,Semua,pesan,subject);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void keuntunganJam(Date awal){
		render(awal);
	}
	public static void keuntunganBulan(Date awal){
		render(awal);
	}
	public static void keuntunganHari(Date awal){
		render(awal);
	}
	public static void pendapatanSekarang(Date tgl,int t){
		String pesan = "<h1> Pendapatan </h1> <hr> ";
		String subject="Pendapatan";
		List<pembelian> p = pembelian.findAll();
		SimpleDateFormat aa = new SimpleDateFormat();
		SimpleDateFormat bb = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
		SimpleDateFormat cc = new SimpleDateFormat("yyyy-MMM-dd ");
		SimpleDateFormat dd = new SimpleDateFormat("yyyy-MMM");
		SimpleDateFormat ff = new SimpleDateFormat("yyyy");
		PrintStream a,b,d,f;
		long bulan = 0,tahun=0, hari = 0;
		try {
			a = new PrintStream("./public/data/datapendapatanJam.tsv");
			b = new PrintStream("./public/data/datapendapatangHari.tsv");
			d = new PrintStream("./public/data/datapendapatanBulan.tsv");
			f = new PrintStream("./public/data/datapendapatangTahun.tsv");
			a.print("date\t");
			a.print("close\n");
			b.print("date\t");
			b.print("close\n");
			d.print("date\t");
			d.print("close\n");
			f.print("date\t");
			f.print("close\n");
			Date awal = new Date();
			double UTS = 0 ,cUTS = 0;
			double UBS = 0 ,cUBS = 0;
			double UDS = 0 ,cUDS = 0;
			String Semua = null ;
			String thn= null,bln = null,day =null ;
			if(t == 1){
				awal = tgl;
				for(pembelian c : p){
					aa =  new SimpleDateFormat("yyyy");
					thn = aa.format(awal);
					if(tahun != awal.getYear()){
						f.print(ff.format(c.tgl_bayar));
						f.print("\t");
						tahun = c.tgl_bayar.getYear();
						for(pembelian cc2:p){
							if(c.tgl_bayar.getYear() == awal.getYear()){
								cUTS += cc2.Harga ;
							}
						}
						f.print(cUTS+"\n");
						cUTS = 0;
					}
					if(c.tgl_bayar.getYear() == awal.getYear() ){
						aa =  new SimpleDateFormat("MMM");
						bln = aa.format(awal);
						UTS += c.Harga ;
						if(bulan != c.tgl_bayar.getMonth()){
							d.print(dd.format(c.tgl_bayar));
							d.print("\t");
							bulan= c.tgl_bayar.getMonth();
							for(pembelian cc1 : p){
								if(c.tgl_bayar.getYear() == awal.getYear()){
									if(c.tgl_bayar.getMonth() == awal.getMonth()){
										cUBS += cc1.Harga ;
									}
								}
							}
							d.print(cUBS+"\n");
							cUBS = 0;
						}
						if(c.tgl_bayar.getMonth() == awal.getMonth()){
							aa =  new SimpleDateFormat("d");
							day = aa.format(awal);
							UBS += c.Harga ;
							if(hari != c.tgl_bayar.getDay() ){
								b.print(cc.format(c.tgl_bayar));
								b.print("\t");
								hari = c.tgl_bayar.getDay();
								for(pembelian ccc: p){
									if(c.tgl_bayar.getYear() == awal.getYear()){
										if(c.tgl_bayar.getMonth() == awal.getMonth()){
											if(ccc.tgl_bayar.getDay() == hari){
												cUDS += ccc.Harga;
											}
										}
									}
								}
								b.print(cUDS+"\n");
								cUDS = 0 ;
							}
							if(c.tgl_bayar.getDay() == awal.getDay()){
								UDS += c.Harga ;
								a.print(bb.format(c.tgl_bayar));
								a.print("\t");
								a.print(c.Harga+"\n");
							}
						}
					}
				}
				a.close();
				b.close();
				d.close();
				f.close();
				pesan += "<hr> Tahun "+thn+" = "+UTS+",-- <br> Bulan "+bln+" = Rp "+UBS+",-- <br>Hari"+UDS+",-- <br>";
				render(awal,thn,bln,day,UTS,UBS,UDS,pesan,subject);
			}else{
				for(pembelian c: p){
					Semua = "Semua";
					UTS += c.Harga ;
				}
				pesan += "<hr> Total Semua Keuntungan dari pembayaran adalah Rp"+UTS+",-- <hr>";
				render(UTS,Semua,pesan,subject);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void pendapatanJam(Date awal){
		render(awal);
	}
	public static void pendapatanBulan(Date awal){
		render(awal);
	}
	public static void pendapatanHari(Date awal){
		render(awal);
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
