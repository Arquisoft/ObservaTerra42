package controllers;

import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.login;
import models.Business;
import models.Collaborator;
import models.User;

public class Languaje extends Controller{

	public static String language(){
		return lang().code();
	}

}
