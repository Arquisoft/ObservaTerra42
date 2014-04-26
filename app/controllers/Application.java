package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import models.*;
import views.html.*;
import play.Logger;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import utils.ExcelReader;
import play.data.*;

public class Application extends Controller {

	public static Result index() {
		String tipoUser = session("type");
		if(tipoUser == null)
			return ok(index.render(Observation.all(), Country.all(),
					Indicator.all()));
		if(!tipoUser.equals("admin"))
		return ok(index.render(Observation.all(), Country.all(),
				Indicator.all()));
		else
			return ok(country.render(Country.all(), countryForm));
	}

	public static Result showCountries() {
		return ok(country.render(Country.all(), countryForm));
	}

	public static Result showIndicators() {
		return ok(indicator.render(Indicator.all(), indicatorForm));
	}

	public static Result showObservations() {
		return ok(observation.render(Observation.find.all(), Country.all(),
				Indicator.all(), observationForm));
	}

	public static Result bars(String indicator) {
		return ok(bars.render(Indicator.findByCode(indicator)));
	}

	public static Result login() {
		return ok(login.render(Form.form(Login.class)));
	}

	public static Result authenticate() {
		Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
		if (loginForm.hasErrors()) {
			return badRequest(login.render(loginForm));
		} else {
			return redirect(routes.Application.index());
		}
	}
	
	public static Result validate() {
		Form<Register> r = Form.form(Register.class).bindFromRequest();
		if (r.hasErrors()) {
			return badRequest(register.render(r));
		} else {
			if(r.get().type.equals("admin")){
				User.create(new User(r.get().username,r.get().name, r.get().password,
						r.get().email, "admin",false));
			}else if(r.get().type.equals("business")){
				Business.create(new Business(r.get().username,r.get().name, r.get().password,
						r.get().email,false,"","","","",""));
			}else if(r.get().type.equals("collaborator")){
				Collaborator.create(new Collaborator(r.get().username,r.get().name, r.get().password,
						r.get().email,false,"","","",""));
			}
			return redirect(routes.Application.index());
		}
		
	}
	
	public static Result register() {
		return ok(register.render(Form.form(Register.class)));
	}

	static Form<Country> countryForm = Form.form(Country.class);
	static Form<Indicator> indicatorForm = Form.form(Indicator.class);
	static Form<Observation> observationForm = Form.form(Observation.class);

	public static class Register{
		public String username;
		public String name;
		public String password;
		public String email;
		public String type;
		
	}
	public static class Login {

		public String username;
		public String password;

		public String validate() {
			String a = validarColaborador();
			if(a != null){
				a = validarBusiness();
				if(a != null){
					a = validarUser();
				}
			}
			return a;
		}

		private String validarUser() {
			User u = (User) User.authenticate(username,
					password);
			if (u == null) {
				return "Invalid user or password";
			} else {
				session().clear();
				session("username",u.id);
				session("password",u.password);
				session("type",u.type);
				return null;
			}
		}

		private String validarColaborador() {
			Collaborator c = (Collaborator) Collaborator.authenticate(username,
					password);
			if (c == null) {
				return "Invalid user or password";
			} else {
				session().clear();
				session("username",c.id);
				session("password",c.password);
				session("type",c.type);
				return null;
			}
		}
		
		private String validarBusiness() {
			Business b = (Business) Business.authenticate(username,
					password);
			if (b == null) {
				return "Invalid user or password";
			} else {
				session().clear();
				session("username",b.id);
				session("password",b.password);
				session("type",b.type);
				return null;
			}
		}
	}
}