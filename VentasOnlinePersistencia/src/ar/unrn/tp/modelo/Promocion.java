package ar.unrn.tp.modelo;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Transient;

@Entity
@Inheritance
@DiscriminatorColumn(name = "tipo_promocion")
public abstract class Promocion {
	
	@Id
	@GeneratedValue
	private long id;
	private Date diaDesde;
	private Date diaHasta;
	private float porcentajeDescuento;
	@Transient
	private ConversorDeFechas conversorFechas;
	
	public Promocion () {
		
	}
	
	public Promocion(LocalDate diaDesde, LocalDate diaHasta, float porcentajeDescuento) 
	{
		this.conversorFechas = new ConversorDeFechas();
		this.diaDesde = this.conversorFechas.convertirADate(diaDesde);
		this.diaHasta = this.conversorFechas.convertirADate(diaHasta);
		this.porcentajeDescuento = porcentajeDescuento;
	}
	
	public boolean promocionEstaVigente() {
		Date fechaActual = new Date();
		if (fechaActual.after(this.diaDesde) && fechaActual.before(this.diaHasta))
			return true;

		return false;
	}

	public Date getDiaDesde() {
		return diaDesde;
	}

	private void setDiaDesde(Date diaDesde) {
		this.diaDesde = diaDesde;
	}

	public abstract String getMarca();
	
	public Date getDiaHasta() {
		return diaHasta;
	}

	private void setDiaHasta(Date diaHasta) {
		this.diaHasta = diaHasta;
	}
	
	public abstract float aplicarDescuento(float monto);
	
	public abstract boolean tieneDescuento(String marca);

	public abstract boolean esPromoSuperpuesta(Promocion promo);

	public float getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	private void setPorcentajeDescuento(float porcentajeDescuento) {
		this.porcentajeDescuento = porcentajeDescuento;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		if (diaDesde == null) {
			if (other.diaDesde != null)
				return false;
		} else if (!diaDesde.equals(other.diaDesde))
			return false;
		if (diaHasta == null) {
			if (other.diaHasta != null)
				return false;
		} else if (!diaHasta.equals(other.diaHasta))
			return false;
		if (Float.floatToIntBits(porcentajeDescuento) != Float.floatToIntBits(other.porcentajeDescuento))
			return false;
		return true;
	}
	
	
}
