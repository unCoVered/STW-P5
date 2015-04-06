/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 04-06-15
 * Fecha modificacion: 04-06-15
 * Tiempo invertido: 30min
 */
package generator.JSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.PrintWriter;

/**
 * Clase encargada de generar el fichero JSON
 */
public class CrearFicheroJSON
{
	/**
	 * Dada una lista de dias, los lee y llama a los metodos necesarios para insertar los datos
	 * leidos en el fichero ficheroJSON pasado por parametros
	 *
	 * @param ficheroJSON
	 * @param listaDias
	 */
	public static void leeDias(File ficheroJSON, EncapsulaDias listaDias)
	{
		try
		{
			PrintWriter out = new PrintWriter(ficheroJSON);
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(listaDias);
			out.print(json);

			out.flush();
			out.close();

		} catch (Exception ex)
		{
			System.out.println("Excepcion en leeDias");
			ex.printStackTrace();
		}
	}
}
