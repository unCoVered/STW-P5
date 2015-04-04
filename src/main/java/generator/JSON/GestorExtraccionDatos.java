/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 04-03-15
 * Fecha modificacion:
 * Tiempo invertido:
 */
package generator.JSON;

import parser.datos.Dia;
import parser.datos.HumedadRelativa;
import parser.datos.SensacionTermica;
import parser.datos.Temperatura;

import java.util.Date;
import java.util.List;

/**
 * Clase encargada de llenar una lista con los dias almacenados en la BD
 */
public class GestorExtraccionDatos
{

	private  ExtraerDatosBeanRemote extraerDatosBeanRemote;

	public GestorExtraccionDatos(){

		extraerDatosBeanRemote = new ExtraerDatosBean();

	}

	public void generarListaDias(List<Dia> listaDias)
	{
		try{
			List<Date> fechaDias = extraerDatosBeanRemote.cargarFechaDias();

			for(Date fecha : fechaDias)
			{
				Dia dia = new Dia();

				// Insertamos datos directos
				dia.setFecha(fecha.toString());
				dia.setUvMax(extraerDatosBeanRemote.cargarUvMaxDia(fecha));
				dia.setProbPrecipitacion(extraerDatosBeanRemote.cargarProbPrecipitacion(fecha));
				dia.setCotaNieveProv(extraerDatosBeanRemote.cargarCotaNieveProv(fecha));
				dia.setEstadoCielo(extraerDatosBeanRemote.cargarEstadoCielo(fecha));
				dia.setViento(extraerDatosBeanRemote.cargarViento(fecha));
				dia.setRachaMax(extraerDatosBeanRemote.cargarRachaMax(fecha));

				// Creamos objetos pertenecientes a Dia
				Temperatura temperatura = new Temperatura();
				SensacionTermica sensacionTermica = new SensacionTermica();
				HumedadRelativa humedadRelativa = new HumedadRelativa();

				// Rellenamos objetos
				temperatura.setMaxima(extraerDatosBeanRemote.cargarTempMaxima(fecha));
				temperatura.setMinima(extraerDatosBeanRemote.cargarTempMinima(fecha));
				temperatura.setDato(extraerDatosBeanRemote.cargarTempIntervalos(fecha));

				sensacionTermica.setMaxima(extraerDatosBeanRemote.cargarSensTermMaxima(fecha));
				sensacionTermica.setMinima(extraerDatosBeanRemote.cargarSensTermMinima(fecha));
				sensacionTermica.setDato(extraerDatosBeanRemote.cargarSensTermIntervalos(fecha));

				humedadRelativa.setMaxima(extraerDatosBeanRemote.cargarHumRelMaxima(fecha));
				humedadRelativa.setMinima(extraerDatosBeanRemote.cargarHumRelMinima(fecha));
				humedadRelativa.setDato(extraerDatosBeanRemote.cargarHumRelIntervalos(fecha));

				// Agregamos objetos a Dia
				dia.setTemperatura(temperatura);
				dia.setSensacionTermica(sensacionTermica);
				dia.setHumedadRelativa(humedadRelativa);

				listaDias.add(dia);
			}
		}catch(Exception ex)
		{
			System.out.println("Excepcion en generarListaDias");
			ex.printStackTrace();
		}
	}

}
