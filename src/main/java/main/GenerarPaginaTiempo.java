/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 03-31-15
 * Fecha modificacion: 04-06-15
 * Tiempo invertido: 30min
 */
package main;

import generator.HTML.CrearPaginaHTML;
import generator.HTML.GestorSubidaFicheroHTML;
import generator.JSON.EncapsulaDias;
import parser.JSON.GestorDescargaFicheroJSON;
import parser.JSON.ProcesadorFicheroJSON;
import parser.datos.Dia;

import java.io.File;
import java.util.ArrayList;

/**
 * Main de la tercera parte de la practica
 * - Descarga fichero JSON
 * - Parsea fichero JSON
 * - Crea fichero HTML
 * - Sube fichero HTML a un servidor web
 */
public class GenerarPaginaTiempo
{
	private static final String RUTA_RESOURCES = "C:\\Users\\alex1_000\\GitRepositories\\stw\\Practica5\\src\\main\\resources\\";

	public static void main(String[] args)
	{
		String urlFichero = args[0];
		String rutaServer = args[1];
		String rutaFicheroJSON = RUTA_RESOURCES + "prediccion.json";
		String nombreFicheroHTML = RUTA_RESOURCES + "tiempo-zaragoza.html";
		EncapsulaDias diasPrediccion = new EncapsulaDias(new ArrayList<Dia>());

		try
		{
			//Creamos pagina HTML
			File ficheroHTML = new File(nombreFicheroHTML);
			ficheroHTML.createNewFile();

			//Descargamos fichero
			File fichero = GestorDescargaFicheroJSON.descargarFichero(rutaFicheroJSON, urlFichero);

			System.out.println("Fichero de prediccion metereologica descargado con exito");

			//Procesamos con GSON el fichero

			diasPrediccion = ProcesadorFicheroJSON.leeDatos(fichero);

			System.out.println("Informacion procesada con GSON");

			// Escribimos los datos en pagina HTML
			CrearPaginaHTML.escribeDatosDia(ficheroHTML, diasPrediccion);

			System.out.println("Pagina HTML creada");

			// Subimos al servidor la pagina HTML
			GestorSubidaFicheroHTML.subirFichero(ficheroHTML, rutaServer);

			System.out.println("Fichero HTML subido al servidor");

		} catch (Exception ex)
		{
			System.out.println("Excepcion en GenerarPaginaTiempo");
			ex.printStackTrace();
		}

	}
}
