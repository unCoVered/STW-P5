/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 03-31-15
 * Fecha modificacion: 04-05-15
 * Tiempo invertido: 25min
 */
package main;

import parser.XML.*;


import java.io.File;

/**
 * Main de la primera parte de la practica
 * - Descarga el fichero XML
 * - Parsea el fichero XML
 * - Introduce los datos en una BD MySQL
 */
public class DescargaInfoTiempo
{

	private static final String RUTA_RESOURCES = "C:\\Users\\alex1_000\\GitRepositories\\stw\\Practica5\\src\\main\\resources\\Prediccion-Zaragoza.xml";

	public static void main(String[] args)
	{
		//Ruta de fichero y lista de dias parseados
		String urlFichero = args[0];
		String rutaFichero = RUTA_RESOURCES;
		ListaDia diasPrediccion = new ListaDia();
		try
		{
			//Descargamos fichero
			File fichero = GestorDescargaFichero.descargarFichero(rutaFichero, urlFichero);

			System.out.println("Fichero de prediccion metereologica descargado con exito");

			//Procesamos con JDOM el fichero
			ProcesadorFicheroXML.leePrediccion(diasPrediccion, fichero.getCanonicalPath());

			System.out.println("Informacion procesada con JDOM");

			//Almacenamos en BD
			GestorInsercionDatos gestorInsercionDatos = new GestorInsercionDatos();
			gestorInsercionDatos.lectorListaDias(diasPrediccion);

			System.out.println("Informacion almacenada en BD MySQL local");
		} catch (Exception ex)
		{
			System.out.println("Excepcion en DescargaInfoTiempo");
			ex.getStackTrace();
		}
	}
}
