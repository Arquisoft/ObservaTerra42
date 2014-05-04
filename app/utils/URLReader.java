package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class URLReader {	

	/**
	 * Metodo que lee un String que se le pasa por parametro, este String es la URL
	 * de la pagina que queremos analizar. Lee toda la pagina y la almacena
	 * @param URL
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static void readerFromWeb(String URL) {
		URL oracle;
		try {
			oracle = new URL(URL);
			URLConnection yc = oracle.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	                                    yc.getInputStream()));
	        String inputLine;
	        StringBuilder resultado = new StringBuilder();
	        System.out.println();
	        while ((inputLine = in.readLine()) != null){
	            resultado.append(inputLine);
	        }
	        in.close();
	        if(URL.contains("xml")){
	        	String fichero = "app/utils/xml/prueba.xml";
	    		Path target = Paths.get(fichero);
	    		Files.deleteIfExists(target);
	        	XMLParser.writeFile(fichero, resultado);
	        	XMLParser.lectorXML(fichero);
	        }
		} catch (MalformedURLException e) {
			System.out.println("La URL introducida no es correcta");
		} catch (IOException e) {
			e.printStackTrace();
		}
        
	}
	
}
