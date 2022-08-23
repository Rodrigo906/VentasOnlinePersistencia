package ar.unrn.tp.servicio_tarjeta;

import ar.unrn.tp.modelo.VerificarTarjeta;

public class VerificacionDeTarjetaWeb implements VerificarTarjeta{

	public boolean estaActiva(int numeroTarjeta, String marca)
	{
		return true;
	}
	
	public boolean tieneFondosSuficientes(int numeroTarjeta, String marca)
	{
		return true;
	}
}
