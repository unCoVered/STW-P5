/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 03-31-15
 * Fecha modificacion: 04-06-15
 * Tiempo invertido: 30min
 */
package generator.HTML;

import org.apache.commons.io.FileUtils;

import java.io.File;

public class GestorSubidaFicheroHTML
{
	/**
	 * Sube el fichero 'fichero' a la ruta indicada por parametros
	 * @param fichero
	 * @param rutaServer
	 */
	public static void subirFichero(File fichero, String rutaServer)
	{
		File rutaServerFile = null;

		try{
			rutaServerFile = new File(rutaServer+"/tiempo");

			FileUtils.copyFileToDirectory(fichero, rutaServerFile);
		}catch(Exception ex)
		{
			System.out.println("Excepcion en subirFichero");
			ex.printStackTrace();
		}
	}
}
