/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 04-01-15
 * Fecha modificacion:
 * Tiempo invertido:
 */
package persistence.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "temperatura", schema = "", catalog = "stw_weather")
public class TemperaturaEntity
{
	@Id
	@Column(name = "codigo")
	private int codigo;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "maxima")
	private int maxima;

	@Column(name = "minima")
	private int minima;

	@OneToMany(mappedBy = "temperatura")
	private List<TemperaturaIntervalosEntity> temperaturaIntervalos;

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

	public int getMaxima()
	{
		return maxima;
	}

	public void setMaxima(int maxima)
	{
		this.maxima = maxima;
	}

	public int getMinima()
	{
		return minima;
	}

	public void setMinima(int minima)
	{
		this.minima = minima;
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

		TemperaturaEntity that = (TemperaturaEntity) o;

		if (codigo != that.codigo)
		{
			return false;
		}
		if (maxima != that.maxima)
		{
			return false;
		}
		if (minima != that.minima)
		{
			return false;
		}
		if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null)
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
		result = 31 * result + maxima;
		result = 31 * result + minima;
		return result;
	}
}
