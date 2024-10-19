package info.gigagamer.servicio;

import info.gigagamer.modelo.Carrito;
import info.gigagamer.modelo.Producto;
import info.gigagamer.modelo.Usuario;
import info.gigagamer.repositorio.CarritoRepository;
import info.gigagamer.repositorio.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Transactional
    public boolean agregarProductoAlCarrito(int usuarioId, int productoId, int cantidad) {
        Carrito carrito = carritoRepository.findByUsuarioId(usuarioId);
        if (carrito == null) {
            carrito = new Carrito();
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(usuarioId);
            carrito.setUsuario(usuario);
        }

        Producto producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        carrito.agregarProducto(producto, cantidad);
        carritoRepository.save(carrito);

        return true;
    }
}