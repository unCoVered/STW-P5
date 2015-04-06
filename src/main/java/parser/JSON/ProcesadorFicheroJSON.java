/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 03-31-15
 * Fecha modificacion: 04-06-15
 * Tiempo invertido: 30min
 */
package parser.JSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import generator.JSON.EncapsulaDias;
import parser.datos.Dia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ProcesadorFicheroJSON
{
	/**
	 * Metodo estatico encargado de leer y parsear el contenido de un fichero
	 * JSON previamente indicado como atributo de la clase
	 */
	public static EncapsulaDias leeDatos(File ficheroLectura)
	{
		EncapsulaDias diasPrediccion = new EncapsulaDias(new ArrayList<Dia>());

		try
		{
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			BufferedReader lectorFichero = new BufferedReader(new FileReader(ficheroLectura));
			String json = "";
			String linea;
			while((linea=lectorFichero.readLine()) != null)
			{
				json += linea;
			}

			diasPrediccion = gson.fromJson(json, EncapsulaDias.class);

			lectorFichero.close();

			return diasPrediccion;

		} catch (Exception ex)
		{
			System.out.println("Excepcion en leerPrediccion de PrecesadorFicheroJSON");
			ex.printStackTrace();

			return diasPrediccion;

		}
	}
}
