package ar.unrn.tp.modelo;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PC")
public class PromocionDeCompra extends Promocion{

	private String marcaTarjeta;
	
	protected PromocionDeCompra() {
		
	}
	
	public PromocionDeCompra(LocalDate diaDesde, LocalDate diaHasta, String marcaTarjeta, float porcentajeDescuento)
	{
		super(diaDesde, diaHasta, porcentajeDescuento);
		this.marcaTarjeta = marcaTarjeta;
	}
	
	@Override
	public float aplicarDescuento(float monto) {
		return monto - monto * this.getPorcentajeDescuento();
	}

	@Override
	public boolean tieneDescuento(String marcaTarjeta) {
		if(this.marcaTarjeta.equals(marcaTarjeta) && this.promocionEstaVigente())
			return true;
		
		return false;
	}
	
	public String getMarca() {
		return this.marcaTarjeta;
	}
	
	private boolean seSuperponen(Promocion promo) {
		PromocionDeCompra pc = (PromocionDeCompra) promo;
		Date diaDesdeNueva = pc.getDiaDesde();
		Date diaHastaNueva = pc.getDiaHasta();
		Date diaDesdeExistente = this.getDiaDesde();
		Date diaHastaExistente = this.getDiaHasta();
		boolean seSuperponen = false;
		
		if(diaDesdeNueva.before(diaHastaExistente) && diaDesdeExistente.before(diaHastaNueva))
			seSuperponen = true;
	
		if(diaDesdeNueva.compareTo(diaHastaExistente) == 0 || diaDesdeExistente.compareTo(diaHastaNueva) == 0)
			seSuperponen = true;

		return seSuperponen;
	}
	
	public boolean esPromoSuperpuesta(Promocion promo) {
		PromocionDeCompra pc = (PromocionDeCompra) promo;
		if (pc.getMarca() == this.getMarca() && this.seSuperponen(promo))
			return true;
		
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		PromocionDeCompra other = (PromocionDeCompra) obj;
		if (marcaTarjeta == null) {
			if (other.marcaTarjeta != null)
				return false;
		} else if (!marcaTarjeta.equals(other.marcaTarjeta))
			return false;
		return true;
	}	
	
}
