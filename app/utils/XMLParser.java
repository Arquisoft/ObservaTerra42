package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import models.Country;
import models.Indicator;
import models.Observation;

import org.xml.sax.InputSource;

public class XMLParser {

	/**
	 * Metodo que le pasamos el nombre del fichero y los datos que queremos
	 * guardar en ese fichero
	 * 
	 * @param name
	 * @param resultado
	 */
	static void writeFile(String name, StringBuilder resultado) {
		FileWriter fichero = null;
		//PrintWriter pw = null;
		try {
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

	/**
	 * Metodo que divide elimina del string, las dos primeras etiquetas
	 * 
	 * @param resultado
	 */
	public static void XMLfromWeb(StringBuilder resultado) {
		String code = resultado.toString();
		int length = code.length();
		String result = code.substring(length - length + 17, length - 15);
		separador(result);
	}

	/**
	 * Metodo que lee un fichero y quita los dos primeras etiquetas
	 * 
	 * @param fichero
	 * @throws IOException
	 */
	public static void lectorXML(String fichero) throws IOException {
		try {
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(fichero));
			String line = null;
			StringBuilder stringBuilder = new StringBuilder();

			while ((line = reader.readLine()) != null) {
				stringBuilder.append(line);
			}
			stringBuilder.delete(stringBuilder.length() - 17,
					stringBuilder.length());
			stringBuilder.delete(
					stringBuilder.length() - stringBuilder.length(),
					stringBuilder.length() - stringBuilder.length() + 1128);

			separador(stringBuilder.toString());
		} catch (Exception e) {
			System.out
					.println("Hay algun problema con la codificacion de caracteres (Ej. UTF-8)");
		}
	}

	/**
	 * Metodo que divide el XML por paises
	 * 
	 * @param code
	 * @return
	 */
	private static String separador(String code) {

		String str = code;
		String[] parts = str.split("</row>");
		for (int i = 0; i < parts.length; i++) {
			parts[i] += "</row>";
			analizadorPais(parts[i]);
		}
		return code;

	}

	/**
	 * Metodo que parsea el xml del pais, y consulta el nombre, abreviacion y el
	 * resto de indicadores que se proporcionen al mismo
	 * 
	 * @param code
	 */
	private static void analizadorPais(String code) {
		String xml = code;
		String[] partes = xml
				.split("[<_][0123456789][0123456789][0123456789][0123456789]_");
		String[] indicatorName = xml.split("</");

		XPathFactory xpathFactory = XPathFactory.newInstance();
		XPath xpath = xpathFactory.newXPath();

		InputSource source = new InputSource(new StringReader(xml));
		InputSource source2 = new InputSource(new StringReader(xml));
		InputSource source3 = new InputSource(new StringReader(xml));

		String name = null;
		String abreviacion = null;

		String[] indicador = null;
		try {
			int k = 5;
			name = xpath.evaluate("/row/name", source);
			abreviacion = xpath.evaluate("/row/abbreviation", source2);
			if (abreviacion.equals("")) {
				source2 = new InputSource(new StringReader(xml));
				abreviacion = xpath.evaluate("/row/country_code", source2);
			}
			for (int i = 4; i < partes.length - 1; i += 2) {
				indicador = partes[i].split("><");

				for (int x = 0; x < indicador.length; x++) {
					String fechaza = indicatorName[k++].substring(0, 6);
					String consulta = fechaza + indicador[x];
					String indicationTitle = "";
					String indicationValue = "";
					String[] aux = indicador[x].split("_");
					for (int j = 0; j < aux.length; j++) {
						indicationTitle += aux[j] + " ";
					}
					indicationValue = xpath.evaluate("/row/" + consulta,
							source3);
					addDatos(name, abreviacion, indicationTitle,
							indicationValue);
					source3 = new InputSource(new StringReader(xml));
				}
			}
		} catch (XPathExpressionException e) {
			// e.printStackTrace();
		}
	}

	/**
	 * Metodo que añade los datos al modelo
	 * 
	 * @param name
	 * @param abreviacion
	 * @param indicador
	 * @param valor
	 */
	private static void addDatos(String name, String abreviacion,
			String indicador, String valor) {

		Double valorFinal;
		try {
			valorFinal = Double.parseDouble(valor);
		} catch (NumberFormatException e) {
			return;
		}
		if (Country.findByName(name) == null) {
			Country.create(new Country(abreviacion, name));
			//TODO JDBC insert country 
		}
		if (Indicator.findByCode(indicador) == null) {
			Indicator.create(new Indicator(indicador, indicador));
			//TODO JDBC insert indicator
		};
		List<Observation> o = Observation.findByIndicatorName(indicador);
		if (o != null) {
			for (Observation ob : o) {
				if (ob.country.name.equals(name)) {
					if (ob.obsValue != valorFinal) {
						ob.obsValue = valorFinal;
						ob.save();
						//TODO JDBC update observation
						return;
					}
				}
			}
		}
		Observation.create(abreviacion, indicador, valorFinal);
		//TODO JDCB insert observation
	}
}