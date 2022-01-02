package es.unican.ps.supermercado.negocio;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.lang3.time.DateUtils;

import es.unican.ps.supermercado.common.dominio.Articulo;
import es.unican.ps.supermercado.common.dominio.LineaPedido;
import es.unican.ps.supermercado.common.dominio.Pedido;
import es.unican.ps.supermercado.common.dominio.Usuario;
import es.unican.ps.supermercado.common.interfaces.IArticulosDAORemote;
import es.unican.ps.supermercado.common.interfaces.IGestionComprasLocal;
import es.unican.ps.supermercado.common.interfaces.IGestionComprasRemote;
import es.unican.ps.supermercado.common.interfaces.IGestionPedidosLocal;
import es.unican.ps.supermercado.common.interfaces.IGestionPedidosRemote;
import es.unican.ps.supermercado.common.interfaces.ILineasPedidoDAORemote;
import es.unican.ps.supermercado.common.interfaces.IPedidosDAORemote;
import es.unican.ps.supermercado.common.interfaces.IRealizacionPedidosLocal;
import es.unican.ps.supermercado.common.interfaces.IRealizacionPedidosRemote;
import es.unican.ps.supermercado.common.interfaces.IUsuariosDAORemote;
import es.unican.ps.supermercado.dao.PedidosDAO;
import es.unican.ps.supermercado.dao.UsuariosDAO;



@Stateless
public class GestionPedidos implements IGestionPedidosLocal, IGestionPedidosRemote, IGestionComprasLocal, IGestionComprasRemote, IRealizacionPedidosLocal, IRealizacionPedidosRemote {

	@EJB
	private IPedidosDAORemote pedidosDAO;

	@EJB
	private IUsuariosDAORemote usuariosDAO;

	@EJB
	private IArticulosDAORemote articulosDAO;

	@EJB
	private ILineasPedidoDAORemote lineasDAO;

	private static int idPedido = 0;

	public GestionPedidos() {}

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
		List<Usuario> usuarios = usuariosDAO.usuarios();
		for (Usuario u:usuarios) {
			u.setComprasMensuales(0);
			usuariosDAO.modificarUsuario(u);
		}
		return true;
	}

	public List<Articulo> verArticulos() {
		return articulosDAO.articulos();
	}

	public boolean anhadirAlCarrito(long id, int numUnidades, Usuario usuario) {
		Articulo articulo = articulosDAO.articuloPorId(id);
		boolean resultado = false;

		if (articulo.getUnidadesStock() >= numUnidades) {
			LineaPedido lineaPedido = new LineaPedido(numUnidades, articulo);
			lineaPedido.setUsuario(usuario);
			usuario.getCarritoActual().add(lineaPedido);
			usuariosDAO.modificarUsuario(usuario);
			resultado = true;
		}

		return resultado;
	}

	public List<LineaPedido> verCarrito(Usuario usuario) {
		return usuario.getCarritoActual();
	}

	@SuppressWarnings("deprecation")
	public Pedido confirmarPedido(Date horaDeRecogida, Usuario usuario) {
		Calendar cal = Calendar.getInstance();

		// getTime devuelve la hora en GMT a partir del 01/01/1970
		long hours = TimeUnit.MILLISECONDS.toHours(horaDeRecogida.getTime() + 1);

		cal.set(Calendar.HOUR_OF_DAY, horaDeRecogida.getHours() - 1);
		cal.set(Calendar.MINUTE, horaDeRecogida.getMinutes());
		cal.set(Calendar.SECOND, horaDeRecogida.getSeconds());

		Date d = cal.getTime();

		Pedido pedido = null;

		// Comprueba que la hora de recogida sea en el mismo dia, entre las 9:00 y las 21:00.
		if (hours >= 9 && hours <= 21) {
			// Se almacena el pedido.
			List<LineaPedido> lineasPedido = new LinkedList<LineaPedido>();
			lineasPedido.addAll(usuario.getCarritoActual());
			pedido = new Pedido(usuario, lineasPedido, new Date(), d);
			pedido.setReferencia("pedido" + idPedido);
			idPedido++;

			// Actualiza el stock de los articulos y calcula el precio total del pedido
			double precio = 0;
			for (LineaPedido l : usuariosDAO.usuarioPorDni(usuario.getDni()).getCarritoActual()) {
				Articulo a = l.getArticulo();				
				a.setUnidadesStock(l.getArticulo().getUnidadesStock() - l.getCantidad());
				articulosDAO.modificarArticulo(a);
				l.setPedido(pedido);
				lineasDAO.modificarLineaPedido(l);
				pedido.getLineasPedido().add(l);
				
				precio += l.getCantidad() * l.getArticulo().getPrecio();
			}

			pedido.setPrecio(precio);
			
			usuario.anhadirPedido(pedido);
			usuario.getCarritoActual().clear();
			usuariosDAO.modificarUsuario(usuario);
			pedidosDAO.modificarPedido(pedido);
		}
		return pedido;
	}

}
