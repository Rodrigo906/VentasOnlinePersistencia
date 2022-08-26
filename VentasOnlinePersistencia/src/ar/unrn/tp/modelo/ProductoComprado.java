package ar.unrn.tp.modelo;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ProductoComprado {

	@Id
	@GeneratedValue
	private long id;
	private String codigo;
	private String descripcion;
	private float precio;
	
	public ProductoComprado(String codigo, String descripcion, float precio) 
	{
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.precio = precio;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "descripcion = " + descripcion +", "+ "codigo = " + codigo + ", " + "precio=" + precio + "\n";
	}
}
