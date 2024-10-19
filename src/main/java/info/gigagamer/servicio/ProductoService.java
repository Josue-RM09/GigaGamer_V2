package info.gigagamer.servicio;

import info.gigagamer.modelo.Producto;
import info.gigagamer.repositorio.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }
    public Producto obtenerProductoPorId(int id) {
        return productoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
    }

    public void actualizarProducto(int id, Producto productoActualizado) {
        Producto producto = obtenerProductoPorId(id);
        producto.setNombre_producto(productoActualizado.getNombre_producto());
        producto.setDescripcion(productoActualizado.getDescripcion());
        producto.setPrecio(productoActualizado.getPrecio());
        producto.setStock(productoActualizado.getStock());
        productoRepository.save(producto);
    }

    public void eliminarProducto(int id) {
        productoRepository.deleteById(id);
    }
    public void guardarProducto(Producto producto) {
        productoRepository.save(producto);
    }


}
