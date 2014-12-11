package controllers;


import models.user;
import controllers.Secure.Security;

public class Hansip extends Security{
	static boolean authenticate(String username, String password){
		boolean boleh=false;
		user x=user.find("user=? and pass=?", username, password).first();
		if(x!=null){
			boleh=true;
		}
		return boleh;
	}
	static boolean check(String profile){
		String username= Security.connected();
		user z=user.find("user=?", username).first();
		if(z.level.Nama_Akses.equals(profile)){
			return true;
		}
		else {
			return false;
		}
		
	}
	public static void akses(){
		String username= Security.connected();
		user akses=user.find("user=?", username).first();
		if(akses.level.Nama_Akses.equals("admin")){
			redirect("admins.awal");
		}
		else if(akses.level.Nama_Akses.equals("kasir")){
			redirect("kasir.index");
		}
		else if(akses.level.Nama_Akses.equals("pelayan")){
			redirect("pelayan.index");
		}
		else {
			redirect("Secure.Logout");
		}
	}
}
