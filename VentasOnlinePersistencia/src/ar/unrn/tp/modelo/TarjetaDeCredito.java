package ar.unrn.tp.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TarjetaDeCredito {
	@Id
	@GeneratedValue
	private long id;
	private int numero;
	private String marca;
	
	public TarjetaDeCredito(int numero, String marca) {
		this.numero = numero;
		this.marca = marca;
	}

	protected int getNumero() {
		return numero;
	}

	private void setNumero(int numero) {
		this.numero = numero;
	}

	public String getMarca() {
		return marca;
	}

	private void setMarca(String marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return "TarjetaDeCredito [numero=" + numero + ", marca=" + marca + "]";
	}
	
	
	

}
