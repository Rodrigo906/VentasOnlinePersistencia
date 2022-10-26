package ar.unrn.tp.jpa.servicios;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.unrn.tp.api.CacheService;
import ar.unrn.tp.modelo.Venta;
import redis.clients.jedis.Jedis;

public class CacheServiceJedis implements CacheService{

	private Jedis jedis;
	private Gson gson;
	private String recurso;
	
	public CacheServiceJedis(String host, int port, String recurso) {
		this.jedis = new Jedis(host, port);
		this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		this.recurso = recurso;
	}
	
	@Override
	public void registrarVenta(Venta venta, long idCliente) {
		String ventaJson = this.obtenerJson(venta);
		String cola = formatearNombre(idCliente);
		this.jedis.lpush(cola, ventaJson);
		if (this.jedis.llen(cola) > 3)
			this.jedis.rpop(cola);
	}

	@Override
	public void cargarVentas(List<Venta> ventas, long idCliente) {
		String cola =formatearNombre(idCliente);
		for (Venta venta : ventas) {
			this.jedis.lpush(cola, obtenerJson(venta));
		}	
	}

	@Override
	public List<Venta> obtenerVentas(long idCliente) {
		String cola = formatearNombre(idCliente);
		List<Venta> ventas = new ArrayList<Venta>();
		List<String> ventasJson = jedis.lrange(cola, 0, 2);
		for (String stringVenta : ventasJson) {
			ventas.add(this.obtenerVentaDeJson(stringVenta));
		}
		return ventas;
	}
	
	private String obtenerJson(Venta venta) {
		return gson.toJson(venta);
	}
	
	private Venta obtenerVentaDeJson(String json) {
	    return gson.fromJson(json, Venta.class);
	}
	
	private String formatearNombre(long id) {
		return recurso +":"+ id;
	}
}
