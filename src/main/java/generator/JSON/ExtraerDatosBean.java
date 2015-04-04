/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 04-03-15
 * Fecha modificacion:
 * Tiempo invertido:
 */
package generator.JSON;

import persistence.entities.*;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class ExtraerDatosBean implements ExtraerDatosBeanRemote
{
	@PersistenceContext
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("STW_P5_Persistence");
	EntityManager em = entityManagerFactory.createEntityManager();

	public ExtraerDatosBean(){}

	@Override
	public List<Date> cargarFechaDias(){

		List<Date> listaFechasQuery = new ArrayList<Date>();

		try
		{
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Date> criteria = cb.createQuery(Date.class);

			Root<DiaEntity> diaRoot = criteria.from(DiaEntity.class);

			//QUERY APROX: (SELECT DISTINCT FECHA FROM DIA)
			criteria.select(diaRoot.get(DiaEntity_.fecha).alias("fecha")).distinct(true);

			listaFechasQuery = em.createQuery(criteria).getResultList();

			return listaFechasQuery;

		}catch(NoResultException ex)
		{
			System.out.println("Excepcion en cargarFechaDias");
			ex.printStackTrace();
			return listaFechasQuery;
		}
	}

	@Override
	public int cargarUvMaxDia(Date fecha)
	{
		Integer uvMax = Integer.valueOf(0);

		try
		{
			List<Predicate> restricciones = new ArrayList<Predicate>();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Integer> criteria = cb.createQuery(Integer.class);

			Root<DiaEntity> diaRoot = criteria.from(DiaEntity.class);

			//QUERY APROX: (SELECT UV_MAX FROM DIA WHERE FECHA = PARAM)
			criteria.select(diaRoot.get(DiaEntity_.uvMax).alias("uvMax"));

			restricciones.add(cb.equal(diaRoot.get(DiaEntity_.fecha), fecha));

			criteria.where(cb.and(restricciones.toArray(new Predicate[restricciones.size()])));

			uvMax = em.createQuery(criteria).getSingleResult();

			return uvMax;
		}catch(NoResultException ex)
		{
			System.out.println("Excepcion en cargarUvMaxDia");
			ex.printStackTrace();
			return uvMax;
		}
	}

	@Override
	public Map<String, String> cargarProbPrecipitacion(Date fecha)
	{
		Map<String, String> mapProb = new HashMap<String, String>();
		try
		{
			List<Predicate> restricciones = new ArrayList<Predicate>();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = cb.createTupleQuery();

			Root<ProbPrecipitacionEntity> probPreRoot = criteria.from(ProbPrecipitacionEntity.class);

			// QUERY APROX: SELECT PERIODO, VALORPERIODO FROM PROB_PRECIPITACIONES WHERE FECHA = PARAM
			criteria.multiselect(probPreRoot.get(ProbPrecipitacionEntity_.periodo).alias("periodo"),
					probPreRoot.get(ProbPrecipitacionEntity_.valorPeriodo).alias("valorPeriodo"));

			restricciones.add(cb.equal(probPreRoot.get(ProbPrecipitacionEntity_.fecha), fecha));

			criteria.where(cb.and(restricciones.toArray(new Predicate[restricciones.size()])));

			for(Tuple tupla : em.createQuery(criteria).getResultList())
			{
				String periodo = tupla.get("periodo", String.class);
				String valorPeriodo = "";

				if (tupla.get("valorPeriodo") != null)
					valorPeriodo = Integer.toString(tupla.get("valorPeriodo", Integer.class));

				mapProb.put(periodo, valorPeriodo);
			}

			return mapProb;
		}catch(NoResultException ex)
		{
			System.out.println("Excepcion en cargarProbPrecipitacion");
			ex.printStackTrace();
			return mapProb;
		}
	}

	@Override
	public Map<String, String> cargarCotaNieveProv(Date fecha)
	{
		Map<String, String> mapCota = new HashMap<String, String>();
		try
		{
			List<Predicate> restricciones = new ArrayList<Predicate>();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = cb.createTupleQuery();

			Root<CotaNieveProvEntity> cotaNieveRoot = criteria.from(CotaNieveProvEntity.class);

			// QUERY APROX: SELECT PERIODO, VALORPERIODO FROM COTA_NIEVE_PROV WHERE FECHA = PARAM
			criteria.multiselect(cotaNieveRoot.get(CotaNieveProvEntity_.periodo).alias("periodo"),
					cotaNieveRoot.get(CotaNieveProvEntity_.valorPeriodo).alias("valorPeriodo"));

			restricciones.add(cb.equal(cotaNieveRoot.get(CotaNieveProvEntity_.fecha), fecha));

			criteria.where(cb.and(restricciones.toArray(new Predicate[restricciones.size()])));

			for(Tuple tupla : em.createQuery(criteria).getResultList())
			{
				String periodo = tupla.get("periodo", String.class);
				String valorPeriodo = "";

				if (tupla.get("valorPeriodo") != null)
					valorPeriodo = Integer.toString(tupla.get("valorPeriodo", Integer.class));

				mapCota.put(periodo, valorPeriodo);
			}

			return mapCota;
		}catch(NoResultException ex)
		{
			System.out.println("Excepcion en cargarCotaNieveProv");
			ex.printStackTrace();
			return mapCota;
		}
	}

	@Override
	public Map<String, String[]> cargarEstadoCielo(Date fecha)
	{
		Map<String, String[]> mapEstado = new HashMap<String, String[]>();
		try
		{
			List<Predicate> restricciones = new ArrayList<Predicate>();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = cb.createTupleQuery();

			Root<EstadoCieloEntity> estadoCieloRoot = criteria.from(EstadoCieloEntity.class);

			criteria.multiselect(estadoCieloRoot.get(EstadoCieloEntity_.periodo).alias("periodo"),
					estadoCieloRoot.get(EstadoCieloEntity_.descripcion).alias("descripcion"));

			restricciones.add(cb.equal(estadoCieloRoot.get(EstadoCieloEntity_.fecha), fecha));

			criteria.where(cb.and(restricciones.toArray(new Predicate[restricciones.size()])));

			for(Tuple tupla : em.createQuery(criteria).getResultList())
			{
				String periodo = tupla.get("periodo", String.class);
				String[] valorPeriodo = new String[1];
				valorPeriodo[0] = "";

				if (tupla.get("descripcion") != null)
					valorPeriodo[0] = tupla.get("descripcion", String.class);

				mapEstado.put(periodo, valorPeriodo);
			}

			return mapEstado;
		}catch(NoResultException ex)
		{
			System.out.println("Excepcion en cargarEstadoCielo");
			ex.printStackTrace();
			return mapEstado;
		}
	}

	@Override
	public Map<String, String[]> cargarViento(Date fecha)
	{
		Map<String, String[]> mapViento = new HashMap<String, String[]>();
		try
		{
			List<Predicate> restricciones = new ArrayList<Predicate>();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = cb.createTupleQuery();

			Root<VientoEntity> vientoRoot = criteria.from(VientoEntity.class);

			criteria.multiselect(vientoRoot.get(VientoEntity_.periodo).alias("periodo"),
					vientoRoot.get(VientoEntity_.direccion).alias("direccion"),
					vientoRoot.get(VientoEntity_.velocidad).alias("velocidad"));

			restricciones.add(cb.equal(vientoRoot.get(VientoEntity_.fecha), fecha));

			criteria.where(cb.and(restricciones.toArray(new Predicate[restricciones.size()])));

			for(Tuple tupla : em.createQuery(criteria).getResultList())
			{
				String periodo = tupla.get("periodo", String.class);
				String[] valorPeriodo = new String[2];
				valorPeriodo[0] = "";
				valorPeriodo[1] = "";

				if (tupla.get("direccion") != null)
					valorPeriodo[0] = tupla.get("direccion", String.class);
				else if (tupla.get("velocidad") != null)
					valorPeriodo[1] = Integer.toString(tupla.get("velocidad", Integer.class));


				mapViento.put(periodo, valorPeriodo);
			}

			return mapViento;
		}catch(NoResultException ex)
		{
			System.out.println("Excepcion en cargarViento");
			ex.printStackTrace();
			return mapViento;
		}
	}

	@Override
	public Map<String, String> cargarRachaMax(Date fecha)
	{
		Map<String, String> mapRacha = new HashMap<String, String>();
		try
		{
			List<Predicate> restricciones = new ArrayList<Predicate>();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = cb.createTupleQuery();

			Root<RachaMaxEntity> rachMaxRoot = criteria.from(RachaMaxEntity.class);

			// QUERY APROX: SELECT PERIODO, VALORPERIODO FROM RACHA_MAX WHERE FECHA = PARAM
			criteria.multiselect(rachMaxRoot.get(RachaMaxEntity_.periodo).alias("periodo"),
					rachMaxRoot.get(RachaMaxEntity_.valorPeriodo).alias("valorPeriodo"));

			restricciones.add(cb.equal(rachMaxRoot.get(RachaMaxEntity_.fecha), fecha));

			criteria.where(cb.and(restricciones.toArray(new Predicate[restricciones.size()])));

			for(Tuple tupla : em.createQuery(criteria).getResultList())
			{
				String periodo = tupla.get("periodo", String.class);
				String valorPeriodo = "";

				if (tupla.get("valorPeriodo") != null)
					valorPeriodo = Integer.toString(tupla.get("valorPeriodo", Integer.class));

				mapRacha.put(periodo, valorPeriodo);
			}

			return mapRacha;
		}catch(NoResultException ex)
		{
			System.out.println("Excepcion en cargarRachaMax");
			ex.printStackTrace();
			return mapRacha;
		}
	}

	@Override
	public int cargarTempMaxima(Date fecha)
	{
		Integer tempMaxima = Integer.valueOf(0);
		try
		{
			List<Predicate> restricciones = new ArrayList<Predicate>();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Integer> criteria = cb.createQuery(Integer.class);

			Root<TemperaturaEntity> temperaturaRoot = criteria.from(TemperaturaEntity.class);

			//QUERY APROX: (SELECT MAXIMA FROM TEMPERATURA WHERE FECHA = PARAM)
			criteria.select(temperaturaRoot.get(TemperaturaEntity_.maxima).alias("uvMax"));

			restricciones.add(cb.equal(temperaturaRoot.get(TemperaturaEntity_.fecha), fecha));

			criteria.where(cb.and(restricciones.toArray(new Predicate[restricciones.size()])));

			tempMaxima = em.createQuery(criteria).getSingleResult();

			return tempMaxima;
		}catch(NoResultException ex)
		{
			System.out.println("Excepcion en cargarTempMaxima");
			ex.printStackTrace();
			return tempMaxima;
		}
	}

	@Override
	public int cargarTempMinima(Date fecha)
	{
		Integer tempMinima = Integer.valueOf(0);
		try
		{
			List<Predicate> restricciones = new ArrayList<Predicate>();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Integer> criteria = cb.createQuery(Integer.class);

			Root<TemperaturaEntity> temperaturaRoot = criteria.from(TemperaturaEntity.class);

			//QUERY APROX: (SELECT MINIMA FROM TEMPERATURA WHERE FECHA = PARAM)
			criteria.select(temperaturaRoot.get(TemperaturaEntity_.minima).alias("uvMax"));

			restricciones.add(cb.equal(temperaturaRoot.get(TemperaturaEntity_.fecha), fecha));

			criteria.where(cb.and(restricciones.toArray(new Predicate[restricciones.size()])));

			tempMinima = em.createQuery(criteria).getSingleResult();

			return tempMinima;
		}catch(NoResultException ex)
		{
			System.out.println("Excepcion en cargarTempMinima");
			ex.printStackTrace();
			return tempMinima;
		}
	}

	@Override
	public Map<String, Integer> cargarTempIntervalos(Date fecha)
	{
		Map<String, Integer> mapTempIntervalos = new HashMap<String, Integer>();
		try
		{
			List<Predicate> restricciones = new ArrayList<Predicate>();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = cb.createTupleQuery();

			Root<TemperaturaIntervalosEntity> tempIntRoot = criteria.from(TemperaturaIntervalosEntity.class);

			// QUERY APROX: SELECT HORA, VALORHORA FROM TEMPERATURA_INTERVALOS WHERE FECHA = PARAM
			criteria.multiselect(tempIntRoot.get(TemperaturaIntervalosEntity_.hora).alias("hora"),
					tempIntRoot.get(TemperaturaIntervalosEntity_.valorHora).alias("valorHora"));

			restricciones.add(cb.equal(tempIntRoot.get(TemperaturaIntervalosEntity_.fecha), fecha));

			criteria.where(cb.and(restricciones.toArray(new Predicate[restricciones.size()])));

			for(Tuple tupla : em.createQuery(criteria).getResultList())
			{
				String hora = tupla.get("hora", String.class);
				Integer valorHora = Integer.valueOf(0);

				if (tupla.get("valorHora") != null)
					valorHora = tupla.get("valorHora", Integer.class);

				mapTempIntervalos.put(hora, valorHora);
			}

			return mapTempIntervalos;
		}catch(NoResultException ex)
		{
			System.out.println("Excepcion en cargarTempIntervalos");
			ex.printStackTrace();
			return mapTempIntervalos;
		}
	}

	@Override
	public int cargarSensTermMaxima(Date fecha)
	{
		Integer sensTermMaxima = Integer.valueOf(0);
		try
		{
			List<Predicate> restricciones = new ArrayList<Predicate>();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Integer> criteria = cb.createQuery(Integer.class);

			Root<SensacionTermicaEntity> sensacionTermicaRoot = criteria.from(SensacionTermicaEntity.class);

			//QUERY APROX: (SELECT MAXIMA FROM SENSACION_TERMICA WHERE FECHA = PARAM)
			criteria.select(sensacionTermicaRoot.get(SensacionTermicaEntity_.maxima).alias("uvMax"));

			restricciones.add(cb.equal(sensacionTermicaRoot.get(SensacionTermicaEntity_.fecha), fecha));

			criteria.where(cb.and(restricciones.toArray(new Predicate[restricciones.size()])));

			sensTermMaxima = em.createQuery(criteria).getSingleResult();

			return sensTermMaxima;
		}catch(NoResultException ex)
		{
			System.out.println("Excepcion en cargarSensTermMaxima");
			ex.printStackTrace();
			return sensTermMaxima;
		}
	}

	@Override
	public int cargarSensTermMinima(Date fecha)
	{
		Integer sensTermMinima = Integer.valueOf(0);
		try
		{
			List<Predicate> restricciones = new ArrayList<Predicate>();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Integer> criteria = cb.createQuery(Integer.class);

			Root<SensacionTermicaEntity> sensacionTermicaRoot = criteria.from(SensacionTermicaEntity.class);

			//QUERY APROX: (SELECT MINIMA FROM SENSACION_TERMICA WHERE FECHA = PARAM)
			criteria.select(sensacionTermicaRoot.get(SensacionTermicaEntity_.minima).alias("uvMax"));

			restricciones.add(cb.equal(sensacionTermicaRoot.get(SensacionTermicaEntity_.fecha), fecha));

			criteria.where(cb.and(restricciones.toArray(new Predicate[restricciones.size()])));

			sensTermMinima = em.createQuery(criteria).getSingleResult();

			return sensTermMinima;
		}catch(NoResultException ex)
		{
			System.out.println("Excepcion en cargarSensTermMinima");
			ex.printStackTrace();
			return sensTermMinima;
		}
	}

	@Override
	public Map<String, Integer> cargarSensTermIntervalos(Date fecha)
	{
		Map<String, Integer> mapSensTermIntervalos = new HashMap<String, Integer>();
		try
		{
			List<Predicate> restricciones = new ArrayList<Predicate>();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = cb.createTupleQuery();

			Root<SensacionTermicaIntervalosEntity> sensTermIntRoot = criteria.from(SensacionTermicaIntervalosEntity.class);

			// QUERY APROX: SELECT HORA, VALORHORA FROM SENSACION_TERMICA_INTERVALOS WHERE FECHA = PARAM
			criteria.multiselect(sensTermIntRoot.get(SensacionTermicaIntervalosEntity_.hora).alias("hora"),
					sensTermIntRoot.get(SensacionTermicaIntervalosEntity_.valorHora).alias("valorHora"));

			restricciones.add(cb.equal(sensTermIntRoot.get(SensacionTermicaIntervalosEntity_.fecha), fecha));

			criteria.where(cb.and(restricciones.toArray(new Predicate[restricciones.size()])));

			for(Tuple tupla : em.createQuery(criteria).getResultList())
			{
				String hora = tupla.get("hora", String.class);
				Integer valorHora = Integer.valueOf(0);

				if (tupla.get("valorHora") != null)
					valorHora = tupla.get("valorHora", Integer.class);

				mapSensTermIntervalos.put(hora, valorHora);
			}

			return mapSensTermIntervalos;
		}catch(NoResultException ex)
		{
			System.out.println("Excepcion en cargarSensTermIntervalos");
			ex.printStackTrace();
			return mapSensTermIntervalos;
		}
	}

	@Override
	public int cargarHumRelMaxima(Date fecha)
	{
		Integer humRelMaxima = Integer.valueOf(0);
		try
		{
			List<Predicate> restricciones = new ArrayList<Predicate>();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Integer> criteria = cb.createQuery(Integer.class);

			Root<HumedadRelativaEntity> humedadRelativaRoot = criteria.from(HumedadRelativaEntity.class);

			//QUERY APROX: (SELECT MAXIMA FROM HUMEDAD_RELATIVA WHERE FECHA = PARAM)
			criteria.select(humedadRelativaRoot.get(HumedadRelativaEntity_.maxima).alias("uvMax"));

			restricciones.add(cb.equal(humedadRelativaRoot.get(HumedadRelativaEntity_.fecha), fecha));

			criteria.where(cb.and(restricciones.toArray(new Predicate[restricciones.size()])));

			humRelMaxima = em.createQuery(criteria).getSingleResult();

			return humRelMaxima;
		}catch(NoResultException ex)
		{
			System.out.println("Excepcion en cargarHumRelMaxima");
			ex.printStackTrace();
			return humRelMaxima;
		}
	}

	@Override
	public int cargarHumRelMinima(Date fecha)
	{
		Integer humRelMinima = Integer.valueOf(0);
		try
		{
			List<Predicate> restricciones = new ArrayList<Predicate>();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Integer> criteria = cb.createQuery(Integer.class);

			Root<HumedadRelativaEntity> humedadRelativaRoot = criteria.from(HumedadRelativaEntity.class);

			//QUERY APROX: (SELECT MINIMA FROM HUMEDAD_RELATIVA WHERE FECHA = PARAM)
			criteria.select(humedadRelativaRoot.get(HumedadRelativaEntity_.minima).alias("uvMax"));

			restricciones.add(cb.equal(humedadRelativaRoot.get(HumedadRelativaEntity_.fecha), fecha));

			criteria.where(cb.and(restricciones.toArray(new Predicate[restricciones.size()])));

			humRelMinima = em.createQuery(criteria).getSingleResult();

			return humRelMinima;
		}catch(NoResultException ex)
		{
			System.out.println("Excepcion en cargarHumRelMinima");
			ex.printStackTrace();
			return humRelMinima;
		}
	}

	@Override
	public Map<String, Integer> cargarHumRelIntervalos(Date fecha)
	{
		Map<String, Integer> mapHumRelIntervalos = new HashMap<String, Integer>();
		try
		{
			List<Predicate> restricciones = new ArrayList<Predicate>();

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Tuple> criteria = cb.createTupleQuery();

			Root<HumedadRelativaIntervalosEntity> humRelIntRoot = criteria.from(HumedadRelativaIntervalosEntity.class);

			// QUERY APROX: SELECT HORA, VALORHORA FROM HUMEDAD_RELATIVA_INTERVALOS WHERE FECHA = PARAM
			criteria.multiselect(humRelIntRoot.get(HumedadRelativaIntervalosEntity_.hora).alias("hora"),
					humRelIntRoot.get(HumedadRelativaIntervalosEntity_.valorHora).alias("valorHora"));

			restricciones.add(cb.equal(humRelIntRoot.get(HumedadRelativaIntervalosEntity_.fecha), fecha));

			criteria.where(cb.and(restricciones.toArray(new Predicate[restricciones.size()])));

			for(Tuple tupla : em.createQuery(criteria).getResultList())
			{
				String hora = tupla.get("hora", String.class);
				Integer valorHora = Integer.valueOf(0);

				if (tupla.get("valorHora") != null)
					valorHora = tupla.get("valorHora", Integer.class);

				mapHumRelIntervalos.put(hora, valorHora);
			}

			return mapHumRelIntervalos;
		}catch(NoResultException ex)
		{
			System.out.println("Excepcion en cargarHumRelIntervalos");
			ex.printStackTrace();
			return mapHumRelIntervalos;
		}
	}
}
