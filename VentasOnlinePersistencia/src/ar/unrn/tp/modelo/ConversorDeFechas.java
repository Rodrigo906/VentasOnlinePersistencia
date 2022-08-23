package ar.unrn.tp.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class ConversorDeFechas {
		
	public LocalDate convertirALocalDate(Date fecha)
	{
		return fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public Date convertirADate(LocalDate fecha)
	{
		return Date.from(fecha.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
	
	public LocalDateTime convertirALocalDateTime(Date fecha)
	{
		return fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
	public Date convertirADate(LocalDateTime fecha)
	{
		return Date.from(fecha.atZone(ZoneId.systemDefault()).toInstant());
	}
}
