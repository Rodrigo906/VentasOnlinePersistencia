package ar.unrn.tp.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class GestorPromociones {

	@Id
	@GeneratedValue
	private long id;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Promocion> promocionesProducto;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Promocion> promocionesCompra;
	
	public GestorPromociones() {
		this.promocionesProducto = new ArrayList<Promocion>();
		this.promocionesCompra = new ArrayList<Promocion>();
	}
	
	public boolean agregarPromocionProducto(Promocion promo) {
		if (this.promoExiste(promo, this.promocionesProducto))
			throw new RuntimeException("Ya existe una promocion vigente para este producto");
		
		return this.promocionesProducto.add(promo);
	}
	
	public boolean quitarPromocionProducto(Promocion promo) {
		return this.promocionesProducto.remove(promo);
	}
	
	public boolean agregarPromocionCompra(Promocion promo) {
		if (this.promoExiste(promo, this.promocionesCompra))
			throw new RuntimeException("Ya existe una promocion vigente para esta tarjeta");
		
		return this.promocionesCompra.add(promo);
	}
	
	public boolean quitarPromocionCompra(Promocion promo) {
		return this.promocionesCompra.remove(promo);
	}
	
	public boolean noSeSuperponen(Date diaDesde, Date diaHasta, List<Promocion> promociones) {
		for (Promocion promo : promociones) {	
			if (diaDesde.before(promo.getDiaHasta()) && promo.getDiaDesde().before(diaHasta))
				return true;
		}
		return false;
	}
	
	public float aplicarDescuentoDeProducto(List<ProductoSeleccionado> listaProductos) {
		float montoTotal = 0;
		float precioProductos = 0;
		for(ProductoSeleccionado ps : listaProductos) {
			//Por toda la cantidad de productos del mismo tipo comprados
			precioProductos = ps.calcularPrecio();
			
			//Buscar si el producto tiene alguna promocion vigente
			for (Promocion promo : this.promocionesProducto) {
				if (promo.tieneDescuento(ps.getProducto().getMarca()))
					precioProductos = promo.aplicarDescuento(precioProductos);
				
			}
			montoTotal = montoTotal + precioProductos;
		}
		return montoTotal;
	}
	
	public float aplicarDescuentoCompra(float monto, String tarjeta) {
		for (Promocion promo : this.promocionesCompra) {
			if (promo.tieneDescuento(tarjeta))
				monto = promo.aplicarDescuento(monto);
		}
	return monto;
	}
	
	public float calcularMontoTotal(String tarjeta, List<ProductoSeleccionado> productos) {
		float montoTotal = this.aplicarDescuentoDeProducto(productos);
		montoTotal = this.aplicarDescuentoCompra(montoTotal, tarjeta);
		return montoTotal;
	}
	
	public boolean promoExiste(Promocion promo, List<Promocion> promociones) {
		for(Promocion p : promociones) {
			if (p.esPromoSuperpuesta(promo))
				return true;	
		}
		return false;
	}
	
	public List<Promocion> getPromociones (){
		return this.promocionesProducto;
	}
}
