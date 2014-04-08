package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import models.*;
import play.Logger;
import play.data.*;
import play.i18n.Messages;
import play.mvc.*;
import play.libs.Json;
import play.mvc.Http.*;
import play.mvc.Http.MultipartFormData.FilePart;
import utils.*;

import com.fasterxml.jackson.databind.node.*;

public class API extends Controller {

    public static Result countries() {
    	return ok(Json.toJson(Country.all()));
    }
    
    public static Result country(String code) {
    	return ok(Json.toJson(Country.findByCode(code)));
    }

    public static Result updateCountry(String code) {
    	Country previous 	= Country.findByCode(code);
    	Country newCountry 	= countryForm.bindFromRequest().get();
    	previous.name = newCountry.name;
    	previous.save();
    	return redirect(routes.API.countries());
    }

    public static Result addCountry() {
    	Country country = countryForm.bindFromRequest().get();
    	country.save();
    	return redirect(routes.API.countries());
    }

    public static Result delCountry(String code) {
    	Country.remove(code);
    	return redirect(routes.API.countries());
    }

    public static Result observations() {
    	List<Observation> obsList = Observation.all();
    	return ok(Json.toJson(obsList));
    }

    public static Result observationsByIndicator(String indicator) {
    	List<Observation> obsList = Observation.findByIndicatorName(indicator);
    	return ok(Json.toJson(obsList));
    }

    public static Result observationsByCountry(String country) {
    	List<Observation> obsList = Observation.findByCountryCode(country);
    	return ok(Json.toJson(obsList));
    }

    public static Result addObservation(String country, String indicator) {
    	return TODO;
    }
    
    public static Result delObservation(String country, String indicator) {
    	return TODO;
    }
    
    public static Result uploadExcel() {
    try {
      MultipartFormData body = request().body().asMultipartFormData();
      FilePart excel = body.getFile("excel");
      if (excel != null) {
    	    File file = excel.getFile();
    	    ExcelReader reader = new ExcelReader();
    	    List<Observation> obsList = reader.read(new FileInputStream(file));
    	    for (Observation obs: obsList) {
    	    	obs.save();
    	    }
    	    Logger.info("Excel file uploaded with " + obsList.size() + " observations");
    	    return redirect(routes.Application.index());
       } else {
   	    	Logger.error("Missing file to upload ");
    	    return redirect(routes.Application.index());    
       } 
      }
    catch (IOException e) {
      return(badRequest(Messages.get("read.excel.error") + "." + e.getLocalizedMessage()));	
    }
    }

    static Form<Country>  	  countryForm     = Form.form(Country.class);
    static Form<Indicator>    indicatorForm   = Form.form(Indicator.class);
    static Form<Observation>  observationForm = Form.form(Observation.class);

}
