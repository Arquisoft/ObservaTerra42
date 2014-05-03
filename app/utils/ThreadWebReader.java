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


public class ThreadWebReader implements Runnable{

	public static final String URL1 = "https://data.undp.org/api/views/iv8b-7gbj/rows.xml?accessType=DOWNLOAD";
	public static final String URL2 = "https://data.undp.org/api/views/n9mf-gwye/rows.xml?accessType=DOWNLOAD";
	public static final String URL3 = "https://data.undp.org/api/views/ki8j-r4i6/rows.xml?accessType=DOWNLOAD";

	public ThreadWebReader(){
		
	}
	
	public void run() {
		saveFile(URL1, 1);
//		saveFile(URL2, 2);
//		saveFile(URL3, 3);

	}

	private void saveFile(String URL, int pos) {
		URL oracle;
		try {
			oracle = new URL(URL);
			URLConnection yc = oracle.openConnection();
	        BufferedReader in = new BufferedReader(new InputStreamReader(
	                                    yc.getInputStream()));
	        String inputLine;
	        String resultado = "";
	        System.out.println();
	        while ((inputLine = in.readLine()) != null){
	            System.out.println(inputLine);
	            resultado += inputLine;
	        }
	        try{
	        	String resultadoOld = lectorFichero(pos + ".xml");
	        	if(resultadoOld.equals(resultado))
	        		return;
	        	//AHORA HABRIA QUE COMPARARLOS y AÑADIR LO NUEVO
	        }catch(IOException e){
	        	String fichero = "app/utils/xml/" + pos +".xml";
	        	writeFile(fichero, resultado);
	        	XMLParser.lectorXML(fichero);
	        }
	        in.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
	
	private static void writeFile(String name, String value){
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(name);
            fichero.write(value);

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
