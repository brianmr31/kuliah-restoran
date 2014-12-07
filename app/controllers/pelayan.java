package controllers;

import java.util.List;

import models.Orang;
import models.meja;
import models.menu;
import models.pesanan;
import models.realpesanan;
import models.status;

import play.mvc.Controller;

public class pelayan extends Controller {
	public static void index(){
		List m = realpesanan.findAll();
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
		a.save();
		render(a);
		//savePP(a.id,b);
	}
}
