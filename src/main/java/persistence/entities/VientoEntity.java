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
@Table(name = "viento", schema = "", catalog = "stw_weather")
public class VientoEntity
{
	@Id @Column(name = "codigo")
	private int codigo;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "periodo")
	private String periodo;

	@Column(name = "direccion")
	private String direccion;

	@Column(name = "velocidad")
	private Integer velocidad;

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

	@Basic public String getDireccion()
	{
		return direccion;
	}

	public void setDireccion(String direccion)
	{
		this.direccion = direccion;
	}

	public Integer getVelocidad()
	{
		return velocidad;
	}

	public void setVelocidad(Integer velocidad)
	{
		this.velocidad = velocidad;
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

		VientoEntity that = (VientoEntity) o;

		if (codigo != that.codigo)
		{
			return false;
		}
		if (direccion != null ?
				!direccion.equals(that.direccion) :
				that.direccion != null)
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
		if (velocidad != null ?
				!velocidad.equals(that.velocidad) :
				that.velocidad != null)
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
		result = 31 * result + (direccion != null ? direccion.hashCode() : 0);
		result = 31 * result + (velocidad != null ? velocidad.hashCode() : 0);
		return result;
	}
}
