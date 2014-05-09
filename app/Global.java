import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import models.Business;
import models.Collaborator;
import models.Country;
import models.Observation;
import models.User;

import org.joda.time.DateTime;

import persistence.impl.BusinessJdbcDao;
import persistence.impl.CollaboratorJdbcDao;
import persistence.impl.UserJdbcDao;
import play.Application;
import play.GlobalSettings;
import play.libs.Yaml;
import utils.ThreadWebReader;

import com.avaje.ebean.Ebean;

public class Global extends GlobalSettings {

	public void onStart(Application app) {
		InitialData.insert(app);
	}

	static class InitialData {
		/**
		 * Al arrancar la aplicacion leemos varias veces unas paginas por
		 * defecto, y cada 2 horas leemos de nuevo las paginas
		 * 
		 * @param app
		 */
		public static void insert(Application app) {

			UserJdbcDao userDao = new UserJdbcDao();
			BusinessJdbcDao businessDao = new BusinessJdbcDao();
			CollaboratorJdbcDao collaboratorDao = new CollaboratorJdbcDao();

			DateTime dt = new DateTime(); // current time
			int hours = dt.getHourOfDay(); // gets hour of day
			if (hours % 2 == 0 && dt.getMinuteOfHour() < 1) {
				ThreadWebReader wb = new ThreadWebReader();
				new Thread(wb).start();
				System.out.println("Escaneando porque es la hora" + dt);
			}
			if (Country.all().isEmpty()) {
				//TODO coger areas de la base de datos
				@SuppressWarnings("unchecked")
				Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml
						.load("initial-data.yml");
				Ebean.save(all.get("countries"));
				Ebean.save(all.get("indicators"));
				//TODO coger indicadores de la base de datos
				//TODO coger observaciones de la base de datos
				// Some observations
				new Observation("es", "hdi", 2.3).save();
				new Observation("fr", "hdi", 3.4).save();
				new Observation("it", "hdi", 3.0).save();

			}
			if (Collaborator.all().isEmpty()) {
				
				new Collaborator("spolan", "name", "spolan", "email","collaborator", true, "",
						"", "", "").save();
				new Collaborator("sandoval", "name", "sandoval", "email","collaborator", true,
						"", "", "", "").save();
				new Collaborator("hector", "name", "hector", "email","collaborator", true, "",
						"", "", "").save();
				List<Collaborator> collaborators = new ArrayList<Collaborator>();
				try {
					collaborators=collaboratorDao.getAllCollaborators();
				} catch (SQLException e) {
					System.err.println("Error al iniciar la aplicación al cargar datos");
				}
				
				for (Collaborator business : collaborators) {
					new Collaborator(business.id, business.name,
								business.password, business.email, business.type,
								business.active, business.phone,business.address,
								business.organization, business.specialization);
				}
			}
			if (Business.all().isEmpty()) {
				new Business("pepe", "pepe", "pepe", "email", "business", true, "pepe", "",
						"", "", "").save();
				new Business("manolo", "", "manolo", "email", "business", true, "", "", "",
						"", "").save();
				new Business("luis", "", "luis", "email", "business", true, "", "", "", "",
						"").save();
				
				List<Business> businesses = new ArrayList<Business>();
				try {
					businesses=businessDao.getAllBusinesses();
				} catch (SQLException e) {
					System.err.println("Error al iniciar la aplicación al cargar datos");
				}
				for (Business business : businesses) {
					new Business(business.id, business.name,
								business.password, business.email, business.type,
								business.active, business.nif,business.description,
								business.phone, business.address, business.webSite);
				}
			}
			if (User.all().isEmpty()) {
				List<User> users = new ArrayList<User>();
				try {
					users=userDao.getAllUsers();
				} catch (SQLException e) {
					System.err.println("Error al iniciar la aplicación al cargar datos");
				}
				for (User user : users) {
					new User(user.id, user.name, user.password, user.email,
							user.type, user.active).save();
				}
			}
		}
	}
}
