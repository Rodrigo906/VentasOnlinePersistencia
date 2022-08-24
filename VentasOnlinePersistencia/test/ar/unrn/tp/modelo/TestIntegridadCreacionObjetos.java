package ar.unrn.tp.modelo;

import org.junit.Test;

public class TestIntegridadCreacionObjetos {

	@Test(expected = RuntimeException.class)
	public void creacionProductoConCategoriaNull() {
		Producto producto = new Producto("1234", "zapatilla", null, "Nike", 5000);
	}
	
	@Test(expected = RuntimeException.class)
	public void creacionProductoConCategoriaVacia() {
		Producto producto = new Producto("1234", "zapatilla", "", "Nike", 5000);
	}
	
	@Test(expected = RuntimeException.class)
	public void creacionProductoConDescripcionNull() {
		Producto producto = new Producto("1234", null, "Calzado", "Nike", 5000);
	}
	
	@Test(expected = RuntimeException.class)
	public void creacionProductoConDescripcionVacia() {
		Producto producto = new Producto("1234", "", "Calzado", "Nike", 5000);
	}
	
	@Test(expected = RuntimeException.class)
	public void creacionProductoConPrecioNull() {
		Float precio = null;
		Producto producto = new Producto("1234", "zapatilla", "Calzado", "Nike", precio);
	}
	
	@Test(expected = RuntimeException.class)
	public void creacionClienteConDniNull() {
		Integer dni = null;
		Cliente cliente = new Cliente(dni, "Rodrigo", "Calizaya", "rodrigocalizaya111999@gmail.com");
	}
	
	@Test(expected = RuntimeException.class)
	public void creacionClienteConNombreNull() {
		Cliente cliente = new Cliente(41478211, null, "Calizaya", "rodrigocalizaya111999@gmail.com");
	}
	
	@Test(expected = RuntimeException.class)
	public void creacionClienteConNombreVacio() {
		Cliente cliente = new Cliente(41478211, "","Calizaya", "rodrigocalizaya111999@gmail.com");
	}
	
	@Test(expected = RuntimeException.class)
	public void creacionClienteConApellidoNull() {
		Cliente cliente = new Cliente(41478211, "Rodrigo", null, "rodrigocalizaya111999@gmail.com");
	}
	
	@Test(expected = RuntimeException.class)
	public void creacionClienteConApellidoVacio() {
		Cliente cliente = new Cliente(41478211, "Rodrigo", "", "rodrigocalizaya111999@gmail.com");
	}
	
	@Test(expected = RuntimeException.class)
	public void creacionClienteConEmailInvalido() {
		Cliente cliente = new Cliente(41478211, "Rodrigo","Calizaya", "rodrigocalizaya111999gmail.com");
	}
	
	
}
