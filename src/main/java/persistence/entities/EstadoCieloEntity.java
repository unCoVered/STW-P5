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

@Entity
@Table(name = "estado_cielo", schema = "", catalog = "stw_weather")
public class EstadoCieloEntity
{
	@Id
	@Column(name = "codigo")
	private int codigo;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "periodo")
	private String periodo;

	@Column(name = "descripcion")
	private String descripcion;

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

	public String getPeriodo()
	{
		return periodo;
	}

	public void setPeriodo(String periodo)
	{
		this.periodo = periodo;
	}

	public String getDescripcion()
	{
		return descripcion;
	}

	public void setDescripcion(String descripcion)
	{
		this.descripcion = descripcion;
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

		EstadoCieloEntity that = (EstadoCieloEntity) o;

		if (codigo != that.codigo)
		{
			return false;
		}
		if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null)
		{
			return false;
		}
		if (periodo != null ?
				!periodo.equals(that.periodo) :
				that.periodo != null)
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
		result = 31 * result + (periodo != null ? periodo.hashCode() : 0);
		return result;
	}
}
