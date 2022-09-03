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
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Venta {

	@Id
	@GeneratedValue
	private long id;
	private Date fecha;
	@Embedded
	private Cliente cliente;
	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private List<ProductoComprado> productos;
	private double montoTotal;
	@Transient
	private ConversorDeFechas conversorFechas;
	
	public Venta(LocalDateTime fecha, Cliente cliente, ArrayList<ProductoSeleccionado> productos, double montoTotal) {
		this.conversorFechas = new ConversorDeFechas();
		this.productos = new ArrayList<>();
		this.fecha = this.conversorFechas.convertirADate(fecha);
		this.cliente = cliente;
		this.productos = this.cargarProductos(productos);
		this.montoTotal = montoTotal;
	}
	
	public ArrayList<ProductoComprado> cargarProductos(ArrayList<ProductoSeleccionado> productos) {
		ArrayList<ProductoComprado> prodComprados = new ArrayList<ProductoComprado>();
		for (ProductoSeleccionado ps : productos)
		{
			prodComprados.add(new ProductoComprado(ps.getProducto().getCodigo(), ps.getProducto().getDescripcion(), 
					ps.getProducto().getPrecio()));
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
	
	
}
