package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import models.*;
import views.html.*;
import play.Logger;
import play.i18n.Messages;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Http.Request;
import play.mvc.Result;
import utils.ExcelReader;
import play.data.*;

public class Application extends Controller {

	public static Result index() {
		String tipoUser = session("type");
<<<<<<< HEAD
		if(tipoUser == null)
			return ok(index.render(Observation.all(), Country.all(),
					Indicator.all()));
		if(!tipoUser.equals("admin"))
		return ok(index.render(Observation.all(), Country.all(),
				Indicator.all()));
=======
		if (tipoUser == null)
			return ok(index.render(Observation.all(), Country.all(),
					Indicator.all()));
		if (!tipoUser.equals("admin"))
			return ok(index.render(Observation.all(), Country.all(),
					Indicator.all()));
>>>>>>> model_equipo-A
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

	/**/
	public static Result login() {
		return ok(login.render(Form.form(Login.class)));
	}

	/**/
	public static Result authenticate() {
		Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
		if (loginForm.hasErrors()) {
			return badRequest(login.render(loginForm));
		} else {
			return redirect(routes.Application.index());
		}
	}
<<<<<<< HEAD
	
=======

>>>>>>> model_equipo-A
	public static Result validate() {
		Form<Register> r = Form.form(Register.class).bindFromRequest();
		if (r.hasErrors()) {
			return badRequest(register.render(r));
		} else {
<<<<<<< HEAD
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
	
=======
			if (r.get().type.equals("admin")) {
				User.create(new User(r.get().username, r.get().name,
						r.get().password, r.get().email, "admin", false));
			} else if (r.get().type.equals("business")) {
				Business.create(new Business(r.get().username, r.get().name, r
						.get().password, r.get().email, false, "", "", "", "",
						""));
			} else if (r.get().type.equals("collaborator")) {
				Collaborator.create(new Collaborator(r.get().username,
						r.get().name, r.get().password, r.get().email, false,
						"", "", "", ""));
			}
			return redirect(routes.Application.index());
		}

	}

>>>>>>> model_equipo-A
	public static Result register() {
		return ok(register.render(Form.form(Register.class)));
	}

	static Form<Country> countryForm = Form.form(Country.class);
	static Form<Indicator> indicatorForm = Form.form(Indicator.class);
	static Form<Observation> observationForm = Form.form(Observation.class);

<<<<<<< HEAD
	public static class Register{
		public String username;
		public String name;
		public String password;
		public String email;
		public String type;
		
	}
	public static class Login {

=======
	public static class Register {
>>>>>>> model_equipo-A
		public String username;
		public String name;
		public String password;
		public String email;
		public String type;

	}

	public static class Login {

		public String username ;
		public String password ;

		/*
		 * Limpia la sesión y mete en ésta los datos del usuario si existe
		 */
		public String validate() {
<<<<<<< HEAD
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
=======
			String validado = "Error";
			if (validarUser())
				validado = null;
			else if (validarColaborador())
				validado = null;
			else if (validarBusiness())
				validado = null;
			return validado;
		}
		
		public static void logout(){
			session().clear();
			System.out.println(session().get("id"));
		}

		/*
		 * Comprueba si el el usuario que intenta loguearse existe, coincide su 
		 * password y si está activo
		 */
		private boolean validarUser() {
//			session().isDirty = true;
			User user = User.findByLogin(username);
			if (user != null && user.active == true
					&& user.password.compareTo(password) == 0) {
				session().put("id", user.id);
				session().put("name", user.name);
				session().put("password", user.password);
				session().put("email", user.email);
				session().put("type", user.id);
				return true;
			}
			return false;
>>>>>>> model_equipo-A
		}

		/*
		 * Comprueba si el el usuario que intenta loguearse existe, coincide su 
		 * password y si está activo
		 */
		private boolean validarColaborador() {
//			session().isDirty = true;
			Collaborator colaborador = Collaborator.findByLogin(username);
			if (colaborador != null && colaborador.active == true
					&& colaborador.password.compareTo(password) == 0) {
				session().put("id", colaborador.id);
				session().put("name", colaborador.name);
				session().put("password", colaborador.password);
				session().put("email", colaborador.email);
				session().put("type", colaborador.id);
				session().put("phone", colaborador.phone);
				session().put("adress", colaborador.adress);
				session().put("organization", colaborador.organization);
				session().put("specialization", colaborador.specialization);
				return true;
			}
			return false;
		}

		/*
		 * Comprueba si el el usuario que intenta loguearse existe, coincide su 
		 * password y si está activo
		 */
		private boolean validarBusiness() {
//			session().isDirty = true;
			Business business = Business.findByLogin(username);
			if (business != null && business.active == true
					&& business.password.compareTo(password) == 0) {
				session().put("id", business.id);
				session().put("name", business.name);
				session().put("password", business.password);
				session().put("email", business.email);
				session().put("type", business.id);
				session().put("nif", business.nif);
				session().put("description", business.id);
				session().put("phone", business.phone);
				session().put("address", business.address);
				session().put("webSite", business.webSite);
				return true;
			}
			return false;
		}

		public String getUsername() {
			return username;
		}

		public String getPassword() {
			return password;
		}

	}
}