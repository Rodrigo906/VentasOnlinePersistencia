package ar.unrn.tp.modelo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.google.gson.annotations.Expose;

@Entity
public class ProductoComprado {

	@Id
	@GeneratedValue
	@Expose
	private long id;
	@Expose
	private String codigo;
	@Expose
	private String descripcion;
	@Expose
	private float precio;
	@Expose
	private int cantidad;
	
	protected ProductoComprado() {
		
	}
	
	public ProductoComprado(String codigo, String descripcion, float precio, int cantidad) 
	{
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	private String getCodigo() {
		return codigo;
	}

	private void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	private String getDescripcion() {
		return descripcion;
	}

	private void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	private float getPrecio() {
		return precio;
	}

	private void setPrecio(float precio) {
		this.precio = precio;
	}
	
	private void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	private int getCantidad() {
		return this.cantidad;
	}

	@Override
	public String toString() {
		return "descripcion = " + descripcion +", "+ "codigo = " + codigo + ", " + "precio=" + precio + ", "+ "cantidad= " + cantidad +"\n";
	}
}
