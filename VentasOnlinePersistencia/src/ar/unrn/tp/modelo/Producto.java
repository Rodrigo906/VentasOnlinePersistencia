package ar.unrn.tp.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Producto {
	
	@Id
	@GeneratedValue
	private long id;
	@Column(unique=true)
	private String codigo;
	private String descripcion;
	private String categoria;
	private float precio;
	private String marca;
	@Version
	private long version;
	
	protected Producto() {}
	
	public Producto(String codigo, String descripcion, String categoria, String marca, float precio) {
		if (codigo == null)
			throw new RuntimeException("Codigo vacio");
		if (marca == null || marca.isEmpty())
			throw new RuntimeException("Marca vacia");
		if (categoria == null || categoria.isEmpty())
			throw new RuntimeException("Categoria vacia");
		if (Float.valueOf(precio) == null)
			throw new RuntimeException("Precio vacio");
		if (descripcion == null || descripcion.isEmpty())
			throw new RuntimeException("Descripcion vacia");
		
		this.codigo = codigo;
		this.marca = marca;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.precio = precio;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		if (codigo == null)
			throw new RuntimeException("Codigo vacio");
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		if (categoria == null || categoria.isEmpty())
			throw new RuntimeException("Categoria vacia");
		this.categoria = categoria;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		if (Float.valueOf(precio) == null)
			throw new RuntimeException("Precio vacio");
		this.precio = precio;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		if (marca == null || marca.isEmpty())
			throw new RuntimeException("Marca vacia");
		this.marca = marca;
	}

	public long getId() {
		return this.id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getVersion() {
		return this.version;
	}
	
	public void setVersion(long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", codigo=" + codigo + ", descripcion=" + descripcion + ", categoria=" + categoria
				+ ", precio=" + precio + ", marca=" + marca + ", version=" + version + "]";
	}
	
}
