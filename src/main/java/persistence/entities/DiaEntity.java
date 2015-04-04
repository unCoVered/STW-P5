/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 04-01-15
 * Fecha modificacion:
 * Tiempo invertido:
 */
package persistence.entities;

import parser.datos.Temperatura;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dia", schema = "", catalog = "stw_weather")
public class DiaEntity
{
	@Id
	@Column(name = "codigo")
	private int codigo;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "uv_max")
	private int uvMax;



	@OneToOne
	@JoinColumn(name = "fk_prob_precipitacion")
	private ProbPrecipitacionEntity probPrecipitacion;

	@OneToOne
	@JoinColumn(name = "fk_cota_nieve_prov")
	private CotaNieveProvEntity cotaNieveProv;

	@OneToOne
	@JoinColumn(name = "fk_estado_cielo")
	private EstadoCieloEntity estadoCielo;

	@OneToOne
	@JoinColumn(name = "fk_viento")
	private VientoEntity viento;

	@OneToOne
	@JoinColumn(name = "fk_racha_max")
	private RachaMaxEntity rachaMax;

	@OneToOne
	@JoinColumn(name = "fk_temperatura")
	private TemperaturaEntity temperatura;

	public CotaNieveProvEntity getCotaNieveProv()
	{
		return cotaNieveProv;
	}

	public void setCotaNieveProv(CotaNieveProvEntity cotaNieveProv)
	{
		this.cotaNieveProv = cotaNieveProv;
	}

	@OneToOne
	@JoinColumn(name = "fk_sensacion_termica")
	private SensacionTermicaEntity sensacionTermica;

	@OneToOne
	@JoinColumn(name = "fk_humedad_relativa")
	private HumedadRelativaEntity humedadRelativa;

	public int getCodigo()
	{
		return codigo;
	}

	public void setCodigo(int codigo)
	{
		this.codigo = codigo;
	}

	public Date getFecha()
	{
		return fecha;
	}

	public void setFecha(Date fecha)
	{
		this.fecha = fecha;
	}

	public int getUvMax()
	{
		return uvMax;
	}

	public void setUvMax(int uvMax)
	{
		this.uvMax = uvMax;
	}

	public ProbPrecipitacionEntity getProbPrecipitacion()
	{
		return probPrecipitacion;
	}

	public void setProbPrecipitacion(ProbPrecipitacionEntity probPrecipitacion)
	{
		this.probPrecipitacion = probPrecipitacion;
	}

	public EstadoCieloEntity getEstadoCielo()
	{
		return estadoCielo;
	}

	public void setEstadoCielo(EstadoCieloEntity estadoCielo)
	{
		this.estadoCielo = estadoCielo;
	}

	public VientoEntity getViento()
	{
		return viento;
	}

	public void setViento(VientoEntity viento)
	{
		this.viento = viento;
	}

	public RachaMaxEntity getRachaMax()
	{
		return rachaMax;
	}

	public void setRachaMax(RachaMaxEntity rachaMax)
	{
		this.rachaMax = rachaMax;
	}

	public TemperaturaEntity getTemperatura()
	{
		return temperatura;
	}

	public void setTemperatura(TemperaturaEntity temperatura)
	{
		this.temperatura = temperatura;
	}

	public SensacionTermicaEntity getSensacionTermica()
	{
		return sensacionTermica;
	}

	public void setSensacionTermica(SensacionTermicaEntity sensacionTermica)
	{
		this.sensacionTermica = sensacionTermica;
	}

	public HumedadRelativaEntity getHumedadRelativa()
	{
		return humedadRelativa;
	}

	public void setHumedadRelativa(HumedadRelativaEntity humedadRelativa)
	{
		this.humedadRelativa = humedadRelativa;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}

		DiaEntity diaEntity = (DiaEntity) o;

		if (codigo != diaEntity.codigo)
		{
			return false;
		}
		if (uvMax != diaEntity.uvMax)
		{
			return false;
		}
		if (fecha != null ?
				!fecha.equals(diaEntity.fecha) :
				diaEntity.fecha != null)
		{
			return false;
		}

		return true;
	}

	@Override
	public int hashCode()
	{
		int result = codigo;
		result = 31 * result + (fecha != null ? fecha.hashCode() : 0);
		result = 31 * result + uvMax;
		return result;
	}
}
