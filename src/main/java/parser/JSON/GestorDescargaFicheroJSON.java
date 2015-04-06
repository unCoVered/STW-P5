package parser.JSON;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

public class GestorDescargaFicheroJSON
{
	/**
	 * Dada una ruta, genera un objeto file con el fichero descargado
	 *
	 * @param rutaFichero
	 * @return
	 */
	public static File descargarFichero(String rutaFichero, String urlFichero)
	{
		File fichero = null;
		URL ruta = null;

		try
		{
			fichero = new File(rutaFichero);
			ruta = new URL(urlFichero);

			FileUtils.copyURLToFile(ruta, fichero);


			return fichero;
		} catch (Exception e)
		{
			System.out.println("Error descargarFichero");
			e.printStackTrace();
			return fichero;
		}
	}
}
