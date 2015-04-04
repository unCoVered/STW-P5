/**
 * Autor: Alejandro Galvez
 * NIP: 631211
 * Fecha Creacion: 04-01-15
 * Fecha modificacion:
 * Tiempo invertido:
 */
package persistence.entities;

import parser.datos.HumedadRelativa;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "humedad_relativa_intervalos", schema = "",
		catalog = "stw_weather")
public class HumedadRelativaIntervalosEntity
{
	@Id
	@Column(name = "codigo")
	private int codigo;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha")
	private Date fecha;

	@Column(name = "hora")
	private String hora;

	@Column(name = "valor_hora")
	private Integer valorHora;

	@ManyToOne
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

	public String getHora()
	{
		return hora;
	}

	public void setHora(String hora)
	{
		this.hora = hora;
	}

	public Integer getValorHora()
	{
		return valorHora;
	}

	public void setValorHora(Integer valorHora)
	{
		this.valorHora = valorHora;
	}

	public void setHumedadRelativa(HumedadRelativaEntity humedadRelativaEntity)
	{
		this.humedadRelativa = humedadRelativaEntity;
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

		HumedadRelativaIntervalosEntity that = (HumedadRelativaIntervalosEntity) o;

		if (codigo != that.codigo)
		{
			return false;
		}
		if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null)
		{
			return false;
		}
		if (hora != null ? !hora.equals(that.hora) : that.hora != null)
		{
			return false;
		}
		if (valorHora != null ?
				!valorHora.equals(that.valorHora) :
				that.valorHora != null)
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
		result = 31 * result + (hora != null ? hora.hashCode() : 0);
		result = 31 * result + (valorHora != null ? valorHora.hashCode() : 0);
		return result;
	}
}
