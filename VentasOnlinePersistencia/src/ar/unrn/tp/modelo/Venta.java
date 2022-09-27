package ar.unrn.tp.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Venta {

	@Id
	@GeneratedValue
	private long id;
	private Date fecha;
	private String codigo;
	@ManyToOne   
	private Cliente cliente;
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "id_venta")
	private List<ProductoComprado> productos;
	private double montoTotal;
	@Transient
	private ConversorDeFechas conversorFechas;
	
	protected Venta() {}
	
	public Venta(LocalDateTime fecha, Cliente cliente, ArrayList<ProductoSeleccionado> productos, double montoTotal, String codigo) {
		this.conversorFechas = new ConversorDeFechas();
		this.productos = new ArrayList<ProductoComprado>();
		this.fecha = this.conversorFechas.convertirADate(fecha);
		this.cliente = cliente;
		this.productos = this.cargarProductos(productos);
		this.montoTotal = montoTotal;
		this.codigo = codigo;
	}
	
	public ArrayList<ProductoComprado> cargarProductos(ArrayList<ProductoSeleccionado> productos) {
		ArrayList<ProductoComprado> prodComprados = new ArrayList<ProductoComprado>();
		for (ProductoSeleccionado ps : productos)
		{
			prodComprados.add(new ProductoComprado(ps.getProducto().getCodigo(), ps.getProducto().getDescripcion(), 
					ps.getProducto().getPrecio(), ps.getCantidad()));
		}
		return prodComprados;
	}

	@Override
	public String toString() {
		return "Venta: \n [Fecha = " + fecha
				+ "\n" + cliente.toString() 
				+ "\n Productos =" + "\n" + productos.toString() 
				+ "\n MontoTotal = " + montoTotal+"]";
	}

	private Date getFecha() {
		return fecha;
	}

	private void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	private String getCodigo() {
		return codigo;
	}

	private void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	private Cliente getCliente() {
		return cliente;
	}

	private void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	private List<ProductoComprado> getProductos() {
		return productos;
	}

	private void setProductos(List<ProductoComprado> productos) {
		this.productos = productos;
	}

	private double getMontoTotal() {
		return montoTotal;
	}

	private void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}

	private ConversorDeFechas getConversorFechas() {
		return conversorFechas;
	}

	private void setConversorFechas(ConversorDeFechas conversorFechas) {
		this.conversorFechas = conversorFechas;
	}
	
	
}
