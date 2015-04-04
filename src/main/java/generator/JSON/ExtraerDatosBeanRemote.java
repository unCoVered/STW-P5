/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 04-03-15
 * Fecha modificacion:
 * Tiempo invertido:
 */
package generator.JSON;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ExtraerDatosBeanRemote
{
	/**
	 * Devuelve una lista de Strings con las fechas de los dias almacenados en la BD
	 * @return
	 */
	public List<Date> cargarFechaDias();

	/**
	 * Devuelve el atributo UvMax del dia con la fecha pasada por parametros
	 * @param fecha
	 * @return
	 */
	public int cargarUvMaxDia(Date fecha);

	/**
	 * Devuelve un mapHash con las prob. precipitacion del dia con la fecha pasada por parametros
	 * @param fecha
	 * @return
	 */
	public Map<String, String> cargarProbPrecipitacion(Date fecha);

	/**
	 * Devuelve un MapHash con las cotas de nieve del dia con la fecha pasada por parametros
	 * @param fecha
	 * @return
	 */
	public Map<String, String> cargarCotaNieveProv(Date fecha);

	/**
	 * Devuelve un MapHash con el estado del cielo del dia con la fecha pasada por parametros
	 * @param fecha
	 * @return
	 */
	public Map<String, String[]> cargarEstadoCielo(Date fecha);

	/**
	 * Devuelve un MapHash con el viento del dia con la fecha pasada por parametros
	 * @param fecha
	 * @return
	 */
	public Map<String, String[]> cargarViento(Date fecha);

	/**
	 * Devuelve un MapHash con las rachaMaximas de viento del dia con la fecha pasada por parametros
	 * @param fecha
	 * @return
	 */
	public Map<String, String> cargarRachaMax(Date fecha);

	/**
	 * Devuelve la temp. maxima del dia con la fecha pasada por parametros
	 * @param fecha
	 * @return
	 */
	public int cargarTempMaxima(Date fecha);

	/**
	 * Devuelve la temp. minima del dia con la fecha pasada por parametros
	 * @param fecha
	 * @return
	 */
	public int cargarTempMinima(Date fecha);

	/**
	 * Devuelve la temp. por intervalos del dia con la fecha pasada por parametros
	 * @param fecha
	 * @return
	 */
	public Map<String, Integer> cargarTempIntervalos(Date fecha);

	/**
	 * Devuelve la Sens. Term. maxima del dia con la fecha pasada por parametros
	 * @param fecha
	 * @return
	 */
	public int cargarSensTermMaxima(Date fecha);

	/**
	 * Devuelve la Sens. Term. minima del dia con la fecha pasada por parametros
	 * @param fecha
	 * @return
	 */
	public int cargarSensTermMinima(Date fecha);

	/**
	 * Devuelve la Sens. Term. por intervalos del dia con la fecha pasada por parametros
	 * @param fecha
	 * @return
	 */
	public Map<String, Integer> cargarSensTermIntervalos(Date fecha);

	/**
	 * Devuelve la Hum. Relm maxima del dia con la fecha pasada por parametros
	 * @param fecha
	 * @return
	 */
	public int cargarHumRelMaxima(Date fecha);

	/**
	 * Devuelve la Hum. Rel. minima del dia con la fecha pasada por parametros
	 * @param fecha
	 * @return
	 */
	public int cargarHumRelMinima(Date fecha);

	/**
	 * Devuelve la Hum. Rel. por intervalos del dia con la fecha pasada por parametros
	 * @param fecha
	 * @return
	 */
	public Map<String, Integer> cargarHumRelIntervalos(Date fecha);


}
