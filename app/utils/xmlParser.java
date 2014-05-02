package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import models.Country;
import models.Indicator;
import models.Observation;

import org.xml.sax.InputSource;

public class xmlParser {

	public static void main(String[] args) {
		try {
			String xml = lectorXML();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// test1();
	}

	private static String lectorXML() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(
				"app/utils/health.xml"));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();

		while ((line = reader.readLine()) != null) {

			stringBuilder.append(line);
		}
		stringBuilder.delete(stringBuilder.length() - 17,
				stringBuilder.length());
		stringBuilder.delete(stringBuilder.length() - stringBuilder.length(),
				stringBuilder.length() - stringBuilder.length() + 15);

		separadaor(stringBuilder.toString());
		return stringBuilder.toString();
	}

	private static String separadaor(String code) {

		String str = code;
		String part = "";
		int index = 0;
		int end = 0;
		String[] parts = str.split("</row>");
		for(int i = 0; i<parts.length; i++)
		{
			parts[i] += "</row>";
			test1(parts[i]);
		}
		return code;

	}

	private static void test1(String code) {
		String xml = code;
		//System.out.println(xml);

		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();

		InputSource source = new InputSource(new StringReader(xml));
		InputSource source2 = new InputSource(new StringReader(xml));
		InputSource source3 = new InputSource(new StringReader(xml));
		InputSource source4 = new InputSource(new StringReader(xml));
		InputSource source5 = new InputSource(new StringReader(xml));

		String name = null;
		String abreviacion = null;
		String valorIndicacion1 = null;
		String valorIndicacion2 = null;
		String valorIndicacion3 = null;
		String i1 = "hd1 value";
		String i2 = "life expectancy at birth";
		String i3 = "mean years of schooling";
		try {
			name = xpath.evaluate("/row/name", source);
			abreviacion = xpath.evaluate("/row/abbreviation", source2);
			valorIndicacion1 = xpath.evaluate("/row/_2012_hdi_value", source3);
			valorIndicacion2 = xpath.evaluate("/row/_2012_life_expectancy_at_birth", source4);
			valorIndicacion3 = xpath.evaluate("/row/_2010_mean_years_of_schooling", source5);
		} catch (XPathExpressionException e) {
			//e.printStackTrace();
		}

		System.out.println("name = " + name + "; " + "abreviacion = " + abreviacion +
				 " hdi value = " + valorIndicacion1 + " life expectancy at birth = " + valorIndicacion2 +
				 " mean years of schooling = " + valorIndicacion3);
		
		if(Country.findByName(name) == null)
			Country.create(new Country(abreviacion, name));
		if(Indicator.findByCode(i1) == null)
			Indicator.create(new Indicator(i1, i1));
		if(Indicator.findByCode(i2) == null)
			Indicator.create(new Indicator(i2, i2));
		if(Indicator.findByCode(i3) == null)
			Indicator.create(new Indicator(i3, i3));
		Observation.create(abreviacion, i1, Double.parseDouble(valorIndicacion1));
		Observation.create(abreviacion, i2, Double.parseDouble(valorIndicacion2));
		Observation.create(abreviacion, i3, Double.parseDouble(valorIndicacion3));

	}
}
