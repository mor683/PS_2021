package es.unican.ps.supermercado.negocio;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import es.unican.ps.supermercado.negocio.dominio.Pedido;
import es.unican.ps.supermercado.negocio.dominio.Usuario;
import es.unican.ps.supermercado.negocio.interfaces.IGestionPedidosLocal;
import es.unican.ps.supermercado.negocio.interfaces.IGestionPedidosRemote;
import es.unican.ps.supermercado.negocio.interfaces.IPedidosDAO;
import es.unican.ps.supermercado.negocio.interfaces.IUsuariosDAO;

@Stateless
public class GestionPedidos implements IGestionPedidosLocal, IGestionPedidosRemote {
	
	@EJB
    private IPedidosDAO pedidosDAO;
	
	@EJB
    private IUsuariosDAO usuariosDAO;
	
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

}
