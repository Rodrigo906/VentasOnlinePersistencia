package ar.unrn.tp.modelo;

import java.time.LocalDate;
import java.util.Date;

public class PromocionDeProducto extends Promocion{

	private String marcaProducto;
	
	public PromocionDeProducto(LocalDate diaDesde, LocalDate diaHasta, String marcaProducto, float porcentajeDescuento) 
	{
		super(diaDesde, diaHasta, porcentajeDescuento);
		this.marcaProducto = marcaProducto;
	}
	
	public double aplicarDescuento(double monto)
	{
		return monto - monto * this.getPorcentajeDescuento();
	}
	
	public boolean tieneDescuento(String marcaProducto)
	{
		boolean tieneDescuento = false;
		if (this.marcaProducto == marcaProducto && super.promocionEstaVigente())
		{
			tieneDescuento = true;
		}
		return tieneDescuento;
	}
	
	public String getMarca() {
		return this.marcaProducto;
	}
	
	public boolean seSuperponen(Promocion promo) {
		PromocionDeProducto pp = (PromocionDeProducto) promo;
		Date diaDesdeNueva = pp.getDiaDesde();
		Date diaHastaNueva = pp.getDiaHasta();
		Date diaDesdeExistente = this.getDiaDesde();
		Date diaHastaExistente = this.getDiaHasta();
		boolean seSuperponen = false;
		
		if(diaDesdeNueva.before(diaHastaExistente) && diaDesdeExistente.before(diaHastaNueva))
		{
			seSuperponen = true;
		}
		if(diaDesdeNueva.compareTo(diaHastaExistente) == 0 || diaDesdeExistente.compareTo(diaHastaNueva) == 0)
		{
			seSuperponen = true;
		}
		return seSuperponen;
	}
	
	public boolean esPromoSuperpuesta(Promocion promo) {
		PromocionDeProducto pp = (PromocionDeProducto) promo;
		if (pp.getMarca() == this.getMarca() && this.seSuperponen(promo))
		{
			return true;
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((marcaProducto == null) ? 0 : marcaProducto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromocionDeProducto other = (PromocionDeProducto) obj;
		if (marcaProducto == null) {
			if (other.marcaProducto != null)
				return false;
		} else if (!marcaProducto.equals(other.marcaProducto))
			return false;
		return true;
	}
	
	
}
