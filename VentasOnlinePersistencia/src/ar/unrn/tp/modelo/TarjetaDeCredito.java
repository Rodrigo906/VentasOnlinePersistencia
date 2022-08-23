package ar.unrn.tp.modelo;

import javax.persistence.Embeddable;

@Embeddable
public class TarjetaDeCredito {
	private int numero;
	private String marca;
	
	public TarjetaDeCredito(int numero, String marca) {
		this.numero = numero;
		this.marca = marca;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	

}
