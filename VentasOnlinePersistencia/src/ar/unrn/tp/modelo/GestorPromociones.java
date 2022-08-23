package ar.unrn.tp.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GestorPromociones {

	private List<Promocion> promocionesProducto;
	private List<Promocion> promocionesCompra;
	
	public GestorPromociones() {
		this.promocionesProducto = new ArrayList<Promocion>();
		this.promocionesCompra = new ArrayList<Promocion>();
	}
	
	public boolean agregarPromocionProducto(Promocion promo) {
		if (!this.promoExiste(promo, this.promocionesProducto))
		{
			return this.promocionesProducto.add(promo);
		}
		else
			throw new RuntimeException("Ya existe una promocione vigente para este producto");
	}
	
	public boolean quitarPromocionProducto(Promocion promo) {
		return this.promocionesProducto.remove(promo);
	}
	
	public boolean agregarPromocionCompra(Promocion promo) {
		if (!this.promoExiste(promo, this.promocionesCompra))
		{
			return this.promocionesCompra.add(promo);
		}
		return false;
	}
	
	public boolean quitarPromocionCompra(Promocion promo) {
		return this.promocionesCompra.remove(promo);
	}
	
	public boolean noSeSuperponen(Date diaDesde, Date diaHasta, List<Promocion> promociones) {
		
		for (Promocion promo : promociones)
		{	
			if (diaDesde.before(promo.getDiaHasta()) && promo.getDiaDesde().before(diaHasta))
			{
				return true;
			}
		}
		return false;
	}
	
	public double aplicarDescuentoDeProducto(List<ProductoSeleccionado> listaProductos) {
		double montoTotal = 0;
		double precioProductos = 0;
		for(ProductoSeleccionado ps : listaProductos)
		{
			//Por toda la cantidad de productos del mismo tipo comprados
			precioProductos = ps.calcularPrecio();
			
			//Buscar si el producto tiene alguna promocion vigente
			for (Promocion promo : this.promocionesProducto)
			{
				if (promo.tieneDescuento(ps.getProducto().getMarca()))
				{
					precioProductos = promo.aplicarDescuento(precioProductos);
				}
			}
			montoTotal = montoTotal + precioProductos;
		}
		return montoTotal;
	}
	
	public double aplicarDescuentoCompra(double monto, String tarjeta) {
		for (Promocion promo : this.promocionesCompra)
		{
			if (promo.tieneDescuento(tarjeta))
			{
				monto = promo.aplicarDescuento(monto);
			}
		}
	return monto;
	}
	
	public double calcularMontoTotal(String tarjeta, List<ProductoSeleccionado> productos) {
		double montoTotal = this.aplicarDescuentoDeProducto(productos);
		montoTotal = this.aplicarDescuentoCompra(montoTotal, tarjeta);
		return montoTotal;
	}
	
	public boolean promoExiste(Promocion promo, List<Promocion> promociones) {
		for(Promocion p : promociones)
		{
			if (p.esPromoSuperpuesta(promo))
			{
				return true;
			}
		}
		return false;
	}
	
	public List<Promocion> getPromociones (){
		return this.promocionesProducto;
	}
}
