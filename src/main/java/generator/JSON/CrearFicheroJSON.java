/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 05-04-15
 * Fecha modificacion:
 * Tiempo invertido:
 */
package generator.JSON;

import com.google.gson.Gson;
import parser.datos.Dia;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

/**
 * Clase encargada de generar el fichero JSON
 */
public class CrearFicheroJSON
{
	/**
	 * Dada una lista de dias, los lee y llama a los metodos necesarios para insertar los datos
	 * leidos en el fichero ficheroJSON pasado por parametros
	 * @param ficheroJSON
	 * @param listaDias
	 */
	public static void leeDias(File ficheroJSON, List<Dia> listaDias)
	{
		try
		{
			PrintWriter out = new PrintWriter(ficheroJSON);
			String json = new String("");

			for (Dia dia : listaDias)
			{
				Gson gson = new Gson();
				json = json + gson.toJson(dia) + "\n";
				out.print(json);
			}
		}catch(Exception ex)
		{
			System.out.println("Excepcion en leeDias");
			ex.printStackTrace();
		}
	}
}
