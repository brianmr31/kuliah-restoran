package controllers;

import java.util.List;

import models.realresep;
import models.resep;

import play.db.Model;
import play.exceptions.TemplateNotFoundException;
import controllers.CRUD.ObjectType;

public class realreseps extends CRUD {
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
        long totalharga = 0 ;
        List<realresep> rr = realresep.findAll();
        for(realresep nn: rr){
        	for(resep n: nn.idresep){
            	totalharga += n.Harga;
        	}
        	nn.Harga_menu = totalharga ;
        	nn.save();
        }
        try {
            render(type, objects, count, totalCount, page, orderBy, order);
        } catch (TemplateNotFoundException e) {
            render("CRUD/list.html", type, objects, count, totalCount, page, orderBy, order);
        }
    }
    */
}
