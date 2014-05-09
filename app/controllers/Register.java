package controllers;

import models.Business;
import models.Collaborator;
import models.User;
import persistence.impl.BusinessJdbcDao;
import persistence.impl.CollaboratorJdbcDao;
import persistence.impl.UserJdbcDao;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.register;

public class Register extends Controller {

	static Form<UserRegister> regUser = Form.form(UserRegister.class);
	static Form<TypeRegister> regType = Form.form(TypeRegister.class);
	static Form<BusinessRegister> regBus = Form.form(BusinessRegister.class);
	static Form<CollaboratorRegister> regCol = Form
			.form(CollaboratorRegister.class);

	public String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static Result isValid() {

		regBus = Form.form(BusinessRegister.class).bindFromRequest();
		regCol = Form.form(CollaboratorRegister.class).bindFromRequest();
		regUser = Form.form(UserRegister.class).bindFromRequest();
		regType = Form.form(TypeRegister.class).bindFromRequest();
		if (regType.hasErrors()) {
			return badRequest(register.render(regType, regBus, regCol,regUser));
		} else {
			return badRequest(register.render(regType, regBus, regCol,regUser));
		}

	}

	public static Result userRegister() {
		regUser = Form.form(UserRegister.class).bindFromRequest();
		regType = Form.form(TypeRegister.class).bindFromRequest();
		regBus = Form.form(BusinessRegister.class).bindFromRequest();
		regCol = Form.form(CollaboratorRegister.class).bindFromRequest();
		if (regUser.hasErrors()) {
			return badRequest(register.render(regType, regBus, regCol,regUser));
		} else {

			return badRequest(register.render(regType, regBus, regCol,regUser));
		}

	}

	public static Result collaboratorRegister() {
		regCol = Form.form(CollaboratorRegister.class).bindFromRequest();
		regType = Form.form(TypeRegister.class).bindFromRequest();
		regUser = Form.form(UserRegister.class).bindFromRequest();
		regBus = Form.form(BusinessRegister.class).bindFromRequest();
		if (regCol.hasErrors()) {
			return badRequest(register.render(regType, regBus, regCol,regUser));
		} else {

			return badRequest(register.render(regType, regBus, regCol,regUser));
		}

	}

	public static Result businessRegister() {
		regBus = Form.form(BusinessRegister.class).bindFromRequest();
		regType = Form.form(TypeRegister.class).bindFromRequest();
		regCol = Form.form(CollaboratorRegister.class).bindFromRequest();
		regUser = Form.form(UserRegister.class).bindFromRequest();

		if (regBus.hasErrors()) {
			return badRequest(register.render(regType, regBus, regCol,regUser));
		} else {

			return badRequest(register.render(regType, regBus, regCol,regUser));
		}

	}

	public static Result register() {
		regUser = Form.form(UserRegister.class).bindFromRequest();
		regType = Form.form(TypeRegister.class).bindFromRequest();
		regBus = Form.form(BusinessRegister.class).bindFromRequest();
		regCol = Form.form(CollaboratorRegister.class).bindFromRequest();

		return ok(register.render(regType, regBus, regCol,regUser));
	}

	public static class CollaboratorRegister {
		public String username;
		public String password;
		public String name;
		public String email;
		public String phone;
		public String address;
		public String organization;
		public String specialization;

		CollaboratorJdbcDao collaboratorDao = new CollaboratorJdbcDao();

		public String validate() {
			String validado = "";

			if (username != null && password != null && name != null
					&& email != null && phone != null && address != null
					&& organization != null && specialization != null) {
				if (username.compareTo("") == 0 || password.compareTo("") == 0
						|| name.compareTo("") == 0 || email.compareTo("") == 0
						|| phone.compareTo("") == 0
						|| address.compareTo("") == 0
						|| organization.compareTo("") == 0
						|| specialization.compareTo("") == 0)
					return "Error, todos los campos son obligatorios";
				// Aqui se añade el usuario
				Collaborator collaborator = new Collaborator(username, name,
						password, email, "collaborator", true, phone, address,
						organization, specialization);
//				try {
//					collaboratorDao.insertCollaborator(collaborator);
//				} catch (SQLException e) {
//					return "Error interno de persistencia";
//				}
				collaborator.save();
			}
			return validado;

		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getOrganization() {
			return organization;
		}

		public void setOrganization(String organization) {
			this.organization = organization;
		}

		public String getSpecialization() {
			return specialization;
		}

		public void setSpecialization(String specialization) {
			this.specialization = specialization;
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

	public static class UserRegister {
		public String username;
		public String password;
		public String name;
		public String email;

		UserJdbcDao userDao = new UserJdbcDao();

		public String validate() {
			String validado = "";

			if (username != null && password != null && name != null
					&& email != null) {
				validado = "Usuario Registrado";
				if (username.compareTo("") == 0 || password.compareTo("") == 0
						|| name.compareTo("") == 0 || email.compareTo("") == 0)
					return "Error, todos los campos son obligatorios";
				User user = new User(username, name, password, email, "user",
						true);
//				try {
//					userDao.insertUser(user);
//				} catch (SQLException e) {
//					return "Error interno de persistencia";
//				}
				user.save();
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

	public static class BusinessRegister {
		public String username;
		public String password;
		public String name;
		public String email;
		public String nif;
		public String description;
		public String phone;
		public String address;
		public String webSite;

		BusinessJdbcDao businessDao = new BusinessJdbcDao();

		public String validate() {
			String validado = "";

			if (username != null && password != null && name != null
					&& email != null && nif != null && description != null
					&& phone != null && address != null && webSite != null) {
				validado = "Organización Registrado";
				if (username.compareTo("") == 0 || password.compareTo("") == 0
						|| name.compareTo("") == 0 || email.compareTo("") == 0
						|| description.compareTo("") == 0
						|| phone.compareTo("") == 0
						|| address.compareTo("") == 0
						|| webSite.compareTo("") == 0)
					return "Error, todos los campos son obligatorios";
				Business business = new Business(username, name, password,
						email, "business", true, nif, description, phone,
						address, webSite);

//				try {
//					businessDao.insertBusiness(business);
//				} catch (SQLException e) {
//					return "Error interno de persistencia";
//				}
				business.save();
			}
			return validado;

		}

		public String getNif() {
			return nif;
		}

		public void setNif(String nif) {
			this.nif = nif;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getWebSite() {
			return webSite;
		}

		public void setWebSite(String webSite) {
			this.webSite = webSite;
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
