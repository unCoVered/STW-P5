/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 04-03-15
 * Fecha modificacion:
 * Tiempo invertido:
 */
package generator.JSON;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class GestorSubidaFichero
{
	/**
	 * Sube el fichero 'fichero' a la ruta indicada por parametros
	 *
	 * @param fichero
	 * @param rutaServer
	 */
	public static void subirFichero(File fichero, String rutaServer)
	{
		File rutaServerFile = null;

		try
		{
			rutaServerFile = new File(rutaServer);

			FileUtils.copyFileToDirectory(fichero, rutaServerFile);
		} catch (Exception ex)
		{
			System.out.println("Excepcion en subirFichero");
			ex.printStackTrace();
		}
	}
}
