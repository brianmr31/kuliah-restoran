package controllers;

import java.util.List;

import models.user;
import controllers.Secure.Security;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class Restoran extends Controller {

		public static void index(){
			String username= Security.connected();
			user akses=user.find("user=?", username).first();
			render(akses);
		}
		@Check("admins")
		public static void admin(){
			render();
		}
		@Check("pelayan1")
		public static void pelayan(){
			render();
		}
		@Check("kasir1")
		public static void kasir(){
			render();
		}
}
