package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@SuppressWarnings("serial")
@Entity
public class User extends Model {
    
  @Id
  public String login;
  public String name;
  public String surname;
  public String password;
  
  public User(String name, String surname, String login, String password) {
	  this.name = name;
	  this.surname = surname;
	  this.login = login;
	  this.password = password;
  }
   
  public static Finder<String,User> find = new Finder<String, User>(String.class, User.class);
  
  public static List<User> all() {
    return find.all();
  }
}

