package ar.unrn.tp.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
public class Cliente {
	
	@Id
	@GeneratedValue
	private long id;
	private int dni;
	private String nombre;
	private String apellido;
	private String email;
	
	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_cliente")
	private List<TarjetaDeCredito> tarjetas;

	protected Cliente() {
		
	}
	
	public Cliente(int dni, String nombre, String apellido, String email) 
	{
		if (Integer.valueOf(dni) == null)
			throw new RuntimeException("DNI vacio");
		if (nombre == null || nombre.isEmpty())
			throw new RuntimeException("Nombre vacio");
		if (apellido == null || apellido.isEmpty())
			throw new RuntimeException("Apellido vacio");
		if (!emailEsValido(email) || email.isEmpty())
			throw new RuntimeException("Email incorrecto");
		
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.tarjetas = new ArrayList<TarjetaDeCredito>();
	}
	
	public Boolean emailEsValido (String email) {
		Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}

	@Override
	public String toString() {
		return "Cliente [nombre=" + nombre + ", apellido=" + apellido + "]";
	}
	
	public void agregarTarjeta (TarjetaDeCredito tarjeta) {
		this.tarjetas.add(tarjeta);
	}
	
	public void quitarTarjeta(TarjetaDeCredito tarjeta) {
		this.tarjetas.remove(tarjeta);
	}
	
	public List<TarjetaDeCredito> obtenerTarjetas() {
		return this.tarjetas;
	}
	
	public boolean tieneEstaTarjeta(TarjetaDeCredito tarjeta) {
		return this.obtenerTarjetas().contains(tarjeta);
	}
	
	public void modificarCliente(long idCliente, int dni, String nombre, String apellido, String email) {
		this.setDni(dni);
		this.setNombre(nombre);
		this.setApellido(apellido);
		this.setEmail(email);
	}

	private int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		if (Integer.valueOf(dni) == null)
			throw new RuntimeException("DNI vacio");
		this.dni = dni;
	}

	private String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null || nombre.isEmpty())
			throw new RuntimeException("Nombre vacio");
		this.nombre = nombre;
	}

	private String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		if (apellido == null || apellido.isEmpty())
			throw new RuntimeException("Apellido vacio");
		this.apellido = apellido;
	}

	private String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (!emailEsValido(email) || email.isEmpty())
			throw new RuntimeException("Email incorrecto");
		this.email = email;
	}
}
