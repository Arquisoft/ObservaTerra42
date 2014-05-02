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

	public String type;
	public String username;
	public String password;
	public String name;
	public String email;

	public String validate() {
		String validado = "Error";
		if (type != null) {
			session().put("typeRegister", type);
			System.out.println(type);
			type="";
			validado=null;
		}
		return validado;
	}

	public static Result isValid() {
		Form<Register> r = Form.form(Register.class).bindFromRequest();
		if (r.hasErrors()) {
			return badRequest(register.render(r));
		} else {
			return badRequest(register.render(r));
//			return redirect(routes.Application.index());
		}

	}
	public static Result userRegister() {
		Form<Register> r = Form.form(Register.class).bindFromRequest();
		System.out.println();
		if (r.hasErrors()) {
			return badRequest(register.render(r));
		} else {

			return badRequest(register.render(r));
//			return redirect(routes.Application.index());
		}

	}
	private void reset(){
		 type=null;
		 username=null;
		 password=null;
		 name=null;
		 email=null;
	}
	

	public static Result register() {
		return ok(register.render(Form.form(Register.class)));
	}
}
