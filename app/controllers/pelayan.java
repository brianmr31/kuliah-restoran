package controllers;

import java.util.List;

import models.menu;
import models.pesanan;

import play.mvc.Controller;

public class pelayan extends Controller {
	public static void index(){
		List m = pesanan.findAll();
		render(m);
	}
}
