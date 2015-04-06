/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 03-31-15
 * Fecha modificacion: 04-06-15
 * Tiempo invertido: 30min
 */
package generator.HTML;

import generator.JSON.EncapsulaDias;
import parser.datos.Dia;

import java.io.File;
import java.io.PrintWriter;

/**
 * Clase encargada de crear una pagina HTML.
 */
public class CrearPaginaHTML
{
	/**
	 * Dada una lista de Dias, inctroduce los datos en un fichero HTML
	 * de forma que se muestren los datos al ejecutar la pagina en un
	 * navegador web
	 * @param ficheroHTML
	 * @param diasPrediccion
	 */
	public static void escribeDatosDia(File ficheroHTML, EncapsulaDias diasPrediccion)
	{
		try
		{
			//Definimos printer
			PrintWriter out = new PrintWriter(ficheroHTML);

			//Cabecera
			printCabecera(out);

			//Cuerpo
			printBody(out, diasPrediccion);

			//Cerramos etiquetas hml
			printCierreFichero(out);

			//Cerramos printer
			out.flush();
			out.close();
		} catch (Exception ex)
		{
			System.out.println("Excepcion en leeDias de CrearPaginaHTML");
			ex.printStackTrace();
		}
	}

	/**
	 * Escribe la cabecera del html
	 */
	private static void printCabecera(PrintWriter out)
	{
		out.print("<!DOCTYPE html> \n"
				+ "<html lang=\"es\">\n"
				+ "<!-- Cabecera: Nombre de la pagina y llamada a CSS --> \n"
				+ "<head>\n"
				+ "\t<title>Tiempo Zaragoza</title>\n"
				+ "</head>\n");
	}

	/**
	 * Escribe el cuerpo del html
	 * @param out
	 */
	private static void printBody(PrintWriter out, EncapsulaDias diasPrediccion)
	{
		out.println("<body>");
		out.println("\t<h1>El tiempo de la ciudad del viento</h1>");
		out.println("\t<font>La web mas cutre que puedas encontrar</font>");
		out.println();

		printDatos(out, diasPrediccion);
	}


	/**
	 * Escribe las etiquetas de cierre del html
	 */
	private static void printCierreFichero(PrintWriter out)
	{
		out.println("</body>");
		out.println("</html>");
	}

	/**
	 * Escribe los datos en el html
	 */
	private static void printDatos(PrintWriter out, EncapsulaDias diasPrediccion)
	{
		//Sacamos los dias
		Dia dia03 = diasPrediccion.getListaDias().get(0);
		Dia dia04 = diasPrediccion.getListaDias().get(1);
		Dia dia05 = diasPrediccion.getListaDias().get(2);
		Dia dia06 = diasPrediccion.getListaDias().get(3);
		Dia dia07 = diasPrediccion.getListaDias().get(4);
		Dia dia08 = diasPrediccion.getListaDias().get(5);

		try
		{
			//Inicio tabla
			out.println("\t<table border=1 align=\"center\" cellspacing=\"0\"  style=\"width: 100%\">");

			//Fila fecha y periodos
			out.println("\t\t<tr>");
			out.println("\t\t\t<th rowspan=2 align=\"center\"><h5>Fecha</h5></th>");
			out.println("\t\t\t<td colspan=3 align=\"center\"><font size=\"2\"> Prediccion del dia " + dia03.getFecha().toString().substring(4, 10) + "</font></td>");
			out.println("\t\t\t<td colspan=2 align=\"center\"><font size=\"2\"> Prediccion del dia " + dia04.getFecha().toString().substring(4, 10) + "</font></td>");
			out.println("\t\t\t<td colspan=2 align=\"center\"><font size=\"2\"> Prediccion del dia " + dia05.getFecha().toString().substring(4, 10) + "</font></td>");
			out.println("\t\t\t<td rowspan=2 align=\"center\"><font size=\"2\"> Prediccion del dia " + dia06.getFecha().toString().substring(4, 10) + "</font></td>");
			out.println("\t\t\t<td rowspan=2 align=\"center\"><font size=\"2\"> Prediccion del dia " + dia07.getFecha().toString().substring(4, 10) + "</font></td>");
			out.println("\t\t\t<td rowspan=2 align=\"center\"><font size=\"2\"> Prediccion del dia " + dia08.getFecha().toString().substring(4, 10) + "</font></td>");
			out.println("\t\t</tr>");

			out.println("\t\t<tr>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">6-12</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">12-18</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">18-24</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">0-12</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">12-24</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">0-12</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">12-24</font></td>");
			out.println("\t\t</tr>");

			//Fila Estado Cielo
			out.println("\t\t<tr>");
			out.println("\t\t\t<th><h5>Estado cielo</h5></th>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia03.getEstadoCielo().get("00-06")[0] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia03.getEstadoCielo().get("12-18")[0] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia03.getEstadoCielo().get("18-24")[0] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia04.getEstadoCielo().get("00-12")[0] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia04.getEstadoCielo().get("12-24")[0] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia05.getEstadoCielo().get("00-12")[0] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia05.getEstadoCielo().get("12-24")[0] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia06.getEstadoCielo().get("00-24")[0] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia07.getEstadoCielo().get("00-24")[0] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia08.getEstadoCielo().get("00-24")[0] + "</font></td>");


			out.println("\t\t</tr>");

			//Fila Prob. Preci
			out.println("\t\t<tr>");
			out.println("\t\t\t<th><h5>Prob. precip.</h5></th>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia03.getProbPrecipitacion().get("00-06") + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia03.getProbPrecipitacion().get("12-18") + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia03.getProbPrecipitacion().get("18-24") + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia04.getProbPrecipitacion().get("00-12") + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia04.getProbPrecipitacion().get("12-24") + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia05.getProbPrecipitacion().get("00-12") + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia05.getProbPrecipitacion().get("12-24") + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia06.getProbPrecipitacion().get("00-24") + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia07.getProbPrecipitacion().get("00-24") + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia08.getProbPrecipitacion().get("00-24") + "</font></td>");

			out.println("\t\t</tr>");

			//Fila Cota Nieve
			out.println("\t\t<tr>");
			out.println("\t\t\t<th><h5>Cota nieve</h5></th>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia03.getCotaNieveProv().get("00-06") + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia03.getCotaNieveProv().get("12-18") + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia03.getCotaNieveProv().get("18-24") + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia04.getCotaNieveProv().get("00-12") + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia04.getCotaNieveProv().get("12-24") + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia05.getCotaNieveProv().get("00-12") + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia05.getCotaNieveProv().get("12-24") + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia06.getCotaNieveProv().get("00-24") + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia07.getCotaNieveProv().get("00-24") + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia08.getCotaNieveProv().get("00-24") + "</font></td>");

			out.println("\t\t</tr>");

			//Fila Temp. min
			out.println("\t\t<tr>");
			out.println("\t\t\t<th><h5>Temperatura</h5></th>");
			out.println("\t\t\t<td colspan=3 align=\"center\"><font size=\"2\">" +  dia03.getTemperatura().getMinima() + "/" + dia03.getTemperatura().getMaxima() + "</font></td>");
			out.println("\t\t\t<td colspan=2 align=\"center\"><font size=\"2\">" +  dia04.getTemperatura().getMinima() + "/" + dia04.getTemperatura().getMaxima() + "</font></td>");
			out.println("\t\t\t<td colspan=2 align=\"center\"><font size=\"2\">" +  dia05.getTemperatura().getMinima() + "/" + dia05.getTemperatura().getMaxima() + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia06.getTemperatura().getMinima() + "/" + dia06.getTemperatura().getMaxima() + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia07.getTemperatura().getMinima() + "/" + dia07.getTemperatura().getMaxima() + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia08.getTemperatura().getMinima() + "/" + dia08.getTemperatura().getMaxima() + "</font></td>");


			out.println("\t\t</tr>");

			//Fila Viento
			out.println("\t\t<tr>");
			out.println("\t\t\t<th rowspan=2><h5>Viento</h5></th>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia03.getViento().get("00-06")[0] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia03.getViento().get("12-18")[0] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia03.getViento().get("18-24")[0] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia04.getViento().get("00-12")[0] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia04.getViento().get("12-24")[0] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia05.getViento().get("00-12")[0] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia05.getViento().get("12-24")[0] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia06.getViento().get("00-24")[0] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia07.getViento().get("00-24")[0] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia08.getViento().get("00-24")[0] + "</font></td>");
			out.println("\t\t</tr>");

			out.println("\t\t<tr>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia03.getViento().get("00-06")[1] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia03.getViento().get("12-18")[1] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia03.getViento().get("18-24")[1] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia04.getViento().get("00-12")[1] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia04.getViento().get("12-24")[1] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia05.getViento().get("00-12")[1] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia05.getViento().get("12-24")[1] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia06.getViento().get("00-24")[1] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia07.getViento().get("00-24")[1] + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia08.getViento().get("00-24")[1] + "</font></td>");
			out.println("\t\t</tr>");

			//Fila indice UV
			out.println("\t\t<tr>");
			out.println("\t\t\t<th><h5>UVMaximo</h5></th>");
			out.println("\t\t\t<td colspan=3 align=\"center\"><font size=\"2\">" +  dia03.getUvMax() + "</font></td>");
			out.println("\t\t\t<td colspan=2 align=\"center\"><font size=\"2\">" +  dia04.getUvMax() + "</font></td>");
			out.println("\t\t\t<td colspan=2 align=\"center\"><font size=\"2\">" +  dia05.getUvMax() + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia06.getUvMax() + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia07.getUvMax() + "</font></td>");
			out.println("\t\t\t<td align=\"center\"><font size=\"2\">" +  dia08.getUvMax() + "</font></td>");

			out.println("\t\t</tr>");

			//Fin tabla
			out.println("\t</table>");
		} catch (Exception ex)
		{
			System.out.println("Excepcion en printDatos");
			ex.printStackTrace();
		}
	}
}
