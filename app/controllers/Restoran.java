package controllers;

import java.util.List;

import models.user;
import controllers.Secure.Security;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Restoran extends Controller {

		public static void index(){
			Hansip baru= new Hansip();
			baru.akses();
		}
}
