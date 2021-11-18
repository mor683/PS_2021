package es.unican.ps.supermercado.negocio;

import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.lang3.time.DateUtils;

import es.unican.ps.supermercado.negocio.dominio.Articulo;
import es.unican.ps.supermercado.negocio.dominio.LineaPedido;
import es.unican.ps.supermercado.negocio.dominio.Pedido;
import es.unican.ps.supermercado.negocio.dominio.Usuario;
import es.unican.ps.supermercado.negocio.interfaces.IArticulosDAO;
import es.unican.ps.supermercado.negocio.interfaces.IGestionComprasLocal;
import es.unican.ps.supermercado.negocio.interfaces.IGestionComprasRemote;
import es.unican.ps.supermercado.negocio.interfaces.IGestionPedidosLocal;
import es.unican.ps.supermercado.negocio.interfaces.IGestionPedidosRemote;
import es.unican.ps.supermercado.negocio.interfaces.IPedidosDAO;
import es.unican.ps.supermercado.negocio.interfaces.IRealizacionPedidosLocal;
import es.unican.ps.supermercado.negocio.interfaces.IRealizacionPedidosRemote;
import es.unican.ps.supermercado.negocio.interfaces.IUsuariosDAO;

@Stateless
public class GestionPedidos implements IGestionPedidosLocal, IGestionPedidosRemote, IGestionComprasLocal, IGestionComprasRemote, IRealizacionPedidosLocal, IRealizacionPedidosRemote {

	@EJB
	private IPedidosDAO pedidosDAO;

	@EJB
	private IUsuariosDAO usuariosDAO;

	@EJB
	private IArticulosDAO articulosDAO;

	public double entregarPedido(String ref, String dni) {
		Pedido pedido = pedidosDAO.pedidoPorReferencia(ref);
		Usuario usuario = usuariosDAO.usuarioPorDni(dni);

		// Si el pedido no pertenece al usuario indicado se muestra el error.
		if (!pedido.getUsuario().getDni().equals(dni)) {
			return -1;
		}

		// El sistema marca el pedido como entregado.
		pedido.setEstado("Entregado");
		pedidosDAO.modificarPedido(pedido);

		// El sistema actualiza el número de compras mensuales del usuario.
		int compras = usuario.getComprasMensuales();
		usuario.setComprasMensuales(compras+1);
		usuariosDAO.modificarUsuario(usuario);

		// Devuelve el precio del pedido.
		return pedido.getPrecio();
	}

	public Pedido procesarPedido() {
		Pedido pedido = null;

		// Lee el primer pedido pendiente y lo marca como procesado.
		if (!pedidosDAO.pedidos().isEmpty()) {
			pedido = pedidosDAO.pedidos().iterator().next();
			pedido.setEstado("Procesado");
			pedidosDAO.modificarPedido(pedido);
		}

		return pedido;
	}

	public boolean resetearComprasMensuales() {
		Set<Usuario> usuarios = usuariosDAO.usuarios();
		for (Usuario u:usuarios) {
			u.setComprasMensuales(0);
		}
		return true;
	}

	public Set<Articulo> verArticulos() {
		return articulosDAO.articulos();
	}

	public boolean anhadirAlCarrito(String nombre, int numUnidades, Usuario usuario) {
		Articulo articulo = articulosDAO.articuloPorNombre(nombre);
		boolean resultado = false;

		if (articulo.getUnidadesStock() >= numUnidades) {
			LineaPedido lineaPedido = new LineaPedido(numUnidades, articulo);
			usuario.getCarritoActual().add(lineaPedido);
			resultado = true;
		}

		return resultado;
	}

	public Set<LineaPedido> verCarrito(Usuario usuario) {
		return usuario.getCarritoActual();
	}

	public Pedido confirmarPedido(Date horaDeRecogida, Usuario usuario) {
		long hours = TimeUnit.MILLISECONDS.toHours(horaDeRecogida.getTime());

		// Comprueba que la hora de recogida sea en el mismo dia, entre las 9:00 y las 21:00.
		if (DateUtils.isSameDay(horaDeRecogida, new Date()) && hours >= 9 && hours <= 21) {
			// Actualiza el stock de los articulos.
			for (LineaPedido l : usuario.getCarritoActual()) {
				l.getArticulo().setUnidadesStock(l.getArticulo().getUnidadesStock()-l.getCantidad());
			}
			
			// Se almacena el pedido.
			Pedido pedido = new Pedido(usuario, usuario.getCarritoActual());
			usuario.getPedidos().add(pedido);
		}
		return null;
	}

}
