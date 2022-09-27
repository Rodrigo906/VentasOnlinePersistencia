package ar.unrn.tp.modelo;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class NextNumber {
	
	@Id
	@GeneratedValue
	private long id;
	private int anio;
	private int numActual;
	
	protected NextNumber(){}

	public NextNumber(int anio, int numActual) {
		this.anio = anio;
		this.numActual = numActual;
	}
	
	public String recuperarSiguiente() {
		LocalDate fechaActual = LocalDate.now();
		if(fechaActual.getYear() != this.anio)
			this.numActual = 0;
		
		this.numActual += 1; 
		return this.armarCodigo();
	}
	
	private int anio() {
		return this.anio;
	}
	
	private int numActual() {
		return this.numActual;
	}
	
	private String armarCodigo() {
		return numActual() + "-" + anio();
	}
	
	
	
}
