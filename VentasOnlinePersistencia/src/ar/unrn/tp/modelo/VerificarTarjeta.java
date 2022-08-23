package ar.unrn.tp.modelo;

public interface VerificarTarjeta {

	public boolean estaActiva(int numeroTarjeta, String marca);
	
	public boolean tieneFondosSuficientes(int numeroTarjeta, String marca);
}
