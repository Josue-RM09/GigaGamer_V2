package info.gigagamer.controlador;

import info.gigagamer.modelo.Producto;
import info.gigagamer.servicio.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;
    // Mostrar formulario de creación de nuevo producto
    @GetMapping("/nuevo")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("producto", new Producto());
        return "crear_producto"; // Nombre de la vista de creación de producto
    }

    // Guardar nuevo producto
    @PostMapping("/nuevo")
    public String guardarNuevoProducto(@ModelAttribute("producto") Producto nuevoProducto) {
        productoService.guardarProducto(nuevoProducto);
        return "redirect:/bienvenido";
    }

    // Mostrar formulario de edición de producto
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") int id, Model model) {
        Producto producto = productoService.obtenerProductoPorId(id);
        model.addAttribute("producto", producto);
        return "editar_producto"; // Nombre de la vista de edición de producto
    }

    // Actualizar producto
    @PostMapping("/editar/{id}")
    public String actualizarProducto(@PathVariable("id") int id, @ModelAttribute("producto") Producto productoActualizado) {
        productoService.actualizarProducto(id, productoActualizado);
        return "redirect:/bienvenido";
    }

    // Eliminar producto
    @PostMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") int id) {
        productoService.eliminarProducto(id);
        return "redirect:/bienvenido";
    }
}
