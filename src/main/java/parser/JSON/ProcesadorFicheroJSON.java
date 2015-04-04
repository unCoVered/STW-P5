/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 03-31-15
 * Fecha modificacion:
 * Tiempo invertido:
 */
package parser.JSON;

import com.google.gson.Gson;

public class ProcesadorFicheroJSON
{
	//Example
	public static void main(String[] args)
	{
		Gson gson = new Gson();
		gson.toJson(1);
		gson.toJson("abcd");
		gson.toJson(new Long(10));
	}
}
