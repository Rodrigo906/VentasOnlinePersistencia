package ar.unrn.tp.modelo;

import java.time.LocalDate;
import java.util.Date;

public abstract class Promocion {
	
	private Date diaDesde;
	private Date diaHasta;
	private float porcentajeDescuento;
	private ConversorDeFechas conversorFechas = new ConversorDeFechas();
	
	public Promocion(LocalDate diaDesde, LocalDate diaHasta, float porcentajeDescuento) 
	{
		this.diaDesde = this.conversorFechas.convertirADate(diaDesde);
		this.diaHasta = this.conversorFechas.convertirADate(diaHasta);
		this.porcentajeDescuento = porcentajeDescuento;
	}
	
	public boolean promocionEstaVigente() 
	{
		Date fechaActual = new Date();
		if (fechaActual.after(this.diaDesde) && fechaActual.before(this.diaHasta))
		{
			return true;
		}
		return false;
	}

	public Date getDiaDesde() {
		return diaDesde;
	}

	public void setDiaDesde(Date diaDesde) {
		this.diaDesde = diaDesde;
	}

	public Date getDiaHasta() {
		return diaHasta;
	}

	public void setDiaHasta(Date diaHasta) {
		this.diaHasta = diaHasta;
	}
	
	public abstract double aplicarDescuento(double monto);
	
	public abstract boolean tieneDescuento(String marca);

	public abstract boolean esPromoSuperpuesta(Promocion promo);

	public float getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	public void setPorcentajeDescuento(float porcentajeDescuento) {
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
