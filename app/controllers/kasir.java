package controllers;

import java.util.List;

import models.menu;
import play.mvc.Controller;

public class kasir extends Controller {
	public static void index(){
		List m = menu.findAll();
		render(m);
	}

}
