package controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.io.Files;

import models.Country;
import models.Data;
import models.Indicator;
import models.Observation;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import utils.ThreadWebReader;
import utils.URLReader;
import views.html.*;
import play.*;

public class Application extends Controller {

	public static Result index() {
//		String tipoUser = session("type");
			return ok(index.render(Observation.all(), Country.all(),
					Indicator.all()));
		
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
	
	public static Result about() {
		return ok(about.render());
	}
	
	public static Result mostrarArchivos() {
		verTodosArchivos();
        return ok(mostrar.render(verTodosArchivos()));
    }
	
    public static Result subirArchivo() {
        return ok(upload.render());
    }
    
    public static Result guardarArchivo() {
        MultipartFormData body = request().body().asMultipartFormData();
        FilePart fichero = body.getFile("archivo");
        
        String nombreFichero = fichero.getFilename();
        int indice = nombreFichero.lastIndexOf(".");
        String type = nombreFichero.substring(indice + 1, nombreFichero.length());
        
        if(indice != -1)
            nombreFichero = nombreFichero.substring(0,indice);
        
        String tipo = fichero.getContentType();

        File file = fichero.getFile();
        File newFile = new File("public/data/" + nombreFichero + "."+ type);
        try {
            Files.copy(file, newFile);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        return index();
    }

    public static Result docs(String dir) {
    	  return ok(new java.io.File(dir));
    	}
    
    public static List<Data> verTodosArchivos(){
        final String dir = System.getProperty("user.dir");
        String archivos = dir + "/public/data/";

        File newF = new File(archivos);
        String[] listaDirectorio = newF.list();
        List<Data> resultado = new ArrayList<Data>();
        if (listaDirectorio == null)
              System.out.println("No hay ficheros en el directorio especificado");
            else {
              for (int x=0;x<listaDirectorio.length;x++){
            	  Data data = new Data();
            	  data.nombre = listaDirectorio[x];
            	  data.direccion = archivos + data.nombre;
            	  resultado.add(data);
              }
            }
        return resultado;
        
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