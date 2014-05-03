package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLReader {	

	/**
	 * Metodo que lee un String que se le pasa por parametro, este String es la URL
	 * de la pagina que queremos analizar. Lee toda la pagina y la almacena
	 * @param URL
	 * @throws MalformedURLException
	 * @throws IOException
	 */
	public static void readerFromWeb(String URL) throws MalformedURLException,
			IOException {
		URL oracle = new URL(URL);
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                                    yc.getInputStream()));
        String inputLine;
        StringBuilder resultado = new StringBuilder();
        System.out.println();
        while ((inputLine = in.readLine()) != null){
            System.out.println(inputLine);
            resultado.append(inputLine);
        }
        in.close();
        if(URL.contains("xml")){
        	XMLParser.XMLfromWeb(resultado);
        }
	}
	
}
