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

			Collaborator.deleteAll();
			
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
				
				new Collaborator("polan", "Sergio", "polan", "polan@observaterra.es","collaborator", true, "",
						"", "", "").save();
				new Collaborator("ivan", "Ivan", "ivan", "ivan@observaterra.es","collaborator", true,
						"", "", "", "").save();
				new Collaborator("hector", "Hector", "hector", "hector@observaterra.es","collaborator", true, "",
						"", "", "").save();
				new Collaborator("diego", "Diego", "diego", "diego@observaterra.es","collaborator", true, "",
						"", "", "").save();
				new Collaborator("benito", "Jose Antonio", "beni", "beni@observaterra.es","collaborator", true,
						"", "", "", "").save();
				new Collaborator("edin", "Edinson", "edin", "edin@observaterra.es","collaborator", true, "",
						"", "", "").save();

			}
			if (Business.all().isEmpty()) {
				new Business("labra", "labra", "labra", "labra@uniovi.es", "business", true, "", "",
						"", "", "").save();
				new Business("aquilino", "aquilino", "aquilino", "aquilino@uniovi.es", "business", true, "", "", "",
						"", "").save();
				new Business("crispe", "crispe", "crispe", "crispe@uniovi.es", "business", true, "", "", "", "",
						"").save();
			}
			if(User.all().isEmpty())
			{
				new User("admin","admin","admin","admin@observaterra.es","admin",true).save();
			}
			
		}
	}
}
