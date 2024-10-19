package info.gigagamer.controlador;

import info.gigagamer.modelo.Producto;
import info.gigagamer.modelo.Usuario;
import info.gigagamer.servicio.CarritoService;
import info.gigagamer.servicio.ProductoService;
import info.gigagamer.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import info.gigagamer.modelo.Carrito;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @PostMapping("/agregar/{idProducto}")
    public String agregarAlCarrito(@PathVariable("idProducto") int idProducto,
                                   @RequestParam("cantidad") int cantidad, Model model) {
        boolean agregado = carritoService.agregarProductoAlCarrito(1,idProducto, cantidad);
        if (!agregado) {
            model.addAttribute("error", "Stock insuficiente.");
        }
        return "redirect:/productos";
    }
}
