/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 03-31-15
 * Fecha modificacion:
 * Tiempo invertido:
 */
package parser.XML;

import org.apache.commons.io.FileUtils;
import org.eclipse.persistence.tools.file.FileUtil;

import java.io.File;
import java.net.URL;

public class GestorDescargaFichero
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
