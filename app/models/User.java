package models;

import java.util.List;

import javax.persistence.Entity;

import play.libs.Json;

import com.fasterxml.jackson.databind.JsonNode;

@SuppressWarnings("serial")
@Entity
public abstract class User extends Users {

	

	public User(String id, String name, String password, String email,
			String type, boolean active) {
		super(id, name, password, email, type, active);
	}

	public static Finder<String, User> find = new Finder<String, User>(
			String.class, User.class);

	public static List<User> all() {
		return find.all();
	}

	public static void create(User u) {
		if (User.findByLogin(u.id) == null) {
			u.save();
		}
	}

	public static User findByLogin(String login) {
		return find.where().eq("login", login).findUnique();
	}

	public static void remove(String login) {
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