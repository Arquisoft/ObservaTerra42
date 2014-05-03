package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class URLReader {	

	public static void readerFromWeb(String URL) throws MalformedURLException,
			IOException {
		URL oracle = new URL(URL);
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
        in.close();
        if(URL.contains("xml")){
        	XMLParser.XMLfromWeb(resultado);
        }
	}
	
}
