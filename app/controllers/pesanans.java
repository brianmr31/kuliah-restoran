package controllers;

import java.util.Date;
import java.util.List;

import models.pesanan;
import models.resep;
import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import controllers.CRUD.ObjectType;

public class pesanans extends CRUD {
	/*
	public static void list(int page, String search, String searchFields, String orderBy, String order) {
        ObjectType type = ObjectType.get(getControllerClass());
        notFoundIfNull(type);
        if (page < 1) {
            page = 1;
        }
        List<Model> objects = type.findPage(page, search, searchFields, orderBy, order, (String) request.args.get("where"));
        Long count = type.count(search, searchFields, (String) request.args.get("where"));
        Long totalCount = type.count(null, null, (String) request.args.get("where"));
        /*
        List<pesanan> rr = pesanan.findAll();
        Date current = new Date();
        for(pesanan nn: rr){
           nn.Harga = nn.Nama_Menu * nn.Jumlah_Pesan ;
           if(nn.Tanggal_Pesan == null ||nn.Tanggal_Pesan.getTime() > current.getTime()){
        	      for(resep n: nn.Nama_Menu.Nama_Resep.idresep){
             		n.Bahan.Stock -= n.Jumlah * nn.Jumlah_Pesan ; 
             	 	n.Bahan.save();
            	  }
             	nn.Tanggal_Pesan = current;
             	nn.save();
         	}else{
         	
         	}
        }
     
        try {
            render(type, objects, count, totalCount, page, orderBy, order);
        } catch (TemplateNotFoundException e) {
            render("CRUD/list.html", type, objects, count, totalCount, page, orderBy, order);
        }
    }
    */
}
