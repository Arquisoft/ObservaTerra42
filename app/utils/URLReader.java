package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLReader {

	public static final String URL1 = "https://data.undp.org/api/views/iv8b-7gbj/rows.xml?accessType=DOWNLOAD";
	
	public static void main(String[] args) throws Exception {
        URL oracle = new URL(URL1);
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
    }
	
}
