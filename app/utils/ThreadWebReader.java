package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ThreadWebReader implements Runnable{

	public static final String URL1 = "https://data.undp.org/api/views/iv8b-7gbj/rows.xml?accessType=DOWNLOAD";
	public static final String URL2 = "https://data.undp.org/api/views/n9mf-gwye/rows.xml?accessType=DOWNLOAD";
	public static final String URL3 = "https://data.undp.org/api/views/ki8j-r4i6/rows.xml?accessType=DOWNLOAD";

	public ThreadWebReader(){
		
	}
	
	public void run() {
		analizeURL(URL1, 1);
		analizeURL(URL2, 2);
		analizeURL(URL3, 3);
	}

	/**
	 * Metodo que analiza una URL, mira si existe un fichero con ese nombre,
	 * si existe lo mira y comprueba si hay cambios, si no hay termina, si los hay
	 * añade los nuevos
	 * @param URL
	 * @param pos
	 */
	private void analizeURL(String URL, int pos) {
		URL oracle;
		try {
			oracle = new URL(URL);
			URLConnection yc = oracle.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	                                    yc.getInputStream()));
	        String inputLine;
	        StringBuilder resultado = new StringBuilder();
	        while ((inputLine = in.readLine()) != null){
	            resultado.append(inputLine);
	        }
        	String fichero = "app/utils/xml/" + pos +".xml";
	        writeFile(fichero, resultado);
	        ;/*
	        try{
	        	String resultadoOld = lectorFichero(pos + ".xml");
	        	if(resultadoOld.equals(resultado))
	        		return;
	        	//AHORA HABRIA QUE COMPARARLOS y AÑADIR LO NUEVO
	        }catch(IOException e){
	        	String fichero = "app/utils/xml/" + pos +".xml";
	        	writeFile(fichero, datos);
	        	XMLParser.lectorXML(fichero);
	        }
	        in.close();*/
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que lee un fichero, y devuelve el String con los datos del mismo
	 * @param file
	 * @return
	 * @throws IOException
	 */
	private static String lectorFichero(String file) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(
				"app/utils/xml/" + file));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();

		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
		}
		return stringBuilder.toString();
	}
	
	/**
	 * Metodo que le pasamos el nombre del fichero y los datos
	 * que queremos guardar en ese fichero
	 * @param name
	 * @param resultado
	 */
	private static void writeFile(String name, StringBuilder resultado){
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(name);
            fichero.write(resultado.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
	}

}
