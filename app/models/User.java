package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.databind.JsonNode;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;
import play.libs.Json;

@SuppressWarnings("serial")
@Entity
public class User extends Model {

	@Id
	public String login;
	public String name;
	public String surname;
	public String password;
	public String type;

	public User(String name, String surname, String login, String password,
			String type) {
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
		this.type = type;
	}

	public static Finder<String, User> find = new Finder<String, User>(
			String.class, User.class);

	public static List<User> all() {
		return find.all();
	}

	public static void create(User u) {
		if (User.findByLogin(u.login) == null) {
			u.save();
		}
	}

	public static User findByLogin(String login) {
		return find.where().eq("login", login).findUnique();
	}

	public void remove(String login) {
		find.ref(login).delete();
	}

	public static void deleteAll() {
		for (User u : all())
			u.delete();
	}
	
	public static JsonNode toJson(User u) {
		return Json.toJson(u);
	  }
}
