package controllers;

import java.util.List;

import models.bahan;
import play.mvc.Controller;

public class admins extends Controller {
	public static void lihatBahan(){
		List m = bahan.findAll();
		render(m);
	}
}
