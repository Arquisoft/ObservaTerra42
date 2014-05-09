package controllers;

import java.io.File;
import java.util.List;

import models.*;
import play.data.*;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.country;
import views.html.admin;

public class Admin extends Controller {

	public static Result newCountry() {
		Form<Country> form = countryForm.bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(views.html.country.render(Country.all(),
					countryForm));
		} else {
			Country countryToAdd = form.get();
			Country.create(countryToAdd);
			return redirect(routes.Application.showCountries());
		}
	}

	public static Result deleteCountry(String code) {
		Country.remove(code);
		return redirect(routes.Application.showCountries());
	}
	
	public static Result deleteData(String direccion) {
		File file = new File(direccion);
		file.delete();
		return redirect(routes.Application.index());
	}


	public static Result newIndicator() {
		Form<Indicator> form = indicatorForm.bindFromRequest();
		if (form.hasErrors()) {
			return badRequest(views.html.indicator.render(Indicator.all(),
					indicatorForm));
		} else {
			Indicator ind = form.get();
			Indicator.create(ind);
			return redirect(routes.Application.showIndicators());
		}
	}

	public static Result deleteIndicator(String code) {
		Indicator.remove(code);
		return redirect(routes.Application.showIndicators());
	}

	public static Result newObservation() {
		DynamicForm requestData = Form.form().bindFromRequest();
		String countryId = requestData.get("countryId");
		String indicatorId = requestData.get("indicatorId");
		Double value = Double.parseDouble(requestData.get("value"));
		List<Observation> lista = Observation.findByIndicatorName(indicatorId);
		boolean nuevo = true;

		for (Observation o : lista) {
			if (o.country.code.equals(countryId)) {
				o.obsValue = (o.obsValue + value) / 2;
				o.save();
				nuevo = false;
			}
		}

		if (nuevo) {
			Observation obs = new Observation(countryId, indicatorId, value);
			obs.save();
		}
		return redirect(routes.Application.showObservations());
	}

	public static Result deleteObservation(Long id) {
		Observation.delete(id);
		return redirect(routes.Application.showObservations());
	}

	public static Result showUsers() {
		return ok(admin.render(User.all()));
	}

	static Form<User> usersForm = Form.form(User.class);
	static Form<Country> countryForm = Form.form(Country.class);
	static Form<Indicator> indicatorForm = Form.form(Indicator.class);
	static Form<Observation> observationForm = Form.form(Observation.class);

}
