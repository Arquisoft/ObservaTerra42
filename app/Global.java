import play.*;
import play.libs.*;
import utils.xmlParser;

import java.util.*;

import com.avaje.ebean.*;

import models.*;

public class Global extends GlobalSettings {

	public void onStart(Application app) {
		InitialData.insert(app);
	}

	static class InitialData {
		public static void insert(Application app) {
			//xmlParser.main(null);
			if (Country.all().isEmpty()) {

				@SuppressWarnings("unchecked")
				Map<String, List<Object>> all = (Map<String, List<Object>>) Yaml
						.load("initial-data.yml");
				Ebean.save(all.get("countries"));
				Ebean.save(all.get("indicators"));

				// Some observations
				new Observation("es", "hdi", 2.3).save();
				new Observation("fr", "hdi", 3.4).save();
				new Observation("it", "hdi", 3.0).save();

			}
			if (Collaborator.all().isEmpty()) {
				new Collaborator("spolan", "name", "spolan", "email",
						true, "", "", "", "").save();
				new Collaborator("sandoval", "name", "sandoval", "email"
						, true, "", "", "", "").save();
				new Collaborator("hector", "name", "hector", "email",
						true, "", "", "", "").save();
			}
			if (Business.all().isEmpty()) {
				new Business("pepe","pepe","pepe","email",true,"pepe","","","","").save();
				new Business("manolo","","manolo","email",true,"","","","","").save();
				new Business("luis","","luis","email",true,"","","","","").save();
			}
			if (User.all().isEmpty()) {
				new User("admin","admin","admin","admin@admin.com","admin",true).save();
			}
		}
	}
}
