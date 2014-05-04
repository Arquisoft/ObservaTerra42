package controllers;

import org.omg.CORBA.Current;

import models.Business;
import models.Collaborator;
import models.User;
import views.html.register;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;

public class Register extends Controller {

	static Form<UserRegister> regUser = Form.form(UserRegister.class);
	static Form<TypeRegister> regType = Form.form(TypeRegister.class);

	public String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static Result isValid() {
		regUser = Form.form(UserRegister.class).bindFromRequest();
		regType = Form.form(TypeRegister.class).bindFromRequest();
		if (regType.hasErrors()) {
			return badRequest(register.render(regType, regUser));
		} else {
			return badRequest(register.render(regType, regUser));
		}

	}

	public static Result userRegister() {
		regUser = Form.form(UserRegister.class).bindFromRequest();
		regType = Form.form(TypeRegister.class).bindFromRequest();
		if (regUser.hasErrors()) {
			return badRequest(register.render(regType, regUser));
		} else {

			return badRequest(register.render(regType, regUser));
		}

	}

	public static Result register() {
		regUser = Form.form(UserRegister.class).bindFromRequest();
		regType = Form.form(TypeRegister.class).bindFromRequest();

		return ok(register.render(regType, regUser));
	}

	public static class UserRegister {
		public String username;
		public String password;
		public String name;
		public String email;

		public String validate() {
			String validado = "";

			if (username != null && password != null && name != null
					&& email != null) {
				validado = "Usuario Registrado";
				if (username.compareTo("") == 0 && password.compareTo("") == 0
						&& name.compareTo("") == 0 && email.compareTo("") == 0)
					return "Error, todos los campos son obligatorios";
				//Aqui se añade el usuario
			}
			return validado;

		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	}

	public static class TypeRegister {
		public String type;

		public String validate() {
			String validado = "";

			if (type == null)
				return null;

			if (session().get("typeRegister") != null
					&& session().get("typeRegister").compareTo("empty") != 0) {
				session().put("typeRegister", type);
				return null;
			}

			if (type != null && type.compareTo("empty") != 0) {
				session().put("typeRegister", type);
				return null;
			}
			if (type != null && type.compareTo("empty") == 0) {
				session().put("typeRegister", type);
				return null;
			}

			return validado;
		}
	}

}
