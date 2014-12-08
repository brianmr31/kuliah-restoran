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
}
