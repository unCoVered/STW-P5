/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 03-31-15
 * Fecha modificacion: 04-05-15
 * Tiempo invertido: 45min
 */
package main;

import generator.JSON.CrearFicheroJSON;
import generator.JSON.EncapsulaDias;
import generator.JSON.GestorExtraccionDatos;
import generator.JSON.GestorSubidaFichero;
import parser.datos.Dia;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Main de la segunda parte de la practica:
 * - Accede a BD a extraer informacion
 * - Genera fichero JSON
 * - Sube fichero JSON a Tomcat
 */
public class GenerarJSON
{
	private static final String RUTA_RESOURCES = "C:\\Users\\alex1_000\\GitRepositories\\stw\\Practica5\\src\\main\\resources\\";
	//	private static final String URL_SERVIDOR = "C:\\xampp\\tomcat\\webapps\\docs\\";

	public static void main(String[] args)
	{
		//Nombre del fichero y del directorio del servidor
		String nombreFichero = args[0];
		String rutaServer = args[1];
		String fichero = RUTA_RESOURCES + nombreFichero;
		File ficheroJSON = null;

		//Lista de dias extraidos de la BD
		List<Dia> listaDias = null;

		try
		{
			ficheroJSON = new File(fichero);
			listaDias = new ArrayList<Dia>();

			//Extraemos datos de la BD
			GestorExtraccionDatos gestorExtraccionDatos = new GestorExtraccionDatos();
			gestorExtraccionDatos.generarListaDias(listaDias);

			System.out.println("Informacion obtenida desde la base de datos MySQL local");

			//Encapsulamos la lista de dias
			EncapsulaDias listaDiasEncapsulada = new EncapsulaDias(listaDias);

			//Generamos fichero JSON
			CrearFicheroJSON.leeDias(ficheroJSON, listaDiasEncapsulada);

			System.out.println("Fichero JSON generado");

			//Subimos fichero JSON al servidor
			GestorSubidaFichero.subirFichero(ficheroJSON, rutaServer);

			System.out.println("Fichero JSON subido al servidor");
		} catch (Exception ex)
		{
			System.out.println("Error en GenerarJSON");
			ex.printStackTrace();
		}
	}
}
