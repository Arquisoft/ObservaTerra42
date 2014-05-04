package controllers;

import models.Country;
import models.Indicator;
import models.Observation;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import utils.ThreadWebReader;
import utils.URLReader;
import views.html.*;
import views.html.country;
import views.html.index;
import views.html.indicator;
import views.html.observation;

public class Application extends Controller {

	public static Result index() {
		String tipoUser = session("type");
		if (tipoUser == null)
			return ok(index.render(Observation.all(), Country.all(),
					Indicator.all()));
		if (!tipoUser.equals("admin"))
			return ok(index.render(Observation.all(), Country.all(),
					Indicator.all()));
		else
			return ok(country.render(Country.all(), countryForm));
	}
	
	public static Result register() {
		String tipoUser = session("typeRegister");
		if (tipoUser == null)
			return ok(index.render(Observation.all(), Country.all(),
					Indicator.all()));
		if (!tipoUser.equals("admin"))
			return ok(index.render(Observation.all(), Country.all(),
					Indicator.all()));
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
	
	

	public static Result actualizarPaginas(){
		for (int i = 0; i < 3; i++) {
			ThreadWebReader wb = new ThreadWebReader();
			new Thread(wb).start();
		}
		return index();	
		}

	/**/
	public static Result url() {
		return ok(url.render(Form.form(URLform.class)));
	}
	
	public static Result analizeURL() {
		Form<URLform> r = Form.form(URLform.class).bindFromRequest();
		if (r.hasErrors()) {
			return badRequest(url.render(r));
		} else {
			String url = r.get().urlF;
			URLReader.readerFromWeb(url);
			//URLReader.google();
			return redirect(routes.Application.index());
		}

	}

	public static class URLform {
		public String urlF;
	}


	static Form<Country> countryForm = Form.form(Country.class);
	static Form<Indicator> indicatorForm = Form.form(Indicator.class);
	static Form<Observation> observationForm = Form.form(Observation.class);

}