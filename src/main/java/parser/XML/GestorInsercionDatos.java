/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 04-02-15
 * Fecha modificacion:
 * Tiempo invertido: 5h
 */
package parser.XML;

import parser.datos.DiaUtils;
import parser.datos.HumedadRelativa;
import parser.datos.SensacionTermica;
import parser.datos.Temperatura;
import persistence.entities.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class GestorInsercionDatos
{
	//Codigo es PK en todas las tablas: 2 opciones
	// - Codigo global para todas las tablas
	// - Array que guarde estado del codigo de cada tabla
	int[] codigo = new int[12];

	// Atributos con los que cuenta un dia
	private String fecha;
	private Map<String, String> probPrecipitacion;
	private Map<String, String> cotaNieveProv;
	private Map<String, String[]> estadoCielo;
	private Map<String, String[]> viento;
	private Map<String, String> rachaMax;
	private Temperatura temperatura;
	private SensacionTermica sensacionTermica;
	private HumedadRelativa humedadRelativa;
	private int uvMax;

	public GestorInsercionDatos()
	{
		//Inicializamos los codigos a 1 -> No existen primary keys que valgan 0

		for(int i = 0; i<this.codigo.length; i++)
			codigo[i] = 1;

	}

	/**
	 * Lee los dias que haya en la lista
	 *
	 * @param diasPrediccion
	 */
	public void lectorListaDias(ListaDia diasPrediccion)
	{
		//Gestion de persistencia
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("STW_P5_Persistence");
		EntityManager em = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = em.getTransaction();

		try
		{
			transaction.begin();

			codigo[0] = 0;
			for (Dia dia : diasPrediccion.getDiasPrediccion())
			{
				codigo[0]++;
				extraeDatosDia(dia, em);
			}
		} catch (Exception ex)
		{
			System.out.println("Excepcion en lectorListaDias");
			ex.printStackTrace();
		} finally
		{
			transaction.commit();
			em.close();
			entityManagerFactory.close();
		}
	}

	/**
	 * Extrae los datos de un dia
	 *
	 * @param dia
	 * @param em
	 */
	private void extraeDatosDia(Dia dia, EntityManager em)
	{
		try
		{
			fecha = dia.dia.getFecha();
			probPrecipitacion = dia.dia.getProbPrecipitacion();
			cotaNieveProv = dia.dia.getCotaNieveProv();
			estadoCielo = dia.dia.getEstadoCielo();
			viento = dia.dia.getViento();
			rachaMax = dia.dia.getRachaMax();
			temperatura = dia.dia.getTemperatura();
			sensacionTermica = dia.dia.getSensacionTermica();
			humedadRelativa = dia.dia.getHumedadRelativa();
			uvMax = dia.dia.getUvMax();

			insertaAtributos(em);
		} catch (Exception ex)
		{
			System.out.println("Excepcion en extraeDatosDia");
			ex.printStackTrace();
		}
	}

	/**
	 * Inserta los datos guardados en los atributos de la clase en la BD
	 * @param em
	 */
	private void insertaAtributos(EntityManager em)
	{
		try
		{
			//Entidades
			DiaEntity diaEntity = new DiaEntity();
			ProbPrecipitacionEntity probPrecipitacionEntity = new ProbPrecipitacionEntity();
			CotaNieveProvEntity cotaNieveProvEntity = new CotaNieveProvEntity();
			EstadoCieloEntity estadoCieloEntity = new EstadoCieloEntity();
			VientoEntity vientoEntity = new VientoEntity();
			RachaMaxEntity rachaMaxEntity = new RachaMaxEntity();
			TemperaturaEntity temperaturaEntity = new TemperaturaEntity();
			TemperaturaIntervalosEntity temperaturaIntervalosEntity = new TemperaturaIntervalosEntity();
			SensacionTermicaEntity sensacionTermicaEntity = new SensacionTermicaEntity();
			SensacionTermicaIntervalosEntity sensacionTermicaIntervalosEntity = new SensacionTermicaIntervalosEntity();
			HumedadRelativaEntity humedadRelativaEntity = new HumedadRelativaEntity();
			HumedadRelativaIntervalosEntity humedadRelativaIntervalosEntity = new HumedadRelativaIntervalosEntity();

			//Asignar datos a las entidades
			insertarProbPrecipitacion(codigo[1], fecha, probPrecipitacionEntity, em);
			insertarCotaNieveProv(codigo[2], fecha, cotaNieveProvEntity, em);
			insertarEstadoCielo(codigo[3], fecha, estadoCieloEntity, em);
			insertarViento(codigo[4], fecha, vientoEntity, em);
			insertarRachaMaxima(codigo[5], fecha, rachaMaxEntity, em);
			insertarTemperatura(codigo[6], fecha, temperaturaEntity, em);
			insertarTemperaturaIntervalos(codigo[7], fecha, temperaturaEntity, temperaturaIntervalosEntity, em);
			insertarSensacionTermica(codigo[8], fecha, sensacionTermicaEntity, em);
			insertarSensacionTermicaIntervalos(codigo[9], fecha, sensacionTermicaEntity,
					sensacionTermicaIntervalosEntity,
					em);
			insertarHumedadRelativa(codigo[10], fecha, humedadRelativaEntity, em);
			insertarHumedadRelativaIntervalos(codigo[11], fecha, humedadRelativaEntity, humedadRelativaIntervalosEntity,
					em);
			insertarDia(codigo[0], fecha, diaEntity, probPrecipitacionEntity, cotaNieveProvEntity, estadoCieloEntity,
					vientoEntity,
					rachaMaxEntity, temperaturaEntity, sensacionTermicaEntity, humedadRelativaEntity, uvMax, em);

		} catch (Exception ex)
		{
			System.out.println("Excepcion en insertarAtributos");
			ex.printStackTrace();
		}
	}

	/**
	 * Rellena el objeto ProbPrecipitacionEntity
	 *
	 * @param codigo
	 * @param fecha
	 * @param probPrecipitacionEntity
	 */
	private void insertarProbPrecipitacion(int codigo, String fecha, ProbPrecipitacionEntity probPrecipitacionEntity,
			EntityManager em)
	{
		try
		{
			for (String periodo : DiaUtils.getPeriodos())
			{
				probPrecipitacionEntity = new ProbPrecipitacionEntity();

				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = formatter.parse(fecha);

				probPrecipitacionEntity.setCodigo(codigo);
				probPrecipitacionEntity.setFecha(date);

				if ((this.probPrecipitacion.get(periodo) != null) && (!this.probPrecipitacion.get(periodo).equals("")))
				{
					probPrecipitacionEntity.setPeriodo(periodo);
					probPrecipitacionEntity.setValorPeriodo(Integer.parseInt(this.probPrecipitacion.get(periodo)));
				} else
				{
					probPrecipitacionEntity.setPeriodo(periodo);
				}
				codigo++;
				em.persist(probPrecipitacionEntity);
			}
			this.codigo[1] = codigo;
		} catch (Exception ex)
		{
			System.out.println("Excepcion en insertarProbPrecipitacion");
			ex.printStackTrace();
		}
	}

	/**
	 * Rellena el objeto CotaNieveProvEntity
	 *
	 * @param codigo
	 * @param fecha
	 * @param cotaNieveProvEntity
	 */
	private void insertarCotaNieveProv(int codigo, String fecha, CotaNieveProvEntity cotaNieveProvEntity,
			EntityManager em)
	{
		try
		{
			for (String periodo : DiaUtils.getPeriodos())
			{
				cotaNieveProvEntity = new CotaNieveProvEntity();

				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = formatter.parse(fecha);

				cotaNieveProvEntity.setCodigo(codigo);
				cotaNieveProvEntity.setFecha(date);

				if ((this.cotaNieveProv.get(periodo) != null) && (!this.cotaNieveProv.get(periodo).equals("")))
				{
					cotaNieveProvEntity.setPeriodo(periodo);
					cotaNieveProvEntity.setValorPeriodo(Integer.parseInt(this.cotaNieveProv.get(periodo)));
				} else
				{
					cotaNieveProvEntity.setPeriodo(periodo);
				}
				codigo++;
				em.persist(cotaNieveProvEntity);
			}
			this.codigo[2] = codigo;
		} catch (Exception ex)
		{
			System.out.println("Excepcion en insertarCotaNieveProv");
			ex.printStackTrace();
		}
	}

	/**
	 * Rellena el objeto EstadoCieloEntity
	 *
	 * @param codigo
	 * @param fecha
	 * @param estadoCieloEntity
	 */
	private void insertarEstadoCielo(int codigo, String fecha, EstadoCieloEntity estadoCieloEntity,
			EntityManager em)
	{
		try
		{
			for (String periodo : DiaUtils.getPeriodos())
			{
				estadoCieloEntity = new EstadoCieloEntity();

				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = formatter.parse(fecha);

				estadoCieloEntity.setCodigo(codigo);
				estadoCieloEntity.setFecha(date);

				if ((this.estadoCielo.get(periodo) != null) && (!this.estadoCielo.get(periodo)[1].equals("")))
				{
					estadoCieloEntity.setPeriodo(periodo);
					estadoCieloEntity.setDescripcion(this.estadoCielo.get(periodo)[1]);
				} else
				{
					estadoCieloEntity.setPeriodo(periodo);
				}

				codigo++;
				em.persist(estadoCieloEntity);
			}
			this.codigo[3] = codigo;
		} catch (Exception ex)
		{
			System.out.println("Excepcion en insertarEstadoCielo");
			ex.printStackTrace();
		}
	}

	/**
	 * Rellena el objeto VientoEntity
	 *
	 * @param codigo
	 * @param fecha
	 * @param vientoEntity
	 */
	private void insertarViento(int codigo, String fecha, VientoEntity vientoEntity, EntityManager em)
	{
		try
		{
			for (String periodo : DiaUtils.getPeriodos())
			{
				vientoEntity = new VientoEntity();

				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = formatter.parse(fecha);

				vientoEntity.setCodigo(codigo);
				vientoEntity.setFecha(date);

				if ((this.viento.get(periodo) != null) && (!this.viento.get(periodo)[1].equals("")))
				{
					vientoEntity.setPeriodo(periodo);
					vientoEntity.setDireccion(this.viento.get(periodo)[0]);
					vientoEntity.setVelocidad(Integer.parseInt(this.viento.get(periodo)[1]));
				} else
				{
					vientoEntity.setPeriodo(periodo);
				}

				codigo++;
				em.persist(vientoEntity);
			}
			this.codigo[4] = codigo;
		} catch (Exception ex)
		{
			System.out.println("Excepcion en insertarViento");
			ex.printStackTrace();
		}
	}

	/**
	 * Rellena el objeto RachaMaximaEntity
	 *
	 * @param codigo
	 * @param fecha
	 * @param rachaMaxEntity
	 * @param em
	 */
	private void insertarRachaMaxima(int codigo, String fecha, RachaMaxEntity rachaMaxEntity, EntityManager em)
	{
		try
		{
			for (String periodo : DiaUtils.getPeriodos())
			{
				rachaMaxEntity = new RachaMaxEntity();

				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = formatter.parse(fecha);

				rachaMaxEntity.setCodigo(codigo);
				rachaMaxEntity.setFecha(date);

				if ((this.rachaMax.get(periodo) != null) && (!this.rachaMax.get(periodo).equals("")))
				{
					rachaMaxEntity.setPeriodo(periodo);
					rachaMaxEntity.setValorPeriodo(Integer.parseInt(this.rachaMax.get(periodo)));
				} else
				{
					rachaMaxEntity.setPeriodo(periodo);
				}

				codigo++;
				em.persist(rachaMaxEntity);
			}
			this.codigo[5] = codigo;
		} catch (Exception ex)
		{
			System.out.println("Excepcion en insertarRachaMaxima");
			ex.printStackTrace();
		}
	}

	/**
	 * Rellena el objeto TemperaturaEntity
	 *
	 * @param codigo
	 * @param fecha
	 * @param temperaturaEntity
	 * @param em
	 */
	private void insertarTemperatura(int codigo, String fecha, TemperaturaEntity temperaturaEntity, EntityManager em)
	{
		try
		{
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = formatter.parse(fecha);

			temperaturaEntity.setCodigo(codigo);
			temperaturaEntity.setFecha(date);
			temperaturaEntity.setMaxima(this.temperatura.getMaxima());
			temperaturaEntity.setMinima(this.temperatura.getMinima());

			this.codigo[6]++;
			em.persist(temperaturaEntity);

		} catch (Exception ex)
		{
			System.out.println("Excepcion en insertarTemperatura");
			ex.printStackTrace();
		}
	}

	/**
	 * Rellena el objeto TemperaturaIntervalosEntity
	 *
	 * @param codigo
	 * @param fecha
	 * @param temperaturaEntity
	 * @param temperaturaIntervalosEntity
	 * @param em
	 */
	private void insertarTemperaturaIntervalos(int codigo, String fecha, TemperaturaEntity temperaturaEntity,
			TemperaturaIntervalosEntity temperaturaIntervalosEntity, EntityManager em)
	{
		try
		{
			for (String hora : DiaUtils.getHoras())
			{
				temperaturaIntervalosEntity = new TemperaturaIntervalosEntity();

				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = formatter.parse(fecha);

				temperaturaIntervalosEntity.setCodigo(codigo);
				temperaturaIntervalosEntity.setFecha(date);
				temperaturaIntervalosEntity.setTemperatura(temperaturaEntity);

				if (this.temperatura.getDato().get(hora) != null)
				{
					temperaturaIntervalosEntity.setHora(hora);
					temperaturaIntervalosEntity.setValorHora(this.temperatura.getDato().get(hora));
				} else
				{
					temperaturaIntervalosEntity.setHora(hora);
				}

				codigo++;
				em.persist(temperaturaIntervalosEntity);
			}
			this.codigo[7] = codigo;
		} catch (Exception ex)
		{
			System.out.println("Excepcion en insertarTemperaturaIntervalos");
			ex.printStackTrace();
		}
	}

	/**
	 * Rellena el objeto SensacionTermicaEntity
	 *
	 * @param codigo
	 * @param fecha
	 * @param sensacionTermicaEntity
	 * @param em
	 */
	private void insertarSensacionTermica(int codigo, String fecha, SensacionTermicaEntity sensacionTermicaEntity,
			EntityManager em)
	{
		try
		{
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = formatter.parse(fecha);

			sensacionTermicaEntity.setCodigo(codigo);
			sensacionTermicaEntity.setFecha(date);
			sensacionTermicaEntity.setMaxima(this.sensacionTermica.getMaxima());
			sensacionTermicaEntity.setMinima(this.sensacionTermica.getMinima());

			this.codigo[8]++;
			em.persist(sensacionTermicaEntity);

		} catch (Exception ex)
		{
			System.out.println("Excepcion en insertarSensacionTermica");
			ex.printStackTrace();
		}
	}

	/**
	 * Rellena el objeto SensacionTermicaIntervalosEntity
	 *
	 * @param codigo
	 * @param fecha
	 * @param sensacionTermicaEntity
	 * @param sensacionTermicaIntervalosEntity
	 * @param em
	 */
	private void insertarSensacionTermicaIntervalos(int codigo, String fecha,
			SensacionTermicaEntity sensacionTermicaEntity,
			SensacionTermicaIntervalosEntity sensacionTermicaIntervalosEntity, EntityManager em)
	{
		try
		{
			for (String hora : DiaUtils.getHoras())
			{
				sensacionTermicaIntervalosEntity = new SensacionTermicaIntervalosEntity();

				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = formatter.parse(fecha);

				sensacionTermicaIntervalosEntity.setCodigo(codigo);
				sensacionTermicaIntervalosEntity.setFecha(date);
				sensacionTermicaIntervalosEntity.setSensacionTermica(sensacionTermicaEntity);

				if (this.temperatura.getDato().get(hora) != null)
				{
					sensacionTermicaIntervalosEntity.setHora(hora);
					sensacionTermicaIntervalosEntity.setValorHora(this.sensacionTermica.getDato().get(hora));
				} else
				{
					sensacionTermicaIntervalosEntity.setHora(hora);
				}

				codigo++;
				em.persist(sensacionTermicaIntervalosEntity);
			}
			this.codigo[9] = codigo;
		} catch (Exception ex)
		{
			System.out.println("Excepcion en insertarSensacionTermicoIntervalos");
			ex.printStackTrace();
		}
	}

	/**
	 * Rellena el objeto HumedadRelativaEntity
	 *
	 * @param codigo
	 * @param fecha
	 * @param humedadRelativaEntity
	 */
	private void insertarHumedadRelativa(int codigo, String fecha, HumedadRelativaEntity humedadRelativaEntity,
			EntityManager em)
	{
		try
		{
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = formatter.parse(fecha);

			humedadRelativaEntity.setCodigo(codigo);
			humedadRelativaEntity.setFecha(date);
			humedadRelativaEntity.setMaxima(this.humedadRelativa.getMaxima());
			humedadRelativaEntity.setMinima(this.humedadRelativa.getMinima());

			this.codigo[10]++;
			em.persist(humedadRelativaEntity);

		} catch (Exception ex)
		{
			System.out.println("Excepcion en insertarSensacionTermica");
			ex.printStackTrace();
		}
	}

	/**
	 * Rellena el objeto HumedadRelativaIntervalosEntity
	 *
	 * @param codigo
	 * @param fecha
	 * @param humedadRelativaEntity
	 * @param humedadRelativaIntervalosEntity
	 */
	private void insertarHumedadRelativaIntervalos(int codigo, String fecha,
			HumedadRelativaEntity humedadRelativaEntity,
			HumedadRelativaIntervalosEntity humedadRelativaIntervalosEntity, EntityManager em)
	{
		try
		{
			for (String hora : DiaUtils.getHoras())
			{
				humedadRelativaIntervalosEntity = new HumedadRelativaIntervalosEntity();

				DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				Date date = formatter.parse(fecha);

				humedadRelativaIntervalosEntity.setCodigo(codigo);
				humedadRelativaIntervalosEntity.setFecha(date);
				humedadRelativaIntervalosEntity.setHumedadRelativa(humedadRelativaEntity);

				if (this.humedadRelativa.getDato().get(hora) != null)
				{
					humedadRelativaIntervalosEntity.setHora(hora);
					humedadRelativaIntervalosEntity.setValorHora(this.humedadRelativa.getDato().get(hora));
				} else
				{
					humedadRelativaIntervalosEntity.setHora(hora);
				}
				codigo++;
				em.persist(humedadRelativaIntervalosEntity);
			}

			this.codigo[11] = codigo;
		} catch (Exception ex)
		{
			System.out.println("Excepcion en insertarHumedadRelativaIntervalos");
			ex.printStackTrace();
		}
	}

	/**
	 * Rellena el objeto DiaEntity
	 *
	 * @param codigo
	 * @param fecha
	 * @param diaEntity
	 * @param probPrecipitacionEntity
	 * @param cotaNieveProvEntity
	 * @param estadoCieloEntity
	 * @param vientoEntity
	 * @param rachaMaxEntity
	 * @param temperaturaEntity
	 * @param sensacionTermicaEntity
	 * @param humedadRelativaEntity
	 */
	private void insertarDia(int codigo, String fecha, DiaEntity diaEntity,
			ProbPrecipitacionEntity probPrecipitacionEntity,
			CotaNieveProvEntity cotaNieveProvEntity, EstadoCieloEntity estadoCieloEntity, VientoEntity vientoEntity,
			RachaMaxEntity rachaMaxEntity, TemperaturaEntity temperaturaEntity,
			SensacionTermicaEntity sensacionTermicaEntity, HumedadRelativaEntity humedadRelativaEntity,
			int uvMax, EntityManager em)
	{
		try
		{
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = formatter.parse(fecha);

			diaEntity.setCodigo(codigo);
			diaEntity.setFecha(date);
//			diaEntity.setProbPrecipitacion(probPrecipitacionEntity);
//			diaEntity.setCotaNieveProv(cotaNieveProvEntity);
//			diaEntity.setEstadoCielo(estadoCieloEntity);
//			diaEntity.setViento(vientoEntity);
//			diaEntity.setRachaMax(rachaMaxEntity);
//			diaEntity.setTemperatura(temperaturaEntity);
//			diaEntity.setSensacionTermica(sensacionTermicaEntity);
//			diaEntity.setHumedadRelativa(humedadRelativaEntity);
			diaEntity.setUvMax(uvMax);

			em.persist(diaEntity);
		} catch (Exception ex)
		{
			System.out.println("Excepcion en insertarDia");
			ex.printStackTrace();
		}
	}
}
